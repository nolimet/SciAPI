package sciapi.api.basis.data;

public class DataTrFormSet {
	public IDataTranslator translator;
	public IDataFormatter formatter;
	
	public DataTrFormSet(IDataTranslator ptr, IDataFormatter pform){
		translator = ptr;
		formatter = pform;
	}
	
	public IDataMap trformTo(byte[] bArr){
		return formatter.FormatTo(translator.translateTo(bArr));
	}
	
	public byte[] trformFrom(IDataMap dmap){
		return translator.translateFrom(formatter.FormatFrom(dmap));
	}
}
