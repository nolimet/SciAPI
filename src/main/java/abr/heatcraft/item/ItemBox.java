package abr.heatcraft.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemBox extends Item {

	public ItemBox() {
		super();
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister ir){
        this.itemIcon = ir.registerIcon("heatcraft:hlavabg");
	}
	
    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(ItemStack item, int par2)
    {
    	return this.itemIcon;
    }

}
