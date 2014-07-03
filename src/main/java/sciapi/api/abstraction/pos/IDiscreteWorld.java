package sciapi.api.abstraction.pos;

/**
 * Interface for Discrete World which only contains discrete positions.
 * */
public interface IDiscreteWorld<P extends IAbsPosition, D extends IAbsDifference> extends IWorld{
	/**
	 * get Object on specific Position from this World.
	 * */
	public IPosObject getObjonPos(P pos);
	
	/**
	 * get Possible Directions from this World.
	 * */
	public IDirection<D>[] getPossibleDirections();
}
