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
        } else {
            String cmd = args[0];
            if(cmd.equalsIgnoreCase("item")) {
                if(args.length == 1) {
                    sender.sendMessage(ChatColor.GOLD + "/" + label + " item display <Text to be display name> - Sets the display name on the item");
                    sender.sendMessage(ChatColor.GOLD + "/" + label + " item lore <line #> <Text to be display name> - Sets the lore on that line.");
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
            }
        }
        return true;
    }

}