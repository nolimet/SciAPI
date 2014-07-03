package abr.heatcraft.api.enchant;

import java.util.*;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class ThermalEnchantableRegistry {
	private static List<Item> ilist = new ArrayList();
	private static List<Item> comblist = new ArrayList();
	
	public static void registerItemHeatEnchantable(Item item){
		ilist.add(item);
	}
	
	public static void registerItemCombEnchantable(Item item){
		comblist.add(item);
	}
	
	public static boolean isThermalEnchantable(Item item){
		return ilist.contains(item);
	}
	
	public static boolean isCombustionEnchantable(Item item){
		return comblist.contains(item);
	}
}
