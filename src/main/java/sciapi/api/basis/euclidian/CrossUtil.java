package sciapi.api.basis.euclidian;

import sciapi.api.abstraction.absutil.ICalcUtil;
import sciapi.api.abstraction.numerics.ICompValue;
import sciapi.api.basis.temporaries.DTempManager;

public class CrossUtil implements ICalcUtil<CrossUtil, IEVector> {
	IEVector v;	
	
	@Override
	public CrossUtil set(IEVector vec) {
		DimensionCheck(vec, "set(save)");
		v = vec;
		return this;
	}

	@Override
	public IEVector get() {
		return v;
	}
	
	/**
	 * get Cross Product of two vectors,
	 * as new instance.
	 * */
	public IEVector Cross(IEVector a, IEVector b){
		DimensionCheck(a, "Cross");
		DimensionCheck(b, "Cross");
		
		IEVector res = (IEVector) a.getNew();
		
		ICompValue v = (ICompValue) a.getCoord(0).getTemporary();
		
		for(int i = 0; i < 3; i++){
			res.getCoord(i).setmult(a.getCoord((i+1)%3), b.getCoord((i+2)%3));
			v.setmult(a.getCoord((i+2)%3), b.getCoord((i+1)%3));
			res.getCoord(i).setsub(res.getCoord(i), v);
		}
		
		v.remove(v);
		
		return res;
	}
	
	/**
	 * Sets the saved Vector to Cross Product of two vectors.
	 * @param a Prior parameter of Cross product
	 * @param b Post parameter of Cross product
	 * @return Saved Vector, which was set as Cross Product.
	 * */
	public IEVector setCross(IEVector a, IEVector b){
		DimensionCheck(a, "setCross");
		DimensionCheck(b, "setCross");
		
		ICompValue val = (ICompValue) a.getCoord(0).getTemporary();
		
		for(int i = 0; i < 3; i++){
			v.getCoord(i).setmult(a.getCoord((i+1)%3), b.getCoord((i+2)%3));
			val.setmult(a.getCoord((i+2)%3), b.getCoord((i+1)%3));
			v.getCoord(i).setsub(v.getCoord(i), v);
		}
		
		val.remove(val);
		
		return v;
	}
	
	private void DimensionCheck(IEVector a, String proc){
		if(a.getDimension() != 3)
			throw new VecDimensionException(a, 3, proc);
	}
}
