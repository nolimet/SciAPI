package sciapi.api.abstraction.numerics;

public interface ICompValue<T extends ICompValue> extends IAbsValue<T>, Comparable<T> {
	/**
	 * Check if it is same value.
	 * @see Object#equals(Object)
	 * */
	public boolean equals(Object o);
}
