package abr.heatcraft.tile;

import sciapi.api.heat.def.DefHeatComponent;
import sciapi.api.unit.bsci.BSciConstants;
import sciapi.api.unit.bsci.HeatCapacity;

public class TileHeatBlock extends DefHeatComponent {
	
	public double befTemp, TempDiff = 0;
	
	public TileHeatBlock(){
		double tr = 500.0e+3;
		
		this.befTemp = this.Temp = BSciConstants.air_temp.toDouble();
		this.HeatCapacity = new HeatCapacity(850.39, "kcal/K").toDouble() * 0.5;
		this.isTransferable = new boolean[]{true, true, true, true, true, true};
		this.transferRate = new double[]{tr, tr, tr, tr, tr, tr};
	}
		
	@Override
	public void onHeatTransfer(double heat) {
		super.onHeatTransfer(heat);
	}
	
	
	@Override
    public void updateEntity(){
		if(!this.worldObj.isRemote)
			this.onHeatTransfer((-0.5) * (this.Temp - BSciConstants.air_temp.toDouble()));
		
		TempDiff = this.Temp - this.befTemp;
		this.befTemp = this.Temp;
    }

	
	public double smokingRate() {
		return Math.abs(TempDiff) / 20.0;
	}

	public boolean isHot() {
		return Temp > 1000.0;
	}
}
