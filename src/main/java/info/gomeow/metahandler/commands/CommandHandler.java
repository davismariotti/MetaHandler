package info.gomeow.metahandler.commands;

import info.gomeow.metahandler.MetaHandler;

import java.util.Arrays;
import java.util.LinkedList;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHandler implements CommandExecutor {

    MetaHandler plugin;

    public CommandHandler(MetaHandler mh) {
        plugin = mh;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmdObj, String label, String[] args) {
        label = label.toLowerCase();
        if(args.length == 0) {
            sender.sendMessage(ChatColor.GOLD + " --- MetaHandler Help --- ");
            sender.sendMessage(ChatColor.GOLD + "/" + label + " - Aliases: /mh, /metahandler");
            sender.sendMessage(ChatColor.GOLD + "/" + label + " item - Regular item commands.");
            sender.sendMessage(ChatColor.GOLD + "/" + label + " skull - Skull commands.");
            sender.sendMessage(ChatColor.GOLD + "/" + label + " map - Map commands.");
            sender.sendMessage(ChatColor.GOLD + "/" + label + " leather - Leather armor commands.");
            sender.sendMessage(ChatColor.GOLD + "/" + label + " book - Book commands.");
            sender.sendMessage(ChatColor.GOLD + " ------------------------ ");
        } else {
            String cmd = args[0];
            if(cmd.equalsIgnoreCase("item")) {
                if(sender.hasPermission("metahandler.item")) {
                    if(args.length == 1) {
                        sender.sendMessage(ChatColor.DARK_AQUA + "/" + label + " item display set <Text> - Sets the display name");
                        sender.sendMessage(ChatColor.DARK_AQUA + "/" + label + " item display clear - Clears the display name");
                        sender.sendMessage(ChatColor.DARK_GREEN + "/" + label + " item lore set <Line #> <Text> - Sets the lore on that line.");
                        sender.sendMessage(ChatColor.DARK_GREEN + "/" + label + " item lore del <Line #> - Clears the lore on that item.");
                        sender.sendMessage(ChatColor.DARK_GREEN + "/" + label + " item lore clear - Clears the lore on that item.");
                        sender.sendMessage(ChatColor.YELLOW + "/" + label + " item enchant set <enchantment> <level> - Adds the specified enchantment.");
                        sender.sendMessage(ChatColor.YELLOW + "/" + label + " item enchant del - Removes the specified enchantment.");
                        sender.sendMessage(ChatColor.YELLOW + "/" + label + " item enchant clear - Clears the enchantments.");
                        sender.sendMessage(ChatColor.GOLD + "/" + label + " item clear - Clears all the meta.");
                    } else {
                        if(sender instanceof Player) {
                            LinkedList<String> list = new LinkedList<String>(Arrays.asList(args));
                            list.pollFirst();
                            list.pollFirst();
                            return new ItemCommand().execute((Player) sender, cmdObj, label, args[1], list);
                        } else {
                            sender.sendMessage(ChatColor.RED + "You must be a player to do that!");
                        }
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "You do not have permission to do that!");
                }
            } else if(cmd.equalsIgnoreCase("skull")) {
                if(sender.hasPermission("metahandler.skull")) {
                    if(args.length == 1) {
                        sender.sendMessage(ChatColor.GOLD + "/" + label + " skull owner <name> - Sets the skull owner.");
                        sender.sendMessage(ChatColor.GOLD + "/" + label + " skull clear - Clears the owner.");
                    } else {
                        if(sender instanceof Player) {
                            LinkedList<String> list = new LinkedList<String>(Arrays.asList(args));
                            list.pollFirst();
                            list.pollFirst();
                            return new SkullCommand().execute((Player) sender, cmdObj, label, args[1], list);
                        } else {
                            sender.sendMessage(ChatColor.RED + "You must be a player to do that!");
                        }
                    }
                }
            } else if(cmd.equalsIgnoreCase("map")) {
                if(sender.hasPermission("metahandler.map")) {
                    if(args.length == 1) {
                        sender.sendMessage(ChatColor.GOLD + "/" + label + " map scaling <true/false> - Sets the scaling property.");
                    } else {
                        if(sender instanceof Player) {
                            LinkedList<String> list = new LinkedList<String>(Arrays.asList(args));
                            list.pollFirst();
                            list.pollFirst();
                            return new MapCommand().execute((Player) sender, cmdObj, label, args[1], list);
                        } else {
                            sender.sendMessage(ChatColor.RED + "You must be a player to do that!");
                        }
                    }
                }
            } else if(cmd.equalsIgnoreCase("leather")) {
                if(sender.hasPermission("metahandler.leather")) {
                    if(args.length == 1) {
                        sender.sendMessage(ChatColor.GOLD + "/" + label + " leather color <color> - Sets the color.");
                        sender.sendMessage(ChatColor.GOLD + "/" + label + " leather color <r> <g> <b> - Sets the color.");
                        sender.sendMessage(ChatColor.GOLD + "/" + label + " leather clear - Sets the color to default.");
                    } else {
                        if(sender instanceof Player) {
                            LinkedList<String> list = new LinkedList<String>(Arrays.asList(args));
                            list.pollFirst();
                            list.pollFirst();
                            return new LeatherCommand().execute((Player) sender, cmdObj, label, args[1], list);
                        } else {
                            sender.sendMessage(ChatColor.RED + "You must be a player to do that!");
                        }
                    }
                }
            } else if(cmd.equalsIgnoreCase("book")) {
                if(sender.hasPermission("metahandler.book")) {
                    if(args.length == 1) {
                        sender.sendMessage(ChatColor.GOLD + "/" + label + " book enchant add <enchantment> <level> - Adds a stored enchantment.");
                        sender.sendMessage(ChatColor.GOLD + "/" + label + " book enchant del <enchantment> - Removes a stored enchantment.");
                        sender.sendMessage(ChatColor.GOLD + "/" + label + " book enchant clear - Clears stored enchantments.");
                        sender.sendMessage(ChatColor.GOLD + "/" + label + " book author <name> - Sets the author.");
                        sender.sendMessage(ChatColor.GOLD + "/" + label + " book title <title> - Sets the title.");
                        sender.sendMessage(ChatColor.GOLD + "/" + label + " book unsign - Unsigns the book.");
                    } else {
                        if(sender instanceof Player) {
                            LinkedList<String> list = new LinkedList<String>(Arrays.asList(args));
                            list.pollFirst();
                            list.pollFirst();
                            return new BookCommand().execute((Player) sender, cmdObj, label, args[1], list);
                        } else {
                            sender.sendMessage(ChatColor.RED + "You must be a player to do that!");
                        }
                    }
                }
            } else {
                sender.sendMessage(ChatColor.RED + "That is not a valid command!");
                sender.sendMessage(ChatColor.RED + "Please type /" + label + " to see the help menu!");
            }
        }
        return true;
    }

}