package sciapi.api.mc.inventory;

import java.util.*;

import sciapi.api.mc.IMcManager;

/**
 * Management class for specific inventories.
 * Can be registered in the McInvItemRegistry.
 * */
public interface IMcInvManager extends IMcManager{
	/**
	 * Iterator for Inventories. Must be Iterator for McInventory.
	 * */
	public Iterator getInvIterator();
	
	/**
	 * Checks if this is empty.
	 * */
	public boolean isEmpty();
}
