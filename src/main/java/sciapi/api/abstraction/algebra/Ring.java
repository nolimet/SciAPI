package sciapi.api.abstraction.algebra;

public interface Ring<T extends Ring> extends AdditiveGroup<T> {
	/**
	 * Create as multiplied value.
	 * */
	public T mult(T a);

	/**
	 * get new instance of One, identity of multiplication, of the Ring
	 * */
	public T getOne();

	/**
	 * Set this as multiplied value.
	 * 
	 * @return this
	 * */
	public T setmult(T a, T b);

	/**
	 * Set this to One, identity of multiplication of the Ring.
	 * */
	public T setOne();
}
