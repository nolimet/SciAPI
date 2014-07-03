package sciapi.api.abstraction.absutil;

public interface IExUtil <U extends IExUtil, T> {
	/**
	 * Sets the saved value of this Utility.
	 * @param val value to save.
	 * @return this
	 * */
	public U set(T val);
	
	/**
	 * Gets the saved value of this Utility.
	 * */
	public T get();
}
