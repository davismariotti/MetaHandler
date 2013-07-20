package info.gomeow.metahandler;

import info.gomeow.metahandler.commands.CommandHandler;

import java.io.IOException;

import org.bukkit.plugin.java.JavaPlugin;

public class MetaHandler extends JavaPlugin {

    CommandHandler commands;

    public void onEnable() {
        try {
            Metrics metrics = new Metrics(this);
            metrics.start();
        } catch(IOException e) {
            e.printStackTrace();
        }
        commands = new CommandHandler(this);
        getCommand("meta").setExecutor(commands);
    }

}
