package sciapi.api.basis.storage;

import sciapi.api.abstraction.absutil.IInsBase;

/**
 * Pool for providing new objects without new Memory Allocation.
 * Objects can be removed from this pool.
 * */
public class Pool<T extends IInsBase> {
	
	private IInsBase[] pool;
	private boolean[] inUse;
	
	private int inc;
	
	private T handle;
	
	public Pool(T instance){
		this(10, 5, instance);
	}
	
	public Pool(int initialSize, int increasement, T instance){
		pool = new IInsBase[initialSize];
		inUse = new boolean[initialSize];
		inc = increasement;
		handle = instance;
		
		for(int i = 0; i < initialSize; i++){
			pool[i] = handle.getNew();
			inUse[i] = false;
		}
	}
	
	/**
	 * get New instance of T from Pool.
	 * */
	public T get(){
		for (int i = inUse.length-1; i >= 0; i--)
			if (!inUse[i])
	        {
				inUse[i] = true;
				return (T)pool[i];
	        }

		extend(inc);
	   
		inUse[pool.length-1] = true;
		return (T) pool[pool.length-1];
	}
	
	/**
	 * Remove this instance of T from Pool.
	 * */
	public void remove(T v){
		for (int i = inUse.length-1; i >= 0; i--)
			if (pool[i] == v)
			{
				inUse[i] = false;
				return;
			}
		throw new RuntimeException("Vector was not obtained from the pool: " + v);
	}
	
	/**
	 * Extends this Pool by n.
	 * */
	public void extend(int n){
		boolean[] old_inUse = inUse;
		inUse = new boolean[old_inUse.length + n];
		System.arraycopy(old_inUse, 0, inUse, 0, old_inUse.length);
		
		IInsBase[] old_pool = pool;
		pool = new IInsBase[old_pool.length + n];
		System.arraycopy(old_pool, 0, pool, 0, old_pool.length);
	   
		for (int i = old_pool.length; i < pool.length; i++)
		{
			pool[i] = handle.getNew();
			inUse[i] = false;
		}
	}
}
