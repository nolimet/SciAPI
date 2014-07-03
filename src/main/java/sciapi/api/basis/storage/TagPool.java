package sciapi.api.basis.storage;

import java.util.*;

import sciapi.api.abstraction.absutil.IInsBase;
import sciapi.api.basis.data.*;

/**
 * Pool for providing new objects without new Memory Allocation.
 * Can get value using the {@link IDataTag DataTag}.
 * Objects cannot be removed from this pool.
 * */
public class TagPool implements IDataMap {
	
	private IInsBase base;
	private Map<IDataTag, IInsBase> map;
	
	private IncPool<IDataTag> p;
	
	public TagPool(IInsBase instance){
		this(20, 10, instance);
	}
	
	public TagPool(int initialSize, int increasement, IInsBase instance){
		base = instance;
		
		p = new IncPool(initialSize, increasement, instance);
	}

	/**
	 * Gets the value using the data tag. </p>
	 * If there is no value indicated by the tag,
	 * then get a new value from pool and set the tag indicates the value.
	 * @param tag the data tag.
	 * @return value indicated by the tag.
	 * */
	@Override
	public Object get(IDataTag tag) {
		if(map.containsKey(tag))
			return map.get(tag);
		else{
			return map.put(tag, p.get());
		}
	}
	
	@Override
	public boolean exists(IDataTag tag){
		return map.containsKey(tag);
	}
	
	/**
	 * Resets all the contents from the map.
	 * */
	public void reset(){
		map.clear();
		p.reset();
	}

	/**
	 * Unused. use get(tag).set() or something.
	 * */
	@Deprecated
	@Override
	public void set(IDataTag tag, Object o) {	}

	
	@Override
	public Set<IDataTag> getAllTag() {
		return map.keySet();
	}

	@Override
	public Object[] getAll() {
		return map.values().toArray();
	}

}
