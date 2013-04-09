package info.gomeow.metahandler.commands;

import java.util.LinkedList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public interface BaseCommand {

    public boolean execute(CommandSender sender, Command cmdObj, String label, String cmd, LinkedList<String> args);
}
