package abr.heatcraft.multiitem;

import java.util.Map;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidContainerItem;
import abr.heatcraft.block.BlockHeatFurnace;
import abr.heatcraft.enchant.EnchantmentExplComb;
import abr.heatcraft.enchant.EnchantmentHeatProof;
import abr.heatcraft.enchant.EnchantmentThermalEff;
import abr.heatcraft.itementity.IEFluidProgress;
import abr.heatcraft.itementity.IEHeatPlate;
import abr.heatcraft.itementity.IECookProgress;
import abr.heatcraft.itementity.IEFuelProgress;
import abr.heatcraft.recipes.FluidHeatRecipes;
import sciapi.api.abstraction.absutil.IInsBase;
import sciapi.api.abstraction.pos.IAbsPosition;
import sciapi.api.abstraction.pos.IDirection;
import sciapi.api.abstraction.pos.IWorld;
import sciapi.api.def.euclidian.EVecInt;
import sciapi.api.heat.IHeatComponent;
import sciapi.api.mc.inventory.pos.McInvPos;
import sciapi.api.mc.inventory.pos.McInvWorld;
import sciapi.api.mc.item.multiitem.*;
import sciapi.api.unit.bsci.BSciConstants;
import sciapi.api.unit.bsci.HeatCapacity;

public class MultiFluidHeater extends MultiItem {
	
    private static final double stdfurTemp = 1000;
    private static final double minreactTemp = 450;
    private static final double addedhrate = 0.6;
    private static final double maxreact = 30.0;

    private static final double hratetoj = 101412;
	
	private static SimpleMultiItemType type;
	
	public static IMultiItemType getType(){
		if(type == null){
			SimpleMITypeBuilder builder = new SimpleMITypeBuilder();
			builder.init(MultiFluidHeater.class, "miheater");
			builder.setDiamond(new ItemHPType(), 0, 0, 1);
			builder.setPoint(new ItemFluidProgressType(), 1, 0);
			builder.setPoint(new ItemFuelProgressType(), 0, 1);
			type = builder.build();
		}
		
		return type;
	}
	
	
	private IEFluidProgress progressbar;
	private IEFuelProgress fuelbar;
	private IEHeatPlate plates[] = new IEHeatPlate[2];
	
	/** current temperature */
	public double Temp;
	
    /** remaining amount of heat of the burning fuel */
    public double currentFuelHeat;
    
    /** total amount of heat of burning fuel */
    public int totalFuelHeat;

    /** The amount of heat that the current item(fluid) had got */
    public double cookHeat;
    
    /** total amount of cook of current heating item */
    public int totalCookHeat;
    
    /** Temporary value to save the transferred heat */
    public double trheat = 0.0;
    
	/**
	 * Heat Capacity, determines Temperature Change Rate to received Heat.
	 * */
	protected double HeatCapacity;
	
	/**
	 * Heat Loss Rate.
	 * */
	protected double HeatLossRate;
	
	
	private Random random;
	

	public MultiFluidHeater(McInvPos pcorepos) {
		super(pcorepos);
	}

	@Override
	public boolean checkKeep() {
		if(progressbar.isInvalid())
			return false;
		for(IEHeatPlate plate : plates){
			if(plate.isInvalid())
				return false;
		}
		if(fuelbar.isInvalid())
			return false;
		
		return true;
	}
	

	@Override
	public void onConstruct() {
		
		McInvWorld iworld = corepos.iworld;
		
		EVecInt vec = corepos.vec.getTemporary();
		
		vec.set(1, 0);
		progressbar = (IEFluidProgress) iworld.getItemEntity(corepos.getDiffPos(vec));
		
		vec.set(-1, 0);
		plates[0] = (IEHeatPlate) iworld.getItemEntity(corepos.getDiffPos(vec));
		plates[0].addMultiItem(this);
		
		vec.set(0, -1);
		plates[1] = (IEHeatPlate) iworld.getItemEntity(corepos.getDiffPos(vec));
		plates[1].addMultiItem(this);
		
		vec.set(0, 1);
		fuelbar = (IEFuelProgress) iworld.getItemEntity(corepos.getDiffPos(vec));
		fuelbar.addMultiItem(this);
		
		vec.remove(vec);

		
		Temp = BSciConstants.air_temp.toDouble();
		
		HeatCapacity = 0;
		
		for(int i = 0; i < 2; i++)
			HeatCapacity += plates[i].HeatCapacity;
		HeatCapacity += 2 * new HeatCapacity(465.25, "kcal/K").toDouble() * 0.02;
		
		
		HeatLossRate = 0;
		
		for(int i = 0; i < 2; i++)
			HeatLossRate += 0.3 * EnchantmentHeatProof.getEffect(plates[i].heatprooflvl);
		
		HeatLossRate += 0.5 * EnchantmentHeatProof.getEffect(progressbar.heatprooflvl);
		HeatLossRate += 0.5 * EnchantmentHeatProof.getEffect(fuelbar.heatprooflvl);
		
		
		random = new Random(System.currentTimeMillis());
	}

	@Override
	public void onDestroy() {
		fuelbar.removeMultiItem(this);
		ItemStack is = worldObj.getItemStack(fuelbar.pos);
		if(is != null)
			is.setItemDamage(0);
		
		for(IEHeatPlate plate : plates)
			plate.removeMultiItem(this);
	}

	@Override
	public void onUpdate() {
		updateEntity();
	}
	
	@Override
	public String getId() {
		return "miheater";
	}
	
	
	public void readFromNBT(NBTTagCompound tag) {
		Temp = tag.getDouble("Temp");
		trheat = tag.getDouble("trheat");
		
        this.currentFuelHeat = tag.getFloat("CurFuelHeat");
        this.totalFuelHeat = tag.getShort("TotFuelHeat");
        this.cookHeat = tag.getFloat("CookHeat");
        this.totalCookHeat = tag.getShort("TotCookHeat");
	}
	
	public void writeToNBT(NBTTagCompound tag) {
		tag.setDouble("Temp", Temp);
		tag.setDouble("trheat", trheat);
		
		tag.setFloat("CurFuelHeat", (float)this.currentFuelHeat);
		tag.setShort("TotFuelHeat", (short)this.totalFuelHeat);
		tag.setFloat("CookHeat", (float)this.cookHeat);
		tag.setShort("TotCookHeat", (short)totalCookHeat);
	}
	

	public double getTemperature() {
		return Temp;
	}

	public void onHeatTransfer(double heat) {
		trheat += heat;
	}
	
	private double reciHC = 0.0;
	
	public void onRegularTransfer(double heat) {
		if(reciHC == 0.0)
			reciHC = 1 / HeatCapacity;
		Temp += ( heat * reciHC );
	}
	
	
	public ItemStack getFurnaceItemStack(int i){
		if(i == 0){
			ItemStack is = worldObj.getItemStack(corepos);
					
			return is;
		}
		
		if(i == 1){
			EVecInt vec = corepos.vec.getTemporary();
			
			vec.set(-1, 1);
			ItemStack is = worldObj.getItemStack(corepos.getDiffPos(vec));
			
			vec.remove(vec);
			
			return is;
		}
		
		if(i == 2){
			EVecInt vec = corepos.vec.getTemporary();
				
			vec.set(2, 0);
			ItemStack is = worldObj.getItemStack(corepos.getDiffPos(vec));
				
			vec.remove(vec);
				
			return is;
		}
		
		return null;
	}
	
	public void setFurnaceItemStack(int i, ItemStack stack){
		if(i == 0){
			worldObj.setItemStack(corepos, stack);
		}
		
		if(i == 1){
			EVecInt vec = corepos.vec.getTemporary();
			
			vec.set(-1, 1);
			worldObj.setItemStack(corepos.getDiffPos(vec), stack);
			
			vec.remove(vec);
		}
		
		if(i == 2){
			EVecInt vec = corepos.vec.getTemporary();
			
			vec.set(2, 0);
			worldObj.setItemStack(corepos.getDiffPos(vec), stack);
				
			vec.remove(vec);
		}
	}
	
    
    /** Gets the current burning rate*/
    public double getBurnRate(){
    	double r = 2.5 * (this.Temp - minreactTemp) / (stdfurTemp - minreactTemp);
    	return Math.max(Math.min(r, maxreact) + addedhrate, 0.0)
    			* EnchantmentExplComb.getEffect(fuelbar.explosivelvl);
    }
    
    /** Gets the possible cooking rate*/
    public double getCookRate(){
    	double r = 2.5 * (this.Temp - minreactTemp) / (stdfurTemp - minreactTemp);
    	return Math.max(Math.min(r, maxreact) * (1 + addedhrate), 0.0)
    			* EnchantmentExplComb.getEffect(fuelbar.explosivelvl);
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
        	this.onHeatTransfer((-HeatLossRate) * (this.Temp - BSciConstants.air_temp.toDouble()));
        }

        if (flag)
        {
        	if(this.Temp < (2 * minreactTemp + stdfurTemp)/3){
        		double befTemp = this.Temp;
        		this.Temp = (2 * minreactTemp + stdfurTemp)/3;
        		this.trheat -= (this.Temp - befTemp) * this.HeatCapacity;
        	}

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
                this.currentFuelHeat = this.totalFuelHeat
                		= (int) (TileEntityFurnace.getItemBurnTime(this.getFurnaceItemStack(1))
                		* EnchantmentThermalEff.getEfficiency(fuelbar.thermalefflvl));

                if (this.totalFuelHeat > 0)
                {
                    flag1 = true;

                    if (this.getFurnaceItemStack(1) != null)
                    {
                        --this.getFurnaceItemStack(1).stackSize;

                        if (this.getFurnaceItemStack(1).stackSize == 0)
                        {
                            this.setFurnaceItemStack(1, this.getFurnaceItemStack(1).getItem().getContainerItem(getFurnaceItemStack(1)));
                        }
                    }
                }
            }

            if (this.isCooking() && this.canSmelt())
            {
            	this.totalCookHeat = FluidHeatRecipes.heating().getHeatingTime(this.getFurnaceItemStack(0));
            	
            	double posheat = (this.trheat + (this.Temp - minreactTemp) * this.HeatCapacity) / hratetoj;
            	
            	double cookHeat = Math.min(posheat, Math.min(this.getCookRate(), this.totalCookHeat - this.cookHeat));
            	
            	this.cookHeat += cookHeat;
            	trheat -= cookHeat * hratetoj;

                if (this.cookHeat >= this.totalCookHeat)
                {
                    this.cookHeat = 0;
                    this.smeltItem();
                    flag1 = true;
                }
            }

            if (flag != this.isBurning())
                flag1 = true;
            
            onRegularTransfer(this.trheat);
            this.trheat = 0.0;
            
            //System.out.println(this.Temp);
        }

        if (flag1)
        {
            this.onInventoryChanged();
        }
    }

	/**
     * Returns true if the furnace can smelt an item, i.e. has a source item, destination stack isn't full, etc.
     */
    private boolean canSmelt()
    {
        if (this.getFurnaceItemStack(0) == null)
        {
            return false;
        }
        else
        {
        	ItemStack cur0 = this.getFurnaceItemStack(0);
        	ItemStack cur = this.getFurnaceItemStack(2);
            FluidStack fluidstack = FluidHeatRecipes.heating().getHeatingResultF(cur0);
            if (fluidstack == null) return false;
            if (cur == null) return false;
            if (FluidContainerRegistry.isContainer(cur) && (FluidContainerRegistry.isEmptyContainer(cur) || FluidContainerRegistry.containsFluid(cur, fluidstack))) return true;
            if (cur0.getItem() instanceof IFluidContainerItem && cur.getItem() instanceof IFluidContainerItem)
            {
            	FluidStack stack = ((IFluidContainerItem)cur0.getItem()).drain(cur0, 100, false);
            	if(stack != null && stack.amount == 100)
            		return true;
            	return false;
            }
            else if(cur.getItem() instanceof IFluidContainerItem)
            {
            	return ((IFluidContainerItem)cur.getItem()).fill(cur0, fluidstack, false) != 0;
            }
            return false;
        }
    }

    /**
     * Turn one item from the furnace source stack into the appropriate smelted item in the furnace result stack
     */
    public void smeltItem()
    {
        if (this.canSmelt())
        {        	
            FluidStack res = FluidHeatRecipes.heating().getHeatingResultF(this.getFurnaceItemStack(0));
            FluidStack copy = res.copy();
            
            ItemStack istack;
            Item item;
        	
            ItemStack filled = FluidContainerRegistry.fillFluidContainer(copy, this.getFurnaceItemStack(2));
            if(filled != null)
            {
            	this.setFurnaceItemStack(2, filled);
            	istack = this.getFurnaceItemStack(0);
            	item = istack.getItem();
            	this.setFurnaceItemStack(0, item.getContainerItem(istack));
            	return;
            }
            
            if(this.getFurnaceItemStack(2).getItem() instanceof IFluidContainerItem)
            {
            	int filledam = ((IFluidContainerItem) this.getFurnaceItemStack(2).getItem())
            			.fill(this.getFurnaceItemStack(2), copy, true);
            	if(filledam > 0)
            	{
            		istack = this.getFurnaceItemStack(0);
        			item = istack.getItem();
            		if(item instanceof IFluidContainerItem)
            			((IFluidContainerItem) item).drain(istack, 100, true);
            		else
            		{
            			if(istack.stackSize == 1)
            				this.setFurnaceItemStack(0, item.getContainerItem(istack));
            			else istack.stackSize--;
            		}
            	}
            }
        }
    }
    
    
    /**
     * Returns an integer between 0 and the passed value representing how close the current item is to being completely
     * cooked
     */
    public int getCookProgressScaled(int par1)
    {
        return (int) (this.cookHeat * par1 / this.totalCookHeat);
    }

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
	public boolean equals(Object o) {
		if(o instanceof MultiFluidHeater){
			MultiFluidHeater heater = (MultiFluidHeater) o;
			
			return corepos.equals(heater.corepos);
		}
		
		return false;
	}

}
