package sciapi.api.inetwork;

import java.util.Iterator;

/**
 * Network connection which connects the network components.
 * */
public interface IINConnection {
	
	/** Gets the Network which this component belongs to. */
	public IINetwork getNetwork();
	
	/**
	 * Iteration for components which is connected to this connection.
	 * Do not remove the connected components using this iterator.
	 * 
	 * @return Iterator for connected connections.
	 * */
	public Iterator<IINComponent> connectedIte();
	
	/**
	 * Called when the connected component changes,
	 *  to deal with the change.
	 *  (Component has to be added or removed to this connection at here)
	 * 
	 * @param comp component being changed
	 * @param flag change flag
	 * */
	public void onConnectedChange(IINComponent comp, ChangeFlag flag);
}
