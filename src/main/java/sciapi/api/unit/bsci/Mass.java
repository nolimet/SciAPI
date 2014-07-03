package sciapi.api.unit.bsci;

import sciapi.api.unit.Measurement;

public class Mass extends Measurement {

	public Mass(double pvalue){
		super(pvalue);
	}

	public Mass(double pvalue, String unitname) {
		super(pvalue, unitname);
	}
	
	@Override
	protected String getMeasurementName() {
		return "Mass";
	}

}
