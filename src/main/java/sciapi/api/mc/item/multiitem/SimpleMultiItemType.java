package sciapi.api.mc.item.multiitem;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

import sciapi.api.def.euclidian.EVecInt;
import sciapi.api.mc.inventory.pos.McInvPos;
import sciapi.api.mc.item.ItemEntity;

public class SimpleMultiItemType implements IMultiItemType {
	
	private List<EVecInt> poslist = new ArrayList();
	private List<IEMultiCompType> typelist = new ArrayList();
	private Class<? extends MultiItem> miclass;
	private String mid;
	
	public SimpleMultiItemType(Class<? extends MultiItem> pmiclass, String id){
		miclass = pmiclass;
		mid = id;
	}
	
	public void setPosItemType(EVecInt pos, IEMultiCompType type){
		if(!poslist.contains(pos)){
			poslist.add(pos);
			typelist.add(type);
		}
		else
			typelist.set(poslist.indexOf(pos), type);
	}

	@Override
	public MultiItem construct(McInvPos pos) {
		ItemEntity ie = pos.iworld.getItemEntity(pos);
		
		for(int i = 0; i < poslist.size(); i++)
			if(typelist.get(i).match(ie))
			{
				EVecInt vec = poslist.get(i).getTemporary();
				
				vec.setaddinv(poslist.get(i));
				McInvPos corepos = pos.getDiffPos(vec);
				
				vec.remove(vec);
				
				if(checkConstructable(i, corepos)){
					try {
						return miclass.getConstructor(McInvPos.class).newInstance(corepos);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

		return null;
	}
	
	public boolean checkConstructable(int i, McInvPos corepos){
		
		for(int j = 0; j < poslist.size(); j++){
			EVecInt jpos = poslist.get(j);
			McInvPos jipos = corepos.getDiffPos(jpos);
			ItemEntity ie = jipos.iworld.getItemEntity(jipos);
			
			if(!typelist.get(j).match(ie))
				return false;
		}
		
		return true;
	}

	@Override
	public String getId() {
		return this.mid;
	}

}
