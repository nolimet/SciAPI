package sciapi.api.chem.elem;


public class Isotope implements Comparable<Isotope>{
	
	private Element parElement;
	private String name;
	private String symbol;
	
	/**
	 * Rate of existence of this Isotope.
	 * */
	private float rate;
	
	/**
	 * Unit Mass of this Isotope.
	 * */
	private int mass;
	
	public Isotope(Element pelement, int pmass, float prate){
		parElement = pelement;
		mass = pmass;
		rate = prate;
	}
	
	public Isotope setName(String pname){
		name = pname;
		return this;
	}
	
	public Isotope setSymbol(String psymbol){
		symbol = psymbol;
		return this;
	}
	
	public String getName(){
		return name;
	}
	
	public String getSymbol(){
		return symbol;
	}
	
	public float getRate(){
		return rate;
	}
	
	public int getMass(){
		return mass;
	}

	@Override
	public int compareTo(Isotope o) {
		if(o.mass < this.mass)
			return 1;
		else if(o.mass > this.mass)
			return -1;
		else return 0;
	}

}
