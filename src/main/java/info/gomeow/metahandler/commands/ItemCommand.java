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
    public boolean execute(Player player, Command cmdObj, String label, String cmd, LinkedList<String> args) {
        ItemStack is = player.getItemInHand();
        if(is.getTypeId() == 0) {
            player.sendMessage(ChatColor.RED + "You must be holding an item!");
            return true;
        }
        if(cmd.equalsIgnoreCase("display")) {
            if(args.size() > 0) {
                if(args.get(0).equalsIgnoreCase("set")) {
                    if(args.size() > 1) {
                        StringBuilder sb = new StringBuilder();
                        for(String arg:args) {
                            if(!arg.equals(args.get(0))) {
                                sb.append(arg).append(" ");
                            }
                        }
                        player.setItemInHand(ItemUtil.setDisplayName(is, sb.toString().trim()));
                        player.sendMessage(ChatColor.GOLD + "Display name changed!");
                    } else {
                        player.sendMessage(ChatColor.RED + "Usage: /" + label.toLowerCase() + " item display set <name>");
                    }
                } else if(args.get(0).equalsIgnoreCase("clear")) {
                    player.setItemInHand(ItemUtil.clearDisplayName(is));
                    player.sendMessage(ChatColor.GOLD + "Display name cleared!");
                } else {
                    player.sendMessage(ChatColor.RED + "Invalid command! Available Commands:");
                    player.sendMessage(ChatColor.GOLD + "/" + label + " item display set <Text> - Sets the display name.");
                    player.sendMessage(ChatColor.GOLD + "/" + label + " item display clear - Clears the display name.");
                }
            } else {
                player.sendMessage(ChatColor.RED + "Invalid command! Available Commands:");
                player.sendMessage(ChatColor.GOLD + "/" + label + " item display set <Text> - Sets the display name.");
                player.sendMessage(ChatColor.GOLD + "/" + label + " item display clear - Clears the display name.");
            }
        } else if(cmd.equalsIgnoreCase("lore")) {
            if(args.size() > 0) {
                if(args.get(0).equalsIgnoreCase("set")) {
                    if(args.size() > 2) {
                        int line = 0;
                        try {
                            line = Integer.parseInt(args.get(1));
                        } catch(NumberFormatException nfe) {
                            player.sendMessage(ChatColor.RED + "Line # must be a number!");
                            player.sendMessage(ChatColor.RED + "Usage: /" + label + " item lore set <Line #> <Text>");
                            return true;
                        }
                        if(line >= 5) {
                            player.sendMessage(ChatColor.RED + "You can only set lines 1-5 of the lore!");
                            return true;
                        }
                        StringBuilder sb = new StringBuilder();
                        for(String arg:args) {
                            if(arg != args.get(1) && arg != args.get(0)) {
                                sb.append(arg).append(" ");
                            }
                        }
                        player.setItemInHand(ItemUtil.setLore(is, line, sb.toString().trim()));
                        player.sendMessage(ChatColor.GOLD + "Lore changed!");
                    } else {
                        player.sendMessage(ChatColor.RED + "Usage: /" + label + " item lore set <Line #> <Text>");
                    }
                } else if(args.get(0).equalsIgnoreCase("del")) {
                    if(args.size() > 1) {
                        int line = 0;
                        try {
                            line = Integer.parseInt(args.get(1));
                        } catch(NumberFormatException nfe) {
                            player.sendMessage(ChatColor.RED + "Line # must be a number!");
                            player.sendMessage(ChatColor.RED + "Usage: /" + label + " item lore del <Line #>");
                            return true;
                        }
                        if(line >= 5) {
                            player.sendMessage(ChatColor.RED + "You can only clear lines 1-5 of the lore!");
                            return true;
                        }
                        player.setItemInHand(ItemUtil.setLore(is, line, ""));
                        player.sendMessage(ChatColor.GOLD + "Line cleared!");
                    } else {
                        player.sendMessage(ChatColor.RED + "Usage: /" + label + " item lore del <Line #>");
                    }
                } else if(args.get(0).equalsIgnoreCase("clear")) {
                    player.setItemInHand(ItemUtil.clearLore(is));
                    player.sendMessage(ChatColor.GOLD + "Lore cleared!");
                } else {
                    player.sendMessage(ChatColor.RED + "Invalid command! Available Commands:");
                    player.sendMessage(ChatColor.GOLD + "/" + label + " item lore set <Line #> <Text> - Sets the lore on that line.");
                    player.sendMessage(ChatColor.GOLD + "/" + label + " item lore del <Line #> - Clears the lore on that line.");
                    player.sendMessage(ChatColor.GOLD + "/" + label + " item lore clear - Clears the lore.");
                }
            } else {
                player.sendMessage(ChatColor.RED + "Invalid command! Available Commands:");
                player.sendMessage(ChatColor.GOLD + "/" + label + " item lore set <Line #> <Text> - Sets the lore on that line.");
                player.sendMessage(ChatColor.GOLD + "/" + label + " item lore del <Line #> - Clears the lore on that line.");
                player.sendMessage(ChatColor.GOLD + "/" + label + " item lore clear - Clears the lore.");
            }
        } else if(cmd.equalsIgnoreCase("enchant")) {
            if(args.size() > 0) {
                if(args.get(0).equalsIgnoreCase("set")) {
                    if(args.size() > 2) {
                        Enchantment enchantment = ItemUtil.getEnchantment(args.get(1));
                        if(enchantment == null) {
                            StringBuilder enchantments = new StringBuilder();
                            for(String ench:ItemUtil.getEnchantments()) {
                                enchantments.append(ench.toLowerCase().replace('_', '-')).append(", ");
                            }
                            player.sendMessage(ChatColor.RED + "Invalid enchantment!");
                            player.sendMessage(ChatColor.GOLD + "Valid enchantments: " + enchantments.toString().substring(0, enchantments.toString().length() - 2));
                            return true;
                        }
                        int level = 0;
                        try {
                            level = Integer.parseInt(args.get(2));
                        } catch(NumberFormatException nfe) {
                            player.sendMessage(ChatColor.RED + "Enchantment level must be a number!");
                            player.sendMessage(ChatColor.RED + "Usage: /" + label + " item <Enchantment> <level>");
                            return true;
                        }
                        is.addUnsafeEnchantment(enchantment, level);
                        player.setItemInHand(is);
                        player.sendMessage(ChatColor.GOLD + "Added enchantment!");
                    } else {
                        player.sendMessage(ChatColor.RED + "Usage: /" + label + " item <Enchantment> <level>");
                    }
                } else if(args.get(0).equalsIgnoreCase("del")) {
                    if(args.size() > 1) {
                        Enchantment e = ItemUtil.getEnchantment(args.get(1));
                        if(e == null) {
                            StringBuilder enchantments = new StringBuilder();
                            for(String ench:ItemUtil.getEnchantments()) {
                                enchantments.append(ench.toLowerCase().replace('_', '-')).append(", ");
                            }
                            player.sendMessage(ChatColor.RED + "Invalid enchantment!");
                            player.sendMessage(ChatColor.GOLD + "Valid enchantments: " + enchantments.toString().substring(0, enchantments.toString().length() - 2));
                            return true;
                        }
                        if(is.containsEnchantment(e)) {
                            is.removeEnchantment(e);
                            player.sendMessage(ChatColor.GOLD + "Removed enchantment!");
                        } else {
                            player.sendMessage(ChatColor.RED + "That enchantment is not on the item!");
                        }
                    } else {
                        player.sendMessage(ChatColor.RED + "/" + label + " item enchantdel - Removes the specified item from the item.");
                    }
                } else if(args.get(0).equalsIgnoreCase("clear")) {
                    player.setItemInHand(ItemUtil.clearEnchantments(is));
                    player.sendMessage(ChatColor.GOLD + "Enchantments cleared!");
                } else {
                    player.sendMessage(ChatColor.RED + "Invalid command! Available Commands:");
                    player.sendMessage(ChatColor.GOLD + "/" + label + " item enchant set <enchantment> <level> - Adds the specified enchantment.");
                    player.sendMessage(ChatColor.GOLD + "/" + label + " item enchant del - Removes the specified enchantment.");
                    player.sendMessage(ChatColor.GOLD + "/" + label + " item enchant clear - Clears the enchantments.");
                }
            } else {
                player.sendMessage(ChatColor.RED + "Invalid command! Available Commands:");
                player.sendMessage(ChatColor.GOLD + "/" + label + " item enchant set <enchantment> <level> - Adds the specified enchantment.");
                player.sendMessage(ChatColor.GOLD + "/" + label + " item enchant del - Removes the specified enchantment.");
                player.sendMessage(ChatColor.GOLD + "/" + label + " item enchant clear - Clears the enchantments.");
            }
        } else if(cmd.equalsIgnoreCase("clear")) {
            player.setItemInHand(ItemUtil.clearMeta(is));
            player.sendMessage(ChatColor.GOLD + "Item Meta cleared!");
        } else {
            player.sendMessage(ChatColor.RED + "That is not a supported command!");
        }
        return false;
    }
}
