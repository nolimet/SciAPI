package sciapi.api.basis.bunch.bunchreal;

/**
 * The type of the bunch.
 * Same bunchtype must have same type instance.
 * */
public interface IBunchRealType<S extends IBunchReal> {
	/**
	 * Gets the stack limit of this Bunch.
	 * */
    public double getStackLimit(S bunch);
}
