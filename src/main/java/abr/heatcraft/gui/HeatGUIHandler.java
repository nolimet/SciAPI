package abr.heatcraft.gui;

import sciapi.api.mc.inventory.McInventory;
import sciapi.api.mc.inventory.player.McPlayerInvManager;
import sciapi.api.mc.inventory.pos.McInvWorld;
import sciapi.api.mc.item.ItemEntity;
import abr.heatcraft.container.ContainerHeatFurnace;
import abr.heatcraft.itementity.IEMachineryBag;
import abr.heatcraft.tile.TileHeatFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.relauncher.Side;

public class HeatGUIHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world,
			int x, int y, int z) {
		
		switch(id)
		{
		case 1:
			McInventory inv = McPlayerInvManager.getInstance(Side.SERVER).getPlayerInventory(player);
			McInvWorld worldObj = inv.getWorld();
			ItemEntity ie = worldObj.getItemEntity(inv.fromREntry(player.inventory.currentItem));
			
			break;
		case 0:
    		TileEntity tileentity = world.getTileEntity(x, y, z);
    		
            if(tileentity instanceof TileHeatFurnace){
            	return new ContainerHeatFurnace(player.inventory, (TileHeatFurnace) tileentity);
            }
            
            break;
		}
        
        return null;
	}

	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world,
			int x, int y, int z) {
		
		switch(id)
		{
		case 1:
			McInventory inv = McPlayerInvManager.getInstance(Side.CLIENT).getPlayerInventory(player);
			McInvWorld worldObj = inv.getWorld();
			ItemEntity ie = worldObj.getItemEntity(inv.fromREntry(player.inventory.currentItem));
			
			break;
		case 0:
            TileEntity tileentity = world.getTileEntity(x, y, z);
            
            if(tileentity instanceof TileHeatFurnace){
            	return new GuiHeatFurnace(player.inventory, (TileHeatFurnace) tileentity);
            }
            
            break;
		}
        
		return null;
	}

}
