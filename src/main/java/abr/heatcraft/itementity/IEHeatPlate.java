package abr.heatcraft.itementity;

import java.util.Map;

import abr.heatcraft.enchant.EnchantmentHeatProof;
import abr.heatcraft.enchant.HEnchantments;
import abr.heatcraft.multiitem.MultiItemHeater;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import sciapi.api.abstraction.absutil.IInsBase;
import sciapi.api.abstraction.pos.IAbsPosition;
import sciapi.api.abstraction.pos.IDirection;
import sciapi.api.heat.HeatSystem;
import sciapi.api.heat.IHeatComponent;
import sciapi.api.mc.inventory.pos.McInvWorld;
import sciapi.api.mc.item.multiitem.IEMultiComponent;
import sciapi.api.mc.item.multiitem.MultiItem;
import sciapi.api.unit.bsci.BSciConstants;
import sciapi.api.unit.bsci.HeatCapacity;

public class IEHeatPlate extends IEMultiComponent implements IHeatComponent {
	
	/**
	 * Heat Capacity, determines Temperature Change Rate to received Heat.
	 * */
	public double HeatCapacity = new HeatCapacity(465.25, "kcal/K").toDouble() * 0.05;

	public short heatprooflvl = 0;

	
	/**
	 * Temperature of this plate.
	 * */
	private double Temp;
	
	
	public void syncTemperature() {
		for(MultiItem mitem : this.parmultiitem)
			if(mitem instanceof MultiItemHeater){
				Temp = ((MultiItemHeater)mitem).getTemperature();
			}
	}
	
	@Override
	public double getTemperature() {
		syncTemperature();
		
		return Temp;
	}

	@Override
	public boolean isHeatTransferable(IDirection dir) {
		return true;
	}

	@Override
	public double getHeatTransferRate(IDirection dir) {
		return 400.0e+3;
	}

	@Override
	public Map<IAbsPosition, Double> getExternalTransferList() {
		return null;
	}

	@Override
	public void onHeatTransfer(double heat) {
		if(heat != 0)
			worldObj.onInventoryChanged();
		
		for(MultiItem mitem : this.parmultiitem)
			if(mitem instanceof MultiItemHeater){
				((MultiItemHeater)mitem).onHeatTransfer(heat);
				return;
			}
		
		onRegularTransfer(heat);
	}
	
	private double reciHC = 0.0;
	
	public void onRegularTransfer(double heat) {
		if(reciHC == 0.0)
			reciHC = 1 / HeatCapacity;
		Temp += ( heat * reciHC );
	}
	
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
	    super.readFromNBT(nbt);
	    Temp = nbt.getDouble("Temp");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
	    super.writeToNBT(nbt);
	    nbt.setDouble("Temp", Temp);
	}
	
	
	@Override
	public void updateEntity(){
        if(!this.worldObj.isRemote){
        	this.onHeatTransfer((-0.4 * EnchantmentHeatProof.getEffect(heatprooflvl))
        			* (this.Temp - BSciConstants.air_temp.toDouble()));
        }
        
        
        ItemStack is;
        
        if(!worldObj.isRemote && (is = worldObj.getItemStack(pos)) != null){
        	syncTemperature();
        	McInvWorld.getIECompound(is).setDouble("Temp", Temp);
        }
	}
	
	
	@Override
	public void validate()
	{
		if(!worldObj.isRemote)
			HeatSystem.getHeatSystem().addToSystem(this);
		
		
		NBTTagList enchants = worldObj.getItemStack(this.pos).getEnchantmentTagList();
		
		if(enchants != null){
		
			for(int i = 0; i < enchants.tagCount(); i++){
				NBTTagCompound enchant = enchants.getCompoundTagAt(i);
			
				if(HEnchantments.isHeatProof(enchant))
					heatprooflvl = enchant.getShort("lvl");
			}
		}
		
		
		super.validate();
	}

	@Override
	public void invalidate()
	{
		if(!worldObj.isRemote)
			HeatSystem.getHeatSystem().deleteFromSystem(this);
		
		heatprooflvl = 0;

		super.invalidate();
	}

}
