package abr.heatcraft.item;

import abr.heatcraft.itementity.IELiquidTank;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidContainerItem;
import sciapi.api.mc.inventory.pos.McInvWorld;
import sciapi.api.mc.item.ItemContainer;
import sciapi.api.mc.item.ItemEntity;

public class ItemLiquidTank extends ItemContainer implements IFluidContainerItem {

	public int capacity;
	public IIcon progIcon;
	
	//liquid icons;;
	public IIcon iron, tin, lead, silver, gold, copper;

	public ItemLiquidTank(int capacity) {
		super();
		capacity ++;
		this.setMaxDamage(capacity);
		this.capacity = capacity;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister ir){
        this.itemIcon = ir.registerIcon("heatcraft:tank");
        this.progIcon = ir.registerIcon("");
        
        iron = ir.registerIcon("heatcraft:liqiron");
        tin = ir.registerIcon("heatcraft:liqtin");
        lead = ir.registerIcon("heatcraft:liqlead");
        silver = ir.registerIcon("heatcraft:liqsilver");
        gold = ir.registerIcon("heatcraft:liqgold");
        copper = ir.registerIcon("heatcraft:liqcopper");
	}
    
    @SideOnly(Side.CLIENT)
    @Override
    public boolean requiresMultipleRenderPasses() {
    	return true;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(ItemStack item, int par2)
    {
    	if(par2 == 0)
    		return this.itemIcon;
    	else {
    		FluidStack f = getFluid(item);
    		if(f == null || f.amount == 0)
    			return this.progIcon;
    		return f.getFluid().getIcon();
    	}
    }
	
	@Override
	public ItemStack getContainerItem(ItemStack item)
	{
		return new ItemStack(item.getItem(), capacity);
	}

	@Override
	public FluidStack getFluid(ItemStack container) {
		if(container.hasTagCompound())
		{
			NBTTagCompound tag = container.getTagCompound();
			return FluidStack.loadFluidStackFromNBT(tag.getCompoundTag("fluid"));
		}
		return null;
	}

	@Override
	public int getCapacity(ItemStack container) {
		return capacity;
	}

	@Override
	public int fill(ItemStack container, FluidStack resource, boolean doFill) {
		if(!container.hasTagCompound())
		{
			container.stackTagCompound = new NBTTagCompound();
			if(doFill)
			{
				resource.writeToNBT(container.getTagCompound().getCompoundTag("fluid"));
				this.setDamage(container, capacity - resource.amount);
			}
			return resource.amount;
		}
		
		NBTTagCompound tag = container.getTagCompound();
		FluidStack in = FluidStack.loadFluidStackFromNBT(tag.getCompoundTag("fluid"));
		
		if(in == null)
		{
			int am = Math.min(resource.amount, capacity);
			if(doFill)
			{
				NBTTagCompound comp = new NBTTagCompound();
				resource.writeToNBT(comp);
				container.getTagCompound().setTag("fluid", comp);
				this.setDamage(container, capacity - am);
			}
			return am;
		}
		
		if(!in.isFluidEqual(resource))
			return 0;
		
		int filled;
		
		if(in.amount + resource.amount > this.capacity)
		{
			filled = this.capacity - in.amount;
			in.amount = this.capacity;
		}
		else
		{
			filled = resource.amount;
			in.amount += filled;
		}
		
		if(doFill && filled > 0)
		{
			NBTTagCompound comp = new NBTTagCompound();
			in.writeToNBT(comp);
			tag.setTag("fluid", comp);
			this.setDamage(container, capacity - in.amount);
		}
		
		return (filled > 0)? filled : 0;		
	}

	@Override
	public FluidStack drain(ItemStack container, int maxDrain, boolean doDrain) {
		if(!container.hasTagCompound())
		{
			container.stackTagCompound = new NBTTagCompound();
			return null;
		}
		
		NBTTagCompound tag = container.getTagCompound();
		FluidStack in = FluidStack.loadFluidStackFromNBT(tag.getCompoundTag("fluid"));
		
		if(in == null)
			return null;
		
		int drained;
		
		if(in.amount > maxDrain)
		{
			drained = maxDrain;
			in.amount -= maxDrain;
			if(doDrain)
			{
				NBTTagCompound comp = new NBTTagCompound();
				in.writeToNBT(comp);
				tag.setTag("fluid", comp);
				this.setDamage(container, capacity - in.amount);
			}
		}
		else
		{
			drained = in.amount;
			if(doDrain)
			{
				tag.removeTag("fluid");
				this.setDamage(container, capacity);
			}
		}
		
		return new FluidStack(in.fluidID, drained);
	}

	@Override
	public ItemEntity createNewItemEntity(McInvWorld world) {
		return new IELiquidTank(capacity);
	}

}
