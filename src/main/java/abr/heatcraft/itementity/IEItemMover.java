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

public class IEItemMover extends ItemEntity {
	
	int delay = 0;
	
	public IEItemMover() {
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
			
			if(from == null)
				return;
			
			if(worldObj.toEntry(next.vec) >= worldObj.inv.getSizeInventory())
				return;
			
			if(to != null)
			{
				if(!to.isItemEqual(from))
					return;
				int max = to.getMaxStackSize();
				if(from.stackSize <= 0 || (to.stackSize + 1) > max)
					return;
			}
			
			delay++;
			worldObj.getItemStack(pos).setItemDamage(100-delay);
			
			if(delay >= 100)
			{
				delay = 0;
				
				if(from.stackSize <= 1)
					worldObj.setItemStack(cur, null);
				else from.stackSize--;
				
				if(to == null)
					worldObj.setItemStack(next, new ItemStack(from.getItem()));
				else to.stackSize++;
			}
		}
	}

}
