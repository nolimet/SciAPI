package sciapi.api.unit.bsci;

import sciapi.api.unit.Measurement;

public class Voltage extends Measurement {

	public Voltage(double pvalue){
		super(pvalue);
	}

	public Voltage(double pvalue, String unitname) {
		super(pvalue, unitname);
	}
	
	@Override
	protected String getMeasurementName() {
		return "Voltage";
	}

}
