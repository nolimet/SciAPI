package sciapi.api.basis.bunch.bunchreal;

import sciapi.api.abstraction.absutil.IInsBase;
import sciapi.api.basis.math.BMath;

/**
 * Basic Bunch Class
 * */
public abstract class BunchReal<S extends BunchReal, T extends IBunchRealType> implements IBunchReal<S> {

	private T type;
	private double camount;
	
	public BunchReal(T ptype, double am){
		type = ptype;
		camount = am;
	}

	@Override
	public double getAmount() {
		return camount;
	}
	

	@Override
	public void setAmount(double amount) {
		camount = amount;
	}
	
	public T getType() {
		return type;
	}

	
	@Override
	public double decrBunch(double amount, boolean doDecr) {
		if(camount < amount) {
			if(doDecr)
				camount = 0;
			return camount;
		}
		
		if(doDecr)
			camount -= amount;
		return amount;
	}

	@Override
	public double incrBunch(double amount, boolean doIncr) {
		double maxinc = type.getStackLimit(this) - camount;
		
		if(maxinc < amount) {
			if(doIncr)
				camount += maxinc;
			return maxinc;
		}
		
		if(doIncr)
			camount += amount;
		return amount;
	}

	@Override
	public S pullBunch(double amount, boolean doPull) {
		return this.getBunch(decrBunch(amount, doPull));
	}

	@Override
	public void putBunch(S in, boolean doPut) {
		if(this.type != in.getType())
			return;
			
		in.decrBunch(this.incrBunch(in.getAmount(), doPut), doPut);
	}

	@Override
	public boolean isBunchEqual(S bunch) {
		return (this.type == bunch.getType()) && BMath.isSame(this.camount, bunch.getAmount());
	}
	
	
	@Override
	public Object clone(){
		return this.getBunch(camount);
	}

}
