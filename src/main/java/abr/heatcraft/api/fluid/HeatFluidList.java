package abr.heatcraft.api.fluid;

import java.util.*;

import sciapi.api.unit.bsci.Temperature;
import net.minecraftforge.fluids.*;

public class HeatFluidList {
	private static Map<String, HeatFluidInfo> hfmap = new HashMap();
	
	static{
		addHeatFluid(FluidRegistry.WATER.getName(), new HeatFluidInfo(false).
				setboilTemp(new Temperature(100, "C"))
				.setspecificHeat(1000.0)
				.setlatentHeat(5.4e+5));
		addHeatFluid(FluidRegistry.LAVA.getName(), new HeatFluidInfo(true).
				setboilTemp(FluidRegistry.LAVA.getTemperature())
				.setspecificHeat(200.0)
				.setlatentHeat(2.2e+5));
	}
	
	public static void addHeatFluid(String fluidName, HeatFluidInfo hfi){
		hfmap.put(fluidName, hfi);
	}
	
	public static boolean contains(Fluid fluid){
		return hfmap.containsKey(fluid.getName());
	}
	
	public static HeatFluidInfo getInfo(Fluid fluid){
		return hfmap.get(fluid.getName());
	}
}
