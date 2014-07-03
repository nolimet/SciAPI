package sciapi.api.basis.math;

public class MConverts {
	/**
	 * Compaction
	 * */
	public static byte[] toByteArray(boolean[] boolarr) {
		short length = (short) ((boolarr.length + 1) >> 3);
		byte[] barr = new byte[length];
		
		for(byte b = 0; b < length; b++) {
			barr[b] = 0;
			for(byte ite = 0; ite < 8 && ite + (b>>3) < boolarr.length; ite++) {
				barr[b] = (byte) (barr[b] >> 1);
				if(boolarr[b+ite]) barr[b]++;
			}
		}
		
		return barr;
	}
	
	/**
	 * get boolean from the byte with ite.
	 * */
	public static boolean getBoolean(byte b, byte ite) {
		return (b >> ite) < ((b >> ++ite) << 1);
	}
	
	/**
	 * get boolean from the byte array with ite.
	 * */
	public static boolean getBoolean(byte[] barr, short ite) {
		return getBoolean(barr[ite>>3], (byte) (ite - ((ite>>3)<<3)));
	}
}
