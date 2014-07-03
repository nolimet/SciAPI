package sciapi.api.basis.euclidian;

import sciapi.api.basis.matrix.*;

public interface ITransformation {
	/**
	 * Returns new instance of Transformed vector.
	 * */
	public IEVector transform(IEVector v);
	
	/**
	 * Set vector to Transformed vector.
	 * 
	 * @param s vector being set.
	 * @param v vector being transformed.
	 * @return vector being set.
	 * */
	public IEVector setTransformed(IEVector s, IEVector v);
	
	/**
	 * Get Transformation Matrix for this Transformation.
	 * */
	public ITransformMatrix getTransformationMatrix();
	
	/**
	 * Sets the parameter as Transformation Matrix for this Transformation.
	 * @param m the parameter
	 * @return the matrix set as Transformation Matrix.
	 * */
	public ITransformMatrix setasTransformationMatrix(ITransformMatrix m);
}
