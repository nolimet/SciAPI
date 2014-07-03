package sciapi.api.basis.data;

public interface IDataFormer {
	/**
	 * Format data from strings to tagged Object.
	 * */
	public TagObject formData(String[] str);
	
	/**
	 * Format data from tagged Object to Strings.
	 * */
	public String[] toStrings(TagObject tobj);
}
