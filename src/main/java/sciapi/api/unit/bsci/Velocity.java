package sciapi.api.unit.bsci;

import sciapi.api.unit.Measurement;

public class Velocity extends Measurement {

	public Velocity(double pvalue){
		super(pvalue);
	}

	public Velocity(double pvalue, String unitname) {
		super(pvalue, unitname);
	}
	
	@Override
	protected String getMeasurementName() {
		return "Velocity";
	}

}
