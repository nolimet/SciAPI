package abr.heatcraft.multiitem;

import abr.heatcraft.itementity.IEFluidProgress;
import sciapi.api.mc.item.ItemEntity;
import sciapi.api.mc.item.multiitem.IEMultiCompType;

public class ItemFluidProgressType implements IEMultiCompType {

	@Override
	public boolean match(ItemEntity ie) {
		return (ie instanceof IEFluidProgress);
	}

}
