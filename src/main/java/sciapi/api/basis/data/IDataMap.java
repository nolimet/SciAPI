package sciapi.api.basis.data;

import java.util.*;


public interface IDataMap {
	/**
	 * Get Data from this Data Map using the data tag.
	 * @param tag the data tag.
	 * @return the data indicated by the tag.
	 * */
	public Object get(IDataTag tag);
	
	/**
	 * Set Data in this Data Map.
	 * @param tag the data tag.
	 * @param o the data to be stored with being indicated by the tag.
	 * */
	public void set(IDataTag tag, Object o);
	
	
	/**
	 * Get All Tags from this Data Map.
	 * */
	public Set<IDataTag> getAllTag();
	
	/**
	 * Get All Data from this Data Map.
	 * */
	public Object[] getAll();
	
	
	/**
	 * Check if the data indicated by the tag exists in this datamap or not.
	 * @param tag the data tag.
	 * @return <code>true</code> if the data exists. <code>false</code> otherwise.
	 * */
	public boolean exists(IDataTag tag);
}
