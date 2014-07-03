package sciapi.api.basis.data;

public interface IDataStructure {
	/**
	 * Get Child Data Structures of this data structure.
	 * */
	public IDataStructure[] getSubDataStructures();
	
	/**
	 * Checks if the parameter String is Head of a structure from a string.
	 * */
	public boolean isHead(String str);
	
	/**
	 * Get the Data Tag for the data map with this structure.
	 * the data tag must contains information of this structure,
	 * to distinguish each structure via this tag.
	 * */
	public IDataTag getDataTag(IDataMap map);
	
	/**
	 * <p>Get Custom Data Formatter for this structure.</p>
	 * <p>(#Default: BasicDataFormatter)</p>
	 * */
	public IDataFormatter getCustomFormatter();
	
	/**
	 * Get Data Former for this structure.
	 * */
	public IDataFormer getDataFormer();
}
