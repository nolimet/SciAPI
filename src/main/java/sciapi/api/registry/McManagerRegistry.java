package sciapi.api.registry;

import java.util.*;

import cpw.mods.fml.relauncher.Side;
import sciapi.api.mc.IMcManager;

public class McManagerRegistry {
	private static List<IMcManager> servermlist = new ArrayList();
	private static List<IMcManager> clientmlist = new ArrayList();

	
	public static void registerManager(IMcManager manager, Side side){
		if(side.isClient())
			clientmlist.add(manager);
		else servermlist.add(manager);
	}
	
	public static void onLoad(Side side){
		if(side.isClient())
			for(IMcManager manager : clientmlist)
				manager.onLoad();
		else for(IMcManager manager : servermlist)
			manager.onLoad();
	}
	
	public static void onUnload(Side side){
		if(side.isClient())
			for(IMcManager manager : clientmlist)
				manager.onUnload();
		else for(IMcManager manager : servermlist)
			manager.onUnload();
	}
}
