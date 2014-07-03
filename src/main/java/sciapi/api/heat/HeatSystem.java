package sciapi.api.heat;

import java.util.*;

import cpw.mods.fml.relauncher.Side;
import sciapi.api.abstraction.pos.*;
import sciapi.api.mc.tick.ITickStartTask;

public class HeatSystem implements ITickStartTask {
	
	private static HeatSystem instance;
	private Map<IHeatComponent, HeatCompCalcUnit> list;
	
	public static HeatSystem getHeatSystem(){
		if(instance == null)
			instance = new HeatSystem();
		return instance;
	}
	
	public HeatSystem(){
		list = new TreeMap<IHeatComponent, HeatCompCalcUnit>(new HeatComparator());
	}
	
	public void addToSystem(IHeatComponent obj){
		if(!list.containsKey(obj))
			list.put(obj, new HeatCompCalcUnit(this, obj));
	}
	
	public void deleteFromSystem(IHeatComponent obj){
		list.remove(obj);
	}
	
	public HeatCompCalcUnit getHCCU(IHeatComponent obj){
		return list.get(obj);
	}
	
	
	public void tickStart(){
		calcProc();
	}
	
	public void calcProc(){
		for(HeatCompCalcUnit hccu : list.values()){
			hccu.init();
		}
		
		for(HeatCompCalcUnit hccu : list.values()){
			hccu.calc();
		}
		
		for(HeatCompCalcUnit hccu : list.values()){
			hccu.apply();
		}
	}
}
