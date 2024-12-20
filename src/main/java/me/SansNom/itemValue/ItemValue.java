package me.SansNom.itemValue;

import me.clip.placeholderapi.PlaceholderAPI;
import me.SansNom.itemValue.commands.SetItemValueCommand;
import me.SansNom.itemValue.listeners.PlayerListener;
import me.SansNom.itemValue.utils.NBTUtils;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ItemValue extends JavaPlugin {

    @Override
    public void onEnable() {
        // Register commands
        getCommand("setitemvalue").setExecutor(new commands.SetItemValueCommand(this));

        // Register the PlaceholderAPI placeholders
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new InventoryValuePlaceholder(this);
            new EnderchestValuePlaceholder(this);
        }
    }
}
