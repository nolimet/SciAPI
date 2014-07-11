package abr.heatcraft.itementity;

import abr.heatcraft.item.ItemLiquidTank;
import abr.heatcraft.recipes.CastingRecipes;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import sciapi.api.def.euclidian.EVecInt;
import sciapi.api.mc.inventory.pos.McInvDirection;
import sciapi.api.mc.inventory.pos.McInvPos;
import sciapi.api.mc.item.ItemEntity;

public class IECasting extends IELiquidTank {
	
	int delay = 0;
	
	public IECasting() {
		super();
	}

	public IECasting(int capacity) {
		super(capacity);
	}
	
    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
        fluidtank.readFromNBT(tag);
        fluidtank.setCapacity(tag.getInteger("cap"));
    }

    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        fluidtank.writeToNBT(tag);
        tag.setInteger("cap", fluidtank.getCapacity());
    }
	
	@Override
	public void updateEntity()
	{
		if(!worldObj.isRemote && worldObj.getItemStack(pos) != null)
		{
			FluidStack stack = ((ItemLiquidTank)getItemType()).getFluid(worldObj.getItemStack(pos));
			
			if(stack == null || stack.amount < 1000)
				return;
			
			delay++;
			
			if(delay >= 30)
			{
				delay = 0;
				
				EVecInt dvec = McInvDirection.DOWN.toDifference();
				McInvPos down = pos.getDiffPos(dvec);
			
				ItemStack bef = worldObj.getItemStack(down);
				ItemStack get = CastingRecipes.instance().getCasted(stack);
				
				if(bef == null)
				{
					worldObj.setItemStack(down, get.copy());
				}
				else if(bef.isItemEqual(get) && bef.stackSize + get.stackSize <= bef.getMaxStackSize())
				{
					bef.stackSize += get.stackSize;
				}
				else return;
				
				((ItemLiquidTank)getItemType()).drain(worldObj.getItemStack(pos), 1000, true);
			}
		}
	}

}
