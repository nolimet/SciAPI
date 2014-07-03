package sciapi.api.basis.temporaries;

import sciapi.api.abstraction.absutil.IInsBase;
import sciapi.api.basis.storage.Pool;

public class TempUtil {
	
	//Provides Temporary values.
	private ThreadLocal dt = new ThreadLocal();
	
	private IInsBase base;
	
	public TempUtil(IInsBase pbase){
		base = pbase;
	}
	
	public IInsBase getTemp()
	{
		Pool<IInsBase> d = (Pool<IInsBase>) dt.get();
		if (d == null)
		{
			d = new Pool<IInsBase>(5, 5, base.getNew());
			dt.set(d);
		}
		return d.get();
	}
	public void release(IInsBase r){
		Pool<IInsBase> d = (Pool<IInsBase>) dt.get();
		if (d == null)
			return;
		d.remove(r);
	}
	
}
