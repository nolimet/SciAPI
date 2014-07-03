package abr.heatcraft.multiitem;

import abr.heatcraft.itementity.IEFuelProgress;
import sciapi.api.mc.item.ItemEntity;
import sciapi.api.mc.item.multiitem.IEMultiCompType;

public class ItemFuelProgressType implements IEMultiCompType {

	@Override
	public boolean match(ItemEntity ie) {
		return (ie instanceof IEFuelProgress);
	}

}
