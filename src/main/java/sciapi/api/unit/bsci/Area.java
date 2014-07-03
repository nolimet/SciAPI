package sciapi.api.unit.bsci;

import sciapi.api.unit.Measurement;

public class Area extends Measurement {
	
	public Area(double pvalue){
		super(pvalue);
	}

	public Area(double pvalue, String unitname) {
		super(pvalue, unitname);
	}

	@Override
	protected String getMeasurementName() {
		return "Area";
	}

}
