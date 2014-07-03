package abr.heatcraft.core;

import java.awt.*;

public class ColorUtil {
	public static Color getColorforTemp(double Temp) {
		int r = getRadianceForWavelength(700D, Temp);
		int g = getRadianceForWavelength(500D, Temp);
		int b = getRadianceForWavelength(300D, Temp);
		
		return new Color(r, g, b);
	}
	
	public static int getiColorforTemp(double Temp) {
		int r = getRadianceForWavelength(700D, Temp) + 127;
		int g = getRadianceForWavelength(500D, Temp) + 127;
		int b = getRadianceForWavelength(300D, Temp) + 127;
		
		int max = Math.max(r, Math.max(g, b));
		if(max > 255) {
			r = (r * 255) / max;
			g = (g * 255) / max;
			b = (b * 255) / max;
		}
		
		return (r << 16) + (g << 8) + b;
	}
	
	public static int getlightforTemp(double Temp) {
		int r = getRadianceForWavelength(700D, Temp);
		int g = getRadianceForWavelength(500D, Temp);
		int b = getRadianceForWavelength(300D, Temp);
		
		int max = Math.max(r, Math.max(g, b));
		
		return Math.min(max / 63, 15);
	}
	
	private static final double c1 = 1.0e+22, c2 = 1.0e+7;
	
	public static int getRadianceForWavelength(double wl, double Temp){
		return (int)(c1/(Math.pow(wl, 5) * (Math.expm1(c2/(wl * Temp)))));
	}
}
