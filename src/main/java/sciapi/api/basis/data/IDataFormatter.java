package sciapi.api.basis.data;

public interface IDataFormatter {
	/**
	 * Format Data as String array to DataMap.
	 * */
	public IDataMap FormatTo(String[] str);
	
	/**
	 * Unformat Data as DataMap to String array.
	 * */
	public String[] FormatFrom(IDataMap dmap);
}
