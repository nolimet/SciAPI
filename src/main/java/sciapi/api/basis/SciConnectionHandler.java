package sciapi.api.basis;

import sciapi.api.registry.McManagerRegistry;
import net.minecraft.network.*;
import net.minecraft.server.MinecraftServer;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.*;
import cpw.mods.fml.relauncher.Side;

/**
 * Connection Handler for controlling client side for the specific server.
 * */
public class SciConnectionHandler {

	@SubscribeEvent
	public void connectionClosed(FMLNetworkEvent.ClientDisconnectionFromServerEvent e) {
		McManagerRegistry.onUnload(Side.CLIENT);
	}

	@SubscribeEvent
	public void connectionOpened(FMLNetworkEvent.ClientConnectedToServerEvent e) {
		McManagerRegistry.onLoad(Side.CLIENT);
	}
}
