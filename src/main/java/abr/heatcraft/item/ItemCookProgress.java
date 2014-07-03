package abr.heatcraft.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import abr.heatcraft.itementity.IECookProgress;
import sciapi.api.mc.inventory.pos.McInvWorld;
import sciapi.api.mc.item.ItemContainer;
import sciapi.api.mc.item.ItemEntity;

public class ItemCookProgress extends ItemContainer {
	
	public IIcon progicon;

	public ItemCookProgress() {
		super();
		this.setMaxDamage(1001);
	}

	@Override
	public ItemEntity createNewItemEntity(McInvWorld world) {
		return new IECookProgress();
	}
	
    public int getItemEnchantability()
    {
        return ToolMaterial.IRON.getEnchantability();
    }
    
    
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister ir){
        this.itemIcon = ir.registerIcon("heatcraft:hcookbg");
        this.progicon = ir.registerIcon("heatcraft:hcookfull");
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
