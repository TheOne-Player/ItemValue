package me.SansNom.itemValue;

import me.SansNom.itemValue.commands.BalanceCommand;
import me.SansNom.itemValue.commands.BaltopCommand;
import me.SansNom.itemValue.commands.SetItemValueCommand;
import me.SansNom.itemValue.listeners.PlayerListener;
import org.bukkit.plugin.java.JavaPlugin;

public class ItemValue extends JavaPlugin {
    @Override
    public void onEnable() {
        // Register commands
        this.getCommand("setitemvalue").setExecutor(new SetItemValueCommand(this));
        this.getCommand("baltop").setExecutor(new BaltopCommand(this));
        this.getCommand("balance").setExecutor(new BalanceCommand(this));

        // Register listeners
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}