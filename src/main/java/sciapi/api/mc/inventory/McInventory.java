package sciapi.api.mc.inventory;

import cpw.mods.fml.relauncher.Side;
import sciapi.api.mc.inventory.pos.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

/**
 * Scaffolding for IInventory.
 * */
public abstract class McInventory implements IInventory {
	protected McInvWorld iworld;
	public IInventory inventory;
	
	/**
	 * Checks if the Inventory is changed, and not notified.
	 * */
	public boolean invchanged = false;
	
	public McInvWorld getWorld() {
		return iworld;
	}
	
	/**
	 * Checks if Inventory is changed, etc.
	 * */
	public abstract void onCheck();
	
	/**
	 * Set the Inventory change notified.
	 * */
	public void notifyCheck(){
		invchanged = false;
	}
	
	/**
	 * From McInventory Entry to IInventory Entry.
	 * */
	public abstract int toREntry(int n);
	
	/**
	 * From IInventory Entry to McInventory Entry.
	 * */
	public abstract int fromREntry(int n);
	

	@Override
	public void markDirty() {
		inventory.markDirty();
	}
	
	/**
	 * Called when specific slot has to be synced.
	 * */
	public abstract void onInventorySync(int i);
	
	/**
	 * Get Inventory-Specific Id.
	 * Different Inventory must have another Id.
	 * */
	public abstract String getInvId();
	
	public abstract boolean equals(Object o);


	
	@Override
	public int getSizeInventory() {
		return inventory.getSizeInventory();
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return inventory.getStackInSlot(toREntry(i));
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		this.onInventorySync(i);
		return inventory.decrStackSize(this.toREntry(i), j);
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		return inventory.getStackInSlotOnClosing(this.toREntry(i));
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		this.onInventorySync(i);
		inventory.setInventorySlotContents(this.toREntry(i), itemstack);
	}

	@Override
	public String getInventoryName() {
		return inventory.getInventoryName();
	}

	@Override
	public boolean hasCustomInventoryName() {
		return inventory.hasCustomInventoryName();
	}

	@Override
	public int getInventoryStackLimit() {
		return inventory.getInventoryStackLimit();
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return inventory.isUseableByPlayer(entityplayer);
	}

	@Override
	public void openInventory() {
		inventory.openInventory();
	}

	@Override
	public void closeInventory() {
		inventory.closeInventory();
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return inventory.isItemValidForSlot(this.toREntry(i), itemstack);
	}
}
