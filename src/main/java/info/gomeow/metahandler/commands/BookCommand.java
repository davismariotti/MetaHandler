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
    public boolean execute(Player player, Command cmdObj, String label, String cmd, LinkedList<String> args) {
        ItemStack is = player.getItemInHand();
        if(is.getItemMeta() instanceof BookMeta) {
            BookMeta bm = (BookMeta) is.getItemMeta();
            if(is.getType() == Material.WRITTEN_BOOK) {
                if(cmd.equalsIgnoreCase("unsign")) {
                    is.setType(Material.BOOK_AND_QUILL);
                    player.setItemInHand(is);
                    player.sendMessage(ChatColor.GOLD + "Book unsigned!");
                } else if(cmd.equalsIgnoreCase("author")) {
                    if(args.size() > 0) {
                        StringBuilder sb = new StringBuilder();
                        for(String arg:args) {
                            sb.append(arg).append(" ");
                        }
                        bm.setAuthor(sb.toString().trim());
                        is.setItemMeta(bm);
                        player.setItemInHand(is);
                        player.sendMessage(ChatColor.GOLD + "Author set!");
                    } else {
                        player.sendMessage(ChatColor.RED + "Usage: /" + label + " book author <name>");
                    }
                } else if(cmd.equalsIgnoreCase("title")) {
                    if(args.size() > 0) {
                        StringBuilder sb = new StringBuilder();
                        for(String arg:args) {
                            sb.append(arg).append(" ");
                        }
                        bm.setTitle(sb.toString().trim());
                        is.setItemMeta(bm);
                        player.setItemInHand(is);
                        player.sendMessage(ChatColor.GOLD + "Title set!");
                    } else {
                        player.sendMessage(ChatColor.RED + "Usage: /" + label + " book author <name>");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "That is not a supported command!");
                }
            } else {
                player.sendMessage(ChatColor.RED + "You must be holding a signed book to do that!");
            }
        } else if(is.getType() == Material.BOOK || is.getType() == Material.ENCHANTED_BOOK) {
            if(cmd.equalsIgnoreCase("enchant")) {
                if(args.get(0).equalsIgnoreCase("add")) {
                    if(args.size() == 3) {
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
                        is.setType(Material.ENCHANTED_BOOK);
                        EnchantmentStorageMeta esm = (EnchantmentStorageMeta) is.getItemMeta();
                        esm.addStoredEnchant(enchantment, level, true);
                        is.setItemMeta(esm);
                        player.setItemInHand(is);
                        player.sendMessage(ChatColor.GOLD + "Added enchantment!");
                    }
                } else if(args.get(0).equalsIgnoreCase("del") || args.get(0).equalsIgnoreCase("remove")) {
                    if(is.getType() == Material.BOOK) {
                        player.sendMessage(ChatColor.RED + "You must be holding an enchanted book to do that!");
                        return true;
                    }
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
                    EnchantmentStorageMeta esm = (EnchantmentStorageMeta) is.getItemMeta();
                    if(!esm.hasEnchant(enchantment)) {
                        player.sendMessage(ChatColor.RED + "That book does not contain that enchantment!");
                        return true;
                    }
                    esm.removeEnchant(enchantment);
                    is.setItemMeta(esm);
                    player.setItemInHand(is);
                    player.sendMessage(ChatColor.GOLD + "Removed enchantment!");
                } else if(args.get(0).equalsIgnoreCase("clear")) {
                    if(is.getType() != Material.ENCHANTED_BOOK) {
                        player.sendMessage(ChatColor.RED + "You must be holding an enchanted book to do that!");
                        return true;
                    }
                    is.setType(Material.BOOK);
                    player.setItemInHand(is);
                    player.sendMessage(ChatColor.GOLD + "Removed all enchantments!");
                } else {
                    player.sendMessage(ChatColor.RED + "That is not a supported command!");
                }
            } else {
                player.sendMessage(ChatColor.RED + "That is not a supported command!");
            }
        }
        return false;
    }
}