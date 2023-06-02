package fr.niavlys.openchant;

import java.util.HashMap;

public class Traductions {

	public static HashMap<String, String> en = new HashMap<>();
	public static HashMap<String, String> fr = new HashMap<>();
	
	public static void init() {
		en.put("start", "[OpEnchantTable "+Main.ver+"]: Succefully Launched!");
		en.put("stop", "[OpEnchantTable v0.1]: Succefully Shutdowned!");
		en.put("gw.name", "Enchant:");
		en.put("gw.lore0", "Clic to Enchant");
		en.put("help.name", "Help:");
		en.put("help.lore1", "Put item on the slot");
		en.put("help.lore2", "Warning: This will consume all 60 XP LEVELS");
		en.put("inv.title", "OP Enchant Table");
		en.put("warning", "You don't have much xp (60 Levels)");
		en.put("error", "This is not a valid Language");
		en.put("errortab", "[No_More_Arguments]");
		en.put("validation", "Lang set to: ");

		fr.put("start", "[OpEnchantTable "+Main.ver+"]: Plugin Lance!");
		fr.put("stop", "[OpEnchantTable v0.1]: Plugin Arrete!");
		fr.put("gw.name", "Enchanter:");
		fr.put("gw.lore0", "Cliquer pour Enchant");
		fr.put("help.name", "Aide:");
		fr.put("help.lore1", "Mettre Un Item Enchantable dans le Slot");
		fr.put("help.lore2", "Attention: Enchanter va vous enlever 60 Levels D'XP");
		fr.put("inv.title", "Table D'Enchantement OP");
		fr.put("warning", "Vous n'avez pas assez D'XP (60 Levels)");
		fr.put("error", "Ce n'est pas un language valide");
		fr.put("errortab", "[Plus_Aucun_Argument]");
		fr.put("validation", "Langue Définie sur: ");
	}
	
	public static String getTrad(String key) {
		if(Main.lang.equalsIgnoreCase("en")) return en.get(key);
		else if(Main.lang.equalsIgnoreCase("fr")) return fr.get(key);
		else return null;
	}
}
