package sciapi.api.unit.bsci;

import sciapi.api.unit.Measurement;

public class HeatCapacity extends Measurement {
	
	public HeatCapacity(double pvalue){
		super(pvalue);
	}

	public HeatCapacity(double pvalue, String unitname) {
		super(pvalue, unitname);
	}

	@Override
	protected String getMeasurementName() {
		return "Heat Capacity";
	}

}
