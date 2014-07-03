package sciapi.api.def.numerics;

import sciapi.api.abstraction.numerics.IAbsValue;
import sciapi.api.abstraction.numerics.ICompValue;
import sciapi.api.abstraction.numerics.IInteger;
import sciapi.api.abstraction.numerics.IReal;
import sciapi.api.basis.math.BMath;
import sciapi.api.basis.temporaries.DTempManager;

public class DInteger implements IInteger {
	
	int value;
	
	public DInteger(){
		value = 0;
	}
	
	public DInteger(int n){
		value = n;
	}

	@Override
	public IInteger add(IInteger iav) {
		IInteger r = (IInteger)iav;
		
		return new DInteger(value + iav.toInt());
	}

	@Override
	public IInteger sub(IInteger iav) {
		
		return new DInteger(value - iav.toInt());
	}

	@Override
	public IInteger mult(IInteger iav) {
		
		return new DInteger(value * iav.toInt());
	}

	@Override
	public IInteger div(IInteger iav) {
		
		return new DInteger(value / iav.toInt());
	}

	@Override
	public IInteger setadd(IInteger pre, IInteger post) {
		this.value = pre.toInt() + post.toInt();
		return this;
	}

	@Override
	public IInteger setsub(IInteger pre, IInteger post) {
		this.value = pre.toInt() - post.toInt();
		return this;
	}

	@Override
	public IInteger setmult(IInteger pre, IInteger post) {
		this.value = pre.toInt() * post.toInt();
		return this;
	}

	@Override
	public IInteger setdiv(IInteger pre, IInteger post) {
		this.value = pre.toInt() / post.toInt();
		return this;
	}

	@Override
	public IInteger getZero() {
		return new DInteger(0);
	}
	
	@Override
	public boolean equals(Object o){
		IInteger r = (IInteger)o;
		
		return r.toLong() == this.value;
	}

	@Override
	public int compareTo(IInteger o) {
		if(this.value > o.toLong())
			return 1;
		
		if(this.value < o.toLong())
			return -1;
		
		return 0;
	}

	@Override
	public IInteger remainder(IInteger iav) {
		
		return new DInteger(value % iav.toInt());
	}

	@Override
	public IInteger setremainder(IInteger pre, IInteger post) {
		this.value = pre.toInt() % post.toInt();
		return this;
	}

	@Override
	public int toInt() {
		return value;
	}

	@Override
	public long toLong() {
		return (long)value;
	}

	@Override
	public IInteger getOne() {
		return new DInteger(1);
	}

	@Override
	public IInteger setOne() {
		value = 1;
		return this;
	}

	@Override
	public IInteger addinv() {
		return new DInteger(-value);
	}

	@Override
	public IInteger setZero() {
		value = 0;
		return this;
	}

	@Override
	public IInteger setaddinv(IInteger a) {
		value = -a.toInt();
		return this;
	}

	@Override
	public void set(int v) {
		value = v;
	}

	@Override
	public void set(long v) {
		value = (int)v;
	}

	@Override
	public boolean isZero() {
		return value == 0;
	}

	@Override
	public IInteger getNew() {
		return new DInteger();
	}

	@Override
	public IInteger set(IInteger par) {
		this.value = par.toInt();
		
		return this;
	}

	@Override
	public IInteger getTemporary() {
		return DTempManager.getDInteger();
	}

	@Override
	public void remove(IInteger tval) {
		DTempManager.release((DInteger)tval);
	}

}
