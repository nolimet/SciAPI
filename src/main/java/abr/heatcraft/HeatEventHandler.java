package abr.heatcraft;

import abr.heatcraft.fluid.HFluids;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.client.event.TextureStitchEvent;

public class HeatEventHandler {
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onHandleTexture(TextureStitchEvent.Pre e)
	{
		if(e.map.getTextureType() == 1)
		{
			HFluids.onTextureInit(e.map);
		}
	}

}
