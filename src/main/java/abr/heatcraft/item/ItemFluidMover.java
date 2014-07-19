package abr.heatcraft.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import sciapi.api.mc.inventory.pos.McInvWorld;
import sciapi.api.mc.item.ItemContainer;
import sciapi.api.mc.item.ItemEntity;
import abr.heatcraft.itementity.IECasting;
import abr.heatcraft.itementity.IEFluidFiller;
import abr.heatcraft.itementity.IEFluidMover;

public class ItemFluidMover extends ItemContainer {

	private IIcon progIcon;

	public ItemFluidMover() {
		super();
		this.setMaxDamage(0);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister ir){
        this.itemIcon = ir.registerIcon("heatcraft:fluidmover");
	}

	@Override
	public ItemEntity createNewItemEntity(McInvWorld world) {
		return new IEFluidMover();
	}
}
