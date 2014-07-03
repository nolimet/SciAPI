package sciapi.api.basis.data;

public interface IDataTranslator {
	/**
	 * Translate data from string format to byte format.
	 * */
	public String[] translateTo(byte[] b);
	
	/**
	 * Translate data from byte format to string format.
	 * */
	public byte[] translateFrom(String[] str);
}
