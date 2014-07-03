package sciapi.api.unit.bsci;

import sciapi.api.unit.Measurement;

public class Density extends Measurement {

	public Density(double pvalue){
		super(pvalue);
	}

	public Density(double pvalue, String unitname) {
		super(pvalue, unitname);
	}
	
	@Override
	protected String getMeasurementName() {
		return "Density";
	}

}
