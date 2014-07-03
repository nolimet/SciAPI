package sciapi.api.basis.euclidian;

import java.util.Iterator;

import sciapi.api.abstraction.numerics.ICompValue;
import sciapi.api.abstraction.pos.*;
import sciapi.api.basis.matrix.*;

/**
 * Euclidian Vector Interface.
 * */
public interface IEVector<T extends IEVector, S extends ICompValue> extends IAbsDifference<T, S> {
	
	/**
	 * get Nth Coordinate of this Vector.
	 * @param N from 1 to Dimension
	 * */
	public S getCoord(int N);
	
	/**
	 * Dot Product of two vectors.
	 * */
	public S dotProduct(T v);
	
	/**
	 * Store Dot Product of two vectors into a scalar value.
	 * @return Stored Dot Product.
	 * */
	public S setdotProduct(S s, T v);
	
	/**
	 * gets the Size of this Vector.
	 * */
	public S getSize();
	
	/**
	 * Store Size of this vector into a scalar value.
	 * @return Stored Size of this vector.
	 * */
	public S setasSize(S s);
	
	
	/**
	 * get Dimension of this vector.
	 * */
	public int getDimension();
	
	
	/**
	 * get New Instance of Matrix which can be used as Transformation of this vector.
	 * */
	public ITransformMatrix newTransformMatrix();
	
	/**
	 * get New Instance of Matrix which can be used as Transformation of this vector.
	 * @param toDim The dimension to transform to.
	 * */
	public ITransformMatrix newTransformMatrix(int toDim);
}
