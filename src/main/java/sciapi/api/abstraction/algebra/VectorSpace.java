package sciapi.api.abstraction.algebra;

public interface VectorSpace<T extends VectorSpace, S extends Ring> extends
		AdditiveGroup<T> {
	/**
	 * Create as multiplied(by scalar) value.
	 * */
	public T mult(S sc);

	/**
	 * Create as divided(by scalar) value.
	 * */
	public T div(S sc);

	/**
	 * Set this as multiplied value.
	 * 
	 * @return this
	 * */
	public T setmult(T a, S sc);

	/**
	 * Set this as divided value.
	 * 
	 * @return this
	 * */
	public T setdiv(T a, S sc);
}
