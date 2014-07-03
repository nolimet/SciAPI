package sciapi.api.unit.bsci;

import sciapi.api.unit.Measurement;

public class Energy extends Measurement {

	public Energy(double pvalue){
		super(pvalue);
	}

	public Energy(double pvalue, String unitname) {
		super(pvalue, unitname);
	}
	
	@Override
	protected String getMeasurementName() {
		return "Energy";
	}

}
