package sciapi.api.abstraction.pos;

import sciapi.api.abstraction.absutil.IInsBase;

/**
 * Interface for Positioned Object.
 * */
public interface IPosObject<O extends IPosObject, P extends IAbsPosition> extends IInsBase<O>{
	/**
	 * get the Position of this Object.
	 * */
	public P getPosition();
	
	/**
	 * get the World which this Object has belonged to.
	 * */
	public IWorld getWorld();
	
	/**
	 * Check if the Object has same position.
	 * */
	public boolean equals(Object o);
}
