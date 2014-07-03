package sciapi.api.def.euclidian;

import java.util.*;

import sciapi.api.abstraction.numerics.IReal;
import sciapi.api.basis.euclidian.*;
import sciapi.api.basis.matrix.ITransformMatrix;
import sciapi.api.basis.storage.Pool;
import sciapi.api.basis.temporaries.*;
import sciapi.api.def.matrix.DMatrix;
import sciapi.api.def.numerics.DReal;

/**
 * Euclidian Vector for DReal.
 * 
 * this class is Default Euclidian Vector.
 * */
public class EVector implements IEVector<EVector, IReal> {
	
	private static List<TempUtil> temps = new ArrayList();
	
	
	private IReal value[];
	
	/**
	 * Creates a Vector as Zero Vector.
	 * */
	public EVector(int dim){
		value = new DReal[dim];
		for(IReal r : value)
			r = new DReal();
	}
	
	/**
	 * Creates a Vector via Coordinates.
	 * */
	public EVector(IReal... coords){
		value = coords;
	}
	
	/**
	 * Creates a Vector via Coordinates.
	 * */
	public EVector(double... coords){
		value = new DReal[coords.length];
		
		for(int i = 0; i < coords.length; i++)
			value[i] = new DReal(coords[i]);
	}

	
	public void set(double... coords){
		for(int i = 0; i < coords.length; i++)
			value[i].set(coords[i]);
	}
	
	
	@Override
	public boolean isZero() {
		for(IReal val : value)
			if(!val.isZero())
				return false;
		return true;
	}

	@Override
	public EVector mult(IReal sc) {
		EVector res = new EVector(value.length);
		for(int i = 0; i < value.length; i++)
			res.value[i].setmult(value[i], sc);
		return res;
	}

	@Override
	public EVector div(IReal sc) {
		EVector res = new EVector(value.length);
		for(int i = 0; i < value.length; i++)
			res.value[i].setdiv(value[i], sc);
		return res;
	}

	@Override
	public EVector setmult(EVector a, IReal sc) {
		checkDimension(this, a, "setmult");
		
		for(int i = 0; i < value.length; i++)
			this.value[i].setmult(a.value[i], sc);
		return this;
	}

	@Override
	public EVector setdiv(EVector a, IReal sc) {
		checkDimension(this, a, "setdiv");
		
		for(int i = 0; i < value.length; i++)
			this.value[i].setdiv(a.value[i], sc);
		return this;
	}

	@Override
	public EVector add(EVector a) {
		checkDimension(this, a, "add");
		
		EVector res = new EVector(value.length);
		for(int i = 0; i < value.length; i++)
			res.value[i].setadd(value[i], a.value[i]);
		return res;
	}

	@Override
	public EVector sub(EVector a) {
		checkDimension(this, a, "sub");
		
		EVector res = new EVector(value.length);
		for(int i = 0; i < value.length; i++)
			res.value[i].setsub(value[i], a.value[i]);
		return res;
	}

	@Override
	public EVector getZero() {
		EVector res = new EVector(value.length);
		for(int i = 0; i < value.length; i++)
			res.value[i].setZero();
		return res;
	}

	@Override
	public EVector addinv() {
		EVector res = new EVector(value.length);
		for(int i = 0; i < value.length; i++)
			res.value[i].setaddinv(value[i]);
		return res;
	}

	@Override
	public EVector setadd(EVector a, EVector b) {
		checkDimension(a, b, "setadd");
		checkDimension(this, a, "setadd");
		
		for(int i = 0; i < value.length; i++)
			this.value[i].setadd(a.value[i], b.value[i]);
		return this;
	}

	@Override
	public EVector setsub(EVector a, EVector b) {
		checkDimension(a, b, "setsub");
		checkDimension(this, a, "setsub");
		
		for(int i = 0; i < value.length; i++)
			this.value[i].setsub(a.value[i], b.value[i]);
		return this;
	}

	@Override
	public EVector setZero() {
		for(int i = 0; i < value.length; i++)
			this.value[i].setZero();
		return this;
	}

	@Override
	public EVector setaddinv(EVector a) {
		checkDimension(this, a, "setaddinv");
		
		for(int i = 0; i < value.length; i++)
			this.value[i].setaddinv(a.value[i]);
		return this;
	}

	@Override
	public IReal getCoord(int N) {
		return value[N];
	}

	@Override
	public IReal dotProduct(EVector v) {
		checkDimension(this, v, "dotProduct");
		
		DReal r = new DReal();
		DReal temp = DTempManager.getDReal();
		
		for(int i = 0; i < value.length; i++)
			r.setadd(r, temp.setmult(value[i], v.value[i]));
		
		DTempManager.release(temp);
		
		return r;
	}

	@Override
	public IReal setdotProduct(IReal s, EVector v) {
		checkDimension(this, v, "setdotProduct");
		
		s.setZero();
		DReal temp = DTempManager.getDReal();

		for(int i = 0; i < value.length; i++)
			s.setadd(s, temp.setmult(value[i], v.value[i]));
		
		DTempManager.release(temp);
		
		return s;
	}

	@Override
	public IReal getSize() {
		DReal r = new DReal();
		DReal temp = DTempManager.getDReal();
		
		for(int i = 0; i < value.length; i++)
			r.setadd(r, temp.setmult(value[i], value[i]));
		
		DTempManager.release(temp);
		
		r.set(Math.sqrt(r.toDouble()));
		
		return r;
	}

	@Override
	public IReal setasSize(IReal s) {
		s.setZero();
		DReal temp = DTempManager.getDReal();
		
		for(int i = 0; i < value.length; i++)
			s.setadd(s, temp.setmult(value[i], value[i]));
		
		DTempManager.release(temp);
		
		s.set(Math.sqrt(s.toDouble()));
		
		return s;
	}
	
	/**
	 * Set the parameter as Squared Size of this Vector.
	 * */
	public DReal setasSize2(DReal s) {
		s.setZero();
		DReal temp = DTempManager.getDReal();
		
		for(int i = 0; i < value.length; i++)
			s.setadd(s, temp.setmult(value[i], value[i]));
		
		DTempManager.release(temp);
		
		s.set(Math.sqrt(s.toDouble()));
		
		return s;
	}
	
	/**
	 * Get Squared Size of this Vector.
	 * */
	public DReal getSize2() {
		DReal r = new DReal();
		DReal temp = DTempManager.getDReal();
		
		for(int i = 0; i < value.length; i++)
			r.setadd(r, temp.setmult(value[i], value[i]));
		
		DTempManager.release(temp);
		
		return r;
	}
	
	@Override
	public boolean equals(Object o){
		EVector v = (EVector) o;
		if(v.getDimension() != this.getDimension())
			return false;
		for(int i = 0; i < value.length; i++)
			if(!value[i].equals(v.value[i]))
				return false;
		
		return true;
	}

	@Override
	public int getDimension() {
		return value.length;
	}
	
	
	@Override
	public ITransformMatrix newTransformMatrix() {
		return new DMatrix(value.length, value.length);
	}
	
	@Override
	public ITransformMatrix newTransformMatrix(int toDim) {
		return new DMatrix(toDim, value.length);
	}
	

	@Override
	public EVector getNew() {
		return new EVector(value.length);
	}

	@Override
	public EVector set(EVector par) {
		checkDimension(this, par, "set");
		
		for(int i = 0; i < value.length; i++)
			value[i].set(par.value[i]);
		
		return this;
	}
	
	
	
	private static void checkDimension(EVector a, EVector b, String procname){
		if(a.getDimension() != b.getDimension())
			throw new VecDimensionException(a, b, procname);
	}

	@Override
	public EVector getTemporary() {
		if(this.getDimension() > temps.size()){
			for(int i = temps.size() + 1; i <= this.getDimension(); i++){
				temps.add(new TempUtil(new EVector(i)));
			}
		}
		
		return (EVector) temps.get(getDimension()-1).getTemp();
	}

	@Override
	public void remove(EVector tval) {
		if(tval.getDimension() > temps.size())
			return;
		
		temps.get(getDimension()-1).release(tval);
	}

}
