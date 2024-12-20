package me.SansNom.itemValue.commands;

import me.SansNom.itemValue.ItemValue;
import me.SansNom.itemValue.utils.NBTUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class BaltopCommand implements CommandExecutor {
    private final ItemValue plugin;

    public BaltopCommand(ItemValue plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("itemvalue.baltop")) {
            sender.sendMessage("You do not have permission to use this command.");
            return true;
        }

        Map<String, Double> playerBalances = new HashMap<>();

        for (Player player : Bukkit.getOnlinePlayers()) {
            double totalValue = getTotalValue(player);
            playerBalances.put(player.getName(), totalValue);
        }

        List<Map.Entry<String, Double>> sortedBalances = new ArrayList<>(playerBalances.entrySet());
        sortedBalances.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        sender.sendMessage("Top 10 Item Value Balances:");
        for (int i = 0; i < Math.min(10, sortedBalances.size()); i++) {
            Map.Entry<String, Double> entry = sortedBalances.get(i);
            sender.sendMessage((i + 1) + ". " + entry.getKey() + ": " + entry.getValue());
        }

        return true;
    }

    private double getTotalValue(Player player) {
        double totalValue = 0;

        // Calculate inventory value
        for (ItemStack item : player.getInventory().getContents()) {
            if (item != null) {
                totalValue += NBTUtils.getItemValue(item);
            }
        }

        // Calculate ender chest value
        Inventory enderChest = player.getEnderChest();
        for (ItemStack item : enderChest.getContents()) {
            if (item != null) {
                totalValue += NBTUtils.getItemValue(item);
            }
        }

        return totalValue;
    }
}