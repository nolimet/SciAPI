package sciapi.api.def.data;

import java.nio.Buffer;
import java.util.*;

import sciapi.api.basis.data.*;

/**
 * Data Formatter via Data Structure.
 * This is not Thread-Local process,
 * so be careful when you are using this with threads.
 * */
public class BasicDataFormatter implements IDataFormatter {
	
	IDataStructure dst;
	
	public BasicDataFormatter(IDataStructure ds){
		dst = ds;
	}

	@Override
	public IDataMap FormatTo(String[] str) {
		IDataFormatter custom = dst.getCustomFormatter();
		
		if(dst.getCustomFormatter() != null){
			return custom.FormatTo(str);
		}
		
		IDataMap map = new DataSet();
		
		int bef = 0, size;
		String[] strs = null;
		BasicDataFormatter bdf;
		IDataMap chmap;
		IDataStructure befchild = null;
		
		for(int i = 0; i < str.length; i++){
			for(IDataStructure child : dst.getSubDataStructures()){
				if(child.isHead(str[i])){
					size = i - bef;
					strs = new String[size];
					System.arraycopy(str, bef, strs, 0, size);
					
					if(befchild == null){
						IDataFormer df = dst.getDataFormer();
						if(df != null){
							TagObject to = df.formData(strs);
							if(to != null)
								map.set(to.tag, to.obj);
						}
					}
					else{
						bdf = new BasicDataFormatter(befchild);
						chmap = bdf.FormatTo(strs);
						map.set(befchild.getDataTag(chmap), chmap);
					}
					
					befchild = child;
					
					break;
				}
			}
		}
		
		if(strs != null && befchild != null){
			bdf = new BasicDataFormatter(befchild);
			chmap = bdf.FormatTo(strs);
			map.set(befchild.getDataTag(chmap), chmap);
		}
		
		return map;
	}

	@Override
	public String[] FormatFrom(IDataMap dmap) {
		IDataFormatter custom = dst.getCustomFormatter();
		
		if(dst.getCustomFormatter() != null){
			return custom.FormatFrom(dmap);
		}
		
		List<String[]> strlist = new ArrayList();
		IDataMap submap;
		IDataTag stag;
		BasicDataFormatter bdf;
		
		for(IDataTag tag : dmap.getAllTag()){
			Object o = dmap.get(tag);
			if(dst.getSubDataStructures() != null && o instanceof IDataMap){
				submap = (IDataMap) o;
				for(IDataStructure child : dst.getSubDataStructures())
				{
					stag = child.getDataTag(submap);
					if(stag != null && tag.equals(stag)){
						bdf = new BasicDataFormatter(child);
						strlist.add(bdf.FormatFrom(submap));
						
						break;
					}
				}
			}
			else{
				IDataFormer df = dst.getDataFormer();
				if(df != null){
					String[] strs = df.toStrings(new TagObject(tag, o));
					if(strs != null)
						strlist.add(strs);
				}
			}
		}
		
		int size = 0;
		
		for(String[] strs : strlist)
			size += strs.length;
		
		String[] res = new String[size];
		
		int start = 0;
		
		for(String[] strs : strlist){
			System.arraycopy(strs, 0, res, start, strs.length);
			start += strs.length;
		}
		
		return res;
	}

}
