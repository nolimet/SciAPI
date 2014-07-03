package abr.heatcraft.block;

import java.util.List;
import java.util.Random;

import sciapi.api.heat.IHeatComponent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import abr.heatcraft.HeatCraft;
import abr.heatcraft.core.ColorUtil;
import abr.heatcraft.tile.TileHeatCauldron;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;

public class BlockHeatCauldron extends BlockContainer {
	
    @SideOnly(Side.CLIENT)
    private IIcon cauldronInnerIcon;
    @SideOnly(Side.CLIENT)
    private IIcon cauldronTopIcon;
    @SideOnly(Side.CLIENT)
    private IIcon cauldronBottomIcon;
	
	public BlockHeatCauldron(Material par2Material) {
		super(par2Material);
		
		this.setBlockTextureName("cauldron");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int m) {
		return new TileHeatCauldron();
	}
	
	
	@SideOnly(Side.CLIENT)
	@Override
	public int getRenderColor(int i){
		return 0x7f7f7f;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public int colorMultiplier(IBlockAccess ba, int x, int y, int z){
		if(ba == null)
			return super.colorMultiplier(ba, x, y, z);
		
		IHeatComponent th = (IHeatComponent) ba.getTileEntity(x, y, z);
		
		if(th == null)
			return super.colorMultiplier(ba, x, y, z);
		
		return ColorUtil.getiColorforTemp(th.getTemperature());
	}
	
	@Override
	public int getLightValue(IBlockAccess ba, int x, int y, int z){
		if(ba == null)
			return 0;
		
		IHeatComponent th = (IHeatComponent) ba.getTileEntity(x, y, z);


		if(th != null)
			return ColorUtil.getlightforTemp(th.getTemperature());
		else return super.getLightValue(ba, x, y, z);
	}
	
	@Override
	public boolean onBlockActivated(World world, int xPos, int yPos, int zPos,
			EntityPlayer player, int par6, float par7, float par8, float par9) {
		
		if (player == null) {
			return false;
		}
		
		ItemStack current = player.inventory.getCurrentItem();
		TileHeatCauldron th = (TileHeatCauldron) world.getTileEntity(xPos, yPos, zPos);
		
		if (th != null && current != null) {

			FluidStack liquid = FluidContainerRegistry.getFluidForFilledItem(current);
			ForgeDirection side = ForgeDirection.UP;
		
			if (liquid != null) {
				int used = th.fill(side, liquid, true);
				th.update();

				if (used > 0) {
					if (!player.capabilities.isCreativeMode) {
						player.inventory.setInventorySlotContents(player.inventory.currentItem, consumeItem(current));
						player.inventory.markDirty();
					}
										
					return true;
				}

			} else {

				FluidStack available = th.drain(side, Integer.MAX_VALUE, false);
				if (available != null) {
					ItemStack filled = FluidContainerRegistry.fillFluidContainer(available, current);

					liquid = FluidContainerRegistry.getFluidForFilledItem(filled);
					if (liquid != null) {
						if (!player.capabilities.isCreativeMode) {
						if (current.stackSize > 1) {
							if (!player.inventory.addItemStackToInventory(filled))
								return false;
							player.inventory.setInventorySlotContents(player.inventory.currentItem, consumeItem(current));
							player.inventory.markDirty();
						} else {
							player.inventory.setInventorySlotContents(player.inventory.currentItem, consumeItem(current));
							player.inventory.setInventorySlotContents(player.inventory.currentItem, filled);
							player.inventory.markDirty();
						}
						}

						th.drain(side, liquid.amount, true);
						th.update();
						
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public static ItemStack consumeItem(ItemStack stack) {
		if (stack.stackSize == 1) {
			if (stack.getItem().hasContainerItem(stack)) {
				return stack.getItem().getContainerItem(stack);
			} else {
				return null;
			}
		} else {
			stack.splitStack(1);

			return stack;
		}
	}
	
	
    @SideOnly(Side.CLIENT)

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public IIcon getIcon(int par1, int par2)
    {
        return par1 == 1 ? this.cauldronTopIcon : (par1 == 0 ? this.cauldronBottomIcon : this.blockIcon);
    }
    
    @SideOnly(Side.CLIENT)

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.cauldronInnerIcon = par1IconRegister.registerIcon("heatcraft:furnace_top");
        this.cauldronTopIcon = par1IconRegister.registerIcon("heatcraft:cauldron_top");
        this.cauldronBottomIcon = par1IconRegister.registerIcon("heatcraft:furnace_top");
        this.blockIcon = par1IconRegister.registerIcon("heatcraft:cauldron_side");
    }
    
    /**
     * Adds all intersecting collision boxes to a list. (Be sure to only add boxes to the list if they intersect the
     * mask.) Parameters: World, X, Y, Z, mask, list, colliding entity
     */
    public void addCollisionBoxesToList(World par1World, int par2, int par3, int par4, AxisAlignedBB par5AxisAlignedBB, List par6List, Entity par7Entity)
    {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.3125F, 1.0F);
        super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
        float f = 0.125F;
        this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
        super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
        super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
        this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
        this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
        super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
        this.setBlockBoundsForItemRender();
    }
    
    /**
    * Sets the block's bounds for rendering it as an item
    */
   public void setBlockBoundsForItemRender()
   {
       this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
   }

   /**
    * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
    * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
    */
   public boolean isOpaqueCube()
   {
       return false;
   }

   /**
    * The type of render function that is called for this block
    */
   public int getRenderType()
   {
       return HeatCraft.proxy.cauldronid;
   }

   /**
    * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
    */
   public boolean renderAsNormalBlock()
   {
       return false;
   }
   
   /**
    * If this returns true, then comparators facing away from this block will use the value from
    * getComparatorInputOverride instead of the actual redstone signal strength.
    */
   public boolean hasComparatorInputOverride()
   {
       return true;
   }

   /**
    * If hasComparatorInputOverride returns true, the return value from this is used instead of the redstone signal
    * strength when this block inputs to a comparator.
    */
   public int getComparatorInputOverride(World par1World, int par2, int par3, int par4, int par5)
   {
       int i1 = par1World.getBlockMetadata(par2, par3, par4);
       return func_111045_h_(i1);
   }

   public static int func_111045_h_(int par0)
   {
       return par0;
   }

   @SideOnly(Side.CLIENT)
   public static IIcon getCauldronIcon(String par0Str)
   {
       return par0Str.equals("inner") ? ((BlockHeatCauldron)HBlocks.heatCauldron).cauldronInnerIcon : (par0Str.equals("bottom") ?  ((BlockHeatCauldron)HBlocks.heatCauldron).cauldronBottomIcon : null);
   }
   
   
   @SideOnly(Side.CLIENT)
   /**
    * A randomly called display update to be able to add particles or other items for display
    */
   public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
   {
	   TileHeatCauldron hc = (TileHeatCauldron) par1World.getTileEntity(par2, par3, par4);
	   
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
