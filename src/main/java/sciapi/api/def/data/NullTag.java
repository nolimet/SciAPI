package sciapi.api.def.data;

import sciapi.api.basis.data.IDataTag;

/**
 * Tag which does not contain any information.
 * */
public class NullTag implements IDataTag<NullTag> {

	@Override
	public NullTag getNew() {
		return new NullTag();
	}

	@Override
	public NullTag set(NullTag par) {
		return this;
	}
	
	@Override
	public int hashCode(){
		return 0;
	}
	
	@Override
	public boolean equals(Object o){
		return (o instanceof NullTag);
	}
}
