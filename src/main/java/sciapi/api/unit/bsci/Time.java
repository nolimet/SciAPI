package sciapi.api.unit.bsci;

import sciapi.api.unit.Measurement;

public class Time extends Measurement {
	
	public Time(double pvalue){
		super(pvalue);
	}

	public Time(double pvalue, String unitname) {
		super(pvalue, unitname);
	}

	@Override
	protected String getMeasurementName() {
		return "Time";
	}

}
