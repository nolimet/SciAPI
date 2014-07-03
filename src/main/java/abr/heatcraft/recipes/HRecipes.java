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
		
		Fluid reg;
		
		if(FluidRegistry.isFluidRegistered("steam"))
		{
			reg = FluidRegistry.getFluid("steam");
			FluidHeatRecipes.heating().addFluidHeating("water", reg, 1000);
		}
		
		reg = FluidRegistry.getFluid("lava");
		FluidHeatRecipes.heating().addItemHeating(Item.getItemFromBlock(Blocks.stone),
				new FluidStack(reg, 1000), 30000);
		
		
	}
}
