package sciapi.api.mc.inventory.player;

import java.util.*;

import net.minecraft.entity.player.*;
import net.minecraft.nbt.NBTTagCompound;
import sciapi.api.mc.inventory.entity.McEntityInventory;
import sciapi.api.mc.inventory.pos.McInvWorld;

public class McPlayerInventory extends McEntityInventory {
	
	public static final int column = 9;
	
	@Deprecated
	public McPlayerInventory(EntityPlayer p){
		super(p);
		inventory = getPlayerEntity().inventory;
		iworld = new McInvWorld(this, column, p.worldObj.isRemote);
	}

	
	@Override
	public McInvWorld getWorld() {
		return iworld;
	}
	
	public EntityPlayer getPlayerEntity() {
		return (EntityPlayer) entity;
	}
	
	public InventoryPlayer getPlayerInventory() {
		return (InventoryPlayer) inventory;
	}

	
	@Override
	public boolean equals(Object o) {
		if(o instanceof McPlayerInventory)
			return getPlayerEntity().getUniqueID().equals(((McPlayerInventory) o).getPlayerEntity().getUniqueID());
		return false;
	}


	@Override
	public void onCheck() {
		this.invchanged = getPlayerInventory().inventoryChanged;
	}
	
	@Override
	public void notifyCheck() {
		super.notifyCheck();
		getPlayerInventory().inventoryChanged = false;
	}
	
	
	@Override
	public void markDirty() {
		super.markDirty();
		
		getPlayerEntity().inventoryContainer.detectAndSendChanges();
	}
	
	@Override
	public void onInventorySync(int i) {
		getPlayerEntity().inventoryContainer.inventoryItemStacks
		.set(i + 9, this.getStackInSlot(i));
	}


	@Override
	public void saveNBTData(NBTTagCompound compound) {
		iworld.saveNBTData(compound);	
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		iworld.loadNBTData(compound);
	}


	@Override
	public int toREntry(int n) {
		return (n < 27)? (n + 9) : (n - 27);
	}


	@Override
	public int fromREntry(int n) {
		return (n < 9)? (n + 27) : (n - 9);
	}


	@Override
	public String getInvId() {
		return "playerinv:" + getPlayerEntity().getUniqueID();
	}
}
