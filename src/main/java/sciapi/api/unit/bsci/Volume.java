package sciapi.api.unit.bsci;

import sciapi.api.unit.Measurement;

public class Volume extends Measurement {
	
	public Volume(double pvalue){
		super(pvalue);
	}

	public Volume(double pvalue, String unitname) {
		super(pvalue, unitname);
	}

	@Override
	protected String getMeasurementName() {
		return "Volume";
	}

}
