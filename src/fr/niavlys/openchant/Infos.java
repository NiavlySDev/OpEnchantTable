package fr.niavlys.openchant;


import java.util.ArrayList;
import java.util.List;

import org.bukkit.enchantments.Enchantment;
	
public class Infos {
	
	public static List<Enchantment> enchants = new ArrayList<>();
	
	public static void init() {
		for(Enchantment enchant : Enchantment.values()) {
			enchants.add(enchant);
		}
		enchants.remove(Enchantment.BINDING_CURSE);
		enchants.remove(Enchantment.VANISHING_CURSE);
		enchants.remove(Enchantment.SILK_TOUCH);
	}
	
}
