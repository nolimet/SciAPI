package abr.heatcraft.recipes;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import abr.heatcraft.block.HBlocks;
import abr.heatcraft.item.HItems;
import cpw.mods.fml.common.registry.GameRegistry;

public class HRecipes {
	public static void Init(){
		GameRegistry.addRecipe(new ItemStack(HItems.heatplate, 1),
				"x", "y", "x",
				'x', new ItemStack(Items.iron_ingot),
				'y', new ItemStack(Items.redstone));
		GameRegistry.addRecipe(new ItemStack(HItems.heatplate, 1),
				"x", "z", "x",
				'x', new ItemStack(Items.iron_ingot),
				'z', new ItemStack(HItems.copper_ingot));
		GameRegistry.addRecipe(new ItemStack(HItems.heatplate, 1),
				"x", "z", "x",
				'x', new ItemStack(HItems.lead_ingot),
				'z', new ItemStack(HItems.copper_ingot));
		
		GameRegistry.addRecipe(new ItemStack(HBlocks.heatBlock),
				"xxx", "xxx", "xxx",
				'x', new ItemStack(HItems.heatplate));
		GameRegistry.addRecipe(new ItemStack(HBlocks.heatCauldron),
				"x x", "x x", "xxx", 'x', new ItemStack(HItems.heatplate));
		GameRegistry.addRecipe(new ItemStack(HBlocks.heatFurnaceidle),
				"xxx", "x x", "xyx",
				'x', new ItemStack(HItems.heatplate),
				'y', new ItemStack(Blocks.stone_slab, 1, 4));
		
		GameRegistry.addRecipe(new ItemStack(HItems.box),
				" x ", "x x", " x ", 'x', new ItemStack(HItems.heatplate));
		GameRegistry.addShapelessRecipe(new ItemStack(HItems.boxlava),
				new ItemStack(HItems.box),
				new ItemStack(Items.lava_bucket));
		GameRegistry.addRecipe(new ItemStack(HItems.fuelprogress),
				" x ", "xzx", "xyx",
				'x', new ItemStack(HItems.heatplate),
				'y', new ItemStack(Blocks.stone_slab, 1, 4),
				'z', new ItemStack(Blocks.redstone_lamp));
		GameRegistry.addRecipe(new ItemStack(HItems.cookprogress),
				"xxx", "xyx", " x ",
				'x', new ItemStack(HItems.heatplate),
				'y', new ItemStack(Blocks.redstone_lamp));
		GameRegistry.addShapedRecipe(new ItemStack(HItems.liqtank),
				"yxy", "x x", "yxy",
				'x', new ItemStack(HItems.heatplate),
				'y', new ItemStack(Items.feather));
		GameRegistry.addShapelessRecipe(new ItemStack(HItems.fluidprogress),
				new ItemStack(HItems.liqtank), 
				new ItemStack(HItems.box));
		GameRegistry.addShapedRecipe(new ItemStack(HItems.casting),
				"yxy", "x x", "y y",
				'x', new ItemStack(HItems.heatplate),
				'y', new ItemStack(Items.feather));
		GameRegistry.addShapedRecipe(new ItemStack(HItems.bag),
				"zyz", "yxy", "zyz",
				'x', new ItemStack(HItems.box),
				'y', new ItemStack(Items.leather),
				'z', new ItemStack(HItems.heatplate));
		GameRegistry.addShapedRecipe(new ItemStack(HItems.exbag),
				"zyz", "yxy", "zyz",
				'x', new ItemStack(HItems.bag),
				'y', new ItemStack(Items.leather),
				'z', new ItemStack(HItems.heatplate));
		GameRegistry.addShapedRecipe(new ItemStack(HItems.filler),
				" h ", "yxy", " z ",
				'x', new ItemStack(HItems.liqtank),
				'y', new ItemStack(Items.feather),
				'z', new ItemStack(HItems.heatplate),
				'h', new ItemStack(Blocks.hopper));
		GameRegistry.addShapedRecipe(new ItemStack(HItems.drain),
				" z ", "yxy", " h ",
				'x', new ItemStack(HItems.liqtank),
				'y', new ItemStack(Items.feather),
				'z', new ItemStack(HItems.heatplate),
				'h', new ItemStack(Blocks.hopper));
		GameRegistry.addShapedRecipe(new ItemStack(HItems.fluidmover),
				" z ", "yxy", " z ",
				'x', new ItemStack(HItems.liqtank),
				'y', new ItemStack(Items.feather),
				'z', new ItemStack(HItems.heatplate));
		GameRegistry.addShapedRecipe(new ItemStack(HItems.itemmover),
				"zzz", "y y", "zzz",
				'y', new ItemStack(Items.feather),
				'z', new ItemStack(HItems.heatplate));
		
		Fluid reg;
		
		if(FluidRegistry.isFluidRegistered("steam"))
		{
			reg = FluidRegistry.getFluid("steam");
			FluidHeatRecipes.heating().addFluidHeating("water", reg, 1000);
		}
		
		reg = FluidRegistry.getFluid("lava");
		FluidHeatRecipes.heating().addItemHeating(Item.getItemFromBlock(Blocks.stone),
				new FluidStack(reg, 1000), 30000);
		FluidHeatRecipes.heating().addItemHeating(Item.getItemFromBlock(Blocks.cobblestone),
				new FluidStack(reg, 1000), 28000);
		
		reg = FluidRegistry.getFluid("liqcopper");
		FluidHeatRecipes.heating().addOreHeating("oreCopper", new FluidStack(reg, 2000), 1000);
		CastingRecipes.instance().addCasting(reg, new ItemStack(HItems.copper_ingot));

		reg = FluidRegistry.getFluid("liqtin");
		FluidHeatRecipes.heating().addOreHeating("oreTin", new FluidStack(reg, 2000), 1000);
		CastingRecipes.instance().addCasting(reg, new ItemStack(HItems.tin_ingot));
		
		reg = FluidRegistry.getFluid("liqiron");
		FluidHeatRecipes.heating().addOreHeating("oreIron", new FluidStack(reg, 2000), 1000);
		CastingRecipes.instance().addCasting(reg, new ItemStack(Items.iron_ingot));
		
		reg = FluidRegistry.getFluid("liqgold");
		FluidHeatRecipes.heating().addOreHeating("oreGold", new FluidStack(reg, 2000), 1000);
		CastingRecipes.instance().addCasting(reg, new ItemStack(Items.gold_ingot));
		
		reg = FluidRegistry.getFluid("liqsilver");
		FluidHeatRecipes.heating().addOreHeating("oreSilver", new FluidStack(reg, 2000), 1000);
		CastingRecipes.instance().addCasting(reg, new ItemStack(HItems.silver_ingot));

		reg = FluidRegistry.getFluid("liqlead");
		FluidHeatRecipes.heating().addOreHeating("oreLead", new FluidStack(reg, 2000), 2000);
		CastingRecipes.instance().addCasting(reg, new ItemStack(HItems.lead_ingot));

	}
}
