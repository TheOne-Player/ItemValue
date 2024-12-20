package me.SansNom.itemValue.listeners;

import me.SansNom.itemValue.ItemValue;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {
    private final ItemValue plugin;

    public PlayerListener(ItemValue plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // Example: Send a welcome message to the player
        event.getPlayer().sendMessage("Welcome to the server!");
    }
}