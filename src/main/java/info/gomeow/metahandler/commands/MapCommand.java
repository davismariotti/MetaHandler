package info.gomeow.metahandler.commands;

import java.util.LinkedList;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;

public class MapCommand implements BaseCommand {

    @Override
    public boolean execute(Player player, Command cmdObj, String label, String cmd, LinkedList<String> args) {
        ItemStack is = player.getItemInHand();
        if(is.getItemMeta() instanceof MapMeta) {
            MapMeta mm = (MapMeta) is.getItemMeta();
            if(cmd.equalsIgnoreCase("scaling")) {
                if(args.size() > 0) {
                    boolean scale;
                    if(args.get(0).equalsIgnoreCase("true")) {
                        scale = true;
                    } else if(args.get(0).equalsIgnoreCase("false")) {
                        scale = false;
                    } else {
                        player.sendMessage(ChatColor.RED + "Usage: /" + label + " map scaling <true/false>");
                        return true;
                    }
                    mm.setScaling(scale);
                    is.setItemMeta(mm);
                    player.setItemInHand(is);
                    player.sendMessage(ChatColor.GOLD + "Scaling set!");
                } else {
                    player.sendMessage(ChatColor.RED + "Usage: /" + label + " map scaling <true/false>");
                }
            } else {
                player.sendMessage(ChatColor.RED + "That is not a supported command!");
            }
        } else {
            player.sendMessage(ChatColor.RED + "You must be holding a non-empty map to do that!");
        }
        return false;
    }

}
