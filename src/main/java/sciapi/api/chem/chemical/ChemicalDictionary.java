package sciapi.api.chem.chemical;

import java.util.*;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry.FluidRegisterEvent;
import sciapi.api.basis.bunch.bunchreal.IBunchRealIdManager;
import sciapi.api.basis.bunch.bunchreal.IBunchRealIdType;

public class ChemicalDictionary implements IBunchRealIdManager<Chemical> {
	private static ChemicalDictionary instance = new ChemicalDictionary();
	
	private BiMap<String, Integer> chemicalIDs = HashBiMap.create();
	private Map<String, Chemical> chemicals = new HashMap();
	private int maxID = 0;
	
	public static ChemicalDictionary instance(){
		return instance;
	}
	
	public ChemicalDictionary(){
		ChemicalDictInit.Init(this);
	}
	
    /**
     * Register a new Chemical. If a Chemical with the same name already exists, registration is denied.
     *
     * @param Chemical The Chemical to register.
     * @return True if the Chemical was successfully registered; false if there is a name clash.
     */
    public boolean registerChemical(Chemical chemical)
    {
        if (chemicalIDs.containsKey(chemical.getName()))
            return false;
        
        chemicals.put(chemical.getName(), chemical);
        chemicalIDs.put(chemical.getStrId(), ++maxID);
        chemical.giveId(maxID);

        return true;
    }
    
    /**
     * For Sync.
     * */
    @Deprecated
    public void initIdMap(List<String> strs){
    	maxID = 0;
    	
    	for(String str : strs) {
    		chemicalIDs.put(str, ++maxID);
    		getChemical(str).giveId(maxID);
    	}
    }

	@Override
	public Chemical getType(int id) {
		return getChemical(id);
	}
	
	
	public String getStrId(int id) {
		return chemicalIDs.inverse().get(id);
	}
	
	public Chemical getChemical(int id) {
		return getChemical(getStrId(id));
	}
	
	public Chemical getChemical(String strid) {
		return chemicals.get(strid);
	}
	
	public int getId(String strId) {
		return chemicalIDs.get(strId);
	}
}
