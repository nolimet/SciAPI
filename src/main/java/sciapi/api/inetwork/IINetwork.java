package sciapi.api.inetwork;

/**
 * Network instance for management of the component-connection complex.
 * */
public interface IINetwork {
	
	/** Adds a component to this network, indicating the entity. */
	public void addComponent(IINEntity entity);
	
	/** Removes a component to this network, indicating the entity. */
	public void removeComponent(IINEntity entity);
	
	/**
	 * Gets the component using the component identifier.
	 * 
	 * @param id component identifier
	 * @return the component indicated by the identifier
	 * */
	public IINComponent getComponent(IINCIdentifier id);
	
	/**
	 * Called when the connected connection changes,
	 *  to deal with the change.
	 * 
	 * @param con connection being changed
	 * @param flag change flag
	 * */
	public void onComponentChange(IINComponent comp, ChangeFlag flag);
	
	/**
	 * Called when the connected component changes,
	 *  to deal with the change.
	 * 
	 * @param comp component being changed
	 * @param flag change flag
	 * */
	public void onConnectionChange(IINConnection con, ChangeFlag flag);
}
