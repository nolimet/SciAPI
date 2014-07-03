package sciapi.api.chem.elem;

import java.util.*;

public class ElementDictionary {
	
	public static String chemical = "Chemical";
	
	private static ElementDictionary instance = new ElementDictionary();
	
	private int maxID = 0;
	public Map<String, Integer> elemsetid = new HashMap();
	public List<ElementSet> elemsets = new ArrayList();
	
	public static ElementDictionary instance(){
		return instance;
	}
	
	public ElementDictionary(){
		ElementDictInit.Init(this);
	}
	
	/**
	 * Creates new Element Set.
	 * @param name name of the Element Set.
	 * @param maxSize maximum size of the Element Set, which means the limit of element number.
	 * @return created ElementSet.
	 * */
	public ElementSet newElementSet(String name, int maxSize){
		elemsetid.put(name, maxID);
		elemsets.add(new ElementSet(name, maxSize));
		return elemsets.get(maxID++);
	}
	
	/**
	 * Gets the Element Set with the name.
	 * @param name name of the Element Set
	 * @return the Element Set indicated by the name.
	 * */
	public ElementSet getElementSet(String name){
		return elemsets.get(elemsetid.get(name));
	}
}
