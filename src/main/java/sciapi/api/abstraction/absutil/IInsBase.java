package sciapi.api.abstraction.absutil;

public interface IInsBase<T extends IInsBase> {
	/**
	 * get New Instance of this type.
	 * 
	 * @return new Instance of this type.
	 * */
	public T getNew();
	
	/**
	 * set this object as the parameter.
	 * 
	 * @param par the parameter
	 * @return <code>this</code>
	 * */
	public T set(T par);
}
