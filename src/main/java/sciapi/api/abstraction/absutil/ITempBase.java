package sciapi.api.abstraction.absutil;

public interface ITempBase<T extends ITempBase> extends IInsBase<T> {
	/**
	 * Get Temporary value as this type.
	 * @return Temporary value.
	 * */
	public T getTemporary();
	
	/**
	 * Remove the Temporary value.
	 * Do not use the value after calling this method.
	 * @param tval Temporary value to remove.
	 * */
	public void remove(T tval);
	
}
