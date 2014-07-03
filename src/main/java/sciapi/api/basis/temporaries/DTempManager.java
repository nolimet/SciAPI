package sciapi.api.basis.temporaries;

import java.util.*;

import sciapi.api.abstraction.absutil.IInsBase;
import sciapi.api.basis.storage.Pool;
import sciapi.api.def.numerics.*;

/**
 * Provide Temporary values for calculation.
 * */
public class DTempManager {
	//Provides Temporary DReal values.
	private static ThreadLocal dr = new ThreadLocal();
	
	public static DReal getDReal()
	{
		Pool<DReal> d = (Pool<DReal>) dr.get();
		if (d == null)
		{
			d = new Pool<DReal>(5, 5, new DReal());
			dr.set(d);
		}
		return d.get();
	}
	public static void release(DReal r){
		Pool<DReal> d = (Pool<DReal>) dr.get();
		if (d == null)
			return;
		d.remove(r);
	}
	
	//Provides Temporary DInteger values.
	private static ThreadLocal di = new ThreadLocal();
	
	public static DInteger getDInteger()
	{
		Pool<DInteger> d = (Pool<DInteger>) di.get();
		if (d == null)
		{
			d = new Pool<DInteger>(5, 5, new DInteger());
			di.set(d);
		}
		return d.get();
	}
	public static void release(DInteger r){
		Pool<DInteger> d = (Pool<DInteger>) di.get();
		if (d == null)
			return;
		d.remove(r);
	}
}
