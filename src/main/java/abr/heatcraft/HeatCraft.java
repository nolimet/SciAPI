package abr.heatcraft;

import abr.heatcraft.block.*;
import abr.heatcraft.core.McBagInvManager;
import abr.heatcraft.enchant.HEnchantments;
import abr.heatcraft.fluid.HFluids;
import abr.heatcraft.gui.HeatGUIHandler;
import abr.heatcraft.item.HItems;
import abr.heatcraft.recipes.HRecipes;
import abr.heatcraft.tile.TileHeatBlock;
import abr.heatcraft.tile.TileHeatCauldron;
import sciapi.api.basis.SciTickHandler;
import sciapi.api.registry.McInvItemRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.*;
import cpw.mods.fml.relauncher.Side;

@Mod(modid="heatcraft", name="InvWorks", version="0.2.1", dependencies = "required-after:sciapi@[0.4.0,)")
public class HeatCraft {
	
	@Instance(value = "heatcraft")
	public static HeatCraft instance;
	
	@SidedProxy(clientSide = "abr.heatcraft.HeatClientProxy", serverSide = "abr.heatcraft.HeatCommonProxy")
	public static HeatCommonProxy proxy;

	@EventHandler
    public void preInit(FMLPreInitializationEvent event) {
		Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());
		
		cfg.load();
		
		HEnchantments.Init(cfg);
		HFluids.Init();
		HBlocks.Init();
		HItems.Init();
		HRecipes.Init();
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new HeatGUIHandler());
		
		cfg.save();
	}
    
    @EventHandler
    public void load(FMLInitializationEvent event) {
    	proxy.onLoad();
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    }

}
