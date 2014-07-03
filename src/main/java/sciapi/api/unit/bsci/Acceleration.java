package sciapi.api.unit.bsci;

import sciapi.api.unit.Measurement;

public class Acceleration extends Measurement {

	public Acceleration(double pvalue){
		super(pvalue);
	}

	public Acceleration(double pvalue, String unitname) {
		super(pvalue, unitname);
	}
	
	@Override
	protected String getMeasurementName() {
		return "Acceleration";
	}

}
