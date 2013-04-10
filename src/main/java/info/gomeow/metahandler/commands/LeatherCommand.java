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
    public boolean execute(Player p, Command cmdObj, String label, String cmd, LinkedList<String> args) {
        ItemStack is = p.getItemInHand();
        if(is.getItemMeta() instanceof LeatherArmorMeta) {
            LeatherArmorMeta lm = (LeatherArmorMeta) is.getItemMeta();
            if(cmd.equalsIgnoreCase("color")) {
                if(args.size() == 1) {
                    Color c = LeatherUtil.toColor(args.get(0));
                    if(c == null) {
                        p.sendMessage(ChatColor.RED + "Invalid color!");
                        StringBuilder sb = new StringBuilder();
                        for(String color:LeatherUtil.getColors()) {
                            sb.append(color.toLowerCase()).append(", ");
                        }
                        p.sendMessage(ChatColor.GOLD + "Valid Colors: " + sb.toString().substring(0, sb.toString().length() - 2));
                        return true;
                    }
                    lm.setColor(c);
                    is.setItemMeta(lm);
                    p.setItemInHand(is);
                    p.sendMessage(ChatColor.GOLD + "Color set!");
                } else if(args.size() == 3) {
                    int r, g, b;
                    try {
                        r = Integer.parseInt(args.get(0));
                        g = Integer.parseInt(args.get(1));
                        b = Integer.parseInt(args.get(2));
                    } catch(NumberFormatException nfe) {
                        p.sendMessage(ChatColor.RED + "The RGB values must be integers!");
                        p.sendMessage(ChatColor.RED + "Usage: /" + label + " leather setcolor <r> <g> <b>");
                        return true;
                    }
                    Color c = Color.fromRGB(r, g, b);
                    lm.setColor(c);
                    is.setItemMeta(lm);
                    p.setItemInHand(is);
                    p.sendMessage(ChatColor.GOLD + "Color set!");
                } else {
                    p.sendMessage(ChatColor.RED + "Usage: /" + label + " leather setcolor <color>");
                    p.sendMessage(ChatColor.RED + "Usage: /" + label + " leather setcolor <r> <g> <b>");
                }
            } else if(cmd.equalsIgnoreCase("clear")) {
                lm.setColor(null);
                is.setItemMeta(lm);
                p.setItemInHand(is);
                p.sendMessage(ChatColor.GOLD + "Color cleared!");
            } else {
                p.sendMessage(ChatColor.RED + "That is not a supported command!");
            }
        } else {
            p.sendMessage(ChatColor.RED + "You must be holding a piece of leather armor to do that!");
        }
        return false;
    }

}
