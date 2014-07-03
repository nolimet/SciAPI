package sciapi.api.abstraction.pos;

import sciapi.api.abstraction.algebra.VectorSpace;
import sciapi.api.abstraction.numerics.IAbsValue;

/**
 * Abstract Difference Interface
 * */
public interface IAbsDifference<T extends IAbsDifference, S extends IAbsValue> extends VectorSpace<T, S>{
	
	/**
	 * @return true if this difference is zero.
	 * */
	public boolean isZero();
}
