package abr.heatcraft.tile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import abr.heatcraft.block.BlockHeatFurnace;
import net.minecraft.block.BlockFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityFurnace;
import sciapi.api.heat.def.DefHeatComponent;
import sciapi.api.unit.bsci.BSciConstants;
import sciapi.api.unit.bsci.HeatCapacity;

public class TileHeatFurnace extends DefHeatComponent implements ISidedInventory{
	
    private static final int[] slots_top = new int[] {0};
    private static final int[] slots_bottom = new int[] {2, 1};
    private static final int[] slots_sides = new int[] {1};
    
    private static final double stdfurTemp = 1000;
    private static final double minreactTemp = 450;
    private static final double addedhrate = 0.3;
    private static final double maxreact = 30.0;

    private static final double hratetoj = 101412;
    
    /**
     * The ItemStacks that hold the items currently being used in the furnace
     */
    private ItemStack[] furnaceItemStacks = new ItemStack[3];

    /** remaining amount of heat of the burning fuel */
    public double currentFuelHeat;
    
    /** total amount of heat of burning fuel */
    public int totalFuelHeat;

    /** The amount of heat that the current item had got */
    public double furnaceCookHeat;
    
    /** Temporary value to save the transferred heat */
    public double trheat = 0.0;
    
    private String custom_name;
	
    
	public TileHeatFurnace(){
		double tr = 300.0e+3;
		
		this.Temp = BSciConstants.air_temp.toDouble();
		this.HeatCapacity = new HeatCapacity(850.39, "kcal/K").toDouble() * 0.2;
		this.isTransferable = new boolean[]{true, true, true, true, true, true};
		this.transferRate = new double[]{tr, tr, tr, tr, tr, tr};
	}
		
	@Override
	public void onHeatTransfer(double heat) {
		trheat += heat;
	}

	
	@Override
	public int getSizeInventory() {
		return furnaceItemStacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return furnaceItemStacks[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
        if (this.furnaceItemStacks[i] != null)
        {
            ItemStack itemstack;

            if (this.furnaceItemStacks[i].stackSize <= j)
            {
                itemstack = this.furnaceItemStacks[i];
                this.furnaceItemStacks[i] = null;
                return itemstack;
            }
            else
            {
                itemstack = this.furnaceItemStacks[i].splitStack(j);

                if (this.furnaceItemStacks[i].stackSize == 0)
                {
                    this.furnaceItemStacks[i] = null;
                }

                return itemstack;
            }
        }
        else
        {
            return null;
        }
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
        if (this.furnaceItemStacks[i] != null)
        {
            ItemStack itemstack = this.furnaceItemStacks[i];
            this.furnaceItemStacks[i] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
        this.furnaceItemStacks[i] = itemstack;

        if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit())
        {
        	itemstack.stackSize = this.getInventoryStackLimit();
        }
	}

	@Override
	public String getInventoryName() {
        return this.hasCustomInventoryName() ? this.custom_name : "container.heatfurnace";
	}

	@Override
	public boolean hasCustomInventoryName() {
        return this.custom_name != null && this.custom_name.length() > 0;
	}
	
    /**
     * Sets the custom display name to use when opening a GUI linked to this tile entity.
     */
    public void setGuiDisplayName(String par1Str)
    {
        this.custom_name = par1Str;
    }
    
    
    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items", 10);
        this.furnaceItemStacks = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.furnaceItemStacks.length)
            {
                this.furnaceItemStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }

        this.currentFuelHeat = par1NBTTagCompound.getFloat("CurFuelHeat");
        this.totalFuelHeat = par1NBTTagCompound.getShort("TotFuelHeat");
        this.furnaceCookHeat = par1NBTTagCompound.getFloat("CookHeat");

        if (par1NBTTagCompound.hasKey("CustomName"))
        {
            this.custom_name = par1NBTTagCompound.getString("CustomName");
        }
    }

    
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setFloat("CurFuelHeat", (float)this.currentFuelHeat);
        par1NBTTagCompound.setShort("TotFuelHeat", (short)this.totalFuelHeat);
        par1NBTTagCompound.setFloat("CookHeat", (float)this.furnaceCookHeat);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.furnaceItemStacks.length; ++i)
        {
            if (this.furnaceItemStacks[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.furnaceItemStacks[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        par1NBTTagCompound.setTag("Items", nbttaglist);

        if (this.hasCustomInventoryName())
        {
            par1NBTTagCompound.setString("CustomName", this.custom_name);
        }
    }
    
    /** Gets the current burning rate*/
    public double getBurnRate(){
    	double r = 2 * (this.Temp - minreactTemp) / (stdfurTemp - minreactTemp);
    	return Math.max(Math.min(r, maxreact) + addedhrate, 0.0);
    }
    
    /** Gets the possible cooking rate*/
    public double getCookRate(){
    	double r = 2 * (this.Temp - minreactTemp) / (stdfurTemp - minreactTemp);
    	return Math.max(Math.min(r, maxreact) * (1 + addedhrate), 0.0);
    }
    
    /**
     * Returns true if the furnace is currently burning
     */
    public boolean isBurning()
    {
        return this.currentFuelHeat > 0;
    }
    
    public boolean isCooking()
    {
    	return this.Temp > minreactTemp;
    }
    
    public void updateEntity(){
    	
        boolean flag = isBurning();
        boolean flag1 = false;
        
        if(!this.worldObj.isRemote){
        	this.onHeatTransfer((-1.0) * (this.Temp - BSciConstants.air_temp.toDouble()));
        }

        if (flag)
        {
        	this.Temp = Math.max(this.Temp, (2 * minreactTemp + stdfurTemp)/3);
        	
        	double burnHeat = Math.min(this.currentFuelHeat, this.getBurnRate());
            
        	this.currentFuelHeat -= burnHeat;
            if(this.currentFuelHeat < 0)
            	this.currentFuelHeat = 0;
            
            this.trheat += burnHeat * hratetoj;
        }

        if (!this.worldObj.isRemote)
        {
            if (!this.isBurning())
            {
                this.currentFuelHeat = this.totalFuelHeat = (TileEntityFurnace.getItemBurnTime(this.furnaceItemStacks[1]) * 6 / 5);

                if (this.totalFuelHeat > 0)
                {
                    flag1 = true;

                    if (this.furnaceItemStacks[1] != null)
                    {
                        --this.furnaceItemStacks[1].stackSize;

                        if (this.furnaceItemStacks[1].stackSize == 0)
                        {
                            this.furnaceItemStacks[1] = this.furnaceItemStacks[1].getItem().getContainerItem(furnaceItemStacks[1]);
                        }
                    }
                }
            }

            if (this.isCooking() && this.canSmelt())
            {
            	double posheat = (this.trheat + (this.Temp - minreactTemp) * this.HeatCapacity) / hratetoj;
            	
            	double cookHeat = Math.min(posheat, Math.min(this.getCookRate(), 200 - this.furnaceCookHeat));
            	
            	this.furnaceCookHeat += cookHeat;
            	trheat -= cookHeat * hratetoj;

                if (this.furnaceCookHeat >= 200)
                {
                    this.furnaceCookHeat = 0;
                    this.smeltItem();
                    flag1 = true;
                }
            }
            else
            {
                this.furnaceCookHeat = 0;
            }

            if (flag != this.isBurning())
            {
                flag1 = true;
                BlockHeatFurnace.updateFurnaceBlockState(this.isBurning(), this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            }
            
            super.onHeatTransfer(this.trheat);
            this.trheat = 0.0;
            
            //System.out.println(this.Temp);
        }

        if (flag1)
        {
            this.markDirty();
        }
    }
        
    /**
     * Returns true if the furnace can smelt an item, i.e. has a source item, destination stack isn't full, etc.
     */
    private boolean canSmelt()
    {
        if (this.furnaceItemStacks[0] == null)
        {
            return false;
        }
        else
        {
            ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.furnaceItemStacks[0]);
            if (itemstack == null) return false;
            if (this.furnaceItemStacks[2] == null) return true;
            if (!this.furnaceItemStacks[2].isItemEqual(itemstack)) return false;
            int result = furnaceItemStacks[2].stackSize + itemstack.stackSize;
            return (result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize());
        }
    }

    /**
     * Turn one item from the furnace source stack into the appropriate smelted item in the furnace result stack
     */
    public void smeltItem()
    {
        if (this.canSmelt())
        {
            ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.furnaceItemStacks[0]);

            if (this.furnaceItemStacks[2] == null)
            {
                this.furnaceItemStacks[2] = itemstack.copy();
            }
            else if (this.furnaceItemStacks[2].isItemEqual(itemstack))
            {
                furnaceItemStacks[2].stackSize += itemstack.stackSize;
            }

            --this.furnaceItemStacks[0].stackSize;

            if (this.furnaceItemStacks[0].stackSize <= 0)
            {
                this.furnaceItemStacks[0] = null;
            }
        }
    }
    

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}
	
	
    @SideOnly(Side.CLIENT)
    /**
     * Returns an integer between 0 and the passed value representing how close the current item is to being completely
     * cooked
     */
    public int getCookProgressScaled(int par1)
    {
        return (int) (this.furnaceCookHeat * par1 / 200);
    }

    @SideOnly(Side.CLIENT)
    /**
     * Returns an integer between 0 and the passed value representing how much burn time is left on the current fuel
     * item, where 0 means that the item is exhausted and the passed value means that the item is fresh
     */
    public int getBurnTimeRemainingScaled(int par1)
    {
        if (this.totalFuelHeat == 0)
        {
            this.totalFuelHeat = 200;
        }

        return (int)(this.currentFuelHeat * par1 / this.totalFuelHeat);
    }
	

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : entityplayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory() {}

	@Override
	public void closeInventory() {}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        return i == 2 ? false : (i == 1 ? TileEntityFurnace.isItemFuel(itemstack) : true);
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int var1) {
        return var1 == 0 ? slots_bottom : (var1 == 1 ? slots_top : slots_sides);
	}

	@Override
	public boolean canInsertItem(int i, ItemStack itemstack, int j) {
        return this.isItemValidForSlot(i, itemstack);
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemstack, int j) {
        return j != 0 || i != 1 || itemstack.getItem().equals(Items.bucket);
	}
	
	
	public double smokingRate() {
		if(this.canSmelt())
			return this.getCookRate();
		else return 0;
	}
	
	public boolean isHot() {
		return Temp > 1000.0;
	}
}
