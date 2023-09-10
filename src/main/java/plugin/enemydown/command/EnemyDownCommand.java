package plugin.enemydown.command;

import org.bukkit.event.Listener;
import java.util.List;
import java.util.Objects;
import java.util.SplittableRandom;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;


public class EnemyDownCommand implements CommandExecutor, Listener {

  private Player player;
  private int score;


  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (sender instanceof Player player) {
      this.player = player;
      World world = player.getWorld();

      // プレイヤーの状態を初期化する。（体力と空腹度を最大値にする）
      initPlayerStatus(player);

      world.spawnEntity(getEnemySpawnLocation(player, world), getEnemy());
    }
    return false;
  }

  @EventHandler
  public void onEntityDeath(EntityDeathEvent e) {
    Player player = e.getEntity().getKiller();
    if (Objects.isNull(player)) {
      return;
    }
    if (Objects.isNull(this.player)) {
      return;
    }

    if (this.player.getName().equals(player.getName())) {
      score += 10;
      player.sendMessage("敵を倒した！現在のスコアは" + score + "点！");
    }
  }

  /**
   * ゲームを始める前にプレイヤーの状態を設定する。
   * 体力と空腹度を最大にして、装備はネザライト装備にする。
   *
   * @param player　コマンドを実行したプレイヤー
   */
  private void initPlayerStatus(Player player) {
    player.setHealth(20);
    player.setFoodLevel(20);

    PlayerInventory inventory = player.getInventory();
    inventory.setHelmet(new ItemStack(Material.NETHERITE_HELMET));
    inventory.setChestplate(new ItemStack(Material.NETHERITE_CHESTPLATE));
    inventory.setLeggings(new ItemStack(Material.NETHERITE_LEGGINGS));
    inventory.setBoots(new ItemStack(Material.NETHERITE_BOOTS));
    inventory.setItemInMainHand(new ItemStack(Material.NETHERITE_SWORD));
  }


  /**
   * 敵の出現エリアを取得します。
   * 出現エリアはX軸とZ軸は自分の位置からプラス、ランダムで-10～9の値が設定されます。
   * Y軸はプレイヤーと同じ位置になります。
   *
   * @param player　こまんどを実行したプレイヤー
   * @param world　コマンドを実行したプレイヤーが所属するワールド
   * @return　敵の出現場所
   */


  private Location getEnemySpawnLocation(Player player, World world) {
    Location playerLocation = player.getLocation();
    int randomX = new SplittableRandom().nextInt(20) - 10;
    int randomZ = new SplittableRandom().nextInt(20) - 10;

    double x = playerLocation.getX() + randomX;
    double y = playerLocation.getY();
    double z = playerLocation.getZ() + randomZ;

    return new Location(world, x, y, z);
  }


  /**
   * 敵の種類をランダムで取得します。
   *
   * @return 敵
   */
  private EntityType getEnemy() {
    List<EntityType> enemyList = List.of(EntityType.ZOMBIE, EntityType.SKELETON);
    return enemyList.get(new SplittableRandom().nextInt(2));
  }
}
