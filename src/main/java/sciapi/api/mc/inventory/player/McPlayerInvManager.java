package sciapi.api.mc.inventory.player;

import java.util.*;

import cpw.mods.fml.relauncher.Side;
import sciapi.api.mc.inventory.McInventory;
import sciapi.api.mc.inventory.entity.IMcEntityInvManager;
import sciapi.api.mc.inventory.entity.McEntityInventory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class McPlayerInvManager implements IMcEntityInvManager {
	
	private static EnumMap<Side, McPlayerInvManager> instance = new EnumMap(Side.class);
	
	public static McPlayerInvManager getInstance(Side side){
		if(!instance.containsKey(side))
			instance.put(side, new McPlayerInvManager());
		return instance.get(side);
	}
	
	private Map<UUID, McPlayerInventory> plinvmap = new HashMap();
	
	
	@Override
	public void onLoad() {
	}

	@Override
	public void onUnload() {
		plinvmap.clear();
	}
	
	
	public McPlayerInventory getPlayerInventory(EntityPlayer p){
		if(!plinvmap.containsKey(p.getUniqueID()))
			plinvmap.put(p.getUniqueID(), new McPlayerInventory(p));
		return plinvmap.get(p.getUniqueID());
	}
	
	public void addInventory(EntityPlayer player) {		
		if(!plinvmap.containsKey(player.getUniqueID()))
			plinvmap.put(player.getUniqueID(), new McPlayerInventory(player));
		
		player.registerExtendedProperties("McInventory", plinvmap.get(player.getUniqueID()));
	}

	public void removeInventory(EntityPlayer player) {
		plinvmap.remove(player.getUniqueID());
	}

	@Override
	public McEntityInventory getInventory(Entity e) {
		return getPlayerInventory((EntityPlayer) e);
	}

	@Override
	public boolean isEntityThistype(Entity e) {
		return (e instanceof EntityPlayer);
	}
	

	@Override
	public Iterator getInvIterator() {
		return plinvmap.values().iterator();
	}

	@Override
	public boolean isEmpty() {
		return plinvmap.isEmpty();
	}

}
