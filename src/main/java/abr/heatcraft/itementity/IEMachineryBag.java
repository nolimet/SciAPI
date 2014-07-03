package abr.heatcraft.itementity;

import cpw.mods.fml.relauncher.Side;
import abr.heatcraft.core.McBagInvManager;
import abr.heatcraft.core.McBagInventory;
import abr.heatcraft.item.ItemMachineryBag;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import sciapi.api.mc.inventory.McInventory;
import sciapi.api.mc.inventory.player.McPlayerInventory;
import sciapi.api.mc.item.ItemContainer;
import sciapi.api.mc.item.ItemEntity;

public class IEMachineryBag extends ItemEntity implements IInventory {

	private ItemStack contents[];
	private McBagInventory mcinv;
	private int ysize;
	
	public IEMachineryBag() { }
	
	public IEMachineryBag(int ysize)
	{
		this.ysize = ysize;
	}
	
	@Override
    public void invalidate()
    {
        McBagInvManager.getInstance((worldObj.isRemote)? Side.CLIENT : Side.SERVER)
        .removeInventory(worldObj.inv.getInvId() + ":" + pos.vec.getCoord(0) + "," + pos.vec.getCoord(1));
        
        super.invalidate();
    }

	@Override
    public void validate()
    {
        mcinv = McBagInvManager.getInstance((worldObj.isRemote)? Side.CLIENT : Side.SERVER)
        .addInventory(this, worldObj.inv.getInvId() + ":" + pos.vec.getCoord(0) + "," + pos.vec.getCoord(1));
        
        super.validate();
    }
	
	@Override
	public void readFromNBT(NBTTagCompound comp)
	{
        super.readFromNBT(comp);
        
        if(ysize == 0)
        	this.ysize = (int)comp.getByte("ysize");
        
        NBTTagList nbttaglist = comp.getTagList("Items", 10);
        this.contents = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound1.getByte("Slot") & 255;

            if (j >= 0 && j < this.contents.length)
            {
                this.contents[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
        
        if(mcinv != null)
        	mcinv.loadNBTData(comp);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound comp)
	{
        super.writeToNBT(comp);
        
        comp.setByte("ysize", (byte)(this.ysize));
        
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.contents.length; ++i)
        {
            if (this.contents[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.contents[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }
        
        comp.setTag("Items", nbttaglist);
        
        if(mcinv != null)
        	mcinv.saveNBTData(comp);
	}
	
	@Override
	public int getSizeInventory() {
		int i = 0;
		return 9 * ysize;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return contents[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		return contents[i].splitStack(j);
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		return contents[i];
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		if(this.isItemValidForSlot(i, itemstack))
			contents[i] = itemstack;
		else if(itemstack != null)
		{
			boolean flag;
			McInventory parinv = this.worldObj.inv;
			if(parinv instanceof McPlayerInventory)
			{
				flag = ((McPlayerInventory) parinv).getPlayerInventory().addItemStackToInventory(itemstack);
				if(!flag)
				{
					EntityPlayer player = ((McPlayerInventory) parinv).getPlayerEntity();
					World world = player.worldObj;
					world.spawnEntityInWorld(new EntityItem(world, player.posX, player.posY, player.posZ, itemstack));
				}
			}
		}
	}

	@Override
	public String getInventoryName() {
		return "container.machinerybag";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return true;
	}

	@Override
	public void openInventory() {

	}

	@Override
	public void closeInventory() {

	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return (!(itemstack != null && itemstack.getItem() instanceof ItemMachineryBag));
	}

}
