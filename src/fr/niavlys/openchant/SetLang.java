package fr.niavlys.openchant;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class SetLang implements CommandExecutor, TabCompleter {

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String msg, String[] args) {
		if(!(s instanceof Player)) return false;
		Player p = (Player) s;
		if(args[0].equalsIgnoreCase("en")) {
			Main.lang = "en";
		}
		else if(args[0].equalsIgnoreCase("fr")) {
			Main.lang = "fr";
		}
		else {
			p.sendMessage(Traductions.getTrad("error"));
			return false;
		}
		p.sendMessage(ChatColor.GREEN+Traductions.getTrad("validation")+ChatColor.GOLD+Main.lang);
		return true;
	}

	@Override
	public List<String> onTabComplete(CommandSender s, Command cmd, String msg, String[] args) {
		List<String> choose = new ArrayList<>();
		if(args.length == 1) {
			choose.add("en");
			choose.add("fr");
		}
		else {
			choose.add(Traductions.getTrad("errortab"));
		}
		return choose;
	}
	
	

}
