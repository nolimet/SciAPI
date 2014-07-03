package sciapi.api.unit.bsci;

import sciapi.api.unit.Measurement;

public class Power extends Measurement {

	public Power(double pvalue){
		super(pvalue);
	}

	public Power(double pvalue, String unitname) {
		super(pvalue, unitname);
	}
	
	@Override
	protected String getMeasurementName() {
		return "Power";
	}

}
