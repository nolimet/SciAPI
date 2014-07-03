package sciapi.api.unit.bsci;

import sciapi.api.unit.Measurement;

public class Frequency extends Measurement {

	public Frequency(double pvalue){
		super(pvalue);
	}

	public Frequency(double pvalue, String unitname) {
		super(pvalue, unitname);
	}
	
	@Override
	protected String getMeasurementName() {
		return "Frequency";
	}

}
