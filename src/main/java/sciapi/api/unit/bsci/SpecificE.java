package sciapi.api.unit.bsci;

import sciapi.api.unit.Measurement;

public class SpecificE extends Measurement {

	public SpecificE(double pvalue){
		super(pvalue);
	}

	public SpecificE(double pvalue, String unitname) {
		super(pvalue, unitname);
	}
	
	@Override
	protected String getMeasurementName() {
		return "Specific Energy";
	}

}
