package info.gomeow.metahandler.commands;

import info.gomeow.metahandler.util.ItemUtil;

import java.util.LinkedList;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemCommand implements BaseCommand {

    @Override
    public boolean execute(Player p, Command cmdObj, String label, String cmd, LinkedList<String> args) {
        ItemStack is = p.getItemInHand();
        if(is.getTypeId() == 0) {
            p.sendMessage(ChatColor.RED + "You must be holding an item!");
            return true;
        }
        if(cmd.equalsIgnoreCase("display")) {
            if(args.size() >= 1) {
                if(args.get(0).equalsIgnoreCase("set")) {
                    if(args.size() >= 2) {
                        StringBuilder sb = new StringBuilder();
                        for(String arg:args) {
                            if(!arg.equals(args.get(0)))
                                sb.append(arg).append(" ");
                        }
                        p.setItemInHand(ItemUtil.setDisplayName(is, sb.toString().trim()));
                        p.sendMessage(ChatColor.GOLD + "Display name changed!");
                    } else {
                        p.sendMessage(ChatColor.RED + "Usage: /" + label.toLowerCase() + " item display set <name>");
                    }
                } else if(args.get(0).equalsIgnoreCase("clear")) {
                    p.setItemInHand(ItemUtil.clearDisplayName(is));
                    p.sendMessage(ChatColor.GOLD + "Display name cleared!");
                } else {
                    p.sendMessage(ChatColor.RED + "That is not a supported command!");
                }
            }
        } else if(cmd.equalsIgnoreCase("lore")) {
            if(args.size() >= 2) {
                int line = 0;
                try {
                    line = Integer.parseInt(args.get(0));
                } catch(NumberFormatException nfe) {
                    p.sendMessage(ChatColor.RED + "Line # must be a number!");
                    p.sendMessage(ChatColor.RED + "Usage: /" + label + " item " + cmd.toLowerCase() + " <Line #> <Text for the line>");
                    return true;
                }
                if(line >= 5) {
                    p.sendMessage(ChatColor.RED + "You can only set lines 1-5 of the lore!");
                    return true;
                }
                StringBuilder sb = new StringBuilder();
                for(String arg:args) {
                    if(arg != args.get(0))
                        sb.append(arg).append(" ");
                }
                p.setItemInHand(ItemUtil.setLore(is, line, sb.toString().trim()));
                p.sendMessage(ChatColor.GOLD + "Lore changed!");
            } else {
                p.sendMessage(ChatColor.RED + "Usage: /" + label + " item " + cmd.toLowerCase() + " <Line #> <Text for the line>");
            }
        } else if(cmd.equalsIgnoreCase("loreclr") || cmd.equalsIgnoreCase("loreclear")) {
            p.setItemInHand(ItemUtil.clearLore(is));
            p.sendMessage(ChatColor.GOLD + "Lore cleared!");
        } else if(cmd.equalsIgnoreCase("enchant")) {
            if(args.size() == 2) {
                Enchantment e = ItemUtil.getEnchantment(args.get(0));
                if(e == null) {
                    StringBuilder enchantments = new StringBuilder();
                    for(String ench:ItemUtil.getEnchantments()) {
                        enchantments.append(ench.toLowerCase().replace('_', '-')).append(", ");
                    }
                    p.sendMessage(ChatColor.RED + "Invalid enchantment!");
                    p.sendMessage(ChatColor.GOLD + "Valid enchantments: " + enchantments.toString().substring(0, enchantments.toString().length() - 2));
                    return true;
                }
                int level = 0;
                try {
                    level = Integer.parseInt(args.get(1));
                } catch(NumberFormatException nfe) {
                    p.sendMessage(ChatColor.RED + "Enchantment level must be a number!");
                    p.sendMessage(ChatColor.RED + "Usage: /" + label + " item <Enchantment> <level>");
                    return true;
                }
                is.addUnsafeEnchantment(e, level);
                p.setItemInHand(is);
                p.sendMessage(ChatColor.GOLD + "Added enchantment!");
            } else {
                p.sendMessage(ChatColor.GOLD + "/" + label + " item enchant <enchantment> <level>");
            }
        } else if(cmd.equalsIgnoreCase("enchantdel")) {
            if(args.size() == 1) {
                Enchantment e = ItemUtil.getEnchantment(args.get(0));
                if(e == null) {
                    StringBuilder enchantments = new StringBuilder();
                    for(String ench:ItemUtil.getEnchantments()) {
                        enchantments.append(ench.toLowerCase().replace('_', '-')).append(", ");
                    }
                    p.sendMessage(ChatColor.RED + "Invalid enchantment!");
                    p.sendMessage(ChatColor.GOLD + "Valid enchantments: " + enchantments.toString().substring(0, enchantments.toString().length() - 2));
                    return true;
                }
                if(is.containsEnchantment(e)) {
                    is.removeEnchantment(e);
                    p.sendMessage(ChatColor.GOLD + "Removed enchantment!");
                } else {
                    p.sendMessage(ChatColor.RED + "That enchantment is not on the item!");
                }
            } else {
                p.sendMessage(ChatColor.RED + "/" + label + " item enchantdel - Removes the specified item from the item.");
            }
        } else if(cmd.equalsIgnoreCase("enchantclr") || cmd.equalsIgnoreCase("enchantclear")) {
            p.setItemInHand(ItemUtil.clearEnchantments(is));
            p.sendMessage(ChatColor.GOLD + "Enchantments cleared!");
        }
        else if(cmd.equalsIgnoreCase("clear")) {
            p.setItemInHand(ItemUtil.clearMeta(is));
            p.sendMessage(ChatColor.GOLD + "Item Meta cleared!");
        } else {
            p.sendMessage(ChatColor.RED + "That is not a supported command!");
        }
        return false;
    }

}
