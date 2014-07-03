package sciapi.api.unit;

import java.util.*;

import sciapi.api.basis.storage.IncPool;

import com.google.common.collect.*;

public class UnitDictionary {
	private static UnitDictionary instance;
	
	//Maximum Value for Dimension of a Base Unit.
	public static final int MAX_DIMENSION = 10;
	
	static{
		instance = new UnitDictionary();
	}
	
	protected List<String> basemmap = new ArrayList<String>();
	protected Map<String, MInfo> unitnamemap = new HashMap<String, MInfo>();
	protected Map<InterMDimension, MInfo> unitdimmap = new HashMap<InterMDimension, MInfo>();
	protected Map<String, IncPool<Measurement>> poolmap;
	
	public static UnitDictionary instance(){
		return instance;
	}
	
	/**
	 * add Base Measurement
	 * (etc. Length, Mass, etc.)
	 * */
	public boolean addBaseMeasurement(String bmname){
		return basemmap.add(bmname);
	}
	
	/**
	 * get Measurement from Name.
	 * */
	public MInfo getMeasurement(String mname){
		return unitnamemap.get(mname);
	}
	
	/**
	 * get Measurement from Dimension.
	 * */
	public MInfo getMeasurement(MDimension mdim){
		InterMDimension imdim = new InterMDimension(mdim);
		
		if(!unitdimmap.containsKey(imdim)){
			MInfo info = new MInfo(imdim);
			unitdimmap.put(imdim, info);
			return info;
		}
		return unitdimmap.get(imdim);
	}
	
	/**
	 * get Measurement from Dimension.
	 * (mdim / divdim)
	 * */
	public MInfo getMeasurement(MDimension mdim, MDimension divdim){
		InterMDimension imdim = new InterMDimension(mdim, divdim);
		
		if(!unitdimmap.containsKey(imdim)){
			MInfo info = new MInfo(imdim);
			unitdimmap.put(imdim, info);
			return info;
		}
		return unitdimmap.get(imdim);
	}
	
	/**
	 * Add new Unit to the Measurement.
	 * 
	 * @param mname name of the Measurement.
	 * @param unitname name of the unit.
	 * @param stdval value of 1 Unit as Standard Unit
	 * */
	public void addUnit(String mname, String unitname, double stdval){
		getMeasurement(mname).put(unitname, stdval);
	}
	
	/**
	 * Add new Unit to the Measurement.
	 * 
	 * @param mname name of the Measurement.
	 * @param unitname name of the unit.
	 * @param zeroval value of 0 Unit as Standard Unit
	 * @param stdval value of 1 Unit difference as Standard Unit
	 * */
	public void addUnit(String mname, String unitname, double zeroval, double stdval){
		getMeasurement(mname).put(unitname, zeroval, stdval);
	}
	
	
	public Measurement getNew(MInfo mi){
		try {
			return mi.mclass.newInstance();
		} catch (InstantiationException e) {
			throw new IllegalArgumentException("Invalid Measurement Class!");
		} catch (IllegalAccessException e) {
			throw new IllegalArgumentException("Invalid Measurement Class!");
		}
	}
	
	@Deprecated
	public Measurement getZero(Measurement from){
		Measurement p = getNew(from.getMeasurement());
		p.set(0.0);
		return p;
	}
	
	@Deprecated
	public Measurement add(Measurement pre, Measurement post){
		Measurement p = getNew(pre.getMeasurement());
		p.set(pre.toDouble() + post.toDouble());
		return p;
	}
	
	@Deprecated
	public Measurement sub(Measurement pre, Measurement post){
		Measurement p = getNew(pre.getMeasurement());
		p.set(pre.toDouble() - post.toDouble());
		return p;
	}	
	
	@Deprecated
	public Measurement mult(Measurement pre, Measurement post){
		try{
			MInfo idm = unitdimmap.get(pre.getMeasurement().mdim.mult(post.getMeasurement().mdim));
			Measurement p = getNew(idm);
			p.set(pre.toDouble() * post.toDouble());
			return p;
		}
		catch(NullPointerException e){
			throw new IllegalArgumentException("Invalid Multiplication! This Unit Does not Exist!");
		}
	}
	
	@Deprecated
	public Measurement div(Measurement pre, Measurement post){
		try{
			MInfo idm = unitdimmap.get(pre.getMeasurement().mdim.div(post.getMeasurement().mdim));
			Measurement p = getNew(idm);
			p.set(pre.toDouble() / post.toDouble());
			return p;
		}
		catch(NullPointerException e){
			throw new IllegalArgumentException("Invalid Multiplication! This Unit Does not Exist!");
		}
	}
	
	@Deprecated
	public Measurement mult(Measurement pre, double val){
		Measurement p = getNew(pre.getMeasurement());
		p.set(pre.toDouble() * val);
		return p;
	}
	
	@Deprecated
	public Measurement div(Measurement pre, double val){
		Measurement p = getNew(pre.getMeasurement());
		p.set(pre.toDouble() / val);
		return p;
	}
	
	class InterMDimension{
		protected List<Integer> list;
		
		public InterMDimension(MDimension mdim){
			list = new ArrayList(basemmap.size());
			Update();
			
			for(int i = 0; i < mdim.basename.length; i++){
				String bname = mdim.basename[i];
				int ind = basemmap.indexOf(mdim.basename[i]);
				if(ind > 0)
					list.set(ind, list.get(ind) + 1);
			}
		}
		
		public InterMDimension(MDimension mdim, MDimension divdim){
			list = new ArrayList(basemmap.size());
			Update();
			
			for(int i = 0; i < mdim.basename.length; i++){
				String bname = mdim.basename[i];
				int ind = basemmap.indexOf(mdim.basename[i]);
				if(ind > 0)
					list.set(ind, list.get(ind) + 1);
			}
			
			for(int i = 0; i < divdim.basename.length; i++){
				String bname = divdim.basename[i];
				int ind = basemmap.indexOf(divdim.basename[i]);
				if(ind > 0)
					list.set(ind, list.get(ind) - 1);
			}
		}
		
		public InterMDimension(List<Integer> plist){
			list = plist;
		}
		
		public InterMDimension mult(InterMDimension pimd){
			Update();
			pimd.Update();
			
			List<Integer> list2 = new ArrayList(basemmap.size());
			
			for(int i = 0; i < list.size(); i++){
				list2.set(i, list.get(i) + pimd.list.get(i));
			}
			
			return new InterMDimension(list2);
		}
		
		public InterMDimension div(InterMDimension pimd){
			Update();
			pimd.Update();
			
			List<Integer> list2 = new ArrayList(basemmap.size());
			
			for(int i = 0; i < list.size(); i++){
				list2.set(i, list.get(i) - pimd.list.get(i));
			}
			
			return new InterMDimension(list2);
		}
		
		@Override
		public boolean equals(Object o){
			Update();
			if(o instanceof InterMDimension){
				return list.equals(((InterMDimension) o).list);
			}
			
			return false;
		}
		
		@Override
		public int hashCode(){
			int hash = 0;
			
			for(Integer j : list){
				hash *= (MAX_DIMENSION + 1);
				hash += j;
			}
			
			return hash;
		}
		
		private void Update(){
			for(int i = list.size(); i < basemmap.size(); i++)
				list.add(0);
		}
	}
	
	public class MInfo {
		
		protected String mname, stduname;
		
		/**
		 * "Dimension" of this measurement
		 * */
		protected InterMDimension mdim;
		
		protected Class<? extends Measurement> mclass;
		
		protected Map<String, UnitInfo> uimap;
		
		public MInfo(InterMDimension pmdim){
			mdim = pmdim;
			uimap = new HashMap<String, UnitInfo>();
		}
		
		public MInfo setName(String name){
			mname = name;
			unitnamemap.put(name, this);
			return this;
		}
		
		public MInfo setClass(Class<? extends Measurement> pmclass){
			mclass = pmclass;
			return this;
		}
		
		/**
		 * Sets Name of Standard Unit
		 * */
		public MInfo setStdName(String pstduname){
			stduname = pstduname;
			return this;
		}
		
		public void put(String unitname, double ptostd){
			uimap.put(unitname, new UnitInfo(ptostd));
		}
		
		public void put(String unitname, double pzerovalue, double ptostd){
			uimap.put(unitname, new UnitInfo(pzerovalue, ptostd));
		}
		
		public double toStandard(double pvalue, String unitname){
			if(unitname.equals(stduname))
				return pvalue;
			
			try{
				UnitInfo ui = uimap.get(unitname);
				return ui.toStandard(pvalue);
			}
			catch(NullPointerException e){
				throw new IllegalArgumentException("Invalid Unit: " + unitname + " for " + mname);
			}
		}
		
		public double fromStandard(double pvalue, String unitname){
			if(unitname.equals(stduname))
				return pvalue;
			
			try{
				return uimap.get(unitname).fromStandard(pvalue);
			}
			catch(NullPointerException e){
				throw new IllegalArgumentException("Invalid Unit: " + unitname + " for " + mname);
			}
		}
	}
	
	protected class UnitInfo{
		protected boolean isZeroDetermined;
		protected double tostd;
		protected double fromstd;
		protected double zero_value;
		
		public UnitInfo(double ptostd){
			tostd = ptostd;
			fromstd = 1 / ptostd;
			isZeroDetermined = true;
		}
		
		public UnitInfo(double pzerovalue, double ptostd){
			zero_value = pzerovalue;
			tostd = ptostd;
			fromstd = 1 / ptostd;
			isZeroDetermined = false;
		}
		
		public double toStandard(double val){
			if(isZeroDetermined)
				return tostd * val;
			return tostd * val + zero_value;
		}
		
		public double fromStandard(double val){
			if(isZeroDetermined)
				return fromstd * val;
			return fromstd * (val - zero_value);
		}
	}
}
