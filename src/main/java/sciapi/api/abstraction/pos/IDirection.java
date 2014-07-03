package sciapi.api.abstraction.pos;

public interface IDirection <D extends IAbsDifference> {
		
	/**
	 * get nth IDirection. this would not be affected by the callee instance.
	 * 
	 * @param n index of the Direction
	 * @return nth Direction.
	 * */
	public IDirection<D> getDirection(int n);
	
	/**
	 * get Opposite Direction for this Direction.
	 * */
	public IDirection<D> getOpposite();
	
	/**
	 * get limit of index value.
	 * */
	public int getindexlim();
	
	/**
	 * get index of this Direction.
	 * */
	public int index();
	
	/**
	 * Convert this Direction to Difference.
	 * */
	public D toDifference();
}
