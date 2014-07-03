package sciapi.api.abstraction.pos;

import sciapi.api.abstraction.absutil.IInsBase;

public interface IPosEntity<E extends IPosEntity, R extends IRange> extends IInsBase<E>{
	/**
	 * get the Position of this Object.
	 * */
	public R getRange();
	
	/**
	 * get the World which this Object has belonged to.
	 * */
	public IWorld getWorld();
}
