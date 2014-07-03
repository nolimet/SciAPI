package sciapi.api.def.data;

import java.util.*;

import sciapi.api.abstraction.absutil.IInsBase;
import sciapi.api.basis.data.*;

/**
 * DataMap which acts like a Map.
 * Do not check the duplication or null.
 * */
public class DataSet implements IDataMap {
	
	public Map<IDataTag, Object> map = new HashMap();

	@Override
	public Object get(IDataTag tag) {
		return map.get(tag);
	}

	@Override
	public void set(IDataTag tag, Object o) {
		map.put(tag, o);
	}

	@Override
	public boolean exists(IDataTag tag) {
		return map.containsKey(tag);
	}

	@Override
	public Set<IDataTag> getAllTag() {
		return map.keySet();
	}

	@Override
	public Object[] getAll() {
		return map.values().toArray();
	}

}
