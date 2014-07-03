package sciapi.api.unit;

import sciapi.api.abstraction.numerics.*;
import sciapi.api.basis.math.BMath;

/**
 * Abstract Class for Measurement.
 * */
public abstract  class Measurement implements IReal{
	
	/**
	 * Standard value
	 * */
	private double value;
	
	/**
	 * Get Name of this Measurement.
	 * Must be Constant.
	 * */
	abstract protected String getMeasurementName();
	
	@Deprecated
	public Measurement() {}
	
	/**
	 * Get Measurement in Standard Unit.
	 * */
	public Measurement(double pvalue){
		value = pvalue;
	}
	
	/**
	 * Get Measurement with Unit.
	 * */
	public Measurement(double pvalue, String unitname){
		value = getMeasurement().toStandard(pvalue, unitname);
	}
	
	
	protected UnitDictionary.MInfo getMeasurement(){
		return UnitDictionary.instance().getMeasurement(this.getMeasurementName());
	}
	

	public void setValue(double pvalue, String unitname){
		value = getMeasurement().toStandard(pvalue, unitname);
	}
	
	public double getValue(String unitname){
		return getMeasurement().fromStandard(value, unitname);
	}

	
	@Override
	public IReal add(IReal iav) {
		Measurement m = (Measurement) iav;
		if(equalMeasurement(m)){
			return UnitDictionary.instance().add(this, m);
		}
		else throw new MeasurementException(this, m, "add");
	}

	@Override
	public IReal sub(IReal iav) {
		Measurement m = (Measurement) iav;
		if(equalMeasurement(m)){
			return UnitDictionary.instance().sub(this, m);
		}
		else throw new MeasurementException(this, m, "subtract");
	}

	@Override
	public IReal mult(IReal iav) {
		Measurement m = (Measurement) iav;

		return UnitDictionary.instance().mult(this, m);
	}

	@Override
	public IReal div(IReal iav) {
		Measurement m = (Measurement) iav;

		return UnitDictionary.instance().div(this, m);
	}
	
	@Override
	public IReal setadd(IReal pre, IReal post) {
		Measurement mpre = (Measurement) pre;
		Measurement mpost = (Measurement) post;
		
		if(mpre.equalMeasurement(mpost)){
			this.value = mpre.value + mpost.value;
			return this;
		}
		else throw new MeasurementException(mpre, mpost, "add");
	}

	@Override
	public IReal setsub(IReal pre, IReal post) {
		Measurement mpre = (Measurement) pre;
		Measurement mpost = (Measurement) post;
		
		if(mpre.equalMeasurement(mpost)){
			this.value = mpre.value - mpost.value;
			return this;
		}
		else throw new MeasurementException(mpre, mpost, "subtract");
	}

	/**
	 * Set this to multiplied value. </p>
	 * 
	 * This method do not Check the dimension of the Measurement. </p>
	 * */
	@Override
	public IReal setmult(IReal pre, IReal post) {
		Measurement mpre = (Measurement) pre;
		Measurement mpost = (Measurement) post;
		
		this.value = mpre.value * mpost.value;
		return this;
	}

	/**
	 * Set this to divided value. </p>
	 * 
	 * This method do not Check the dimension of the Measurement. </p>
	 * */
	@Override
	public IReal setdiv(IReal pre, IReal post) {
		Measurement mpre = (Measurement) pre;
		Measurement mpost = (Measurement) post;
		
		this.value = mpre.value / mpost.value;
		return this;
	}
	
	public Measurement mult(double val){
		return UnitDictionary.instance().mult(this, val);
	}
	
	public Measurement div(double val){
		return UnitDictionary.instance().div(this, val);
	}
	
	public Measurement setmult(double val){
		this.value *= val;
		return this;
	}
	
	public Measurement setdiv(double val){
		this.value /= val;
		return this;
	}

	@Override
	public double toDouble() {
		return value;
	}

	@Override
	public float toFloat() {
		return (float)value;
	}
	
	public boolean equalMeasurement(Measurement m){
		return m.getMeasurementName().equals(getMeasurementName());
	}
	
	@Override
	public boolean equals(Object o){
		Measurement m = (Measurement) o;

		if(this.equalMeasurement(m)){
			return BMath.isSame(this.value, m.value);
		}
		else throw new MeasurementException(this, m, "equals");
	}

	@Override
	public int compareTo(IReal o) {
		Measurement m = (Measurement) o;
		
		if(equalMeasurement(m)){
			return BMath.compare(this.value, m.value);
		}
		else throw new MeasurementException(this, m, "compare");
	}

	
	@Override
	public IReal getZero() {
		return UnitDictionary.instance().getZero(this);
	}

	@Override
	public boolean isZero() {
		if(BMath.isSame(this.value, 0.0))
			return true;
		return false;
	}

	@Deprecated
	@Override
	public IReal getOne() {
		return null;
	}

	@Override
	public IReal setOne() {
		this.value = 1.0;
		return this;
	}

	@Deprecated
	@Override
	public IReal addinv() {
		return null;
	}

	@Override
	public IReal setZero() {
		this.value = 0.0;
		return this;
	}

	@Override
	public IReal setaddinv(IReal a) {
		this.value = - a.toDouble();
		return this;
	}

	@Deprecated
	@Override
	public IReal multinv() {
		return null;
	}

	@Deprecated
	@Override
	public IReal setmultinv(IReal a) {
		return null;
	}

	@Deprecated
	@Override
	public void set(double v) {
		value = v;
	}

	@Deprecated
	@Override
	public void set(float v) {
		value = v;
	}

	@Override
	public IReal getNew() {
		return this.getZero();
	}

	/**
	 * Does not Check if Measurement is same or not.
	 * */
	@Deprecated
	@Override
	public IReal set(IReal par) {
		value = par.toDouble();
		return this;
	}

	/**
	 * Temporal Support for Measurements is not available now.
	 * */
	@Deprecated
	@Override
	public IReal getTemporary() {
		return UnitDictionary.instance().getNew(getMeasurement());
	}

	@Deprecated
	@Override
	public void remove(IReal tval) {
	}
}