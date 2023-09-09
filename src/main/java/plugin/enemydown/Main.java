package plugin.enemydown;

import java.net.http.WebSocket.Listener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import plugin.enemydown.command.EnemyDownCommand;

public final class Main extends JavaPlugin implements Listener, org.bukkit.event.Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        getCommand("enemyDown").setExecutor(new EnemyDownCommand());
      // Plugin startup logic
    }
}
