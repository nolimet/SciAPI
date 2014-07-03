package sciapi.api.unit.bsci;

import sciapi.api.unit.Measurement;

public class Temperature extends Measurement {

	public Temperature(double pvalue){
		super(pvalue);
	}

	public Temperature(double pvalue, String unitname) {
		super(pvalue, unitname);
	}
	
	
	@Override
	protected String getMeasurementName() {
		return "Temperature";
	}

}
