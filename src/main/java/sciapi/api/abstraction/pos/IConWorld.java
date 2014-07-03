package sciapi.api.abstraction.pos;

/**
 * Interface for Continuous World which can contains continuous positions.
 * */
public interface IConWorld<R extends IRange, P extends IAbsPosition> extends IWorld{
	/**
	 * get Objects on specific Range from this World.
	 * */
	public IPosObject[] getObjsinRange(R range);
}
