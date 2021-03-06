package abr.heatcraft.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import abr.heatcraft.HeatCraft;
import abr.heatcraft.itementity.IEMachineryBag;
import sciapi.api.def.euclidian.EVecInt;
import sciapi.api.mc.inventory.player.McPlayerInvManager;
import sciapi.api.mc.inventory.pos.McInvWorld;
import sciapi.api.mc.item.ItemContainer;
import sciapi.api.mc.item.ItemEntity;

public class ItemMachineryBag extends ItemContainer {

	int ysize;
	int num;
	
	public ItemMachineryBag(int ysize, int num) {
		super();
		this.ysize = ysize;
		this.num = num;
	}

	@Override
	public ItemEntity createNewItemEntity(McInvWorld world) {
		return new IEMachineryBag(ysize);
	}
	
	@Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
		super.onItemRightClick(stack, world, player);
		
		McInvWorld worldObj = McPlayerInvManager.getInstance((world.isRemote)? Side.CLIENT : Side.SERVER).getPlayerInventory(player).getWorld();
		EVecInt pos = new EVecInt(McInvWorld.getX(stack), McInvWorld.getY(stack));
		IEMachineryBag bag = (IEMachineryBag)worldObj.getItemEntity(worldObj.toEntry(pos));
		player.displayGUIChest(bag);
		
		return stack;
    }

}
