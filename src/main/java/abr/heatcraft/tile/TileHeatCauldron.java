package abr.heatcraft.tile;

import abr.heatcraft.api.fluid.HeatFluidInfo;
import abr.heatcraft.api.fluid.HeatFluidList;
import abr.heatcraft.block.BlockHeatFurnace;
import sciapi.api.heat.def.DefHeatComponent;
import sciapi.api.unit.bsci.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.*;


public class TileHeatCauldron extends DefHeatComponent implements IFluidHandler{
	
    protected FluidTank tank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME * 4);
    private double liqamount = 0;
    private double defCapacity;
    private double smokingRate = 0;
    private int prevam = 0;
	
	public TileHeatCauldron(){
		double tr = 300.0e+3;
		
		this.Temp = BSciConstants.air_temp.toDouble();
		this.defCapacity = this.HeatCapacity = new HeatCapacity(850.39, "kcal/K").toDouble() * 0.2;
		this.isTransferable = new boolean[]{true, false, true, true, true, true};
		this.transferRate = new double[]{tr, tr, tr, tr, tr, tr};
	}
	
    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
        tank.readFromNBT(tag);
        if(tag.hasKey("Empty"))
        	tank.drain(Integer.MAX_VALUE, true);
        liqamount = tag.getDouble("liqamount");
        prevam = tag.getInteger("prevam");
        smokingRate = tag.getDouble("smokingrate");
    }

    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        tank.writeToNBT(tag);
        tag.setDouble("liqamount", liqamount);
        tag.setInteger("prevam", prevam);
        tag.setDouble("smokingrate", smokingRate);
    }
	
    
	@Override
	public void onHeatTransfer(double heat) {
		this.recalcHeatCapacity();
		
		if(tank.getFluidAmount() == 0){
			super.onHeatTransfer(heat);
			this.smokingRate = 0;
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
					worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
				}
				else{
					tank.drain(Integer.MAX_VALUE, true);
					heat -= hfi.getlatentHeat().toDouble() / FluidContainerRegistry.BUCKET_VOLUME * this.liqamount;
					this.liqamount = 0;
					super.onHeatTransfer(heat);
				}
				
				smokingRate = evamount;
			}
			else{
				super.onHeatTransfer(heat);
				smokingRate = 0;
			}
		}
		else{
			if(heat <= 0 && hfi.getboilTemp().toDouble() >= this.Temp){
				heat += (this.Temp - hfi.getboilTemp().toDouble()) * this.defCapacity;
				
				this.Temp = hfi.getboilTemp().toDouble();
				
				evamount = -heat / hfi.getlatentHeat().toDouble() * FluidContainerRegistry.BUCKET_VOLUME;
				
				if(liqamount > evamount){
					liqamount -= evamount;
					tank.getFluid().amount = (int)Math.ceil(liqamount);
					worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
				}
				else{
					tank.drain(Integer.MAX_VALUE, true);
					heat += hfi.getlatentHeat().toDouble() / FluidContainerRegistry.BUCKET_VOLUME * this.liqamount;
					this.liqamount = 0;
					super.onHeatTransfer(heat);
				}
				
				smokingRate = evamount;
			}
			else{ 
				super.onHeatTransfer(heat);
				smokingRate = 0;
			}
		}
		
		prevam = tank.getFluidAmount();
	}
	
	public void update(){
		this.smokingRate = 0;
		
		if(tank.getFluidAmount() == 0){
			this.liqamount = 0;
			this.recalcHeatCapacity();
			return;
		}
		
		HeatFluidInfo hfi = this.getFluidInfo();
		if(this.liqamount == 0)
			this.liqamount = tank.getFluidAmount();
		else{
			this.liqamount += (tank.getFluidAmount() - prevam);
		}
		prevam = tank.getFluidAmount();
		
		recalcHeatCapacity();
		
		if(!this.worldObj.isRemote)
			this.onHeatTransfer(0.0);
		
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}
	
	public void recalcHeatCapacity(){
		this.HeatCapacity = this.defCapacity;

		if(tank.getFluidAmount() > 0) {
			HeatFluidInfo hfi = this.getFluidInfo();
			this.HeatCapacity += hfi.getspecificHeat().toDouble() / FluidContainerRegistry.BUCKET_VOLUME * this.liqamount;
		}
	}
	
	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		if(resource != null && HeatFluidList.contains(resource.getFluid())){
			int f =  tank.fill(resource, doFill);
			if(doFill) update();
			return f;
		}
			
		return 0;
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource,
			boolean doDrain) {
        if (resource == null || !resource.isFluidEqual(tank.getFluid()))
        {
            return null;
        }
      
		FluidStack d = tank.drain(resource.amount, doDrain);
		if(doDrain) update();
		return d;
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		FluidStack d = tank.drain(maxDrain, doDrain);
		if(doDrain) update();
		return d;
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		if(HeatFluidList.contains(fluid))
			return true;
		return false;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		if(HeatFluidList.contains(fluid))
			return true;
		return false;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
        return new FluidTankInfo[] { tank.getInfo() };
	}
	
	
	private HeatFluidInfo getFluidInfo(){
		return HeatFluidList.getInfo(tank.getFluid().getFluid());
	}
	
	
	@Override
    public void updateEntity(){
		if(!this.worldObj.isRemote)
			this.onHeatTransfer((-1.0) * (this.Temp - BSciConstants.air_temp.toDouble()));
    }
	

	public double smokingRate() {
		return smokingRate / 10.0;
	}
	
	public boolean isHot() {
		return this.Temp > 1000.0;
	}
}
