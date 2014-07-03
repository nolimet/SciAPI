package sciapi.api.basis.data;

public interface IPartDataFormer extends IDataFormer {
	/**
	 * Determines if the data as strings can be formatted.
	 * */
	public boolean isFormable(String[] str);
	
	/**
	 * Determines if the TagObject is in this form.
	 * */
	public boolean isThisForm(TagObject tobj);
}
