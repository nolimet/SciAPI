package sciapi.api.unit.bsci;

import sciapi.api.unit.Measurement;

/**
 * Angular Momentum.
 * */
public class Momentum extends Measurement {

	public Momentum(double pvalue){
		super(pvalue);
	}

	public Momentum(double pvalue, String unitname) {
		super(pvalue, unitname);
	}
	
	@Override
	protected String getMeasurementName() {
		return "Momentum";
	}

}
