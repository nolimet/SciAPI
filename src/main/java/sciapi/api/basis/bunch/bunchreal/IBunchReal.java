package sciapi.api.basis.bunch.bunchreal;

import net.minecraft.item.ItemStack;

/**
 * Bunch which uses int for its amount.
 * */
public interface IBunchReal<S extends IBunchReal> extends IBunchRealHandler<S>, Cloneable {
	
	/**
	 * Sets the amount.
	 * */
	public void setAmount(double amount);
    
    /**
     * Checks if the bunch is equal to this bunch.
     * */
    public boolean isBunchEqual(S bunch);
}
