package sciapi.api.basis;

import java.util.EnumSet;

import sciapi.api.mc.inventory.McInventoryManager;
import sciapi.api.registry.TickTaskRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;

public class SciTickHandler {
	
	@SubscribeEvent
	public void onServerTick(TickEvent.ServerTickEvent event) {
	    if (event.phase == TickEvent.Phase.START) 
	    	TickTaskRegistry.onTickStart(Side.SERVER);
	    else if(event.phase == TickEvent.Phase.END)
			TickTaskRegistry.onTickEnd(Side.SERVER);
	}

}
