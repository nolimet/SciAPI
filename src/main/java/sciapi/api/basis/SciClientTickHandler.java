package sciapi.api.basis;

import java.util.EnumSet;

import sciapi.api.mc.inventory.McInventoryManager;
import sciapi.api.registry.TickTaskRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.entity.*;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;

public class SciClientTickHandler {
	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent event) {
	    if (event.phase == TickEvent.Phase.START) 
	    	TickTaskRegistry.onTickStart(Side.CLIENT);
	    else if(event.phase == TickEvent.Phase.END)
			TickTaskRegistry.onTickEnd(Side.CLIENT);
	}
}
