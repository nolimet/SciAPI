package sciapi.api.basis.matrix;

import sciapi.api.abstraction.algebra.Ring;
import sciapi.api.abstraction.numerics.ICompValue;
import sciapi.api.abstraction.pos.IAbsDifference;

/**
 * Matrix Interface.
 * */
public interface IMatrix<T extends IMatrix, S extends ICompValue> extends IAbsDifference<T, S> {

	/**
	 * get Element of this Matrix.
	 * 
	 * @param i row number.
	 * @param j column number.
	 * @return value of 
	 * */
	public S getElement(int i, int j);
	
	
	/**
	 * get new instance of Transpose Matrix.
	 * */
	public T Transpose();
	
	/**
	 * set this to Transpose Matrix.
	 * @return this
	 * */
	public T setasTranspose(T mat);
	
	/**
	 * Create as multiplied value.
	 * */
	public T mult(T a);
	
	/**
	 * Set this as multiplied value.
	 * @return this
	 * */
	public T setmult(T a, T b);
	
	
	/**
	 * get Determinant of this Matrix.
	 * */
	public S Determinant();
	
	/**
	 * Store Determinant into a scalar value.
	 * @return Stored Determinant of this Matrix.
	 * */
	public S setasDeterminant(S a);
	
	
	/**
	 * get Row Number of this Matrix.
	 * */
	public int getRowNum();
	
	/**
	 * get Column Number of this Matrix.
	 * */
	public int getColumnNum();
}
