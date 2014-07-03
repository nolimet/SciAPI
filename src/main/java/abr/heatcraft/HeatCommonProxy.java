package abr.heatcraft;

import cpw.mods.fml.relauncher.Side;
import abr.heatcraft.core.McBagInvManager;
import sciapi.api.registry.McInvItemRegistry;

public class HeatCommonProxy {
	
	public int cauldronid = -1;
	
	public void onLoad() {
		McInvItemRegistry.registerInvManager(McBagInvManager.getInstance(Side.SERVER), Side.SERVER);
	}
}
