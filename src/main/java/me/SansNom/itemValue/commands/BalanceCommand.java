package me.SansNom.itemValue.commands;

import me.SansNom.itemValue.ItemValue;
import me.SansNom.itemValue.utils.NBTUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class BalanceCommand implements CommandExecutor {
    private final ItemValue plugin;

    public BalanceCommand(ItemValue plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be used by players.");
            return true;
        }

        Player player = (Player) sender;
        String balanceType = args.length > 0 ? args[0].toLowerCase() : "total";

        double balance;
        switch (balanceType) {
            case "inventory":
                balance = getInventoryValue(player);
                player.sendMessage("Your inventory balance is: " + balance);
                break;
            case "enderchest":
                balance = getEnderchestValue(player);
                player.sendMessage("Your enderchest balance is: " + balance);
                break;
            case "total":
            default:
                balance = getTotalValue(player);
                player.sendMessage("Your total balance is: " + balance);
                break;
        }

        return true;
    }

    private double getInventoryValue(Player player) {
        double totalValue = 0;
        for (ItemStack item : player.getInventory().getContents()) {
            if (item != null) {
                totalValue += NBTUtils.getItemValue(item);
            }
        }
        return totalValue;
    }

    private double getEnderchestValue(Player player) {
        double totalValue = 0;
        Inventory enderChest = player.getEnderChest();
        for (ItemStack item : enderChest.getContents()) {
            if (item != null) {
                totalValue += NBTUtils.getItemValue(item);
            }
        }
        return totalValue;
    }

    private double getTotalValue(Player player) {
        return getInventoryValue(player) + getEnderchestValue(player);
    }
}