package sciapi.api.mc.unit;

import sciapi.api.unit.*;

public class McUnitInit {
	public static void Init(){
		UnitDictionary unitdict = UnitDictionary.instance();
		
		unitdict.addUnit("Time", "tick", 0.05);
		unitdict.addUnit("Time", "mcday", 1200);
		
		unitdict.addUnit("Volume", "mB", 1.0e-3);
	}
}
