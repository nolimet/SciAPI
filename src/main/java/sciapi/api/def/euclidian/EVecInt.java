package sciapi.api.def.euclidian;

import java.util.*;

import sciapi.api.abstraction.numerics.IInteger;
import sciapi.api.basis.euclidian.*;
import sciapi.api.basis.math.BMath;
import sciapi.api.basis.matrix.ITransformMatrix;
import sciapi.api.basis.storage.Pool;
import sciapi.api.basis.temporaries.*;
import sciapi.api.def.matrix.DMatrix;
import sciapi.api.def.numerics.DInteger;
import sciapi.api.def.numerics.DReal;

public class EVecInt implements IEVector<EVecInt, IInteger> {

	public static List<TempUtil> temps = new ArrayList();

	
	private IInteger value[];
	
	/**
	 * Creates a Vector as Zero Vector.
	 * */
	public EVecInt(int dim){
		value = new DInteger[dim];
		for(int i = 0; i < dim; i++)
			value[i] = new DInteger();
	}
	
	/**
	 * Creates a Vector via Coordinates.
	 * */
	public EVecInt(IInteger... coords){
		value = coords;
	}
	
	/**
	 * Creates a Vector via Coordinates.
	 * */
	public EVecInt(int... coords){
		value = new DInteger[coords.length];
		
		for(int i = 0; i < coords.length; i++)
			value[i] = new DInteger(coords[i]);
	}

	
	public void set(int... coords){
		for(int i = 0; i < coords.length; i++)
			value[i].set(coords[i]);
	}
	
	
	@Override
	public boolean isZero() {
		for(IInteger val : value)
			if(!val.isZero())
				return false;
		return true;
	}

	@Override
	public EVecInt mult(IInteger sc) {
		EVecInt res = new EVecInt(value.length);
		for(int i = 0; i < value.length; i++)
			res.value[i].setmult(value[i], sc);
		return res;
	}

	@Override
	public EVecInt div(IInteger sc) {
		EVecInt res = new EVecInt(value.length);
		for(int i = 0; i < value.length; i++)
			res.value[i].setdiv(value[i], sc);
		return res;
	}

	@Override
	public EVecInt setmult(EVecInt a, IInteger sc) {
		checkDimension(this, a, "setmult");
		
		for(int i = 0; i < value.length; i++)
			this.value[i].setmult(a.value[i], sc);
		return this;
	}

	@Override
	public EVecInt setdiv(EVecInt a, IInteger sc) {
		checkDimension(this, a, "setdiv");
		
		for(int i = 0; i < value.length; i++)
			this.value[i].setdiv(a.value[i], sc);
		return this;
	}

	@Override
	public EVecInt add(EVecInt a) {
		checkDimension(this, a, "add");
		
		EVecInt res = new EVecInt(value.length);
		for(int i = 0; i < value.length; i++)
			res.value[i].setadd(value[i], a.value[i]);
		return res;
	}

	@Override
	public EVecInt sub(EVecInt a) {
		checkDimension(this, a, "sub");
		
		EVecInt res = new EVecInt(value.length);
		for(int i = 0; i < value.length; i++)
			res.value[i].setsub(value[i], a.value[i]);
		return res;
	}

	@Override
	public EVecInt getZero() {
		EVecInt res = new EVecInt(value.length);
		for(int i = 0; i < value.length; i++)
			res.value[i].setZero();
		return res;
	}

	@Override
	public EVecInt addinv() {
		EVecInt res = new EVecInt(value.length);
		for(int i = 0; i < value.length; i++)
			res.value[i].setaddinv(value[i]);
		return res;
	}

	@Override
	public EVecInt setadd(EVecInt a, EVecInt b) {
		checkDimension(a, b, "setadd");
		checkDimension(this, a, "setadd");
		
		for(int i = 0; i < value.length; i++)
			this.value[i].setadd(a.value[i], b.value[i]);
		return this;
	}

	@Override
	public EVecInt setsub(EVecInt a, EVecInt b) {
		checkDimension(a, b, "setsub");
		checkDimension(this, a, "setsub");
		
		for(int i = 0; i < value.length; i++)
			this.value[i].setsub(a.value[i], b.value[i]);
		return this;
	}

	@Override
	public EVecInt setZero() {
		for(int i = 0; i < value.length; i++)
			this.value[i].setZero();
		return this;
	}

	@Override
	public EVecInt setaddinv(EVecInt a) {
		checkDimension(this, a, "setaddinv");
		
		for(int i = 0; i < value.length; i++)
			this.value[i].setaddinv(a.value[i]);
		return this;
	}

	@Override
	public IInteger getCoord(int N) {
		return value[N];
	}

	@Override
	public IInteger dotProduct(EVecInt v) {
		checkDimension(this, v, "dotProduct");
		
		DInteger r = new DInteger();
		DInteger temp = DTempManager.getDInteger();
		
		for(int i = 0; i < value.length; i++)
			r.setadd(r, temp.setmult(value[i], v.value[i]));
		
		DTempManager.release(temp);
		
		return r;
	}

	@Override
	public IInteger setdotProduct(IInteger s, EVecInt v) {
		checkDimension(this, v, "setdotProduct");
		
		s.setZero();
		DInteger temp = DTempManager.getDInteger();

		for(int i = 0; i < value.length; i++)
			s.setadd(s, temp.setmult(value[i], v.value[i]));
		
		DTempManager.release(temp);
		
		return s;
	}

	/**
	 * get the Size of this Vector as "Taxi Distance".
	 * */
	@Override
	public IInteger getSize() {
		DInteger r = new DInteger();
		DInteger temp = DTempManager.getDInteger();
		
		for(int i = 0; i < value.length; i++){
			temp.set(BMath.absl(value[i].toLong()));
			r.setadd(r, temp);
		}

		DTempManager.release(temp);

		return r;
	}

	@Override
	public IInteger setasSize(IInteger s) {
		s.setZero();
		DInteger temp = DTempManager.getDInteger();
		
		for(int i = 0; i < value.length; i++){
			temp.set(BMath.absl(value[i].toLong()));
			s.setadd(s, temp);
		}
		
		DTempManager.release(temp);

		return s;
	}
	
	@Override
	public boolean equals(Object o){
		EVecInt v = (EVecInt) o;
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
	
	
	/**
	 * Not Supported.
	 * */
	@Deprecated
	@Override
	public ITransformMatrix newTransformMatrix() {
		return null;
	}
	
	/**
	 * Not Supported.
	 * */
	@Deprecated
	@Override
	public ITransformMatrix newTransformMatrix(int toDim) {
		return null;
	}
	
	
	@Override
	public EVecInt getNew() {
		return new EVecInt(value.length);
	}

	@Override
	public EVecInt set(EVecInt par) {
		checkDimension(this, par, "set");
		
		for(int i = 0; i < value.length; i++)
			value[i].set(par.value[i]);
		
		return this;
	}
	
	
	private static void checkDimension(EVecInt a, EVecInt b, String procname){
		if(a.getDimension() != b.getDimension())
			throw new VecDimensionException(a, b, procname);
	}

	@Override
	public EVecInt getTemporary() {
		if(this.getDimension() > temps.size()){
			for(int i = temps.size() + 1; i <= this.getDimension(); i++){
				temps.add(new TempUtil(new EVecInt(i)));
			}
		}
		
		return (EVecInt) temps.get(getDimension()-1).getTemp();
	}

	@Override
	public void remove(EVecInt tval) {
		if(this.getDimension() > temps.size())
			return;
		
		temps.get(getDimension()-1).release(tval);
	}

}
