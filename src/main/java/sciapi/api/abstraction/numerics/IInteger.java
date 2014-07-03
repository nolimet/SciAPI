package sciapi.api.abstraction.numerics;

import sciapi.api.def.numerics.DInteger;

/**
 * Abstract Integer Interface.
 * */
public interface IInteger extends ICompValue<IInteger> {
	
	/**
	 * Create Value as Divided value.
	 * */
	public IInteger div(IInteger iav);
	
	/**
	 * Set Value as Divided value.
	 * */
	public IInteger setdiv(IInteger pre, IInteger post);
	
	/**
	 * Create Value as Remainder value.
	 * */
	public IInteger remainder(IInteger iav);
	
	/**
	 * Set Value as Remainder value.
	 * */
	public IInteger setremainder(IInteger pre, IInteger post);
	
	
	/**
	 * Set this value.
	 * */
	public void set(int v);
	
	/**
	 * Set this value.
	 * */
	public void set(long v);
	
	/**
	 * To integer value.
	 * */
	public int toInt();
	
	/**
	 * To long value.
	 * */
	public long toLong();
}
