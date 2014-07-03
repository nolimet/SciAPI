package sciapi.api.basis.bunch.bunchint;

import net.minecraft.item.ItemStack;

/**
 * Bunch which uses int for its amount.
 * */
public interface IBunchInt<S extends IBunchInt> extends IBunchIntHandler<S>, Cloneable {
	
	/**
	 * Sets the amount.
	 * */
	public void setAmount(int amount);
    
    /**
     * Checks if the bunch is equal to this bunch.
     * */
    public boolean isBunchEqual(S bunch);
}
