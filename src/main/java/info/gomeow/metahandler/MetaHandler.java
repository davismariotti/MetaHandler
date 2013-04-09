package info.gomeow.metahandler;

import info.gomeow.metahandler.commands.Commands;

import java.io.IOException;

import org.bukkit.plugin.java.JavaPlugin;

public class MetaHandler extends JavaPlugin {

	Commands commands;
	
	public void onEnable() {
		try {
			Metrics metrics = new Metrics(this);
			metrics.start();
		} catch(IOException e) {
			e.printStackTrace();
		}
		commands = new Commands(this);
		getCommand("mh").setExecutor(commands);
	}

}
