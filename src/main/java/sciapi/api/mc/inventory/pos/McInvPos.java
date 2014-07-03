package sciapi.api.mc.inventory.pos;

import net.minecraft.item.ItemStack;
import sciapi.api.abstraction.pos.*;
import sciapi.api.basis.temporaries.TempUtil;
import sciapi.api.def.euclidian.EVecInt;
import sciapi.api.mc.pos.McWorldPos;

/**
 * 2D Position for Inventory.
 * */
public class McInvPos implements IAbsPosition<McInvPos, EVecInt> {
	
	public McInvWorld iworld;
	public EVecInt vec = new EVecInt(0, 0);
	
	public static TempUtil tu = new TempUtil(new McInvPos());
	
	public McInvPos() { }
	
	public McInvPos(McInvWorld piworld, EVecInt ppos){
		iworld = piworld;
		vec.set(ppos);
	}

	@Override
	public McInvPos getTemporary() {
		return (McInvPos) tu.getTemp();
	}

	@Override
	public void remove(McInvPos tval) {
		tu.release(tval);
	}

	@Override
	public McInvPos getNew() {
		return new McInvPos().set(iworld, new EVecInt(0,0));
	}
	
	public McInvPos set(McInvWorld pworld, EVecInt v){
		iworld = pworld;
		
		if(v == null)
			vec = new EVecInt(0,0);
		else if(vec == null){
			vec = new EVecInt(0,0);
			vec.set(v);
		}
		else vec.set(v);
		
		return this;
	}

	@Override
	public McInvPos set(McInvPos par) {
		this.iworld = par.iworld;
		this.vec.set(par.vec);
		
		return this;
	}

	@Override
	public McInvPos getDiffPos(EVecInt diff) {
		McInvPos w = this.getNew();
		w.iworld = this.iworld;
		w.vec.setadd(this.vec, diff);
		return w;
	}

	@Override
	public EVecInt getDifference(McInvPos pos) {
		if(!this.iworld.equals(pos.iworld))
			return null;
		
		return pos.vec.sub(this.vec);
	}
	
	
	public ItemStack getItemStack(){
		return iworld.getItemStack(vec);
	}
	
	
	@Override
	public boolean equals(Object o){
		if(o instanceof McInvPos){
			McInvPos w = (McInvPos)o;
			return iworld.equals(w.iworld) && vec.equals(w.vec);
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		return iworld.toEntry(vec);
	}
}
