package sciapi.api.def.numerics;

import sciapi.api.abstraction.numerics.*;
import sciapi.api.basis.math.BMath;
import sciapi.api.basis.temporaries.TempUtil;

public class DLong implements IInteger {
	
	private static TempUtil tu = new TempUtil(new DLong());
	long value;
	
	public DLong(){
		value = 0L;
	}
	
	public DLong(long n){
		value = n;
	}

	@Override
	public IInteger add(IInteger iav) {
		IInteger r = (IInteger)iav;
		
		return new DLong(value + iav.toLong());
	}

	@Override
	public IInteger sub(IInteger iav) {
		
		return new DLong(value - iav.toLong());
	}

	@Override
	public IInteger mult(IInteger iav) {
		
		return new DLong(value * iav.toLong());
	}

	@Override
	public IInteger div(IInteger iav) {
		
		return new DLong(value / iav.toLong());
	}

	@Override
	public IInteger setadd(IInteger pre, IInteger post) {
		this.value = pre.toLong() + post.toLong();
		return this;
	}

	@Override
	public IInteger setsub(IInteger pre, IInteger post) {
		this.value = pre.toLong() - post.toLong();
		return this;
	}

	@Override
	public IInteger setmult(IInteger pre, IInteger post) {
		this.value = pre.toLong() * post.toLong();
		return this;
	}

	@Override
	public IInteger setdiv(IInteger pre, IInteger post) {
		this.value = pre.toLong() / post.toLong();
		return this;
	}

	@Override
	public IInteger getZero() {
		return new DLong(0L);
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
		
		return new DLong(value % iav.toLong());
	}

	@Override
	public IInteger setremainder(IInteger pre, IInteger post) {
		this.value = pre.toLong() % post.toLong();
		return this;
	}

	@Override
	public int toInt() {
		return (int)value;
	}

	@Override
	public long toLong() {
		return value;
	}

	@Override
	public IInteger getOne() {
		return new DLong(1L);
	}

	@Override
	public IInteger setOne() {
		value = 1L;
		return this;
	}

	@Override
	public IInteger addinv() {
		return new DLong(-value);
	}

	@Override
	public IInteger setZero() {
		value = 0L;
		return this;
	}

	@Override
	public IInteger setaddinv(IInteger a) {
		value = -a.toLong();
		return this;
	}

	@Override
	public void set(int v) {
		value = (long)v;
	}

	@Override
	public void set(long v) {
		value = v;
	}

	@Override
	public boolean isZero() {
		return value == 0L;
	}

	@Override
	public IInteger getNew() {
		return new DLong();
	}

	@Override
	public IInteger set(IInteger par) {
		this.value = par.toLong();
		
		return this;
	}

	@Override
	public IInteger getTemporary() {
		return (IInteger) tu.getTemp();
	}

	@Override
	public void remove(IInteger tval) {
		tu.release(tval);
	}

}
