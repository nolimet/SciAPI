package sciapi.api.chem.elem;


public class ElementSet {
	
	private String name;
	private Element[] elems;
	
	public ElementSet(String pname, int maxSize){
		name = pname;
		elems = new Element[maxSize];
	}
	
	public Element getElement(int i){
		if(elems[i-1] == null){
			elems[i-1] = new Element(this);
			elems[i-1].setNumber(i);
		}
		return elems[i-1];
	}
	
	public String getName(){
		return name;
	}
}
