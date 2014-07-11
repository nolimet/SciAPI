package abr.heatcraft;

import sciapi.api.mc.inventory.pos.McInvDirection;
import sciapi.api.registry.McInvItemRegistry;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import abr.heatcraft.core.McBagInvManager;
import abr.heatcraft.item.HItems;
import abr.heatcraft.renderer.CauldronRenderer;
import abr.heatcraft.renderer.ProgressRenderer;
import abr.heatcraft.tile.TileHeatCauldron;
import net.minecraftforge.client.MinecraftForgeClient;

public class HeatClientProxy extends HeatCommonProxy {
	
	public void onLoad() {
		super.onLoad();
		
		McInvItemRegistry.registerInvManager(McBagInvManager.getInstance(Side.CLIENT), Side.CLIENT);
		
		MinecraftForgeClient.registerItemRenderer(HItems.cookprogress, new ProgressRenderer(true, false, McInvDirection.UP, 1, 1, 14, 14));
		MinecraftForgeClient.registerItemRenderer(HItems.fuelprogress, new ProgressRenderer(true, false, McInvDirection.UP, 0, 0, 16, 16));
		MinecraftForgeClient.registerItemRenderer(HItems.fluidprogress, new ProgressRenderer(true, true, McInvDirection.RIGHT, 0, 0, 16, 16));
		MinecraftForgeClient.registerItemRenderer(HItems.boxlava, new ProgressRenderer(false, false, McInvDirection.UP, 0, 0, 16, 16));
		MinecraftForgeClient.registerItemRenderer(HItems.liqtank, new ProgressRenderer(true, true, McInvDirection.UP, 0, 0, 16, 16));
		MinecraftForgeClient.registerItemRenderer(HItems.casting, new ProgressRenderer(true, true, McInvDirection.UP, 0, 0, 16, 16));
		
		cauldronid = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(cauldronid, new CauldronRenderer());
	}
}
