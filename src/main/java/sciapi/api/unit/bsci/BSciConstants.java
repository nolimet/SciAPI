package sciapi.api.unit.bsci;

import net.minecraftforge.fluids.FluidRegistry;

public class BSciConstants {
	
	/**
	 * Speed of Light
	 * */
	public static Velocity c;
	
	/**
	 * Planck Constant
	 * */
	public static Momentum h;
	
	
	/**
	 * Atmosphere Pressure
	 * */
	public static Pressure atm;
	
	/**
	 * Air Temperature, or Normal Temperature
	 * */
	public static Temperature air_temp;
	
	
	public static void Init(){
		c = new Velocity(1, "c");
		h = new Momentum(1, "h");
		atm = new Pressure(1, "atm");
		air_temp = new Temperature(FluidRegistry.WATER.getTemperature());
	}
}
