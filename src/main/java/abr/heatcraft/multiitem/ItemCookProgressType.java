package abr.heatcraft.multiitem;

import abr.heatcraft.itementity.IECookProgress;
import sciapi.api.mc.item.ItemEntity;
import sciapi.api.mc.item.multiitem.IEMultiCompType;

public class ItemCookProgressType implements IEMultiCompType {

	@Override
	public boolean match(ItemEntity ie) {
		return (ie instanceof IECookProgress);
	}

}
