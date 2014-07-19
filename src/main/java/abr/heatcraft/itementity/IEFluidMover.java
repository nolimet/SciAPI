package abr.heatcraft.itementity;

import abr.heatcraft.item.ItemLiquidTank;
import abr.heatcraft.recipes.CastingRecipes;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidContainerItem;
import net.minecraftforge.fluids.ItemFluidContainer;
import sciapi.api.def.euclidian.EVecInt;
import sciapi.api.mc.inventory.pos.McInvDirection;
import sciapi.api.mc.inventory.pos.McInvPos;
import sciapi.api.mc.item.ItemEntity;

public class IEFluidMover extends ItemEntity {
		
	public IEFluidMover() {
		super();
	}
	
	@Override
	public void updateEntity()
	{
		if(!worldObj.isRemote && worldObj.getItemStack(pos) != null)
		{
			EVecInt dvec = McInvDirection.LEFT.toDifference();
			McInvPos cur = pos.getDiffPos(dvec);
			dvec = McInvDirection.RIGHT.toDifference();
			McInvPos next = pos.getDiffPos(dvec);
			
			ItemStack from = worldObj.getItemStack(cur);
			ItemStack to = worldObj.getItemStack(next);
			
			if(from == null || !(from.getItem() instanceof IFluidContainerItem))
				return;
			
			if(to == null || !(to.getItem() instanceof IFluidContainerItem))
				return;
			
			IFluidContainerItem fri = (IFluidContainerItem) from.getItem();
			IFluidContainerItem toi = (IFluidContainerItem) to.getItem();
			
			FluidStack drain = fri.drain(from, 10, false);
			if(drain == null || drain.amount == 0)
				return;
			if(toi.fill(to, drain, false) != 0)
			{
				int dr = toi.fill(to, drain, true);
				fri.drain(from, dr, true);
			}
		}
	}

}
