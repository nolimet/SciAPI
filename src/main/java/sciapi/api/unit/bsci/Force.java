package sciapi.api.unit.bsci;

import sciapi.api.unit.Measurement;

public class Force extends Measurement {

	public Force(double pvalue){
		super(pvalue);
	}

	public Force(double pvalue, String unitname) {
		super(pvalue, unitname);
	}
	
	@Override
	protected String getMeasurementName() {
		return "Force";
	}

}
