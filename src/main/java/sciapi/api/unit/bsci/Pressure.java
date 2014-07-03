package sciapi.api.unit.bsci;

import sciapi.api.unit.Measurement;

public class Pressure extends Measurement {

	public Pressure(double pvalue){
		super(pvalue);
	}

	public Pressure(double pvalue, String unitname) {
		super(pvalue, unitname);
	}
	
	@Override
	protected String getMeasurementName() {
		return "Pressure";
	}

}
