package sciapi.api.basis.data;

import sciapi.api.abstraction.absutil.IInsBase;

/**
 * Data Tag for identifying specific data.
 * */
public interface IDataTag<T extends IDataTag> extends IInsBase<T> {
	
	@Override
	public int hashCode();
	
	@Override
	public boolean equals(Object o);
}
