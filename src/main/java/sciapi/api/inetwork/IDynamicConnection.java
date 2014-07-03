package sciapi.api.inetwork;

/**
 * Dynamic connection which can add and remove connected components.
 * */
public interface IDynamicConnection extends IINConnection {
	/** Adds the component to this connection. */
	public void addComponent(IINComponent comp);
	
	/** Removes the component from this connection. */
	public void removeComponent(IINComponent comp);
}
