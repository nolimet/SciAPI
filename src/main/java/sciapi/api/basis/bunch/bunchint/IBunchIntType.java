package sciapi.api.basis.bunch.bunchint;

/**
 * The type of the bunch.
 * Same bunchtype must have same type instance.
 * */
public interface IBunchIntType<S extends IBunchInt> {
	/**
	 * Gets the stack limit of this Bunch.
	 * */
    public int getStackLimit(S bunch);
}
