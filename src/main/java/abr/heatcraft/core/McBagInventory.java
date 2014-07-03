package abr.heatcraft.core;

import abr.heatcraft.itementity.IEMachineryBag;
import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.NBTTagCompound;
import sciapi.api.mc.inventory.McInventory;
import sciapi.api.mc.inventory.player.McPlayerInventory;
import sciapi.api.mc.inventory.pos.McInvWorld;

public class McBagInventory extends McInventory {

	private IEMachineryBag bag;
	private String name;
	
	public McBagInventory(IEMachineryBag inv, String name)
	{
		this.bag = inv;
		this.inventory = inv;
		this.name = name;
		
		this.iworld = new McInvWorld(this, 9, inv.worldObj.isRemote);
	}

	
	public void saveNBTData(NBTTagCompound compound) {
		iworld.saveNBTData(compound);	
	}

	public void loadNBTData(NBTTagCompound compound) {
		iworld.loadNBTData(compound);
	}
	
	@Override
	public void onCheck() {
		
	}
	
	@Override
	public void markDirty() {
		super.markDirty();
		
		bag.worldObj.onInventoryChanged();
	}
	
	@Override
	public void notifyCheck() {
		super.notifyCheck();
	}
	
	@Override
	public int toREntry(int n) {
		return n;
	}

	@Override
	public int fromREntry(int n) {
		return n;
	}

	@Override
	public void onInventorySync(int i) {
		
	}

	@Override
	public String getInvId() {
		return name;
	}

	@Override
	public boolean equals(Object o) {
		if(o instanceof McBagInventory)
			return name.equals(((McBagInventory) o).name);
		return false;
	}

}
