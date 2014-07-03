package sciapi.api.unit.bsci;

import sciapi.api.unit.Measurement;

public class EDensity extends Measurement {

	public EDensity(double pvalue){
		super(pvalue);
	}

	public EDensity(double pvalue, String unitname) {
		super(pvalue, unitname);
	}
	
	@Override
	protected String getMeasurementName() {
		return "Energy Density";
	}

}
