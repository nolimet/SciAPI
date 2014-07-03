package abr.heatcraft.block;

import abr.heatcraft.api.enchant.ThermalEnchantableRegistry;
import abr.heatcraft.tile.TileHeatBlock;
import abr.heatcraft.tile.TileHeatCauldron;
import abr.heatcraft.tile.TileHeatFurnace;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;

public class HBlocks {
	public static Block heatBlock;
	
	public static Block heatCauldron;
	
	public static Block heatFurnaceidle;
	
	public static Block heatFurnaceburning;
	
	public static void Init(){
		heatBlock = new BlockHeat(Material.iron)
	    .setHardness(0.5F).setStepSound(Block.soundTypeStone)
	    .setBlockName("heatBlock").setCreativeTab(CreativeTabs.tabDecorations);
	    		
		heatCauldron = new BlockHeatCauldron(Material.iron)
	    .setHardness(0.5F).setStepSound(Block.soundTypeStone)
	    .setBlockName("heatCauldron").setCreativeTab(CreativeTabs.tabDecorations);
		
		heatFurnaceidle = new BlockHeatFurnace(false)
	    .setHardness(0.5F).setStepSound(Block.soundTypeStone)
	    .setBlockName("heatFurnace").setCreativeTab(CreativeTabs.tabDecorations);
		
		heatFurnaceburning = new BlockHeatFurnace(true)
	    .setHardness(0.5F).setStepSound(Block.soundTypeStone)
	    .setBlockName("heatFurnace");
		
		
		heatBlock.setHarvestLevel("pickaxe", 1);
		heatCauldron.setHarvestLevel("pickaxe", 1);
		heatFurnaceidle.setHarvestLevel("pickaxe", 1);
		heatFurnaceburning.setHarvestLevel("pickaxe", 1);
		
		GameRegistry.registerBlock(heatBlock, "heatblock");
		GameRegistry.registerBlock(heatCauldron, "heatcauldron");
		GameRegistry.registerBlock(heatFurnaceidle, "heatfurnaceidle");
		GameRegistry.registerBlock(heatFurnaceburning, "heatFurnaceburning");
		
		GameRegistry.registerTileEntity(TileHeatBlock.class, "tileheatblock");
		GameRegistry.registerTileEntity(TileHeatCauldron.class, "tileheatcauldron");
		GameRegistry.registerTileEntity(TileHeatFurnace.class, "tileheatfurnace");		
	}
}
