package abr.heatcraft.item;

import abr.heatcraft.api.enchant.ThermalEnchantableRegistry;
import abr.heatcraft.itementity.IEBoxLava;
import abr.heatcraft.itementity.IEFluidProgress;
import abr.heatcraft.itementity.IEFuelProgress;
import abr.heatcraft.itementity.IEHeatPlate;
import abr.heatcraft.itementity.IECookProgress;
import abr.heatcraft.itementity.IEMachineryBag;
import sciapi.api.mc.item.ItemContainer;
import sciapi.api.registry.McInvItemRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;

public class HItems {
	public static Item cookprogress;
	public static Item fuelprogress;
	public static Item fluidprogress;
	public static Item heatplate;
	public static Item box;
	public static Item boxlava;
	public static Item bag;
	public static Item exbag;
	
	public static void Init(){
		cookprogress = new ItemCookProgress().setMaxStackSize(1)
				.setCreativeTab(CreativeTabs.tabDecorations)
				.setUnlocalizedName("cookprogressbar");
		
		fuelprogress = new ItemFuelProgress().setMaxStackSize(1)
				.setCreativeTab(CreativeTabs.tabDecorations)
				.setUnlocalizedName("fuelprogressbar");
		
		fluidprogress = new ItemFluidProgress().setMaxStackSize(1)
				.setCreativeTab(CreativeTabs.tabDecorations)
				.setUnlocalizedName("fluidprogressbar");
		
		heatplate = new ItemHeatPlate().setMaxStackSize(1)
				.setCreativeTab(CreativeTabs.tabDecorations)
				.setUnlocalizedName("heatplate");
		
		box = new ItemBox().setMaxStackSize(1)
				.setCreativeTab(CreativeTabs.tabDecorations)
				.setUnlocalizedName("box");
		
		boxlava = new ItemBoxLava().setMaxStackSize(1)
				.setCreativeTab(CreativeTabs.tabDecorations)
				.setUnlocalizedName("boxlava");
		
		bag = new ItemMachineryBag(3).setMaxStackSize(1)
				.setCreativeTab(CreativeTabs.tabDecorations)
				.setUnlocalizedName("machinerybag");
		
		exbag = new ItemMachineryBag(6).setMaxStackSize(1)
				.setCreativeTab(CreativeTabs.tabDecorations)
				.setUnlocalizedName("expandedmachinerybag");
		
		GameRegistry.registerItem(cookprogress, cookprogress.getUnlocalizedName());
		GameRegistry.registerItem(fuelprogress, fuelprogress.getUnlocalizedName());
		GameRegistry.registerItem(fluidprogress, fluidprogress.getUnlocalizedName());
		GameRegistry.registerItem(heatplate, heatplate.getUnlocalizedName());
		GameRegistry.registerItem(box, box.getUnlocalizedName());
		GameRegistry.registerItem(boxlava, boxlava.getUnlocalizedName());
		GameRegistry.registerItem(bag, bag.getUnlocalizedName());
		GameRegistry.registerItem(exbag, exbag.getUnlocalizedName());
		
		McInvItemRegistry.registerItemEntity(IECookProgress.class, "cookprogressbar");
		McInvItemRegistry.registerItemEntity(IEFuelProgress.class, "fuelprogressbar");
		McInvItemRegistry.registerItemEntity(IEFluidProgress.class, "fluidprogressbar");
		McInvItemRegistry.registerItemEntity(IEHeatPlate.class, "heatplate");
		McInvItemRegistry.registerItemEntity(IEBoxLava.class, "boxlava");
		McInvItemRegistry.registerItemEntity(IEMachineryBag.class, "machinerybag");
		McInvItemRegistry.registerItemEntity(IEMachineryBag.class, "expandedmachinerybag");
				
		ThermalEnchantableRegistry.registerItemHeatEnchantable(cookprogress);
		ThermalEnchantableRegistry.registerItemHeatEnchantable(fuelprogress);
		ThermalEnchantableRegistry.registerItemHeatEnchantable(fluidprogress);
		ThermalEnchantableRegistry.registerItemHeatEnchantable(heatplate);
		ThermalEnchantableRegistry.registerItemHeatEnchantable(boxlava);
		
		ThermalEnchantableRegistry.registerItemCombEnchantable(fuelprogress);
		
	}
}
