package abr.heatcraft.api.fluid;

import sciapi.api.unit.bsci.*;

public class HeatFluidInfo {
	private Temperature boilTemp;
	private Energy specificHeat, latentHeat;
	private boolean coolToRemove;
	
	public HeatFluidInfo(boolean pcoolToRemove){
		coolToRemove = pcoolToRemove;
	}
	
	
	public boolean isCooltoRemove(){
		return coolToRemove;
	}

	
	/**
	 * Specific Heat in kcal. (per Bucket, per Kelvin)
	 * */
	public HeatFluidInfo setspecificHeat(double phc){
		specificHeat = new Energy(phc, "kcal");
		return this;
	}
	
	public HeatFluidInfo setspecificHeat(Energy phc){
		specificHeat = phc;
		return this;
	}
	
	public Energy getspecificHeat(){
		return specificHeat;
	}
	
	
	public HeatFluidInfo setboilTemp(double pbT){
		boilTemp = new Temperature(pbT, "K");
		return this;
	}
	
	public HeatFluidInfo setboilTemp(Temperature pbT){
		boilTemp = pbT;
		return this;
	}
	
	public Temperature getboilTemp(){
		return boilTemp;
	}
	
	
	/**
	 * Latent Heat in kcal. (per Bucket)
	 * */
	public HeatFluidInfo setlatentHeat(double plH){
		latentHeat = new Energy(plH, "kcal");
		return this;
	}
	
	public HeatFluidInfo setboilTemp(Energy plH){
		latentHeat = plH;
		return this;
	}
	
	public Energy getlatentHeat(){
		return latentHeat;
	}
}
