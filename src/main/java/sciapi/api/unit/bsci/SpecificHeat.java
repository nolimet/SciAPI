package sciapi.api.unit.bsci;

import sciapi.api.unit.Measurement;

public class SpecificHeat extends Measurement {

	public SpecificHeat(double pvalue){
		super(pvalue);
	}

	public SpecificHeat(double pvalue, String unitname) {
		super(pvalue, unitname);
	}
	
	@Override
	protected String getMeasurementName() {
		return "Specific Heat";
	}

}
