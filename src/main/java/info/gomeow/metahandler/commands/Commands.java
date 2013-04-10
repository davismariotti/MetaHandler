package info.gomeow.metahandler.commands;

import info.gomeow.metahandler.MetaHandler;

import java.util.Arrays;
import java.util.LinkedList;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {

    MetaHandler plugin;

    public Commands(MetaHandler mh) {
        plugin = mh;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmdObj, String label, String[] args) {
        if(args.length == 0) {
            sender.sendMessage(ChatColor.GOLD + " --- MetaHandler Help --- ");
            sender.sendMessage(ChatColor.GOLD + "/" + label + " - Aliases: /meta, /metahandler");
            sender.sendMessage(ChatColor.GOLD + "/" + label + " item - Regular item commands.");
            sender.sendMessage(ChatColor.GOLD + "/" + label + " skull - Skull commands.");
            sender.sendMessage(ChatColor.GOLD + "/" + label + " map - Map commands.");
            sender.sendMessage(ChatColor.GOLD + "/" + label + " leather - Leather armor commands.");
            sender.sendMessage(ChatColor.GOLD + " ------------------------ ");
        } else {
            String cmd = args[0];
            if(cmd.equalsIgnoreCase("item")) {
                if(args.length == 1) {
                    sender.sendMessage(ChatColor.GOLD + "/" + label + " item display <Text to be display name> - Sets the display name on the item");
                    sender.sendMessage(ChatColor.GOLD + "/" + label + " item displayclear - Clears the display name on the item");
                    sender.sendMessage(ChatColor.GOLD + "/" + label + " item lore <line #> <Text to be display name> - Sets the lore on that line.");
                    sender.sendMessage(ChatColor.GOLD + "/" + label + " item loreclear - Clears the lore on that item.");
                    sender.sendMessage(ChatColor.GOLD + "/" + label + " item clear - Clears all the meta on that item.");
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
            } else if(cmd.equalsIgnoreCase("skull")) {
                if(args.length == 1) {
                    sender.sendMessage(ChatColor.GOLD + "/" + label + " skull owner <name> - Sets the owner.");
                    sender.sendMessage(ChatColor.GOLD + "/" + label + " item clear - Clears the owner.");
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
            } else if(cmd.equalsIgnoreCase("map")) {
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
            } else if(cmd.equalsIgnoreCase("leather")) {
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
        }
        return true;
    }

}