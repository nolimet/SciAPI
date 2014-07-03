package sciapi.api.basis.bunch.bunchreal;

import sciapi.api.basis.math.BMath;

public abstract class IdBunchReal<S extends IdBunchReal, T extends IBunchRealIdType> implements IBunchReal<S> {

	private int id;
	private double camount;
	
	public abstract IBunchRealIdManager getIdManager();
	
	public IdBunchReal(T type, double amount) {
		id = type.getId();
		camount = amount;
	}
	
	public IdBunchReal(int pid, double amount) {
		id = pid;
		camount = amount;
	}

	@Override
	public double getAmount() {
		return camount;
	}
	
	public int getId() {
		return id;
	}

	@Override
	public void setAmount(double amount) {
		camount = amount;
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
		double maxinc = getIdManager().getType(id).getStackLimit(this) - camount;
		
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
		if(this.id != ((IdBunchReal) in).getId())
			return;
			
		in.decrBunch(this.incrBunch(in.getAmount(), doPut), doPut);
	}

	@Override
	public boolean isBunchEqual(S bunch) {
		return (this.id == bunch.getId()) && BMath.isSame(this.camount, bunch.getAmount());
	}

	@Override
	public Object clone(){
		return this.getBunch(camount);
	}
}
