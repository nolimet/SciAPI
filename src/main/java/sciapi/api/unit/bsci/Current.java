package sciapi.api.unit.bsci;

import sciapi.api.unit.Measurement;

public class Current extends Measurement {

	public Current(double pvalue){
		super(pvalue);
	}

	public Current(double pvalue, String unitname) {
		super(pvalue, unitname);
	}
	
	@Override
	protected String getMeasurementName() {
		return "Current";
	}

}
