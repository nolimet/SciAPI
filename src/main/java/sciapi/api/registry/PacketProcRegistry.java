package sciapi.api.registry;

import java.util.*;

import sciapi.api.mc.data.IPacketProcUnit;

public class PacketProcRegistry {
	private static Map<String, IPacketProcUnit> ppumap = new HashMap();
	
	/**
	 * Register Packet Process Unit via id.
	 * id cannot contain ':'
	 * */
	public static void registerPacketProcUnit(String id, IPacketProcUnit procunit){
		if(ppumap.containsKey(id))
			throw new DuplicateRegisterException("Problem with PacketProcRegistry");
		ppumap.put(id, procunit);
	}
	
	public static IPacketProcUnit getPacketProcUnit(String id){
		return ppumap.get(id);
	}
}
