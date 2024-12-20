package me.SansNom.itemValue;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class EnderchestValuePlaceholder extends PlaceholderExpansion {
    private ItemValue plugin;

    public EnderchestValuePlaceholder(ItemValue plugin) {
        this.plugin = plugin;
        this.register();
    }

    @Override
    public String getIdentifier() {
        return "enderchest_value";
    }

    @Override
    public String getAuthor() {
        return "SansNom";
    }

    @Override
    public String getVersion() {
        return "0.0.1";
    }

    public String onRequest(Player player, String params) {
        double totalValue = 0;

        // Access the player's Ender Chest
        Inventory enderChest = player.getEnderChest();
        // Iterate over the Ender Chest contents and calculate total value
        for (ItemStack item : enderChest.getContents()) {
            if (item != null) {
                double value = me.SansNom.itemValue.utils.NBTUtils.getItemValue(item);
                totalValue += value;
            }
        }

        return String.valueOf(totalValue);
    }
}
