package sciapi.api.def.data;

import java.util.*;

import sciapi.api.abstraction.absutil.IInsBase;
import sciapi.api.basis.data.IDataMap;
import sciapi.api.basis.data.IDataTag;

/**
 * DataMap which stacks data when it is indicated by existing tag.
 * gives List of data from specific tag.
 * Does not check if null or not.
 * */
public class DataStack implements IDataMap {
	
	Map<IDataTag, List> map = new HashMap();
	
	@Override
	public Object get(IDataTag tag) {
		return map.get(tag);
	}

	@Override
	public void set(IDataTag tag, Object o) {
		if(!exists(tag)){
			List list = new ArrayList();
			list.add(o);
			map.put(tag, list);
		}
		else map.put(tag, map.get(tag));
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
