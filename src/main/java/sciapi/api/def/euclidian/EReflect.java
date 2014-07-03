package sciapi.api.def.euclidian;

import sciapi.api.abstraction.numerics.*;
import sciapi.api.basis.euclidian.*;
import sciapi.api.basis.math.BMath;
import sciapi.api.basis.matrix.ITransformMatrix;
import sciapi.api.basis.matrix.MatrixSizeException;

/**
 * Reflection Transformation for Euclidian Vectors.
 * */
public class EReflect implements ITransformation {
	
	IEVector refvec;
	
	/**
	 * @param pref the vector orthogonal to the reflection plane.
	 * it should be normalized.
	 * */
	public EReflect(IEVector pref){
		refvec = pref;
	
	}

	@Override
	public IEVector transform(IEVector v) {
		IEVector vec = (IEVector) v.getNew();
		IEVector temp = (IEVector) v.getTemporary();
		IReal tv = (IReal) v.getCoord(0).getTemporary();
		
		v.setdotProduct(tv, refvec);
		tv.set(tv.toDouble() * 2.0);
		temp.setmult(refvec, tv);
		vec.setsub(v, temp);
		
		temp.remove(temp);
		tv.remove(tv);
		
		return vec;
	}

	@Override
	public IEVector setTransformed(IEVector s, IEVector v) {
		IEVector temp = (IEVector) v.getTemporary();
		IReal tv = (IReal) v.getCoord(0).getTemporary();
		
		v.setdotProduct(tv, refvec);
		tv.set(tv.toDouble() * 2.0);
		temp.setmult(refvec, tv);
		s.setsub(v, temp);
		
		temp.remove(temp);
		tv.remove(tv);
		
		return s;
	}

	@Override
	public ITransformMatrix getTransformationMatrix() {
		return setasTransformationMatrix(
				refvec.newTransformMatrix());
	}

	@Override
	public ITransformMatrix setasTransformationMatrix(ITransformMatrix m) {
		if(m.getRowNum() != refvec.getDimension()
				|| m.getColumnNum() != refvec.getDimension())
			throw new MatrixSizeException(m, refvec, "EReflect#setasTransformationMatrix");
		
		IReal tv = (IReal) refvec.getCoord(0).getTemporary();
		
		for(int i = 0; i < m.getRowNum(); i++)
			for(int j = 0; j < m.getColumnNum(); j++){
				((IReal)m.getElement(i, j)).set((BMath.Kronecker_delta(i, j)));
				tv.set(((IReal)refvec.getCoord(i)).toDouble()
						*((IReal)refvec.getCoord(j)).toDouble()
						*2.0);
				m.getElement(i, j).setsub(m.getElement(i, j), tv);
			}
		
		tv.remove(tv);
		
		return m;
	}

}
