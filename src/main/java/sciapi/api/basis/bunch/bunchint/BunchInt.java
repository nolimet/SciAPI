package sciapi.api.basis.bunch.bunchint;


/**
 * Basic Bunch Class
 * */
public abstract class BunchInt<S extends BunchInt, T extends IBunchIntType> implements IBunchInt<S> {

	private T type;
	private int camount;
	
	public BunchInt(T ptype, int am){
		type = ptype;
		camount = am;
	}
	
	@Override
	public abstract S getBunch(int amount);

	@Override
	public int getAmount() {
		return camount;
	}
	

	@Override
	public void setAmount(int amount) {
		camount = amount;
	}
	
	public T getType() {
		return type;
	}

	
	@Override
	public int decrBunch(int amount, boolean doDecr) {
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
	public int incrBunch(int amount, boolean doIncr) {
		int maxinc = type.getStackLimit(this) - camount;
		
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
	public S pullBunch(int amount, boolean doPull) {
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
		return (this.type == bunch.getType()) && this.camount == bunch.getAmount();
	}
	
	
	@Override
	public Object clone(){
		return this.getBunch(camount);
	}

}
