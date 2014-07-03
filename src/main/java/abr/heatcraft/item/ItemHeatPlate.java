package abr.heatcraft.item;

import java.util.Map;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import abr.heatcraft.core.ColorUtil;
import abr.heatcraft.itementity.IEHeatPlate;
import sciapi.api.abstraction.absutil.IInsBase;
import sciapi.api.abstraction.pos.IAbsPosition;
import sciapi.api.abstraction.pos.IDirection;
import sciapi.api.abstraction.pos.IWorld;
import sciapi.api.heat.IHeatComponent;
import sciapi.api.mc.inventory.pos.McInvWorld;
import sciapi.api.mc.item.ItemContainer;
import sciapi.api.mc.item.ItemEntity;

public class ItemHeatPlate extends ItemContainer {

	public ItemHeatPlate() {
		super();
	}

	@Override
	public ItemEntity createNewItemEntity(McInvWorld world) {
		return new IEHeatPlate();
	}
	
    public int getItemEnchantability()
    {
        return ToolMaterial.IRON.getEnchantability();
    }
	
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister ir){
        this.itemIcon = ir.registerIcon("heatcraft:heatplate");
	}
	
	
    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack par1ItemStack, int par2)
    {
    	if(par1ItemStack.hasTagCompound()) {
    		NBTTagCompound comp = McInvWorld.getIECompound(par1ItemStack);
    		double Temp = comp.getDouble("Temp");
    		return ColorUtil.getiColorforTemp(Temp);
    	}
    	
    	return super.getColorFromItemStack(par1ItemStack, par2);    	
    }

}
