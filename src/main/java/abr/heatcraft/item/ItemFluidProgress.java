package abr.heatcraft.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import abr.heatcraft.itementity.IEFluidProgress;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import sciapi.api.mc.inventory.pos.McInvWorld;
import sciapi.api.mc.item.ItemContainer;
import sciapi.api.mc.item.ItemEntity;

public class ItemFluidProgress extends ItemContainer {
	
	public IIcon progicon;

	public ItemFluidProgress() {
		super();
		this.setMaxDamage(1000);
	}

	@Override
	public ItemEntity createNewItemEntity(McInvWorld world) {
		return new IEFluidProgress();
	}
	
    public int getItemEnchantability()
    {
        return ToolMaterial.IRON.getEnchantability();
    }
    
    
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister ir){
        this.itemIcon = ir.registerIcon("heatcraft:hfluidbg");
        this.progicon = ir.registerIcon("heatcraft:hfluidfull");
	}
    
    @SideOnly(Side.CLIENT)
    @Override
    public boolean requiresMultipleRenderPasses() {
    	return true;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(ItemStack item, int par2)
    {
    	if(par2 == 0)
    		return this.itemIcon;
    	else return this.progicon;
    }
}
