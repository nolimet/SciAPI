package abr.heatcraft.block;

import java.util.Random;

import sciapi.api.heat.IHeatComponent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import abr.heatcraft.HeatCraft;
import abr.heatcraft.core.ColorUtil;
import abr.heatcraft.tile.TileHeatBlock;
import abr.heatcraft.tile.TileHeatCauldron;
import abr.heatcraft.tile.TileHeatFurnace;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;


public class BlockHeatFurnace extends BlockContainer{
	
    @SideOnly(Side.CLIENT)
    private IIcon furnaceIconTop;
    @SideOnly(Side.CLIENT)
    private IIcon furnaceIconFront;
	
    /**
     * Is the random generator used by furnace to drop the inventory contents in random directions.
     */
    private final Random furnaceRand = new Random();

    /** True if this is an active furnace, false if idle */
    private final boolean isActive;
	
	public static boolean keepFurnaceInventory;

	public BlockHeatFurnace(boolean activity) {
		super(Material.iron);
		isActive = activity;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int m) {
	 	return new TileHeatFurnace(); 
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister ir){
        this.blockIcon = ir.registerIcon("heatcraft:heatplate");
        this.furnaceIconFront = ir.registerIcon(this.isActive ? "heatcraft:furnace_front_on" : "heatcraft:furnace_front_off");
        this.furnaceIconTop = ir.registerIcon("heatcraft:furnace_top");
		
	//	this.blockIcon = ir.registerIcon("heatcraft:HeatFurnace");
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public int colorMultiplier(IBlockAccess ba, int x, int y, int z){
		IHeatComponent th = (IHeatComponent) ba.getTileEntity(x, y, z);
		
		return ColorUtil.getiColorforTemp(th.getTemperature());
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
	
	public int getRenderColor(int i){
		return 0x7f7f7f;
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public IIcon getIcon(int par1, int par2)
    {
    	if(par2 == 0)
    		par2 = 3;
    	
        return par1 == 1 ? this.furnaceIconTop : (par1 == 0 ? this.furnaceIconTop : (par1 != par2 ? this.blockIcon : this.furnaceIconFront));
    }
	
    @Override
    /**
     * Returns the ID of the items to drop on destruction.
     */
    public Item getItemDropped(int par1, Random par2Random, int par3)
    {
        return Item.getItemFromBlock(HBlocks.heatFurnaceidle);
    }
    
    /**
     * Update which block ID the furnace is using depending on whether or not it is burning
     */
    public static void updateFurnaceBlockState(boolean par0, World par1World, int par2, int par3, int par4)
    {
        int l = par1World.getBlockMetadata(par2, par3, par4);
        TileEntity tileentity = par1World.getTileEntity(par2, par3, par4);
        keepFurnaceInventory = true;

        if (par0)
        {
            par1World.setBlock(par2, par3, par4, HBlocks.heatFurnaceburning);
        }
        else
        {
            par1World.setBlock(par2, par3, par4, HBlocks.heatFurnaceidle);
        }

        keepFurnaceInventory = false;
        par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 2);

        if (tileentity != null)
        {
            tileentity.validate();
            par1World.setTileEntity(par2, par3, par4, tileentity);
        }
    }
    
    @Override
    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        super.onBlockAdded(par1World, par2, par3, par4);
        this.setDefaultDirection(par1World, par2, par3, par4);
    }

    /**
     * set a blocks direction
     */
    private void setDefaultDirection(World par1World, int par2, int par3, int par4)
    {
        if (!par1World.isRemote)
        {
            Block l = par1World.getBlock(par2, par3, par4 - 1);
            Block i1 = par1World.getBlock(par2, par3, par4 + 1);
            Block j1 = par1World.getBlock(par2 - 1, par3, par4);
            Block k1 = par1World.getBlock(par2 + 1, par3, par4);
            byte b0 = 3;

            if (l.func_149730_j() && !i1.func_149730_j())
            {
                b0 = 3;
            }

            if (i1.func_149730_j() && !l.func_149730_j())
            {
                b0 = 2;
            }

            if (j1.func_149730_j() && !k1.func_149730_j())
            {
                b0 = 5;
            }

            if (k1.func_149730_j() && !j1.func_149730_j())
            {
                b0 = 4;
            }

            par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 2);
        }
    }
    
    @Override
    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World par1World, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        if (par1World.isRemote)
        {
            return true;
        }
        else
        {
            TileHeatFurnace tileentityfurnace = (TileHeatFurnace)par1World.getTileEntity(x, y, z);

            if (tileentityfurnace != null)
            {
            	player.openGui(HeatCraft.instance, 0, par1World, x, y, z);
            }

            return true;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (this.isActive)
        {
            int l = par1World.getBlockMetadata(par2, par3, par4);
            float f = (float)par2 + 0.5F;
            float f1 = (float)par3 + 0.0F + par5Random.nextFloat() * 6.0F / 16.0F;
            float f2 = (float)par4 + 0.5F;
            float f3 = 0.52F;
            float f4 = par5Random.nextFloat() * 0.6F - 0.3F;

            if (l == 4)
            {
                par1World.spawnParticle("smoke", (double)(f - f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
                par1World.spawnParticle("flame", (double)(f - f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
            }
            else if (l == 5)
            {
                par1World.spawnParticle("smoke", (double)(f + f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
                par1World.spawnParticle("flame", (double)(f + f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
            }
            else if (l == 2)
            {
                par1World.spawnParticle("smoke", (double)(f + f4), (double)f1, (double)(f2 - f3), 0.0D, 0.0D, 0.0D);
                par1World.spawnParticle("flame", (double)(f + f4), (double)f1, (double)(f2 - f3), 0.0D, 0.0D, 0.0D);
            }
            else if (l == 3)
            {
                par1World.spawnParticle("smoke", (double)(f + f4), (double)f1, (double)(f2 + f3), 0.0D, 0.0D, 0.0D);
                par1World.spawnParticle("flame", (double)(f + f4), (double)f1, (double)(f2 + f3), 0.0D, 0.0D, 0.0D);
            }
        }
        
        
	   TileHeatFurnace hc = (TileHeatFurnace) par1World.getTileEntity(par2, par3, par4);
	   
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
       
       if(hc.isHot())
       {
           float f = (float)par2 + 0.5F;
           float f1 = (float)par3 + 0.1F + par5Random.nextFloat() * 12.0F / 16.0F;
           float f2 = (float)par4 + 0.5F;
           float rx = par5Random.nextFloat() * 1.2F - 0.6F;
           float rz = par5Random.nextFloat() * 1.2F - 0.6F;

           par1World.spawnParticle("flame", (double)(f + rx), (double)f1, (double)(f2 + rz), 0.0D, 0.0D, 0.0D);
       }
    }
    
    @Override
    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
    {
        int l = MathHelper.floor_double((double)(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 2, 2);
        }

        if (l == 1)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 5, 2);
        }

        if (l == 2)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 3, 2);
        }

        if (l == 3)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 4, 2);
        }

        if (par6ItemStack.hasDisplayName())
        {
            ((TileHeatFurnace)par1World.getTileEntity(par2, par3, par4)).setGuiDisplayName(par6ItemStack.getDisplayName());
        }
    }

    @Override
    /**
     * Called on server worlds only when the block has been replaced by a different block ID, or the same block with a
     * different metadata value, but before the new metadata value is set. Args: World, x, y, z, old block ID, old
     * metadata
     */
    public void breakBlock(World par1World, int par2, int par3, int par4, Block par5, int par6)
    {
        if (!keepFurnaceInventory)
        {
            TileHeatFurnace tileentityfurnace = (TileHeatFurnace)par1World.getTileEntity(par2, par3, par4);

            if (tileentityfurnace != null)
            {
                for (int j1 = 0; j1 < tileentityfurnace.getSizeInventory(); ++j1)
                {
                    ItemStack itemstack = tileentityfurnace.getStackInSlot(j1);

                    if (itemstack != null)
                    {
                        float f = this.furnaceRand.nextFloat() * 0.8F + 0.1F;
                        float f1 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;
                        float f2 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;

                        while (itemstack.stackSize > 0)
                        {
                            int k1 = this.furnaceRand.nextInt(21) + 10;

                            if (k1 > itemstack.stackSize)
                            {
                                k1 = itemstack.stackSize;
                            }

                            itemstack.stackSize -= k1;
                            EntityItem entityitem = new EntityItem(par1World, (double)((float)par2 + f), (double)((float)par3 + f1), (double)((float)par4 + f2), new ItemStack(itemstack.getItem(), k1, itemstack.getItemDamage()));

                            if (itemstack.hasTagCompound())
                            {
                                entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                            }

                            float f3 = 0.05F;
                            entityitem.motionX = (double)((float)this.furnaceRand.nextGaussian() * f3);
                            entityitem.motionY = (double)((float)this.furnaceRand.nextGaussian() * f3 + 0.2F);
                            entityitem.motionZ = (double)((float)this.furnaceRand.nextGaussian() * f3);
                            par1World.spawnEntityInWorld(entityitem);
                        }
                    }
                }

                par1World.func_147453_f(par2, par3, par4, par5);
            }
        }

        super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }

    @Override
    /**
     * If this returns true, then comparators facing away from this block will use the value from
     * getComparatorInputOverride instead of the actual redstone signal strength.
     */
    public boolean hasComparatorInputOverride()
    {
        return true;
    }

    @Override
    /**
     * If hasComparatorInputOverride returns true, the return value from this is used instead of the redstone signal
     * strength when this block inputs to a comparator.
     */
    public int getComparatorInputOverride(World par1World, int par2, int par3, int par4, int par5)
    {
        return Container.calcRedstoneFromInventory((IInventory)par1World.getTileEntity(par2, par3, par4));
    }

}
