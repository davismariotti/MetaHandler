package info.gomeow.metahandler.commands;

import java.util.LinkedList;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

public class FireworkCommand implements BaseCommand {

    @Override
    public boolean execute(Player p, Command cmdObj, String label, String cmd, LinkedList<String> args) {
        ItemStack is = p.getItemInHand();
        if(is.getItemMeta() instanceof FireworkMeta) {

        }
        return false;
    }

}
