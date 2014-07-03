package sciapi.api.mc.data;

import net.minecraft.nbt.NBTTagCompound;

public interface INBTio {
	/**
	 * Reads from NBTTagCompound.
	 * */
	public void readFromNBT(NBTTagCompound nbt);
	
	/**
	 * Writes to NBTTagCompound.
	 * */
	public void writeToNBT(NBTTagCompound nbt);
}
