package sciapi.api.mc.inventory.entity;

import sciapi.api.mc.inventory.IMcInvManager;
import net.minecraft.entity.Entity;

/**
 * Management Class for Sets of Entity Inventory.
 * Saves Inventory for such entity.
 * */
public interface IMcEntityInvManager extends IMcInvManager {
	
	/**
	 * Gets the Inventory of an entity.
	 * */
	public McEntityInventory getInventory(Entity e);
	
	/**
	 * Checks if an entity is this type.
	 * */
	public boolean isEntityThistype(Entity e);
}
