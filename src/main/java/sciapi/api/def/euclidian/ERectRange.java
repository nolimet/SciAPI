package sciapi.api.def.euclidian;

import sciapi.api.abstraction.pos.*;

public class ERectRange<P extends IAbsPosition<P, EVector>> implements IRange<ERectRange, P> {

	P p1, p2;
	
	public ERectRange(P point1, P point2){
		p1 = point1;
		p2 = point2;
	}
	
	@Override
	public ERectRange getNew() {
		return new ERectRange(p1.getNew(), p2.getNew());
	}

	@Override
	public ERectRange set(ERectRange par) {
		p1.set((P) par.p1);
		p2.set((P) par.p2);
		
		return this;
	}

	@Override
	public boolean inRange(P pos) {
		EVector v1 = p1.getDifference(pos);
		EVector v2 = p2.getDifference(pos);
		
		if(v1.getDimension() != v2.getDimension())
			return false;
		
		for(int i = 0; i < v1.getDimension(); i++){
			if(!(v1.getCoord(i).toDouble() > 0.0) ^ (v2.getCoord(i).toDouble() > 0.0)){
				return false;
			}
		}
		
		return true;
	}

	@Override
	public boolean isOverlapped(ERectRange range) {
		EVector p1to1 = p1.getDifference((P) range.p1);
		EVector p2to1 = p2.getDifference((P) range.p1);
		EVector p1to2 = p1.getDifference((P) range.p2);
		EVector p2to2 = p2.getDifference((P) range.p2);
		
		for(int i = 0; i < p1to1.getDimension(); i++){
			if(!(p1to1.getCoord(i).toDouble() > 0.0)
					^ (p2to1.getCoord(i).toDouble() > 0.0)
					^ (p1to2.getCoord(i).toDouble() > 0.0)
					^ (p2to2.getCoord(i).toDouble() > 0.0)){
				return false;
			}
		}
		
		return true;
	}

	@Override
	public ERectRange getIntersect(ERectRange range) {
		if(!isOverlapped(range))
			return null;
		
		EVector p1to1 = p1.getDifference((P) range.p1);
		EVector p2to1 = p2.getDifference((P) range.p1);
		EVector p1to2 = p1.getDifference((P) range.p2);
		EVector p2to2 = p2.getDifference((P) range.p2);
		
		EVector v1 = p1to1.getNew(), v2 = p1to1.getNew();
		
		for(int i = 0; i < p1to1.getDimension(); i++){
			if((p1to1.getCoord(i).toDouble() > 0.0)
					^ (p1to2.getCoord(i).toDouble() > 0.0)){
				v1.getCoord(i).setZero();
			}
			else v1.getCoord(i).setsub(p2to1.getCoord(i), p1to1.getCoord(i));
			
			if((p1to1.getCoord(i).toDouble() > 0.0)
					^ (p2to1.getCoord(i).toDouble() > 0.0)){
				v2.getCoord(i).set(p1to1.getCoord(i));
			}
			else v2.getCoord(i).set(p1to2.getCoord(i));
		}
		
		return new ERectRange(p1.getDiffPos(v1), p1.getDiffPos(v2));
	}

}
