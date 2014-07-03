package abr.heatcraft.itementity;

import java.util.Map;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import abr.heatcraft.enchant.HEnchantments;
import abr.heatcraft.multiitem.MultiItemHeater;
import sciapi.api.abstraction.absutil.IInsBase;
import sciapi.api.abstraction.pos.*;
import sciapi.api.heat.HeatSystem;
import sciapi.api.heat.IHeatComponent;
import sciapi.api.mc.item.multiitem.*;

public class IEFuelProgress extends IEMultiComponent implements IHeatComponent {

	public short heatprooflvl = 0;
	public short explosivelvl = 0;
	public short thermalefflvl = 0;
	
	public void addMultiItem(MultiItem mitem) {
		super.addMultiItem(mitem);
	}
	
	@Override
	public double getTemperature() {
		for(MultiItem mitem : this.parmultiitem)
			if(mitem instanceof MultiItemHeater){
				return ((MultiItemHeater)mitem).getTemperature();
			}
		
		return 0;
	}

	@Override
	public boolean isHeatTransferable(IDirection dir) {
		for(MultiItem mitem : this.parmultiitem)
			if(mitem instanceof MultiItemHeater)
				return true;
		
		return false;
	}

	@Override
	public double getHeatTransferRate(IDirection dir) {
		return 250.0e+3;
	}

	@Override
	public Map<IAbsPosition, Double> getExternalTransferList() {
		return null;
	}

	@Override
	public void onHeatTransfer(double heat) {
		for(MultiItem mitem : this.parmultiitem)
			if(mitem instanceof MultiItemHeater){
				((MultiItemHeater) mitem).onHeatTransfer(heat);
				return;
			}
	}

	
	public int getProgress(int par1){
		for(MultiItem mitem : this.parmultiitem)
			if(mitem instanceof MultiItemHeater)
				return ((MultiItemHeater) mitem).getBurnTimeRemainingScaled(par1);
		return 0;
	}
	
	public void updateEntity(){
		if(!worldObj.isRemote && worldObj.getItemStack(pos) != null){
			int prog = getProgress(1000);
			if(prog > 0)
				worldObj.getItemStack(pos).setItemDamage(1000-prog);
			else worldObj.getItemStack(pos).setItemDamage(0);
		}
		super.updateEntity();
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
				
				else if(HEnchantments.isExplosive(enchant))
					explosivelvl = enchant.getShort("lvl");
				
				else if(HEnchantments.isThermalEff(enchant))
					thermalefflvl = enchant.getShort("lvl");
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
		explosivelvl = 0;
		
		super.invalidate();
	}
}
