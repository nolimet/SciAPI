package sciapi.api.basis.init;

import sciapi.api.chem.ChemInit;
import sciapi.api.heat.HeatInit;
import sciapi.api.mc.init.McSidedInit;
import sciapi.api.unit.UnitDictionary;
import sciapi.api.unit.bsci.BSciUnitInit;

public class InitManager {
	public static void Init(){
		UnitDictionary.instance();
		BSciUnitInit.Init();
		ChemInit.Init();
		HeatInit.Init();
	}
}
