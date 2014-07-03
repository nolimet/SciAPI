package sciapi.api.unit.bsci;

import sciapi.api.unit.Measurement;

public class Angle extends Measurement {

	public Angle(double pvalue){
		super(pvalue);
	}

	public Angle(double pvalue, String unitname) {
		super(pvalue, unitname);
	}
	
	@Override
	protected String getMeasurementName() {
		return "Angle";
	}

}
