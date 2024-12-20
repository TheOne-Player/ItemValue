package me.SansNom.itemValue.commands;

import me.SansNom.itemValue.ItemValue;
import me.SansNom.itemValue.utils.NBTUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SetItemValueCommand implements CommandExecutor {
    private final ItemValue plugin;

    public SetItemValueCommand(ItemValue plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be used by players.");
            return true;
        }

        Player player = (Player) sender;
        if (args.length != 1) {
            player.sendMessage("Usage: /setitemvalue <value>");
            return false;
        }

        double value;
        try {
            value = Double.parseDouble(args[0]);
        } catch (NumberFormatException e) {
            player.sendMessage("Invalid value. Please enter a numeric value.");
            return false;
        }

        ItemStack item = player.getInventory().getItemInMainHand();
        if (item == null) {
            player.sendMessage("You must be holding an item to set its value.");
            return true;
        }

        NBTUtils.setItemValue(item, value);
        player.sendMessage("Item value set to " + value);
        return true;
    }
}