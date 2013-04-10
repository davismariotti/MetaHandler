package info.gomeow.metahandler.commands;

import java.util.LinkedList;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public interface BaseCommand {

    public boolean execute(Player p, Command cmdObj, String label, String cmd, LinkedList<String> args);
}
