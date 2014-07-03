package sciapi.api.mc.item.multiitem;

import sciapi.api.mc.inventory.pos.McInvPos;

public interface IMultiItemType {
	/**
	 * Constructs MultiItem with
	 * */
	public MultiItem construct(McInvPos pos);
	
	/**
	 * Get Identifier String; Same as MultiItem#getId()
	 * */
	public String getId();
}
