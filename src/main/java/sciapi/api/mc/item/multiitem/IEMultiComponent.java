package sciapi.api.mc.item.multiitem;

import java.util.*;

import sciapi.api.mc.item.ItemEntity;

public class IEMultiComponent extends ItemEntity {
	protected List<MultiItem> parmultiitem = new ArrayList();
	
	public void addMultiItem(MultiItem mitem){
		parmultiitem.add(mitem);
	}
	
	public void removeMultiItem(MultiItem mitem){
		parmultiitem.remove(mitem);
	}
}
