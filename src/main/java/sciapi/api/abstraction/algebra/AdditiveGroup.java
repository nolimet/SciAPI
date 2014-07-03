package sciapi.api.abstraction.algebra;

import sciapi.api.abstraction.absutil.*;

/**
 * Additive Group in abstract algebra.
 * */
public interface AdditiveGroup<T extends AdditiveGroup> extends ITempBase<T> {
	/**
	 * Create as added value.
	 * */
	public T add(T a);

	/**
	 * Create as subtracted value.
	 * */
	public T sub(T a);

	/**
	 * get new instance of Zero, identity of addition, of the Group
	 * */
	public T getZero();

	/**
	 * get new instance of Additive Inverse element, which is mainly '-'
	 * */
	public T addinv();

	/**
	 * Set this as added value.
	 * 
	 * @return this
	 * */
	public T setadd(T a, T b);

	/**
	 * Set this as subtracted value.
	 * 
	 * @return this
	 * */
	public T setsub(T a, T b);

	/**
	 * Set this to Zero, identity of addition of the Group.
	 * */
	public T setZero();

	/**
	 * Set this to the Additive Inverse element of the parameter.
	 * */
	public T setaddinv(T a);

	/**
	 * Check if this value is zero.
	 * */
	public boolean isZero();

}