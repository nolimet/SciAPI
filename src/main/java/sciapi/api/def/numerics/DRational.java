package sciapi.api.def.numerics;

import sciapi.api.abstraction.numerics.IAbsValue;
import sciapi.api.abstraction.numerics.ICompValue;
import sciapi.api.abstraction.numerics.IInteger;
import sciapi.api.abstraction.numerics.IReal;
import sciapi.api.basis.math.BMath;
import sciapi.api.basis.temporaries.DTempManager;
import sciapi.api.basis.temporaries.TempUtil;

public class DRational implements IReal {
	
	private static TempUtil tu = new TempUtil(new DRational());
	
	//value = num / den.
	int num;
	int den;
	
	public DRational(){
		num = 0;
		den = 1;
	}
	
	public DRational(int n){
		num = n;
		den = 1;
	}
	
	public DRational(int u, int d){
		int g = BMath.GCD(u, d);
		if(g != 1){
			num = u / g;
			den = d / g;
		}
		else{
			num = u;
			den = d;
		}
	}
	
	public DRational(IInteger i){
		num = i.toInt();
		den = 1;
	}
	
	public void set(int u, int d){
		int g = BMath.GCD(u, d);
		if(g != 1){
			num = u / g;
			den = d / g;
		}
		else{
			num = u;
			den = d;
		}
	}
	
	public void reduce(){
		int g = BMath.GCD(num, den);
		if(g != 1){
			num = num / g;
			den = den / g;
		}
	}

	@Override
	public IReal add(IReal iav) {
		DRational r = (DRational)iav;
		return new DRational(r.den * this.num + r.num * this.den, r.den * this.den);
	}

	@Override
	public IReal sub(IReal iav) {		
		DRational r = (DRational)iav;
		return new DRational(r.den * this.num - r.num * this.den, r.den * this.den);
	}

	@Override
	public IReal mult(IReal iav) {
		DRational r = (DRational)iav;
		return new DRational(r.num * this.num, r.den * this.den);
	}

	@Override
	public IReal div(IReal iav) {
		DRational r = (DRational)iav;
		return new DRational(this.num * r.den, this.den * r.num);
	}

	@Override
	public IReal setadd(IReal pre, IReal post) {
		DRational r = (DRational)pre;
		DRational o = (DRational)post;
		this.num = r.den * o.num + r.num * o.den;
		this.den = r.den * o.den;
		this.reduce();
		return this;
	}

	@Override
	public IReal setsub(IReal pre, IReal post) {
		DRational r = (DRational)pre;
		DRational o = (DRational)post;
		this.num = r.num * o.den - r.num * o.den;
		this.den = r.den * o.den;
		this.reduce();
		return this;
	}

	@Override
	public IReal setmult(IReal pre, IReal post) {
		DRational r = (DRational)pre;
		DRational o = (DRational)post;
		this.num = r.num * o.num;
		this.den = r.den * o.den;
		this.reduce();
		return this;
	}

	@Override
	public IReal setdiv(IReal pre, IReal post) {
		DRational r = (DRational)pre;
		DRational o = (DRational)post;
		this.num = r.num * o.den;
		this.den = r.den * o.num;
		this.reduce();
		return this;
	}

	@Override
	public IReal getZero() {
		return new DRational(0);
	}
	
	@Override
	public boolean equals(Object o){
		IReal r = (IReal)o;
		
		return BMath.isSame(((double)num) / ((double)den), r.toDouble());
	}

	@Override
	public int compareTo(IReal o) {
		return BMath.compare(((double)num) / ((double)den), o.toDouble());
	}

	@Override
	public double toDouble() {
		return ((double)num) / ((double)den);
	}

	@Override
	public float toFloat() {
		return ((float)num) / ((float)den);
	}

	@Override
	public IReal getOne() {
		return new DRational(1);
	}

	@Override
	public IReal setOne() {
		num = 1;
		den = 1;
		return this;
	}

	@Override
	public IReal addinv() {
		return new DRational(-num, den);
	}

	@Override
	public IReal setZero() {
		num = 0;
		den = 1;
		return this;
	}

	@Override
	public IReal setaddinv(IReal a) {
		num = -((DRational)a).num;
		den = ((DRational)a).den;
		return this;
	}

	@Override
	public IReal multinv() {
		return new DRational(den, num);
	}

	@Override
	public IReal setmultinv(IReal a) {
		num = ((DRational)a).den;
		den = ((DRational)a).num;
		return this;
	}

	@Deprecated
	@Override
	public void set(double v) {
//		value = v;
	}

	@Deprecated
	@Override
	public void set(float v) {
//		value = v;
	}

	@Override
	public boolean isZero() {
		return BMath.isSame(((double)num) / ((double)den), 0);
	}

	@Override
	public IReal getNew() {
		return new DRational();
	}

	@Override
	public IReal set(IReal par) {
		this.num = ((DRational)par).num;
		this.den = ((DRational)par).den;
		
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
