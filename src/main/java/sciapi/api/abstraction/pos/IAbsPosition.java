package sciapi.api.abstraction.pos;

import sciapi.api.abstraction.absutil.ITempBase;

/**
 * Abstract Position Interface
 * */
public interface IAbsPosition<P extends IAbsPosition, D extends IAbsDifference> extends ITempBase<P> {
	
	/**
	 * Get Position which have the difference with original Position.
	 * if Position is invalid, this method can return null.
	 * */
	public P getDiffPos(D diff);

	/**
	 * Get Difference from this Position to parameter Position.
	 * If Difference is invalid, this method can return null.
	 * */
	public D getDifference(P pos);
	
	/**
	 * Check if it is equal Position.
	 * */
	public boolean equals(Object o);
}
