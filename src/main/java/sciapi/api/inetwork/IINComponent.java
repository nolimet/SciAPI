package sciapi.api.inetwork;

import java.util.Iterator;

/**
 * The network component which substitutes the entity in the network.
 * */
public interface IINComponent {
	
	/** Gets the Network which this component belongs to. */
	public IINetwork getNetwork();
	
	/** Gets the actual entity indicated by this network component. */
	public IINEntity getEntity();
	
	/**
	 * Iteration for connections which is connected to this component.
	 * Do not remove the connections using this iterator.
	 * 
	 * @return Iterator for connected connections.
	 * */
	public Iterator<IINConnection> connectionIte();
	
	/**
	 * Called when the connected connection changes,
	 *  to deal with the change.
	 *  (Connection has to be added or removed to this component at here)
	 * 
	 * @param con connection being changed
	 * @param flag change flag
	 * */
	public void onConnectionChange(IINConnection con, ChangeFlag flag);
}
