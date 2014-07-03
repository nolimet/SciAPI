package sciapi.api.basis.storage;

import sciapi.api.abstraction.absutil.IInsBase;

/**
 * Pool for providing new objects without new Memory Allocation.
 * Objects cannot be removed from this pool.
 * */
public class IncPool<T extends IInsBase> {

	private IInsBase[] pool;
	
	private int inc;
	
	private int curindex;
	
	private T handle;
	
	public IncPool(T instance){
		this(20, 10, instance);
	}
	
	public IncPool(int initialSize, int increasement, T instance){
		pool = new IInsBase[initialSize];
		inc = increasement;
		handle = instance;
		
		for(int i = 0; i < initialSize; i++){
			pool[i] = handle.getNew();
		}
		
		curindex = 0;
	}
	
	/**
	 * get New instance of T from Pool.
	 * */
	public T get(){
		if(curindex < pool.length)
			return (T) pool[curindex++];

		extend(inc);
		
		return (T) pool[curindex++];
	}
	
	/**
	 * Resets this pool, with its memory space not changing.
	 * (Every element has to be used, or this may cause unpredictable side-effects.)
	 * */
	public void reset(){
		curindex = 0;
	}
	
	/**
	 * Extends this Pool by n.
	 * */
	protected void extend(int n){
		IInsBase[] old_pool = pool;
		pool = new IInsBase[old_pool.length + n];
		System.arraycopy(old_pool, 0, pool, 0, old_pool.length);
	   
		for (int i = old_pool.length; i < pool.length; i++)
			pool[i] = handle.getNew();
	}
	
}
