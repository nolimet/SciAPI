package sciapi.api.heat;

import java.util.Map;

import sciapi.api.abstraction.pos.*;

/**
 * Interface for Heat Component.
 * 
 * Heat will be calculated 5 times a tick.
 * */
public interface IHeatComponent extends IPosObject {
	/**
	 * get Temperature of this Component.
	 * 
	 * @return Temperature
	 * */
	public double getTemperature();

	/**
	 * Check if it is heat-transferable via direction.
	 * @param dir the direction.
	 * @return true if this component is heat-transferable via this direction.
	 * */
	public boolean isHeatTransferable(IDirection dir);
	
	/**
	 * Get Heat Transfer Rate for the Direction.
	 * @param dir the direction.
	 * @return Heat Transfer Rate.
	 * */
	public double getHeatTransferRate(IDirection dir);

	
	
	/**
	 * get External Transfer Position List,
	 * with amount of Heat provided by this component.
	 * */
	public Map<IAbsPosition, Double> getExternalTransferList();
	
	/**
	 * Called when Heat is Transferred.
	 * (Heat is calculated once on Tick Start,
	 * so 20 calculations are done per second)
	 * 
	 * @param heat amount of heat transferred into this component.
	 * if heat had transferred out of this component, heat is lesser than 0.
	 * */
	public void onHeatTransfer(double heat);
	
}
