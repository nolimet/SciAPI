package sciapi.api.basis.math;

public class BMath {
	/**
	 * Very Small Value
	 * */
	public static final double eps = 1.0e-20;
	
	/**
	 * PI value for float
	 * */
	public static final float PI=(float)Math.PI;

	
	/**
	 * For Comparing double
	 * */
	public static int double_prec = 45;
	
	/**
	 * For Comparing float
	 * */
	public static int float_prec = 17;
	
	/**
	 * abs for int
	 * */
	public static int absi(int x){
		return (x>0)? x:-x;
	}
	
	/**
	 * abs for long
	 * */
	public static long absl(long x){
		return (x>0)? x:-x;
	}
	
	/**
	 * GCD for int
	 * */
	public static int GCD(int a, int b) { return b==0 ? a : GCD(b, a%b); }
	
	/**
	 * GCD for long
	 * */
	public static long GCD(long a, long b) { return b==0 ? a : GCD(b, a%b); }

	
	/**
	 * fmod for double
	 * */
	public static final double fmod(double a, double b){
		return a-Math.floor(a/b)*b;
	}
	
	/**
	 * fmod for float
	 * */
	public static final float fmod(float a, float b){
		return a-(float)Math.floor(a/b)*b;
	}
	

	
	/**
	 * Supports for comparing double
	 * */
	public static boolean isSame(double a, double b){
		if(Math.getExponent(a - b) < Math.getExponent(b) - double_prec)
			return true;
		
		return false;
	}
	
	/**
	 * Supports for comparing double
	 * */
	public static int compare(double a, double b){
		if(isSame(a, b))
			return 0;
		else if(a > b)
			return 1;
		else return -1;
	}
	
	/**
	 * Supports for comparing float
	 * */
	public static boolean isSame(float a, float b){
		if(Math.getExponent(a - b) < Math.getExponent(b) - float_prec)
			return true;
		
		return false;
	}
	
	/**
	 * Supports for comparing float
	 * */
	public static int compare(float a, float b){
		if(isSame(a, b))
			return 0;
		else if(a > b)
			return 1;
		else return -1;
	}
	
	
	
	/**
	 * Degrees to Radians, for double.
	 * */
	public static final double Radians(double d){
		return d*Math.PI/180.0;
	}
	
	/**
	 * Radians to Degrees, for double.
	 * */
	public static final double Degrees(double r){
		return r/Math.PI*180.0;
	}
	
	/**
	 * Degrees to Radians, for float.
	 * */
	public static final float Radians(float d){
		return d*PI/180.0f;
	}
	
	/**
	 * Radians to Degrees, for float.
	 * */
	public static final float Degrees(float r){
		return r/PI*180.0f;
	}
	
	/**
	 * Angle Undercut
	 * */
	public static final double AngleUndercut(double x){
		if(x < 0) return x+2.0*Math.PI;
		return x;
	}
	
	/**
	 * Angle Uppercut
	 * */
	public static final double AngleUppercut(double x){
		if(x > 2.0*Math.PI) return x-2.0*Math.PI;
		return x;
	}
	
	
	public static final int Kronecker_delta(int x, int y){
		if(x == y) return 1;
		else return 0;
	}
	
	public static final int Kronecker_delta(double x, double y){
		if(BMath.isSame(x, y)) return 1;
		else return 0;
	}
	
	public static final int Kronecker_delta(float x, float y){
		if(BMath.isSame(x, y)) return 1;
		else return 0;
	}
}
