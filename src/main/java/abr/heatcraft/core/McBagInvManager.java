package abr.heatcraft.core;

import java.util.*;

import abr.heatcraft.itementity.IEMachineryBag;
import net.minecraft.inventory.IInventory;
import cpw.mods.fml.relauncher.Side;
import sciapi.api.mc.inventory.IMcInvManager;
import sciapi.api.mc.inventory.McInventory;
import sciapi.api.mc.inventory.McInventoryManager;

public class McBagInvManager implements IMcInvManager {

	private static EnumMap<Side, McBagInvManager> instance = new EnumMap(Side.class);
	private boolean enabled = false;
	
	private Map<String, McInventory> invmap = new HashMap();
	
	public static McBagInvManager getInstance(Side side) {
		if(!instance.containsKey(side))
			instance.put(side, new McBagInvManager());
		return instance.get(side);
	}
	
	@Override
	public void onLoad() {
		enabled = true;
	}

	@Override
	public void onUnload() {
		enabled = false;
	}
	
	public McBagInventory addInventory(IEMachineryBag bag, String name)
	{
		McBagInventory mcinv = new McBagInventory(bag, name);
		invmap.put(name, mcinv);
		
		return mcinv;
	}
	
	public void removeInventory(String name)
	{
		invmap.remove(name);
	}

	@Override
	public Iterator getInvIterator() {
		return invmap.values().iterator();
	}

	@Override
	public boolean isEmpty() {
		return invmap.isEmpty();
	}

}
