package info.gomeow.metahandler.commands;

import java.util.LinkedList;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class SkullCommand implements BaseCommand {

    @Override
    public boolean execute(Player player, Command cmdObj, String label, String cmd, LinkedList<String> args) {
        ItemStack is = player.getItemInHand();
        if(is.getItemMeta() instanceof SkullMeta) {
            SkullMeta sm = (SkullMeta) is.getItemMeta();
            if(cmd.equalsIgnoreCase("owner")) {
                if(args.size() > 0) {
                    sm.setOwner(ChatColor.translateAlternateColorCodes('&', args.get(0)));
                    is.setItemMeta(sm);
                    player.setItemInHand(is);
                    player.sendMessage(ChatColor.GOLD + "Owner set!");
                } else {
                    player.sendMessage(ChatColor.RED + "Usage: /" + label + " skull owner <name>");
                }
            } else if(cmd.equalsIgnoreCase("clear")) {
                sm.setOwner(null);
                is.setItemMeta(sm);
                player.setItemInHand(is);
                player.sendMessage(ChatColor.GOLD + "Owner cleared!");
            } else {
                player.sendMessage(ChatColor.RED + "That is not a supported command!");
            }
        } else {
            player.sendMessage(ChatColor.RED + "You must be holding a skull to do that!");
        }
        return true;
    }
}
