package sciapi.api.abstraction.numerics;

import sciapi.api.abstraction.algebra.Field;

/**
 * Abstract Real Number Interface.
 * */
public interface IReal extends ICompValue<IReal>, Field<IReal> {
	/**
	 * Set this value.
	 * */
	public void set(double v);
	
	/**
	 * Set this value.
	 * */
	public void set(float v);
	
	/**
	 * To double value
	 * */
	public double toDouble();
	
	/**
	 * To float value
	 * */
	public float toFloat();
}
