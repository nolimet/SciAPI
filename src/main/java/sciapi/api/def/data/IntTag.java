package sciapi.api.def.data;

import sciapi.api.basis.data.IDataTag;

public class IntTag implements IDataTag<IntTag> {
	
	int val;
	
	public IntTag(){
		val = 0;
	}
	
	public IntTag(int i){
		val = i;
	}

	@Override
	public IntTag getNew() {
		return new IntTag();
	}

	@Override
	public IntTag set(IntTag par) {
		val = par.val;
		return this;
	}
	
	/**
	 * Sets this IntTag with the parameter.
	 * */
	public void set(int pval){
		val = pval;
	}
	
	
	public int toInt(){
		return val;
	}
	
	
	@Override
	public int hashCode(){
		return val;
	}
	
	@Override
	public boolean equals(Object o){
		if(!(o instanceof IntTag))
			return false;
		return val == ((IntTag)o).toInt();
	}
}
