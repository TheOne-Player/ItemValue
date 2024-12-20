package me.SansNom.itemValue;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class InventoryValuePlaceholder extends PlaceholderExpansion {
    private ItemValue plugin;

    public InventoryValuePlaceholder(ItemValue plugin) {
        this.plugin = plugin;
        this.register();
    }

    @Override
    public String getIdentifier() {
        return "inventory_value";
    }

    @Override
    public String getAuthor() {
        return "YourName";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    public String onRequest(Player player, String params) {
        double totalValue = 0;

        PlayerInventory inventory = player.getInventory();
        for (ItemStack item : inventory.getContents()) {
            if (item != null) {
                double value = me.SansNom.itemValue.utils.NBTUtils.getItemValue(item);
                totalValue += value;
            }
        }

        return String.valueOf(totalValue);
    }
}
