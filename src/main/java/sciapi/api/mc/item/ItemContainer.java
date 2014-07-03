package sciapi.api.mc.item;

import cpw.mods.fml.relauncher.Side;
import sciapi.api.def.euclidian.EVecInt;
import sciapi.api.mc.inventory.entity.McEntityInvManager;
import sciapi.api.mc.inventory.player.McPlayerInventory;
import sciapi.api.mc.inventory.pos.McInvPos;
import sciapi.api.mc.inventory.pos.McInvWorld;
import sciapi.api.registry.McInvItemRegistry;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class ItemContainer extends Item {

    public ItemContainer()
    {
        super();
    }
    
    public abstract ItemEntity createNewItemEntity(McInvWorld world);
    
    
    /*@Override
    public void onCreated(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
    	onCreated(par1ItemStack);
    }*/
    
    public void onCreated(ItemStack itemStack)
    {
    	if(itemStack.stackTagCompound == null)
    		itemStack.setTagCompound(new NBTTagCompound());
    	itemStack.getTagCompound().setTag("itementity", new NBTTagCompound());
    }
    
    /*@Override
    public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5)
    { 	
    	McInvWorld world;
    	
    	if(par2World.isRemote)
    		world = McEntityInvManager.getInstance(Side.CLIENT)
    		.getInventory(par3Entity).getWorld();
    	else world = McEntityInvManager.getInstance(Side.SERVER)
    			.getInventory(par3Entity).getWorld();
    	
    	if(world != null){
    		
    		ItemEntity ie = world.getItemEntity(par4);
    	
    		if(ie != null && par1ItemStack.hasTagCompound()){
    			if(world.isRemote){
    				int x = ie.pos.vec.getCoord(0).toInt();
    				int y = ie.pos.vec.getCoord(1).toInt();
    				
    				ie.readFromNBT(McInvWorld.getIECompound(par1ItemStack));
    				
    				ie.pos.vec.set(x, y);
    			}
    			else ie.writeToNBT(McInvWorld.getIECompound(par1ItemStack));
    		}
    	}
    }*/
    
    @Override
    public boolean getShareTag(){ return true; }
}
