package fr.niavlys.openchant;

import java.util.Arrays;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.niavlys.api.NvApi;

public class OPEListener implements Listener {
	
	private static Inventory enchantable;
	
	@EventHandler
	public boolean onOpOpen (PlayerInteractEvent e) {
		ItemStack gw = new ItemStack(Material.GREEN_WOOL, 1);
		ItemStack help = new ItemStack(Material.STICK, 1);
		ItemMeta gwM = gw.getItemMeta();
		ItemMeta helpM = help.getItemMeta();
		gwM.addEnchant(Enchantment.DURABILITY, 10, true);
		helpM.addEnchant(Enchantment.DURABILITY, 10, true);
		gwM.setDisplayName(ChatColor.AQUA+Traductions.getTrad("gw.name"));
		helpM.setDisplayName(ChatColor.AQUA+Traductions.getTrad("help.name"));
		gwM = NvApi.fonctions.setItemFlags(gwM);
		helpM = NvApi.fonctions.setItemFlags(helpM);
		gwM.setLore(Arrays.asList(ChatColor.AQUA+Traductions.getTrad("gw.lore0")));
		helpM.setLore(Arrays.asList(ChatColor.AQUA+Traductions.getTrad("help.lore1"),ChatColor.RED+Traductions.getTrad("help.lore")));
		gw.setItemMeta(gwM);
		help.setItemMeta(helpM);
		Player p = e.getPlayer();
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
        	if(e.getClickedBlock().getType() == Material.ENCHANTING_TABLE) {
        		enchantable = Bukkit.createInventory(null, 45, ChatColor.RED+Traductions.getTrad("inv.title"));
				NvApi.fonctions.si(enchantable, 45);
				enchantable.setItem(4, help);
				enchantable.setItem(19, gw);
				enchantable.setItem(22, null);
				enchantable.setItem(25, gw);
				p.openInventory(enchantable);
	        	e.setCancelled(true);
        	}
        }
        return false;
	}
	
	@EventHandler
	public boolean onClose (InventoryCloseEvent e) {
		Inventory inv = e.getInventory();
		Player p = (Player) e.getPlayer();
		if(inv.equals(enchantable)) {
			Bukkit.getWorld(p.getWorld().getName()).dropItem(p.getLocation(), inv.getItem(22));
		}
		return false;
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if(!(e.getWhoClicked() instanceof Player)) return;
		Player p = (Player) e.getWhoClicked();
		ItemStack gw = new ItemStack(Material.GREEN_WOOL, 1);
		ItemStack help = new ItemStack(Material.STICK, 1);
		ItemMeta gwM = gw.getItemMeta();
		ItemMeta helpM = help.getItemMeta();
		gwM.addEnchant(Enchantment.DURABILITY, 10, true);
		helpM.addEnchant(Enchantment.DURABILITY, 10, true);
		gwM.setDisplayName(ChatColor.AQUA+Traductions.getTrad("gw.name"));
		helpM.setDisplayName(ChatColor.AQUA+Traductions.getTrad("help.name"));
		gwM = NvApi.fonctions.setItemFlags(gwM);
		helpM = NvApi.fonctions.setItemFlags(helpM);
		gwM.setLore(Arrays.asList(ChatColor.AQUA+Traductions.getTrad("gw.lore0")));
		helpM.setLore(Arrays.asList(ChatColor.AQUA+Traductions.getTrad("help.lore1"),ChatColor.RED+Traductions.getTrad("help.lore")));
		gw.setItemMeta(gwM);
		help.setItemMeta(helpM);
		Inventory inv = e.getInventory();
		if(inv.equals(enchantable)) {
			if(e.getCurrentItem() == null) return;
			if(e.getCurrentItem().equals(gw)) {
				e.setCancelled(true);
			}
			if(e.getCurrentItem().equals(help)) {
				e.setCancelled(true);
				return;
			}
			if(e.getCurrentItem().equals(NvApi.fonctions.getNoneItem())) {
				e.setCancelled(true);
				return;
			}
			ItemStack item = e.getCurrentItem();
			if(item.equals(gw)) {
				if(p.getLevel() < 60) {
					p.sendMessage(ChatColor.RED+Traductions.getTrad("warning"));
					return;
				}
				if(inv.getItem(22) == null) return;
				ItemStack itemtoenchant = inv.getItem(22);
				ItemMeta itemM = itemtoenchant.getItemMeta();
				Random ran = new Random();
				int nb_enchant = ran.nextInt(Infos.enchants.size());
				int a = 0;
				while(a != nb_enchant) {
					int level = ran.nextInt(100);
					enchant(itemtoenchant, itemM, ran, level);
					a++;
				}
				itemtoenchant.setItemMeta(itemM);
				inv.setItem(22, itemtoenchant);
				p.setLevel(p.getLevel()-60);
			}
		}
	}
	
	private static ItemMeta enchant(ItemStack items, ItemMeta itemm, Random ran, int level) {
		int nb = ran.nextInt(Infos.enchants.size()/2);
		Enchantment enchant = Infos.enchants.get(nb);
		if(!enchant.canEnchantItem(items)) return enchant(items, itemm, ran, level);
		else{
			itemm.addEnchant(Infos.enchants.get(nb), level, true);
			return itemm;
		}
	}
}