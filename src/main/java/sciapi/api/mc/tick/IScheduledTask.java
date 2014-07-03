package sciapi.api.mc.tick;

import cpw.mods.fml.relauncher.Side;

public interface IScheduledTask extends ITickTaskBase{
	public void onTask();
}
