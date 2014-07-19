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

public class IEFluidFiller extends ItemEntity {
	
	int delay = 0;
	
	public IEFluidFiller() {
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
			dvec = McInvDirection.UP.toDifference();
			McInvPos fr = pos.getDiffPos(dvec);
			
			ItemStack frup = worldObj.getItemStack(fr);
			ItemStack from = worldObj.getItemStack(cur);
			ItemStack post = worldObj.getItemStack(next);
			
			if(frup == null || !(frup.getItem() instanceof IFluidContainerItem))
				return;
			
			if(from == null || !FluidContainerRegistry.isEmptyContainer(from))
				return;
			
			IFluidContainerItem con = (IFluidContainerItem) frup.getItem();
			ItemStack copy = from.copy();
			ItemStack res = FluidContainerRegistry.fillFluidContainer(con.drain(frup, Integer.MAX_VALUE, false), copy);
			
			if(res == null)
				return;
			
			if(post != null)
			{
				if(!post.isItemEqual(res))
					return;
				int max = post.getMaxStackSize();
				if((res.stackSize + post.stackSize) > max)
					return;
			}
			
			delay++;
			worldObj.getItemStack(pos).setItemDamage(100-delay);
			
			if(delay >= 100)
			{
				delay = 0;
				
				FluidStack filled = FluidContainerRegistry.getFluidForFilledItem(res);
				
				if(from.stackSize <= 1)
					worldObj.setItemStack(cur, null);
				else from.stackSize--;
				
				if(post == null)
					worldObj.setItemStack(next, res);
				else post.stackSize += res.stackSize;
				
				con.drain(frup, filled.amount, true);
			}
		}
	}

}
