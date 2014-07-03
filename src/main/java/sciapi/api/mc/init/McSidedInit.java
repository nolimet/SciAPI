package sciapi.api.mc.init;

import cpw.mods.fml.relauncher.Side;
import sciapi.api.mc.inventory.McInvInit;
import sciapi.api.mc.unit.McUnitInit;

public class McSidedInit {
	public static void Init(Side side){
		McInvInit.Init(side);
	}
}
