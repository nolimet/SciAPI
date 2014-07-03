package sciapi.api.def.numerics;

import sciapi.api.abstraction.numerics.IReal;
import sciapi.api.basis.math.BMath;
import sciapi.api.basis.temporaries.*;

public class DFloat implements IReal {
	
	private static TempUtil tu = new TempUtil(new DReal());

	float value;
	
	public DFloat(){
		value = 0.0f;
	}
	
	public DFloat(float d){
		value = d;
	}

	@Override
	public IReal add(IReal iav) {		
		return new DReal(value + iav.toDouble());
	}

	@Override
	public IReal sub(IReal iav) {		
		return new DReal(value - iav.toDouble());
	}

	@Override
	public IReal mult(IReal iav) {
		return new DReal(value * iav.toDouble());
	}

	@Override
	public IReal div(IReal iav) {
		return new DReal(value / iav.toDouble());
	}

	@Override
	public IReal setadd(IReal pre, IReal post) {
		this.value = pre.toFloat() + post.toFloat();
		return this;
	}

	@Override
	public IReal setsub(IReal pre, IReal post) {
		this.value = pre.toFloat() - post.toFloat();
		return this;
	}

	@Override
	public IReal setmult(IReal pre, IReal post) {
		this.value = pre.toFloat() * post.toFloat();
		return this;
	}

	@Override
	public IReal setdiv(IReal pre, IReal post) {
		this.value = pre.toFloat() / post.toFloat();
		return this;
	}

	@Override
	public IReal getZero() {
		return new DReal(0.0);
	}
	
	@Override
	public boolean equals(Object o){
		IReal r = (IReal)o;
		
		return BMath.isSame(this.value, r.toFloat());
	}

	@Override
	public int compareTo(IReal o) {
		return BMath.compare(this.value, o.toFloat());
	}

	@Override
	public double toDouble() {
		return value;
	}

	@Override
	public float toFloat() {
		return (float)value;
	}

	@Override
	public IReal getOne() {
		return new DReal(1.0);
	}

	@Override
	public IReal setOne() {
		value = 1.0f;
		return this;
	}

	@Override
	public IReal addinv() {
		return new DReal(-value);
	}

	@Override
	public IReal setZero() {
		value = 0.0f;
		return this;
	}

	@Override
	public IReal setaddinv(IReal a) {
		value = - a.toFloat();
		return this;
	}

	@Override
	public IReal multinv() {
		return new DReal(1/value);
	}

	@Override
	public IReal setmultinv(IReal a) {
		value = 1 / a.toFloat();
		return this;
	}

	@Override
	public void set(double v) {
		value = (float)v;
	}

	@Override
	public void set(float v) {
		value = v;
	}

	@Override
	public boolean isZero() {
		return BMath.isSame(value, 0.0);
	}

	@Override
	public IReal getNew() {
		return new DReal();
	}

	@Override
	public IReal set(IReal par) {
		this.value = par.toFloat();
		
		return this;
	}

	@Override
	public IReal getTemporary() {
		return (IReal) tu.getTemp();
	}

	@Override
	public void remove(IReal tval) {
		tu.release(tval);
	}

}
