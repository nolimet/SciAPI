package sciapi.api.chem.chemical;

import sciapi.api.basis.bunch.bunchreal.IBunchReal;
import sciapi.api.basis.bunch.bunchreal.IBunchRealIdManager;
import sciapi.api.basis.bunch.bunchreal.IBunchRealIdType;
import sciapi.api.basis.bunch.bunchreal.IdBunchReal;

public class ChemicalStack extends IdBunchReal<ChemicalStack, Chemical> {

	public ChemicalStack(int id, double amount) {
		super(id, amount);
	}
	
	public ChemicalStack(Chemical type, double amount) {
		super(type, amount);
	}

	@Override
	public ChemicalStack getBunch(double amount) {
		return new ChemicalStack(this.getId(), amount);
	}

	@Override
	public IBunchRealIdManager<Chemical> getIdManager() {
		return ChemicalDictionary.instance();
	}

}
