package sciapi.api.unit.bsci;

import sciapi.api.unit.Measurement;

public class Length extends Measurement {
	
	public Length(double pvalue){
		super(pvalue);
	}

	public Length(double pvalue, String unitname) {
		super(pvalue, unitname);
	}

	@Override
	protected String getMeasurementName() {
		return "Length";
	}

}
