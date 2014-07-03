package sciapi.api.basis.bunch.bunchint;

public abstract class IdBunchInt<S extends IdBunchInt> implements IBunchInt<S> {

	private int id, camount;
	
	public abstract IBunchIntIdManager getIdManager();
	
	public IdBunchInt(IBunchIntIdType type, int amount) {
		id = type.getId();
		camount = amount;
	}
	
	public IdBunchInt(int pid, int amount) {
		id = pid;
		camount = amount;
	}

	@Override
	public int getAmount() {
		return camount;
	}
	
	public int getId() {
		return id;
	}

	@Override
	public void setAmount(int amount) {
		camount = amount;
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
		int maxinc = getIdManager().getType(id).getStackLimit(this) - camount;
		
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
		if(this.id != in.getId())
			return;
			
		in.decrBunch(this.incrBunch(in.getAmount(), doPut), doPut);
	}

	@Override
	public boolean isBunchEqual(S bunch) {
		return (this.id == bunch.getId()) && this.camount == bunch.getAmount();
	}

	@Override
	public Object clone(){
		return this.getBunch(camount);
	}
}
