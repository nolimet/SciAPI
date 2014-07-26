package sciapi.api.basis;

import net.minecraftforge.common.MinecraftForge;
import sciapi.api.basis.init.InitManager;
import sciapi.api.heat.HeatSystem;
import sciapi.api.mc.init.*;
import sciapi.api.registry.*;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.*;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.relauncher.Side;

@Mod(modid="sciapi", name="SciAPI", version="0.4.1")
public class SciAPI {
	@Instance(value = "sciapi")
	public static SciAPI instance;
    
	@SidedProxy(clientSide="sciapi.api.basis.SciClientProxy",
			serverSide="sciapi.api.basis.SciServerProxy")
	public static SciCommonProxy proxy;
    
	@EventHandler
    public void preInit(FMLPreInitializationEvent event) {
		InitManager.Init();
		McUnivInit.Init();
		proxy.onPreInit();
    }
    
    @EventHandler
    public void load(FMLInitializationEvent event) {
    	proxy.onLoad();
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	proxy.onPostInit();
    }
    
    
	@EventHandler
	public void serverLoad(FMLServerStartingEvent event) {
		McManagerRegistry.onLoad(Side.SERVER);
	}
	
	@EventHandler
	public void serverUnload(FMLServerStoppingEvent event) {
		McManagerRegistry.onUnload(Side.SERVER);
	}
}
