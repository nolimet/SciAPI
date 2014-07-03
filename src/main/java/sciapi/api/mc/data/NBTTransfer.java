package sciapi.api.mc.data;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagByteArray;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagLong;
import net.minecraft.nbt.NBTTagShort;
import net.minecraft.nbt.NBTTagString;
import sciapi.api.abstraction.absutil.IExUtil;
import sciapi.api.basis.data.*;
import sciapi.api.def.data.DataSet;

public class NBTTransfer implements IDataTransfer, IExUtil<NBTTransfer, NBTTagCompound> {

	NBTTagCompound nbttc;
	NBTioUtil nu = new NBTioUtil();
	
	@Override
	public NBTTransfer set(NBTTagCompound val) {
		nbttc = val;
		return this;
	}

	@Override
	public NBTTagCompound get() {
		return nbttc;
	}
	
	
	@Override
	public void ExtractFrom(IDataMap map) {
		nu.set(map);
		nu.writeToNBT(nbttc);
	}

	@Override
	public IDataMap Eject() {
		nu.set(new DataSet());
		nu.readFromNBT(nbttc);
		return nu.get();
	}

}
