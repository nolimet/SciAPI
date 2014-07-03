package sciapi.api.mc.inventory.player;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.relauncher.Side;
import sciapi.api.mc.IMcManager;
import sciapi.api.mc.tick.*;

public class McClientPlayerInvInit implements IMcManager {

	@Override
	public void onLoad() {
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		
		McPlayerInvManager.getInstance(Side.CLIENT).addInventory(player);
	}

	@Override
	public void onUnload() {
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		
		if(player != null)
			McPlayerInvManager.getInstance(Side.CLIENT).removeInventory(player);
	}

}
