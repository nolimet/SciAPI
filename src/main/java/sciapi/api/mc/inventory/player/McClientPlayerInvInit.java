package sciapi.api.mc.inventory.player;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import sciapi.api.mc.IMcManager;
import sciapi.api.mc.tick.*;

public class McClientPlayerInvInit implements IMcManager {
	
	public boolean initiated = false;

	@Override
	public void onLoad() {
		
	}

	@Override
	public void onUnload() {
		initiated = false;
		
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		
		if(player != null)
			McPlayerInvManager.getInstance(Side.CLIENT).removeInventory(player);
	}
	
	@SubscribeEvent
	public void onTick(TickEvent.ClientTickEvent e)
	{
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		
		if(!initiated && player != null)
		{
			McPlayerInvManager.getInstance(Side.CLIENT).addInventory(player);
			initiated = true;
		}
	}

}
