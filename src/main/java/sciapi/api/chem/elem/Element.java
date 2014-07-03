package sciapi.api.chem.elem;

import java.util.*;

import sciapi.api.basis.bunch.bunchint.IBunchInt;
import sciapi.api.basis.bunch.bunchint.IBunchIntType;

public class Element {
	
	private ElementSet parelemset;
	private String name;
	
	/**
	 * Chemical Symbol of this Element.
	 * (ex> The Symbol of Copper is "Cu")
	 * */
	private String symbol;
	private int number;
	
	private Map<Integer, Isotope> isotopes = new HashMap();
	
	/**
	 * Molar Mass of this Element.
	 * */
	private float molar_mass = 0.0f;
	
	public Element(ElementSet pelemset){
		parelemset = pelemset;
	}
	
	
	/**
	 * Sets (Unlocalized) name of this element.
	 * */
	public Element setName(String pname){
		this.name = pname;
		return this;
	}
	
	public Element setSymbol(String psymbol){
		this.symbol = psymbol;
		return this;
	}
	
	public Element setNumber(int pnumber){
		number = pnumber;
		return this;
	}
	
	
	public String getName(){
		return this.name;
	}
	
	public String getSymbol(){
		return this.symbol;
	}
	
	public int getNumber(){
		return this.number;
	}
	
	
	private float rest = 1.0f;
	
	/**
	 * Sets Isotope for the mass as the rate.
	 * @param mass the mass
	 * @param rate the natural rate the isotope appears.
	 * @return <code>this</code>
	 * */
	public Element setIsotope(int mass, float rate){
		Integer m = mass;
		if(isotopes.containsKey(m)){
			return this;
		}
		else{
			if(rest < rate)
				return null;
			
			Isotope iso = new Isotope(this, mass, rate);
			iso.setName(this.name + "-" + mass);
			iso.setSymbol(this.symbol + "-" + mass);
			rest -= rate;
			molar_mass += rate * mass;
			isotopes.put(m, iso);
			return this;
		}
	}
	
	/**
	 * Sets Isotope for the mass as the rate with custom name.
	 * @param name the custom name of the isotope.
	 * @param mass the mass
	 * @param rate the natural rate the isotope appears.
	 * @return <code>this</code>
	 * */
	public Element setIsotope(String name, String symbol, int mass, float rate){
		Integer m = mass;
		if(isotopes.containsKey(m)){
			return this;
		}
		else{
			if(rest < rate)
				return null;
			
			Isotope iso = new Isotope(this, mass, rate);
			iso.setName(name);
			iso.setSymbol(symbol);
			rest -= rate;
			molar_mass += rate * mass;
			isotopes.put(m, iso);
			return this;
		}
	}
	
	/**
	 * Sets Isotope for the mass as the rest of rate.
	 * Call this method when adding last isotope.
	 * @param mass the mass
	 * @return <code>this</code>
	 * */
	public Element setIsotopeasRest(int mass){
		Integer m = mass;
		if(isotopes.containsKey(m)){
			return this;
		}
		else{
			Isotope iso = new Isotope(this, mass, rest);
			iso.setName(this.name + "-" + mass);
			iso.setSymbol(this.symbol + "-" + mass);
			molar_mass += rest * mass;
			isotopes.put(m, iso);
			return this;
		}
	}
	
	/**
	 * Sets Isotope for the mass as the rest of rate with custom name.
	 * Call this method when adding last isotope.
	 * @param name the custom name of the isotope.
	 * @param mass the mass
	 * @return <code>this</code>
	 * */
	public Element setIsotopeasRest(String name, String symbol, int mass){
		Integer m = mass;
		if(isotopes.containsKey(m)){
			return this;
		}
		else{
			Isotope iso = new Isotope(this, mass, rest);
			iso.setName(name);
			iso.setSymbol(symbol);
			molar_mass += rest * mass;
			isotopes.put(m, iso);
			return this;
		}
	}
	
	
	public Isotope getIsotope(int mass){
		return isotopes.get(mass);
	}

	public Collection<Isotope> getIsotopeList(){
		return isotopes.values();
	}

}
