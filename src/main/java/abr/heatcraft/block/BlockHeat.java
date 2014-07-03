package abr.heatcraft.block;

import java.util.Random;

import sciapi.api.heat.IHeatComponent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import abr.heatcraft.core.ColorUtil;
import abr.heatcraft.tile.TileHeatBlock;
import abr.heatcraft.tile.TileHeatCauldron;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockHeat extends BlockContainer {

	public BlockHeat(Material par2Material) {
		super(par2Material);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int m) {
	 	return new TileHeatBlock(); 
	}
	
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister ir){
		this.blockIcon = ir.registerIcon("heatcraft:heatplate");
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public int colorMultiplier(IBlockAccess ba, int x, int y, int z){
		IHeatComponent th = (IHeatComponent) ba.getTileEntity(x, y, z);
		
		return ColorUtil.getiColorforTemp(th.getTemperature());
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public int getRenderColor(int i){
		return 0x7f7f7f;
	}
	
	@Override
	public int getLightValue(IBlockAccess ba, int x, int y, int z){
		IHeatComponent th = (IHeatComponent) ba.getTileEntity(x, y, z);


		if(th != null)
			return ColorUtil.getlightforTemp(th.getTemperature());
		else return super.getLightValue(ba, x, y, z);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	   /**
	    * A randomly called display update to be able to add particles or other items for display
	    */
	   public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
	   {
		   TileHeatBlock hc = (TileHeatBlock) par1World.getTileEntity(par2, par3, par4);
		   
	       if (hc.smokingRate() > 0)
	       {
	    	   int rate = (int) hc.smokingRate();
	    	   
	           float f = (float)par2 + 0.5F;
	           float f2 = (float)par4 + 0.5F;
	    	   
	    	   for(int i = 0; i < rate; i++){
	    		   float f1 = (float)par3 + 0.8F + par5Random.nextFloat() * 6.0F / 16.0F;
	    		   float rx = par5Random.nextFloat() * 0.6F - 0.3F;
	    		   float rz = par5Random.nextFloat() * 0.6F - 0.3F;

	    		   par1World.spawnParticle("smoke", (double)(f + rx), (double)f1, (double)(f2 + rz), 0.0D, 0.0D, 0.0D);
	    	   }
	       }
	       
	       if(hc.isHot() && par5Random.nextBoolean())
	       {
	           float f = (float)par2 + 0.5F;
	           float f1 = (float)par3 + 0.3F + par5Random.nextFloat() * 12.0F / 16.0F;
	           float f2 = (float)par4 + 0.5F;
	           float rx = par5Random.nextFloat() * 1.2F - 0.6F;
	           float rz = par5Random.nextFloat() * 1.2F - 0.6F;

	           par1World.spawnParticle("flame", (double)(f + rx), (double)f1, (double)(f2 + rz), 0.0D, 0.0D, 0.0D);
	       }
	   }
}
