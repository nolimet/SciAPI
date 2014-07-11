package abr.heatcraft.itementity;

import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import sciapi.api.mc.item.ItemEntity;

//Currently not used.
public class IELiquidTank extends ItemEntity implements IFluidHandler {

	public FluidTank fluidtank;
	
	public IELiquidTank() {
		fluidtank = new FluidTank(1000);
}
	
	public IELiquidTank(int capacity)
	{
		fluidtank = new FluidTank(capacity);
	}
	
	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		return fluidtank.fill(resource, doFill);
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource,
			boolean doDrain) {
		if(fluidtank.getFluidAmount() == 0 || !(fluidtank.getFluid().isFluidEqual(resource)))
			return null;
		return fluidtank.drain(resource.amount, doDrain);
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		return fluidtank.drain(maxDrain, doDrain);
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		return true;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return true;
	}

	@Deprecated
	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return null;
	}

}
