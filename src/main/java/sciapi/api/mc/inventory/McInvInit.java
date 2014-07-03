package sciapi.api.mc.inventory;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import sciapi.api.mc.inventory.player.*;
import sciapi.api.registry.*;

public class McInvInit {
	public static void Init(Side side){
		McManagerRegistry.registerManager(McInventoryManager.getInstance(side), side);
		
		TickTaskRegistry.registerTickTask(McInventoryManager.getInstance(side), side);
		
		McInvItemRegistry.registerEntityInventoryType(McPlayerInvManager.getInstance(side), side);
		
		if(side.isServer()) {
			FMLCommonHandler.instance().bus().register(new McPlayerHandlerInvInit());
		}
		else {
			McManagerRegistry.registerManager(new McClientPlayerInvInit(),
					Side.CLIENT);
		}
	}
}
