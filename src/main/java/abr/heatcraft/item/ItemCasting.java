package abr.heatcraft.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import sciapi.api.mc.inventory.pos.McInvWorld;
import sciapi.api.mc.item.ItemEntity;
import abr.heatcraft.itementity.IECasting;

public class ItemCasting extends ItemLiquidTank {

	public ItemCasting(int capacity) {
		super(capacity);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister ir){
        this.itemIcon = ir.registerIcon("heatcraft:casting");
        this.progIcon = ir.registerIcon("");
	}

	@Override
	public ItemEntity createNewItemEntity(McInvWorld world) {
		return new IECasting(capacity);
	}
}
