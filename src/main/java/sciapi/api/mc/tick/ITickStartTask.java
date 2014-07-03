package sciapi.api.mc.tick;

import cpw.mods.fml.relauncher.Side;

public interface ITickStartTask extends ITickTaskBase {
	public void tickStart();
}
