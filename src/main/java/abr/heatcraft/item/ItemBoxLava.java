package abr.heatcraft.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.FluidContainerRegistry;
import abr.heatcraft.itementity.IEBoxLava;
import abr.heatcraft.itementity.IECookProgress;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import sciapi.api.mc.inventory.pos.McInvWorld;
import sciapi.api.mc.item.ItemContainer;
import sciapi.api.mc.item.ItemEntity;

public class ItemBoxLava extends ItemContainer {

	public IIcon progIcon;

	public ItemBoxLava() {
		super();
		this.setMaxDamage(FluidContainerRegistry.BUCKET_VOLUME);
		this.setContainerItem(HItems.box);
	}

	@Override
	public ItemEntity createNewItemEntity(McInvWorld world) {
		return new IEBoxLava();
	}
	
    public int getItemEnchantability()
    {
        return ToolMaterial.IRON.getEnchantability();
    }
    
    
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister ir){
        this.itemIcon = ir.registerIcon("heatcraft:hlavabg");
        this.progIcon = ir.registerIcon("heatcraft:hlavafull");
	}
    
    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(ItemStack item, int par2)
    {
    	if(par2 == 0)
    		return this.itemIcon;
    	else return this.progIcon;
    }

}
