package fr.niavlys.openchant;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	public static String lang = "en";
	public static String ver = "2.0";
	
	public void onEnable() {
		System.out.println(Traductions.getTrad("start"));
		getServer().getPluginManager().registerEvents(new OPEListener(), this);
		getCommand("setlang").setExecutor(new SetLang());
		getCommand("setlang").setTabCompleter(new SetLang());
		Infos.init();
		Traductions.init();
	}
	public void onDisable() {
		System.out.println(Traductions.getTrad("stop"));
	}
}
