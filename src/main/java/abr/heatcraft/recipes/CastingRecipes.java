package abr.heatcraft.recipes;

import java.util.*;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

public class CastingRecipes {
	
	private static CastingRecipes castrec = new CastingRecipes();
	
	private Map<Integer, ItemStack> casting = new HashMap();
	
	public static CastingRecipes instance()
	{
		return castrec;
	}
	
	public void addCasting(Fluid fluid, ItemStack stack)
	{
		casting.put(Integer.valueOf(fluid.getID()), stack);
	}
	
	public ItemStack getCasted(FluidStack fluid)
	{
		return (ItemStack) casting.get(fluid.getFluid().getID());
	}
}
