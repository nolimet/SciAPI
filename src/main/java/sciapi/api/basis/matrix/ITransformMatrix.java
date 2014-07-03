package sciapi.api.basis.matrix;

import sciapi.api.abstraction.numerics.ICompValue;
import sciapi.api.basis.euclidian.*;

public interface ITransformMatrix<T extends ITransformMatrix,
V extends IEVector, S extends ICompValue> extends IMatrix<T, S> {
	
	/**
	 * Get the new instance of transformed Vector via this matrix.
	 * @param vec Vector to transformed.
	 * @return new instance of Transformed Vector.
	 * */
	public V getTransformed(V vec);
	
	/**
	 * Sets the vector as transformed Vector via this matrix.
	 * @param set Vector being set.
	 * @param vec Vector to transformed.
	 * @return Transformed Vector.
	 * */
	public V setasTransformed(V set, V vec);
}
