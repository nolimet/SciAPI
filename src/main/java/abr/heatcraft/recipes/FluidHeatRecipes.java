package abr.heatcraft.recipes;

import java.util.*;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidContainerItem;
import net.minecraftforge.oredict.OreDictionary;

public class FluidHeatRecipes {
    private static final FluidHeatRecipes heatingBase = new FluidHeatRecipes();

    public static class FluidHeatResult
    {	
    	public FluidStack fstack;
    	public int cookheat;
    	
    	public FluidHeatResult(FluidStack fstack, int cookheat)
    	{
    		this.fstack = fstack;
    		this.cookheat = cookheat;
    	}
    }
    
    /** The list of heating results. */
    private Map<Integer, FluidHeatResult> heatingList = new HashMap();
    private Map<String, FluidHeatResult> oreheatingList = new HashMap();
    private Map<String, FluidHeatResult> heatingfluidList = new HashMap();
    
    public static final FluidHeatRecipes heating()
    {
        return heatingBase;
    }
    
    /**
     * Item to Fluid Heating Recipe.
     * */
    public void addItemHeating(Item item, FluidStack fstack, int cookheat)
    {
        this.heatingList.put(Integer.valueOf(Item.getIdFromItem(item)), new FluidHeatResult(fstack, cookheat));
    }
    
    public void addOreHeating(String oreName, FluidStack fstack, int cookheat)
    {
    	this.oreheatingList.put(oreName, new FluidHeatResult(fstack, cookheat));
    }
    
    /**
     * Fluid to Fluid Heating Recipe.
     * the size of Fluid Stack  must be small,
     * for they will be multiplied with the fluid size.
     * cookheat is the cook heat for 1000mb fluid.
     * */
    public void addFluidHeating(String fluidId, FluidStack fstack, int cookheat)
    {
    	this.heatingfluidList.put(fluidId, new FluidHeatResult(fstack, cookheat));
    }
    
    public void addFluidHeating(String fluidId, Fluid fluid, int cookheat)
    {
    	this.heatingfluidList.put(fluidId, new FluidHeatResult(new FluidStack(fluid, 1), cookheat));
    }
	
    public FluidHeatResult getHeatingResult(ItemStack item) 
    {
        if (item == null)
        {
            return null;
        }
        
        FluidHeatResult ret = (FluidHeatResult)heatingList.get(Item.getIdFromItem(item.getItem()));
        if (ret != null)
        {
            return ret;
        }
        
        int[] arroreid = OreDictionary.getOreIDs(item);
        for(int oreid : arroreid)
        	if(oreid >= 0)
        	{
        		ret = oreheatingList.get(OreDictionary.getOreName(oreid));
        		if (ret != null) 
        		{
        			return ret;
        		}
        	}
       
        if(FluidContainerRegistry.isFilledContainer(item))
        {
        	FluidStack fstack = FluidContainerRegistry.getFluidForFilledItem(item);
        	ret = heatingfluidList.get(fstack.getFluid().getUnlocalizedName());
        	FluidStack retf = ret.fstack;
        	retf.amount *= fstack.amount;
        	return new FluidHeatResult(retf, (ret.cookheat * fstack.amount) / 1000);
        }
        
        if(item.getItem() instanceof IFluidContainerItem)
        {
        	FluidStack fstack = ((IFluidContainerItem)item.getItem()).getFluid(item);
        	ret = heatingfluidList.get(fstack.getFluid().getUnlocalizedName());
        	FluidStack retf = ret.fstack;
        	retf.amount *= 100;
        	return new FluidHeatResult(retf, (ret.cookheat));
        }

        return null;
    }
    
    public FluidStack getHeatingResultF(ItemStack item)
    {
    	if(getHeatingResult(item) == null)
    		return null;
    	return getHeatingResult(item).fstack;
    }
    
    public int getHeatingTime(ItemStack item)
    {
    	if(getHeatingResult(item) == null)
    		return 0;
    	return getHeatingResult(item).cookheat;
    }
}
