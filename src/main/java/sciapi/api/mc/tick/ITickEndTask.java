package sciapi.api.mc.tick;

import cpw.mods.fml.relauncher.Side;

public interface ITickEndTask extends ITickTaskBase {
	public void tickEnd();
}
