package info.gomeow.metahandler.commands;

import info.gomeow.metahandler.util.ItemUtil;

import java.util.LinkedList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

public class BookCommand implements BaseCommand {

    @Override
    public boolean execute(Player p, Command cmdObj, String label, String cmd, LinkedList<String> args) {
        ItemStack is = p.getItemInHand();
        if(is.getItemMeta() instanceof BookMeta) {
            BookMeta bm = (BookMeta) is.getItemMeta();
        } else if(is.getType() == Material.BOOK || is.getType() == Material.ENCHANTED_BOOK) {
            if(cmd.equalsIgnoreCase("enchant")) {
                if(args.get(0).equalsIgnoreCase("add")) {
                    if(args.size() == 3) {
                        Enchantment e = ItemUtil.getEnchantment(args.get(1));
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
                            level = Integer.parseInt(args.get(2));
                        } catch(NumberFormatException nfe) {
                            p.sendMessage(ChatColor.RED + "Enchantment level must be a number!");
                            p.sendMessage(ChatColor.RED + "Usage: /" + label + " item <Enchantment> <level>");
                            return true;
                        }
                        is.setType(Material.ENCHANTED_BOOK);
                        EnchantmentStorageMeta esm = (EnchantmentStorageMeta) is.getItemMeta();
                        esm.addStoredEnchant(e, level, true);
                        is.setItemMeta(esm);
                        p.setItemInHand(is);
                        p.sendMessage(ChatColor.GOLD + "Added enchantment!");
                    }
                } else if(args.get(0).equalsIgnoreCase("del") || args.get(0).equalsIgnoreCase("remove")) {
                    if(is.getType() == Material.BOOK) {
                        p.sendMessage(ChatColor.RED + "You must be holding an enchanted book to do that!");
                        return true;
                    }
                    Enchantment e = ItemUtil.getEnchantment(args.get(1));
                    if(e == null) {
                        StringBuilder enchantments = new StringBuilder();
                        for(String ench:ItemUtil.getEnchantments()) {
                            enchantments.append(ench.toLowerCase().replace('_', '-')).append(", ");
                        }
                        p.sendMessage(ChatColor.RED + "Invalid enchantment!");
                        p.sendMessage(ChatColor.GOLD + "Valid enchantments: " + enchantments.toString().substring(0, enchantments.toString().length() - 2));
                        return true;
                    }
                    EnchantmentStorageMeta esm = (EnchantmentStorageMeta) is.getItemMeta();
                    if(!esm.hasEnchant(e)) {
                        p.sendMessage(ChatColor.RED + "That book does not contain that enchantment!");
                        return true;
                    }
                    esm.removeEnchant(e);
                    is.setItemMeta(esm);
                    p.setItemInHand(is);
                    p.sendMessage(ChatColor.GOLD + "Removed enchantment!");
                } else if(args.get(0).equalsIgnoreCase("clear") || args.get(0).equalsIgnoreCase("clr")) {
                    if(is.getType() == Material.BOOK) {
                        p.sendMessage(ChatColor.RED + "You must be holding an enchanted book to do that!");
                        return true;
                    }
                    is.setType(Material.BOOK);
                    p.setItemInHand(is);
                    p.sendMessage(ChatColor.GOLD + "Removed all enchantments!");
                } else {
                    p.sendMessage(ChatColor.RED + "That is not a supported command!");
                }
            } else {
                p.sendMessage(ChatColor.RED + "That is not a supported command!");
            }
        }
        return false;
    }
}