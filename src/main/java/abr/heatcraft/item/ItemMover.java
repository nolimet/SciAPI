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
import abr.heatcraft.itementity.IEItemMover;

public class ItemMover extends ItemContainer {

	private IIcon progIcon;

	public ItemMover() {
		super();
		this.setMaxDamage(101);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister ir){
        this.itemIcon = ir.registerIcon("heatcraft:mover");
        this.progIcon = ir.registerIcon("heatcraft:blank");
	}
	
    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(ItemStack item, int par2)
    {
    	if(par2 == 0)
    		return this.itemIcon;
    	else return this.progIcon;
    }

	@Override
	public ItemEntity createNewItemEntity(McInvWorld world) {
		return new IEItemMover();
	}
}
