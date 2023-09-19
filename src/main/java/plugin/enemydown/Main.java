package plugin.enemydown;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import plugin.enemydown.command.EnemyDownCommand;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        EnemyDownCommand enemyDownCommand = new EnemyDownCommand(this) {
            @Override
            public boolean onExecuteNPCCommand(CommandSender sender, Command command, String label, String[] args) {
                return false;
            }
        };
        Bukkit.getPluginManager().registerEvents(enemyDownCommand, this);
        getCommand("enemyDown").setExecutor(enemyDownCommand);
        // Plugin startup logic
    }

}
