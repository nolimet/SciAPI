package sciapi.api.mc.pos;

import sciapi.api.abstraction.pos.IAbsDifference;
import sciapi.api.abstraction.pos.IAbsPosition;
import sciapi.api.basis.storage.Pool;
import sciapi.api.basis.temporaries.TempUtil;
import sciapi.api.def.euclidian.EVecInt;
import sciapi.api.def.numerics.DInteger;
import sciapi.api.registry.TickTaskRegistry;

/**
 * Positions for World!
 * */
public class McWorldPos implements IAbsPosition<McWorldPos, EVecInt>{
	
	public int dimension;
	public EVecInt vec;
	
	private static TempUtil tu = new TempUtil(new McWorldPos());

	
	public McWorldPos(){
		dimension = 0;
		vec = new EVecInt(0, 0, 0);
	}
	
	public McWorldPos(int pdim, EVecInt pvec){
		dimension = pdim;
		vec.set(pvec);
	}


	@Override
	public McWorldPos getDiffPos(EVecInt diff) {
		McWorldPos w = this.getNew();
		w.dimension = this.dimension;
		w.vec.setadd(this.vec, diff);
		return w;
	}

	@Override
	public EVecInt getDifference(McWorldPos pos) {
		if(this.dimension != pos.dimension)
			return null;
		
		return pos.vec.sub(this.vec);
	}
	
	
	public static McWorldPos getTemp(){
		return (McWorldPos) tu.getTemp();
	}
	
	@Override
	public McWorldPos getTemporary() {
		return (McWorldPos) tu.getTemp();
	}

	@Override
	public void remove(McWorldPos tval) {
		tu.release(tval);
	}
	
	
	public McWorldPos set(int pdim, int x, int y, int z){
		dimension = pdim;
		vec.set(x, y, z);
		
		return this;
	}
	
	public McWorldPos set(int pdim, EVecInt v){
		dimension = pdim;
		
		if(v == null)
			vec = new EVecInt(0,0,0);
		else vec.set(v);
		
		return this;
	}
	
	
	@Override
	public McWorldPos getNew() {
		return new McWorldPos().set(dimension, vec);
	}

	@Override
	public McWorldPos set(McWorldPos par) {
		this.dimension = par.dimension;
		
		if(par.vec == null)
			this.vec = new EVecInt(0,0,0);
		else this.vec.set(par.vec);
		
		return this;
	}
	
	
	@Override
	public boolean equals(Object o){
		if(o instanceof McWorldPos){
			McWorldPos w = (McWorldPos)o;
			return dimension == w.dimension && vec.equals(w.vec);
		}
		
		return false;
	}
}
