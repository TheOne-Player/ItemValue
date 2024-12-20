package me.SansNom.itemValue.utils;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.NamespacedKey;

public class NBTUtils {

    private static final NamespacedKey ITEM_VALUE_KEY = new NamespacedKey("itemvalueplugin", "item_value");

    // Method to retrieve the value of an item from its NBT data
    public static double getItemValue(ItemStack item) {
        if (item == null || !item.hasItemMeta()) {
            return 0.0;
        }

        ItemMeta meta = item.getItemMeta();
        if (meta == null) {
            return 0.0;
        }

        // Check if the item has a custom value stored in its NBT
        if (meta.getPersistentDataContainer().has(ITEM_VALUE_KEY, PersistentDataType.DOUBLE)) {
            return meta.getPersistentDataContainer().get(ITEM_VALUE_KEY, PersistentDataType.DOUBLE);
        }

        // If no custom value is found, return 0 (or handle as needed)
        return 0.0;
    }

    // Method to set a custom value to an item (useful if you want to modify items programmatically)
    public static void setItemValue(ItemStack item, double value) {
        if (item == null || !item.hasItemMeta()) {
            return;
        }

        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            // Store the custom value in the NBT data of the item
            meta.getPersistentDataContainer().set(ITEM_VALUE_KEY, PersistentDataType.DOUBLE, value);
            item.setItemMeta(meta);
        }
    }
}
