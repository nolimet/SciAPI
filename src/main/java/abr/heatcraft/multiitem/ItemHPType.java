package abr.heatcraft.multiitem;

import abr.heatcraft.itementity.IEHeatPlate;
import sciapi.api.mc.item.ItemEntity;
import sciapi.api.mc.item.multiitem.IEMultiCompType;

public class ItemHPType implements IEMultiCompType {

	@Override
	public boolean match(ItemEntity ie) {
		return (ie instanceof IEHeatPlate);
	}

}
