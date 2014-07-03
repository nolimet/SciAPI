package sciapi.api.basis.bunch.bunchreal;

public interface IBunchRealHandler<S extends IBunchReal> {
	
	/**
	 * Gets the bunch with the same type.
	 * @param amount the amount.
	 * @return the bunch with the amount.
	 * */
	public S getBunch(double amount);
	
    /**
     * Gets the Amount of the bunch in this handler.
     * */
    public double getAmount();
    
    /**
     * Decreases the amount of the bunch in this handler.
     * 
     * @param amount the decrement
     * @param doDecr if doDecr is set to false, this method only simulates the behavior.
     * @return the decreased amount.
     * */
    public double decrBunch(double amount, boolean doDecr);
    
    /**
     * Increases the amount of the bunch in this handler.
     * 
     * @param amount the increment
     * @param doIncr if doIncr is set to false, this method will only simulate the behavior.
     * @return the increased amount.
     * */
    public double incrBunch(double amount, boolean doIncr);
    
    
    /**
     * Pulls bunch out of the bunch in this handler.
     * @param amount to be pulled.
     * @param doPull if doIncr is set to false, this method will only simulate the behavior. 
     * @return Pulled bunch
     * */
    public S pullBunch(double amount, boolean doPull);
    
    /**
     * Puts bunch into the bunch in this handler.
     * @param in the bunch to be put. The amount will be automatically decreased
     * */
    public void putBunch(S in, boolean doPut);
}
