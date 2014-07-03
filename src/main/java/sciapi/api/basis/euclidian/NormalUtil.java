package sciapi.api.basis.euclidian;

import sciapi.api.abstraction.absutil.ICalcUtil;
import sciapi.api.abstraction.numerics.ICompValue;
import sciapi.api.basis.temporaries.DTempManager;

public class NormalUtil implements ICalcUtil<NormalUtil, IEVector> {
	
	IEVector v;	
	
	@Override
	public NormalUtil set(IEVector vec) {
		v = vec;
		return this;
	}

	@Override
	public IEVector get() {
		return v;
	}
	
	/**
	 * get Normalized vector of the parameter vector as new instance.
	 * */
	public IEVector getNormalized(IEVector par){
		ICompValue cv = (ICompValue) par.getCoord(0).getTemporary();
		IEVector vec = (IEVector) par.div(par.setasSize(cv));
		cv.remove(cv);
		
		return vec;
	}
	
	/**
	 * Sets the saved Vector to normalized form of the parameter vector.
	 * @param par the parameter vector.
	 * @return Saved Vector, which was set as Normalized Vector.
	 * */
	public IEVector setNormalized(IEVector par){
		ICompValue cv = (ICompValue) v.getCoord(0).getTemporary();
		v.setdiv(v, par.setasSize(cv));
		cv.remove(cv);
		
		return v;
	}
}
