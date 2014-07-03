package sciapi.api.mc.item.multiitem;

import net.minecraft.nbt.NBTTagCompound;
import sciapi.api.mc.inventory.pos.*;

public abstract class MultiItem {
	public McInvPos corepos;
	public McInvWorld worldObj;
	
	public MultiItem(McInvPos pcorepos){
		corepos = pcorepos;
		worldObj = corepos.iworld;
	}
	
	public void readFromNBT(NBTTagCompound tag) { }
	
	public void writeToNBT(NBTTagCompound tag) { }
	
    
	public void onInventoryChanged() {
		worldObj.onInventoryChanged();
	}
	
	public abstract boolean checkKeep();
	public abstract void onConstruct();
	public abstract void onDestroy();
	public abstract void onUpdate();
	public abstract String getId();
	
	public abstract boolean equals(Object o);
}
