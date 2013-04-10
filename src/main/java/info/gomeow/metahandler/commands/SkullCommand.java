package info.gomeow.metahandler.commands;

import java.util.LinkedList;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class SkullCommand implements BaseCommand {

    @Override
    public boolean execute(Player p, Command cmdObj, String label, String cmd, LinkedList<String> args) {
        ItemStack is = p.getItemInHand();
        if(is.getItemMeta() instanceof SkullMeta) {
            SkullMeta sm = (SkullMeta) is.getItemMeta();
            if(cmd.equalsIgnoreCase("owner")) {
                if(args.size() > 1) {
                    sm.setOwner(args.get(0));
                    is.setItemMeta(sm);
                    p.setItemInHand(is);
                    p.sendMessage(ChatColor.GOLD + "Owner set!");
                } else {
                    p.sendMessage(ChatColor.RED + "Usage: /" + label + " skull owner <name>");
                }
            } else if(cmd.equalsIgnoreCase("clear")) {
                sm.setOwner(null);
                is.setItemMeta(sm);
                p.setItemInHand(is);
                p.sendMessage(ChatColor.GOLD + "Owner cleared!");
            } else {
                p.sendMessage(ChatColor.RED + "That is not a supported command!");
            }
        } else {
            p.sendMessage(ChatColor.RED + "You must be holding a skull to do that!");
        }
        return true;
    }
}
