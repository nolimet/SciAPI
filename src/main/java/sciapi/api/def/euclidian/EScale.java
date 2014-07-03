package sciapi.api.def.euclidian;

import sciapi.api.basis.euclidian.IEVector;
import sciapi.api.basis.euclidian.ITransformation;
import sciapi.api.basis.matrix.IMatrix;
import sciapi.api.basis.matrix.ITransformMatrix;
import sciapi.api.basis.matrix.MatrixSizeException;
import sciapi.api.def.matrix.DMatrix;

/**
 * Scaling Transformation for Euclidian Vectors.
 * */
public class EScale implements ITransformation {
	
	public IEVector scale;
	
	public EScale (IEVector pscale){
		scale = pscale;
	}

	@Override
	public IEVector transform(IEVector v) {
		IEVector vec = (IEVector) v.getNew();
		
		for(int i = 0; i < v.getDimension(); i++){
			vec.getCoord(i).setmult(v.getCoord(i), scale.getCoord(i));
		}
		
		return vec;
	}

	@Override
	public IEVector setTransformed(IEVector s, IEVector v) {		
		for(int i = 0; i < v.getDimension(); i++){
			s.getCoord(i).setmult(v.getCoord(i), scale.getCoord(i));
		}
		
		return s;
	}

	@Override
	public ITransformMatrix getTransformationMatrix() {
		return setasTransformationMatrix(
				scale.newTransformMatrix());
	}

	@Override
	public ITransformMatrix setasTransformationMatrix(ITransformMatrix m) {
		if(m.getRowNum() != scale.getDimension()
				|| m.getColumnNum() != scale.getDimension())
			throw new MatrixSizeException(m, scale, "EScale#setasTransformationMatrix");
		
		for(int i = 0; i < m.getRowNum(); i++)
			for(int j = 0; j < m.getColumnNum(); j++){
				if(i != j)
					m.getElement(i, j).setZero();
				else m.getElement(i, j).set(scale.getCoord(i));
			}
		
		return m;
	}

}
