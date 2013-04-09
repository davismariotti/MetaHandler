package info.gomeow.metahandler.commands;

import info.gomeow.metahandler.util.ItemUtil;

import java.util.LinkedList;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemStackCommand implements BaseCommand {

    @Override
    public boolean execute(CommandSender sender, Command cmdObj, String label, String cmd, LinkedList<String> args) {
        Player p = (Player) sender;
        if(cmd.equalsIgnoreCase("displayname")) {
            if(args.size() >= 1) {
                StringBuilder sb = new StringBuilder();
                for(String arg:args) {
                    sb.append(arg).append(" ");
                }
                ItemStack is = p.getItemInHand();
                p.setItemInHand(ItemUtil.setDisplayName(is, sb.toString().trim()));
                p.sendMessage(ChatColor.GOLD + "Display name changed!");
            }
        } else if(cmd.equalsIgnoreCase("lore")) {
            if(args.size() >= 2) {
                int line = 0;
                try {
                    line = Integer.parseInt(args.get(0));
                } catch (NumberFormatException nfe) {
                    p.sendMessage(ChatColor.RED + "Line # must be a number!");
                    p.sendMessage(ChatColor.RED + "Usage: /" + label + " <Line #> <Text for the line>");
                    return true;
                }
                StringBuilder sb = new StringBuilder();
                for(String arg:args) {
                    if(arg != args.get(0))
                        sb.append(arg).append(" ");
                }
                ItemStack is = p.getItemInHand();
                p.setItemInHand(ItemUtil.setLore(is, line, sb.toString().trim()));
                p.sendMessage(ChatColor.GOLD + "Lore changed!");
            }
        } else {
            p.sendMessage(ChatColor.RED + "That is not a supported command!");
        }
        return false;
    }
    

}
