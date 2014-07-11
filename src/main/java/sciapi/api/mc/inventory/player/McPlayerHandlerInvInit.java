package sciapi.api.mc.inventory.player;

import sciapi.api.mc.inventory.entity.McEntityInvManager;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.relauncher.Side;

public class McPlayerHandlerInvInit {

	@SubscribeEvent
	public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent e) {
		McPlayerInvManager.getInstance(Side.SERVER).addInventory(e.player);
	}

	@SubscribeEvent
	public void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent e) {
		McPlayerInvManager.getInstance(Side.SERVER).removeInventory(e.player);
	}

}
