package abr.heatcraft.enchant;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.config.Configuration;

public class HEnchantments {
	public static int hpid, exid, teid;
	
	public static Enchantment heatproof;
	public static Enchantment explosive;
	public static Enchantment thermaleff;
	
	public static void Init(Configuration cfg){
		hpid = cfg.get("enchantments", "HeatProof_EnchantmentID", 130).getInt();
		exid = cfg.get("enchantments", "Explosive_Combustion_EnchantmentID", 131).getInt();
		teid = cfg.get("enchantments", "Thermal_Efficiency_EnchantmentID", 133).getInt();
		
		heatproof = new EnchantmentHeatProof(hpid, 2);
		explosive = new EnchantmentExplComb(exid, 4);
		thermaleff = new EnchantmentThermalEff(teid, 3);
	}
	
	public static boolean isHeatProof(NBTTagCompound comp){
		return comp.getShort("id") == hpid;
	}
	
	public static boolean isExplosive(NBTTagCompound comp){
		return comp.getShort("id") == exid;
	}
	
	public static boolean isThermalEff(NBTTagCompound comp){
		return comp.getShort("id") == teid;
	}
}
