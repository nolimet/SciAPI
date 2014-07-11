package abr.heatcraft;

import abr.heatcraft.fluid.HFluids;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.TextureStitchEvent;

public class HeatEventHandler {
	@SubscribeEvent
	public void onHandleTexture(TextureStitchEvent.Pre e)
	{
		if(e.map.getTextureType() == 1)
		{
			HFluids.onTextureInit(e.map);
		}
	}

}
