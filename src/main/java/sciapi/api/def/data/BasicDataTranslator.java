package sciapi.api.def.data;

import java.nio.charset.*;
import java.util.*;

import com.google.common.collect.*;
import sciapi.api.basis.data.IDataTranslator;

/**
 * Translate Data with Separator bytes and Translation map.
 * Every String have Separator byte in its end.
 * */
public class BasicDataTranslator implements IDataTranslator {
	
	/**
	 * Separator Byte lists
	 * */
	public byte[] separators;
	
	/**
	 * Ignores separator bytes between igopen and igclose byte.
	 * Mainly for '', "" or ()
	 * */
	public byte igopen, igclose;
	
	/**
	 * Charset to be used.
	 * if this is set to null, then uses default charset.
	 * */
	public Charset charset;
	public BiMap<String, String> translatemap = HashBiMap.create();
	
	private int i;
	
	public BasicDataTranslator(byte[] pseparators, byte pigop, byte pigcl){
		this(pseparators, pigop, pigcl, null);
	}
	
	public BasicDataTranslator(byte[] pseparators, byte pigop, byte pigcl, Charset pcharset){
		separators = pseparators;
		
		igopen = pigop;
		igclose = pigcl;
		
		charset = pcharset;
	}
	
	
	public void addTrMapping(String from, String to){
		translatemap.put(from, to);
	}
	
	
	@Override
	public String[] translateTo(byte[] b) {
		List<String> strlist = new ArrayList();
		List<Byte> blist = new ArrayList();
		
		i = 0;
		
		for(byte c : b){
			blist.add(c);
			
			if(i == 0){
				for(byte sep : separators)
					if(sep == c){
						String str = bytelistToString(blist, charset);
						blist.clear();
						strlist.add(str);

						break;
					}
			}
			
			if(i > 0 && c == igclose)
				i--;
			else if(c == igopen)
				i++;
		}
		
		
		String[] ret = new String[strlist.size()];
		
		int i = 0;
		for(String str : strlist){
			String p = translatemap.get(str);
			ret[i++] = (p != null)? p : str;
		}
		
		return ret;
	}

	@Override
	public byte[] translateFrom(String[] str) {
		List<Byte> blist = new ArrayList();
		byte[] tempbarr, ret;
		
		for(String s: str){
			String p = translatemap.get(s);
			p = (p != null)? p : s;
			
			if(charset != null)
				tempbarr = p.getBytes(charset);
			else tempbarr = p.getBytes();
			
			for(byte b : tempbarr)
				blist.add(b);
		}
		
		
		ret = new byte[blist.size()];
		
		int i = 0;
		for(Byte c : ret)
			ret[i++] = c;
		
		return ret;
	}
	
	
	private static String bytelistToString(List<Byte> l, Charset charset) {
	    if (l == null) {
	        return "";
	    }
	    
	    byte[] array = new byte[l.size()];
	    int i = 0;
	    for (Byte current : l)
	        array[i++] = current;
	    
	    if(charset == null)
	    	return new String(array);
	    return new String(array, charset);
	}
}
