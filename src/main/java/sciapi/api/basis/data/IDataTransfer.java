package sciapi.api.basis.data;

public interface IDataTransfer {
	/**
	 * Extracts data from the datamap.
	 * @param map datamap to be extracted.
	 * */
	public void ExtractFrom(IDataMap map);
	
	/**
	 * Ejects data as datamap from this object.
	 * @return datamap ejected.
	 * */
	public IDataMap Eject();
}
