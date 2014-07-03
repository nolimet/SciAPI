package sciapi.api.mc.item.multiitem;

import java.util.*;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import sciapi.api.mc.inventory.pos.McInvWorld;
import sciapi.api.mc.item.ItemContainer;
import sciapi.api.mc.item.ItemEntity;

public class IEMultiCore extends IEMultiComponent {
	
	public List<IMultiItemType> typelist = new ArrayList();
	public Map<String, NBTTagCompound> tagcomp = new HashMap();
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		
		for(IMultiItemType type : typelist) {
			if(tag.hasKey(type.getId()))
				tagcomp.put(type.getId(), tag.getCompoundTag(type.getId()));
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
				
		for(MultiItem mitem : parmultiitem) {
			if(!tag.hasKey(mitem.getId()))
				tag.setTag(mitem.getId(), new NBTTagCompound());
			
			mitem.writeToNBT(tag.getCompoundTag(mitem.getId()));
		}
	}
	
	@Override
	public void updateEntity(){
		if(this.worldObj.inv.invchanged){
			for(IMultiItemType type : typelist){
				MultiItem mi = type.construct(pos);
				if(mi != null && !this.parmultiitem.contains(mi)){
					mi.onConstruct();
					this.addMultiItem(mi);
					tagcomp.put(type.getId(), new NBTTagCompound());
					mi.writeToNBT(tagcomp.get(mi.getId()));
				}
			}
			
			Iterator<MultiItem> ite = parmultiitem.iterator();
			
			while(ite.hasNext()) {
				MultiItem mitem = ite.next();
				if(!mitem.checkKeep())
				{
					ite.remove();
					mitem.onDestroy();
				}
			}
		}
				
		for(MultiItem mitem : parmultiitem){
			if(tagcomp.containsKey(mitem.getId())){
				mitem.readFromNBT(tagcomp.get(mitem.getId()));
				tagcomp.remove(mitem.getId());
			}
			
			mitem.onUpdate();
		}
	}
	
	@Override
	public void invalidate(){
		Iterator<MultiItem> ite = parmultiitem.iterator();
		
		while(ite.hasNext()) {
			MultiItem mitem = ite.next();
			mitem.onDestroy();
		}
	}
}
