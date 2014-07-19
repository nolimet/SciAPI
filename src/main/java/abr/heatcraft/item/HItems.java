package abr.heatcraft.item;

import abr.heatcraft.api.enchant.ThermalEnchantableRegistry;
import abr.heatcraft.itementity.IEBoxLava;
import abr.heatcraft.itementity.IECasting;
import abr.heatcraft.itementity.IEFluidDrain;
import abr.heatcraft.itementity.IEFluidFiller;
import abr.heatcraft.itementity.IEFluidMover;
import abr.heatcraft.itementity.IEFluidProgress;
import abr.heatcraft.itementity.IEFuelProgress;
import abr.heatcraft.itementity.IEHeatPlate;
import abr.heatcraft.itementity.IECookProgress;
import abr.heatcraft.itementity.IEItemMover;
import abr.heatcraft.itementity.IELiquidTank;
import abr.heatcraft.itementity.IEMachineryBag;
import sciapi.api.mc.item.ItemContainer;
import sciapi.api.registry.McInvItemRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;
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
	public static Item liqtank;
	public static Item casting;
	public static Item filler;
	public static Item drain;
	public static Item fluidmover;
	public static Item itemmover;
	
	public static Item copper_ingot, tin_ingot, silver_ingot, lead_ingot;
	
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
		
		bag = new ItemMachineryBag(3, 0).setMaxStackSize(1)
				.setCreativeTab(CreativeTabs.tabDecorations)
				.setTextureName("heatcraft:machinerybag")
				.setUnlocalizedName("machinerybag");
		
		exbag = new ItemMachineryBag(6, 1).setMaxStackSize(1)
				.setCreativeTab(CreativeTabs.tabDecorations)
				.setTextureName("heatcraft:expandedmachinerybag")
				.setUnlocalizedName("expandedmachinerybag");
		
		liqtank = new ItemLiquidTank(4000).setMaxStackSize(1)
				.setCreativeTab(CreativeTabs.tabDecorations)
				.setUnlocalizedName("itemtank");
		
		casting = new ItemCasting(2000).setMaxStackSize(1)
				.setCreativeTab(CreativeTabs.tabDecorations)
				.setUnlocalizedName("itemcasting");
		
		filler = new ItemFluidFiller().setMaxStackSize(1)
				.setCreativeTab(CreativeTabs.tabDecorations)
				.setUnlocalizedName("fluidfiller");
		
		drain = new ItemFluidDrain().setMaxStackSize(1)
				.setCreativeTab(CreativeTabs.tabDecorations)
				.setUnlocalizedName("fluiddrain");
		
		fluidmover = new ItemFluidMover().setMaxStackSize(1)
				.setCreativeTab(CreativeTabs.tabDecorations)
				.setUnlocalizedName("fluidmover");
		
		itemmover = new ItemMover().setMaxStackSize(1)
				.setCreativeTab(CreativeTabs.tabDecorations)
				.setUnlocalizedName("itemmover");
		
		copper_ingot = new Item().setMaxStackSize(64).setCreativeTab(CreativeTabs.tabMaterials)
				.setTextureName("heatcraft:copper").setUnlocalizedName("copper");
		tin_ingot = new Item().setMaxStackSize(64).setCreativeTab(CreativeTabs.tabMaterials)
				.setTextureName("heatcraft:tin").setUnlocalizedName("tin");
		silver_ingot = new Item().setMaxStackSize(64).setCreativeTab(CreativeTabs.tabMaterials)
				.setTextureName("heatcraft:silver").setUnlocalizedName("silver");
		lead_ingot = new Item().setMaxStackSize(64).setCreativeTab(CreativeTabs.tabMaterials)
				.setTextureName("heatcraft:lead").setUnlocalizedName("lead");

		
		GameRegistry.registerItem(cookprogress, cookprogress.getUnlocalizedName());
		GameRegistry.registerItem(fuelprogress, fuelprogress.getUnlocalizedName());
		GameRegistry.registerItem(fluidprogress, fluidprogress.getUnlocalizedName());
		GameRegistry.registerItem(heatplate, heatplate.getUnlocalizedName());
		GameRegistry.registerItem(box, box.getUnlocalizedName());
		GameRegistry.registerItem(boxlava, boxlava.getUnlocalizedName());
		GameRegistry.registerItem(bag, bag.getUnlocalizedName());
		GameRegistry.registerItem(exbag, exbag.getUnlocalizedName());
		GameRegistry.registerItem(liqtank, liqtank.getUnlocalizedName());
		GameRegistry.registerItem(casting, casting.getUnlocalizedName());
		GameRegistry.registerItem(filler, filler.getUnlocalizedName());
		GameRegistry.registerItem(drain, drain.getUnlocalizedName());
		GameRegistry.registerItem(itemmover, itemmover.getUnlocalizedName());
		GameRegistry.registerItem(fluidmover, fluidmover.getUnlocalizedName());

		GameRegistry.registerItem(copper_ingot, copper_ingot.getUnlocalizedName());
		GameRegistry.registerItem(tin_ingot, tin_ingot.getUnlocalizedName());
		GameRegistry.registerItem(silver_ingot, silver_ingot.getUnlocalizedName());
		GameRegistry.registerItem(lead_ingot, lead_ingot.getUnlocalizedName());

		OreDictionary.registerOre("ingotCopper", copper_ingot);
		OreDictionary.registerOre("ingotLead", lead_ingot);
		OreDictionary.registerOre("ingotTin", tin_ingot);
		OreDictionary.registerOre("ingotSilver", silver_ingot);

		FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluid("lava"), new ItemStack(boxlava), new ItemStack(box));
		
		McInvItemRegistry.registerItemEntity(IECookProgress.class, "cookprogressbar");
		McInvItemRegistry.registerItemEntity(IEFuelProgress.class, "fuelprogressbar");
		McInvItemRegistry.registerItemEntity(IEFluidProgress.class, "fluidprogressbar");
		McInvItemRegistry.registerItemEntity(IEHeatPlate.class, "heatplate");
		McInvItemRegistry.registerItemEntity(IEBoxLava.class, "boxlava");
		McInvItemRegistry.registerItemEntity(IEMachineryBag.class, "machinerybag");
		McInvItemRegistry.registerItemEntity(IEMachineryBag.class, "expandedmachinerybag");
		McInvItemRegistry.registerItemEntity(IELiquidTank.class, "itemtank");
		McInvItemRegistry.registerItemEntity(IECasting.class, "casting");
		McInvItemRegistry.registerItemEntity(IEFluidFiller.class, "fluidfiller");
		McInvItemRegistry.registerItemEntity(IEFluidDrain.class, "fluiddrain");
		McInvItemRegistry.registerItemEntity(IEFluidMover.class, "fluidmover");
		McInvItemRegistry.registerItemEntity(IEItemMover.class, "itemmover");		

		ThermalEnchantableRegistry.registerItemHeatEnchantable(cookprogress);
		ThermalEnchantableRegistry.registerItemHeatEnchantable(fuelprogress);
		ThermalEnchantableRegistry.registerItemHeatEnchantable(fluidprogress);
		ThermalEnchantableRegistry.registerItemHeatEnchantable(heatplate);
		ThermalEnchantableRegistry.registerItemHeatEnchantable(boxlava);
		
		ThermalEnchantableRegistry.registerItemCombEnchantable(fuelprogress);
	}
}
