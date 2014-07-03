package abr.heatcraft;

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
		
		MinecraftForgeClient.registerItemRenderer(HItems.cookprogress, new ProgressRenderer(true, 1, 1, 14, 14));
		MinecraftForgeClient.registerItemRenderer(HItems.fuelprogress, new ProgressRenderer(true, 0, 0, 16, 16));
		MinecraftForgeClient.registerItemRenderer(HItems.boxlava, new ProgressRenderer(false, 0, 0, 16, 16));
		
		cauldronid = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(cauldronid, new CauldronRenderer());
	}
}
