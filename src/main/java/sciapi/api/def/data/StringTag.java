package sciapi.api.def.data;

import sciapi.api.basis.data.IDataTag;

public class StringTag implements IDataTag<StringTag> {
	
	public String str;
	
	public StringTag(){
		str = "";
	}
	
	public StringTag(String pstr){
		str = pstr;
	}

	@Override
	public StringTag getNew() {
		return new StringTag();
	}

	@Override
	public StringTag set(StringTag par) {
		str = par.str;
		return this;
	}
	
	/**
	 * Sets this StringTag with the parameter.
	 * */
	public void set(String pstr){
		str = pstr;
	}
	
	
	@Override
	public String toString(){
		return str;
	}
	
	@Override
	public int hashCode(){
		return str.hashCode();
	}
	
	@Override
	public boolean equals(Object o){
		return str.equals(o.toString());
	}
}
