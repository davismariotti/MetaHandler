package info.gomeow.metahandler.commands;

import info.gomeow.metahandler.util.LeatherUtil;

import java.util.LinkedList;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class LeatherCommand implements BaseCommand {

    @Override
    public boolean execute(Player player, Command cmdObj, String label, String cmd, LinkedList<String> args) {
        ItemStack is = player.getItemInHand();
        if(is.getItemMeta() instanceof LeatherArmorMeta) {
            LeatherArmorMeta lm = (LeatherArmorMeta) is.getItemMeta();
            if(cmd.equalsIgnoreCase("color")) {
                if(args.size() == 1) {
                    Color color = LeatherUtil.toColor(args.get(0));
                    if(color == null) {
                        player.sendMessage(ChatColor.RED + "Invalid color!");
                        StringBuilder sb = new StringBuilder();
                        for(String string:LeatherUtil.getColors()) {
                            sb.append(string.toLowerCase()).append(", ");
                        }
                        player.sendMessage(ChatColor.GOLD + "Valid Colors: " + sb.toString().substring(0, sb.toString().length() - 2));
                        return true;
                    }
                    lm.setColor(color);
                    is.setItemMeta(lm);
                    player.setItemInHand(is);
                    player.sendMessage(ChatColor.GOLD + "Color set!");
                } else if(args.size() == 3) {
                    int r, g, b;
                    try {
                        r = Integer.parseInt(args.get(0));
                        g = Integer.parseInt(args.get(1));
                        b = Integer.parseInt(args.get(2));
                    } catch(NumberFormatException nfe) {
                        player.sendMessage(ChatColor.RED + "The RGB values must be integers!");
                        player.sendMessage(ChatColor.RED + "Usage: /" + label + " leather setcolor <r> <g> <b>");
                        return true;
                    }
                    lm.setColor(Color.fromRGB(r, g, b));
                    is.setItemMeta(lm);
                    player.setItemInHand(is);
                    player.sendMessage(ChatColor.GOLD + "Color set!");
                } else {
                    player.sendMessage(ChatColor.RED + "Usage: /" + label + " leather setcolor <color>");
                    player.sendMessage(ChatColor.RED + "Usage: /" + label + " leather setcolor <r> <g> <b>");
                }
            } else if(cmd.equalsIgnoreCase("clear")) {
                lm.setColor(null);
                is.setItemMeta(lm);
                player.setItemInHand(is);
                player.sendMessage(ChatColor.GOLD + "Color cleared!");
            } else {
                player.sendMessage(ChatColor.RED + "That is not a supported command!");
            }
        } else {
            player.sendMessage(ChatColor.RED + "You must be holding a piece of leather armor to do that!");
        }
        return false;
    }

}
