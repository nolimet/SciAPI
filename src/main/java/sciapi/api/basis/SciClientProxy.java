package sciapi.api.basis;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import sciapi.api.mc.init.McSidedInit;

public class SciClientProxy extends SciCommonProxy {
	public void onPreInit(){
		super.onPreInit();
		McSidedInit.Init(Side.CLIENT);
	}

	@Override
	public void onLoad() {
		super.onLoad();
		FMLCommonHandler.instance().bus().register(new SciClientTickHandler());
	}

	@Override
	public void onPostInit() {
		super.onPostInit();
	}
}
