package sciapi.api.chem;

import sciapi.api.chem.elem.ElementDictionary;

public class ChemInit {
	public static void Init(){
		ElementDictionary.instance();
	}
}
