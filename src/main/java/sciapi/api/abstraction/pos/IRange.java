package sciapi.api.abstraction.pos;

import sciapi.api.abstraction.absutil.IInsBase;

/**
 * Abstract Range Interface
 * */
public interface IRange<R extends IRange, P extends IAbsPosition> extends IInsBase<R> {
	/**
	 * Check if this Position is in Range.
	 * */
	public boolean inRange(P pos);
	
	/**
	 * Check if the range is Overlapped.
	 * */
	public boolean isOverlapped(R range);
	
	/**
	 * get Intersection of the ranges.
	 * returns null if there is no Intersection.
	 * */
	public R getIntersect(R range);
}
