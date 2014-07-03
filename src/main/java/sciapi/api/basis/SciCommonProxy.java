package sciapi.api.basis;

import sciapi.api.mc.init.McSidedInit;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

public abstract class SciCommonProxy {
	public void onPreInit() {
		McSidedInit.Init(Side.SERVER);
	}
	public void onLoad() {
		FMLCommonHandler.instance().bus().register(new SciTickHandler());
	}
	public void onPostInit() {
		
	}
}
