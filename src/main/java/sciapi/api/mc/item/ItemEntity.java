package sciapi.api.mc.item;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import sciapi.api.abstraction.absutil.IInsBase;
import sciapi.api.abstraction.pos.IPosObject;
import sciapi.api.abstraction.pos.IWorld;
import sciapi.api.def.euclidian.EVecInt;
import sciapi.api.mc.inventory.pos.McInvPos;
import sciapi.api.mc.inventory.pos.McInvWorld;
import net.minecraft.block.Block;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.*;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

/**
 * TileEntity For Items.
 * */
public class ItemEntity implements IPosObject {
	
    private static final Logger logger = LogManager.getLogger();

    /**
     * A HashMap storing string names of classes mapping to the actual java.lang.Class type.
     */
    private static Map nameToClassMap = new HashMap();

    /**
     * A HashMap storing the classes and mapping to the string names (reverse of nameToClassMap).
     */
    private static Map classToNameMap = new HashMap();
	
    
    /** The reference to the inventory world. */
    public McInvWorld worldObj;
    
    /** The Position of this itementity. */
    public McInvPos pos;
    
    protected boolean itemEntityInvalid;
    private int itemDamage = -1;

    /** the Item type that this ItemEntity is contained within */
	private Item itemType;
	

	public boolean isupdated = false;
	
	
    /**
     * Adds a new two-way mapping between the class and its string name in both hashmaps.
     */
    public static void addMapping(Class par0Class, String par1Str)
    {
        if (nameToClassMap.containsKey(par1Str))
        {
            throw new IllegalArgumentException("Duplicate id: " + par1Str);
        }
        else
        {
            nameToClassMap.put(par1Str, par0Class);
            classToNameMap.put(par0Class, par1Str);
        }
    }
    
    /**
     * Returns the worldObj for this tileEntity.
     */
    public McInvWorld getWorldObj()
    {
        return this.worldObj;
    }

    /**
     * Sets the worldObj for this tileEntity.
     */
    public void setWorldObj(McInvWorld par1World)
    {
        this.worldObj = par1World;
        this.pos.iworld = worldObj;
    }
    
    /**
     * Returns true if the worldObj isn't null.
     */
    public boolean hasWorldObj()
    {
        return this.worldObj != null;
    }
    
    
    /**
     * Reads a item entity from NBT.
     */
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
    	if(this.pos == null)
    		this.pos = new McInvPos(this.worldObj, new EVecInt(0,0));
        this.pos.vec.set(par1NBTTagCompound.getInteger("x"),
        		par1NBTTagCompound.getInteger("y"));
    }

    /**
     * Writes a item entity to NBT.
     */
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        String s = (String)classToNameMap.get(this.getClass());

        if (s == null)
        {
            throw new RuntimeException(this.getClass() + " is missing a mapping! This is a bug!");
        }
        else
        {
            par1NBTTagCompound.setString("id", s);
            par1NBTTagCompound.setInteger("x", this.pos.vec.getCoord(0).toInt());
            par1NBTTagCompound.setInteger("y", this.pos.vec.getCoord(1).toInt());
        }
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    public void updateEntity() {}

    /**
     * Creates a new entity and loads its data from the specified NBT.
     */
    public static ItemEntity createAndLoadEntity(NBTTagCompound par0NBTTagCompound)
    {
    	ItemEntity itementity = null;

        Class oclass = null;

        try
        {
            oclass = (Class)nameToClassMap.get(par0NBTTagCompound.getString("id"));

            if (oclass != null)
            {
                itementity = (ItemEntity)oclass.newInstance();
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }

        if (itementity != null)
        {
            try
            {
                itementity.readFromNBT(par0NBTTagCompound);
            }
            catch (Exception e)
            {
                FMLLog.log(Level.ERROR, e,
                        "A ItemEntity %s(%s) has thrown an exception during loading, its state cannot be restored. Report this to the mod author",
                        par0NBTTagCompound.getString("id"), oclass.getName());
                itementity = null;
            }
        }
        else
        {
        	logger.warn("Skipping ItemEntity with id " + par0NBTTagCompound.getString("id"));
        }

        return itementity;
    }

    /**
     * Returns block data at the location of this entity (client-only).
     */
    public int getItemDamage()
    {
        if (this.itemDamage == -1)
        {
            this.itemDamage = this.worldObj.getItemDamage(this.pos);
        }

        return this.itemDamage;
    }

    /**
     * Called when an the contents of an Inventory change
     */
    public void markDirty()
    {
        if (this.worldObj != null)
        {
            if (this.getItemType() != null)
                this.worldObj.checkNeighbors(this.pos);
            
            this.worldObj.onInventoryChanged();
        }
    }

    /**
     * Returns the square of the distance between this entity and the passed in coordinates.
     */
    public double getDistanceFrom(double par1, double par3)
    {
        double d3 = (double)pos.vec.getCoord(0).toInt() + 0.5D - par1;
        double d4 = (double)pos.vec.getCoord(1).toInt() + 0.5D - par3;
        return d3 * d3 + d4 * d4;
    }

    /**
     * Gets the block type at the location of this entity (client-only).
     */
    public Item getItemType()
    {
        if (this.itemType == null)
        {
            this.itemType = this.worldObj.getItem(this.pos);
        }

        return this.itemType;
    }

    /**
     * Overridden in a sign to provide the text.
     */
    /*public Packet getDescriptionPacket()
    {
        return null;
    }*/

    /**
     * returns true if tile entity is invalid, false otherwise
     */
    public boolean isInvalid()
    {
        return this.itemEntityInvalid;
    }

    /**
     * invalidates a tile entity
     */
    public void invalidate()
    {
        this.itemEntityInvalid = true;
    }

    /**
     * validates a tile entity
     */
    public void validate()
    {
        this.itemEntityInvalid = false;
    }
    
    /**
     * moves a tile entity
     * */
    public void move(McInvPos ppos){
    	this.pos.set(ppos);
    }

    /**
     * Causes the ItemEntity to reset all it's cached values for it's container item, itemID, itemDamage and in the case
     * of chests, the adjacent chest check
     */
    public void updateContainingBlockInfo()
    {
        this.itemType = null;
        this.itemDamage = -1;
    }

    public void func_85027_a(CrashReportCategory par1CrashReportCategory)
    {
        par1CrashReportCategory.addCrashSectionCallable("Name", new CallableItemEntityName(this));
        par1CrashReportCategory.addCrashSectionCallable("Actual item type", new CallableItemEntityType(this));
        par1CrashReportCategory.addCrashSectionCallable("Actual item data value", new CallableItemEntityData(this));
    }

    static Map getClassToNameMap()
    {
        return classToNameMap;
    }


    /**
     * Determines if this ItemEntity requires update calls.
     * @return True if you want updateEntity() to be called, false if not
     */
    public boolean canUpdate()
    {
        return true;
    }

    /**
     * Called when you receive a TileEntityData packet for the location this
     * TileEntity is currently in. On the client, the NetworkManager will always
     * be the remote server. On the server, it will be whomever is responsible for
     * sending the packet.
     *
     * @param net The NetworkManager the packet originated from
     * @param pkt The data packet
     */
    /*public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt)
    {
    }*/
    
    /**
     * Called when neighboring item has changed.
     * */
	public void onNeighborItemChange(McInvPos p) { }


    
	@Override
	public ItemEntity getNew() {
		return new ItemEntity();
	}

	@Override
	public IInsBase set(IInsBase par) {
		ItemEntity par2 = (ItemEntity) par;
		this.worldObj = par2.worldObj;
		if(this.pos == null)
			this.pos = par2.pos.getNew();
		this.pos.set(par2.pos);
		
		return this;
	}

	@Override
	public McInvPos getPosition() {
		return this.pos;
	}

	@Override
	public IWorld getWorld() {
		return this.worldObj;
	}
}
