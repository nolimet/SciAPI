package sciapi.api.chem.chemical;

import java.util.*;

import sciapi.api.basis.bunch.bunchreal.*;
import sciapi.api.chem.elem.Element;

public class Chemical implements IBunchRealIdType {
	
	/**
	 * String-ID and (Unlocalized) Name for the chemical.
	 * */
	private String id, name;
	
	/**
	 * Given ID
	 * */
	private int giveniid;
	
	public Element[] elementlist;
	public int[] elemamount;
	
	public Chemical(String pid){
		this.id = pid;
	}
	
	public Chemical setName(String pname){
		this.name = pname;
		return this;
	}
	
	public void giveId(int iid) {
		giveniid = iid;
	}


	@Override
	public double getStackLimit(IBunchReal bunch) {
		//No Stack Limit!
		return 1.0e+20;
	}


	@Override
	public int getId() {
		return giveniid;
	}
	
	public String getStrId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
}
