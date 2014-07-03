package sciapi.api.basis.matrix;

import sciapi.api.basis.euclidian.IEVector;


public class MatrixSizeException extends RuntimeException {
	public MatrixSizeException(IMatrix m1, IMatrix m2, String proc){
		super(MatrixtoExpr(m1) + " and " + MatrixtoExpr(m2)
				+ " have invalid size, so can't run this process: " + proc + ".\n");
	}
	
	private static String MatrixtoExpr(IMatrix m){
		return m + "(" + m.getRowNum() + "," + m.getColumnNum() + ")";
	}
	
	public MatrixSizeException(IMatrix m, IEVector v, String proc){
		super(MatrixtoExpr(m) + " and " + v + "(" + v.getDimension() + ")"
				+ " have invalid size, so can't run this process: " + proc + ".\n");
	}
}
