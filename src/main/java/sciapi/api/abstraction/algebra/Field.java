package sciapi.api.abstraction.algebra;

public interface Field<T extends Field> extends Ring<T> {
	/**
	 * Create as divided value.
	 * */
	public T div(T a);

	/**
	 * get new instance of Multiplicative Inverse element, which is mainly '/'
	 * */
	public T multinv();

	/**
	 * Set this as divided value.
	 * 
	 * @return this
	 * */
	public T setdiv(T a, T b);

	/**
	 * Set this to the Multiplicative Inverse element of the parameter.
	 * */
	public T setmultinv(T a);
}
