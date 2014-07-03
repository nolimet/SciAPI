package sciapi.api.mc.init;

import sciapi.api.basis.SciConnectionHandler;
import sciapi.api.mc.unit.McUnitInit;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;

public class McUnivInit {
	public static void Init(){
		McUnitInit.Init();
		
		FMLCommonHandler.instance().bus().register(new SciConnectionHandler());
	}
}
