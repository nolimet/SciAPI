package sciapi.api.unit.bsci;

import sciapi.api.unit.Measurement;

public class ElCharge extends Measurement {

	public ElCharge(double pvalue){
		super(pvalue);
	}

	public ElCharge(double pvalue, String unitname) {
		super(pvalue, unitname);
	}
	
	@Override
	protected String getMeasurementName() {
		return "ElCharge";
	}

}
