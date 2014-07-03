package sciapi.api.abstraction.pos;

/**
 * Connection between Two Positioned Objects.
 * */
public interface IObjConnection<O extends IPosObject> {
	/**
	 * get Objects belongs to the connection.
	 * */
	public O[] getObjects();
	
	/**
	 * get Another Object from posobj.
	 * 
	 * @param posobj belongs to the connection.
	 * @return another Positioned Object.
	 * null if posobj is not belongs to the connection.
	 * */
	public O getAnotherObj(O posobj);
}
