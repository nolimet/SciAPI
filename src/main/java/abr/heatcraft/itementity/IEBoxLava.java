package abr.heatcraft.itementity;

import java.util.Map;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.fluids.*;
import abr.heatcraft.api.fluid.*;
import abr.heatcraft.enchant.EnchantmentHeatProof;
import abr.heatcraft.enchant.HEnchantments;
import abr.heatcraft.item.HItems;
import abr.heatcraft.multiitem.MultiItemHeater;
import sciapi.api.abstraction.pos.IAbsPosition;
import sciapi.api.abstraction.pos.IDirection;
import sciapi.api.heat.HeatSystem;
import sciapi.api.heat.IHeatComponent;
import sciapi.api.mc.item.ItemEntity;
import sciapi.api.mc.item.multiitem.MultiItem;
import sciapi.api.unit.bsci.BSciConstants;
import sciapi.api.unit.bsci.HeatCapacity;

public class IEBoxLava extends ItemEntity implements IHeatComponent {
	
    protected FluidTank tank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME);
    private double liqamount = 0;
    private int prevam = 0;
    private double HeatCapacity, defCapacity;
    private double Temp;
    private short heatprooflvl = 0;
	
	public IEBoxLava(){
		this.Temp = BSciConstants.air_temp.toDouble();
		this.defCapacity = this.HeatCapacity = new HeatCapacity(465.25, "kcal/K").toDouble() * 0.2;
	}
	
    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
        tank.readFromNBT(tag);
        liqamount = tag.getDouble("liqamount");
        prevam = tag.getInteger("prevam");
    }

    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        tank.writeToNBT(tag);
        tag.setDouble("liqamount", liqamount);
        tag.setInteger("prevam", prevam);
    }
	
    
    public void onRegularTransfer(double heat) {
		Temp += ( heat / HeatCapacity );
    }
    
	@Override
	public void onHeatTransfer(double heat) {
		if(tank.getFluidAmount() == 0){
			this.onRegularTransfer(heat);
			return;
		}
		
		double evamount;
		int drain;
		
		HeatFluidInfo hfi = this.getFluidInfo();
		if(!(hfi.isCooltoRemove())){
			if(heat >= 0 && hfi.getboilTemp().toDouble() <= this.Temp){
				heat += (this.Temp - hfi.getboilTemp().toDouble()) * this.defCapacity;
				
				this.Temp = hfi.getboilTemp().toDouble();
				
				evamount = heat / hfi.getlatentHeat().toDouble() * FluidContainerRegistry.BUCKET_VOLUME;
				
				if(liqamount > evamount){
					liqamount -= evamount;
					tank.getFluid().amount = (int)Math.ceil(liqamount);
				}
				else{
					tank.drain(Integer.MAX_VALUE, true);
					heat -= hfi.getlatentHeat().toDouble() / FluidContainerRegistry.BUCKET_VOLUME * this.liqamount;
					this.liqamount = 0;
					this.onRegularTransfer(heat);
				}
			}
			else this.onRegularTransfer(heat);
		}
		else{
			if(heat <= 0 && hfi.getboilTemp().toDouble() >= this.Temp){
				heat += (this.Temp - hfi.getboilTemp().toDouble()) * this.defCapacity;
				
				this.Temp = hfi.getboilTemp().toDouble();
				
				evamount = -heat / hfi.getlatentHeat().toDouble() * FluidContainerRegistry.BUCKET_VOLUME;
				
				if(liqamount > evamount){
					liqamount -= evamount;
					tank.getFluid().amount = (int)Math.ceil(liqamount);
				}
				else{
					tank.drain(Integer.MAX_VALUE, true);
					heat += hfi.getlatentHeat().toDouble() / FluidContainerRegistry.BUCKET_VOLUME * this.liqamount;
					this.liqamount = 0;
					this.onRegularTransfer(heat);
				}
			}
			else this.onRegularTransfer(heat);
		}
		prevam = tank.getFluidAmount();
	}
	
	public void update(){
		if(tank.getFluidAmount() == 0){
			this.liqamount = 0;
			this.recalcHeatCapacity();
			return;
		}
				
		HeatFluidInfo hfi = this.getFluidInfo();
		this.HeatCapacity = hfi.getspecificHeat().getValue("kcal");
		if(this.liqamount == 0)
			this.liqamount = tank.getFluidAmount();
		else{
			this.liqamount += (tank.getFluidAmount() - prevam);
		}
		
		this.recalcHeatCapacity();
		
		prevam = tank.getFluidAmount();
		
		if(!this.worldObj.isRemote)
			this.onHeatTransfer(0.0);
	}

	public void recalcHeatCapacity(){
		this.HeatCapacity = new HeatCapacity(1929.13, "kJ/K").toDouble();

		if(tank.getFluidAmount() > 0) {
			HeatFluidInfo hfi = this.getFluidInfo();
			this.HeatCapacity += hfi.getspecificHeat().toDouble() / FluidContainerRegistry.BUCKET_VOLUME * this.liqamount;
		}
	}
	
	
	private HeatFluidInfo getFluidInfo(){
		return HeatFluidList.getInfo(tank.getFluid().getFluid());
	}
	
	
	public int getLavaAmount(int par1){
		return tank.getFluidAmount() * par1 / tank.getCapacity();
	}
	
	@Override
    public void updateEntity(){
		if(!this.worldObj.isRemote)
			this.onHeatTransfer((-0.4 * EnchantmentHeatProof.getEffect(heatprooflvl))
        			* (this.Temp - BSciConstants.air_temp.toDouble()));
		
		ItemStack st = worldObj.getItemStack(pos);
		
		if(!worldObj.isRemote && st != null)
		{
			int am = getLavaAmount(FluidContainerRegistry.BUCKET_VOLUME);
			worldObj.getItemStack(pos)
			.setItemDamage(FluidContainerRegistry.BUCKET_VOLUME - am);
			
			if(st.getItemDamage() >= HItems.boxlava.getMaxDamage())
				worldObj.setItemStack(pos, HItems.boxlava.getContainerItem(st));
		}
		
		super.updateEntity();
    }

	@Override
	public double getTemperature() {
		return this.Temp;
	}

	@Override
	public boolean isHeatTransferable(IDirection dir) {
		return true;
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
		
		tank.setFluid(null);
		tank.fill(new FluidStack(FluidRegistry.LAVA,
				FluidContainerRegistry.BUCKET_VOLUME - worldObj.getItemDamage(pos)), true);
		this.Temp = this.getFluidInfo().getboilTemp().toDouble();
		
		update();
		
		super.validate();
	}

	@Override
	public void invalidate()
	{
		if(!worldObj.isRemote)
			HeatSystem.getHeatSystem().deleteFromSystem(this);
		
		heatprooflvl = 0;
		
		tank.setFluid(null);
		
		super.invalidate();
	}
}
