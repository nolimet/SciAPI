package sciapi.api.heat;

import cpw.mods.fml.relauncher.Side;
import sciapi.api.basis.SciAPI;
import sciapi.api.registry.TickTaskRegistry;

public class HeatInit {

	public static void Init() {
		TickTaskRegistry.registerTickStartTask(HeatSystem.getHeatSystem(), Side.SERVER);
	}
}
