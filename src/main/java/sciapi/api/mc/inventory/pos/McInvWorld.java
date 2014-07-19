package sciapi.api.mc.inventory.pos;

import java.util.*;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.block.Block;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ReportedException;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.ForgeModContainer;
import sciapi.api.abstraction.pos.*;
import sciapi.api.basis.math.MConverts;
import sciapi.api.def.euclidian.*;
import sciapi.api.mc.data.INBTio;
import sciapi.api.mc.inventory.McInventory;
import sciapi.api.mc.item.ItemContainer;
import sciapi.api.mc.item.ItemEntity;
import sciapi.api.mc.tick.ITickTask;

/**
 * 2D World for Inventory.
 * */
public class McInvWorld implements IDiscreteWorld<McInvPos, EVecInt> {
	
	public boolean isRemote;
	public McInventory inv;
	public int columnnum;
	
	private boolean isCreated;
	
	public List<ItemEntity> entityList = new ArrayList();
	
	private ItemStack[] prevstacks;
	private boolean[] ievalid;
	
	private boolean changed = false;
	
	public McInvWorld(McInventory pinv, int pcolumnnum, boolean isClient){
		inv = pinv;
		columnnum = pcolumnnum;
		isRemote = isClient;
		isCreated = true;
		prevstacks = new ItemStack[inv.getSizeInventory()];
		ievalid = new boolean[inv.getSizeInventory()];
	}

	@Override
	public IPosObject getObjonPos(McInvPos pos) {
		return this.getItemEntity(pos);
	}

	@Override
	public IDirection<EVecInt>[] getPossibleDirections() {
		return McInvDirection.getAlldirs();
	}
	
	
    public static NBTTagCompound getIECompound(ItemStack st){
    	return st.getTagCompound().getCompoundTag("itementity");
    }
    
    public static int getX(ItemStack st){
    	return getIECompound(st).getInteger("x");
    }
    
    public static int getY(ItemStack st){
    	return getIECompound(st).getInteger("y");
    }
    
    public static String getInvId(ItemStack st){
    	return getIECompound(st).getString("inventory");
    }
    
    public static void setX(ItemStack st, int x){
    	getIECompound(st).setInteger("x", x);
    }
    
    public static void setY(ItemStack st, int y){
    	getIECompound(st).setInteger("y", y);
    }
    
    public static void setInventory(ItemStack st, McInventory inv){
    	getIECompound(st).setString("inventory", inv.getInvId());
    }
    
    public static void delInventory(ItemStack st){
    	getIECompound(st).setString("inventory", "");
    }
	
	
	public int toEntry(EVecInt v){
		return v.getCoord(0).toInt() + v.getCoord(1).toInt() * columnnum;
	}
	
	public int toEntry(int x, int y){
		return x + y * columnnum;
	}
	
	
	public ItemStack getItemStack(McInvPos pos){
		return inv.getStackInSlot(toEntry(pos.vec));
	}
	
	public ItemStack getItemStack(EVecInt v){
		return inv.getStackInSlot(toEntry(v));
	}
	
	public ItemStack getItemStack(int i){
		return inv.getStackInSlot(i);
	}
	
	
	public void setItemStack(McInvPos pos, ItemStack itemstack){
		inv.setInventorySlotContents(toEntry(pos.vec), itemstack);
	}
	
	public void setItemStack(EVecInt v, ItemStack itemstack){
		inv.setInventorySlotContents(toEntry(v), itemstack);
	}
	
	public void setItemStack(int i, ItemStack itemstack){
		inv.setInventorySlotContents(i, itemstack);
	}

	
	public boolean equals(Object o){
		if(o instanceof McInvWorld){
			McInvWorld miw = (McInvWorld) o;
			return miw.inv.equals(this.inv);
		}
		return false;
	}

	public int getItemDamage(McInvPos p) {
		return getItemStack(p).getItemDamage();
	}
	
	public Item getItem(McInvPos p){
		return getItemStack(p).getItem();
	}
	
	public boolean itemExists(McInvPos p) {
		return getItemStack(p) != null;
	}
	
	/**
	 * Checks if ItemStack can have the ItemEntity.
	 * */
	public static boolean itemValid(ItemStack is){
		if(is == null)
			 return false;
		return (is.getItem() instanceof ItemContainer);
	}
	
	/**
	 * Checks if the position has the valid ItemEntity.
	 * */
	public boolean hasItemEntity(int i){
		ItemStack is = getItemStack(i);
		
		if(!itemValid(is))
			return false;
		
		if(!is.hasTagCompound())
			return false;
		
		return this.ievalid[i];
	}
	
	/**
	 * Checks if the item has the valid ItemEntity.
	 * */
	public boolean hasItemEntity(ItemStack is){
		if(!itemValid(is))
			return false;
		
		if(!is.hasTagCompound())
			return false;
		
		return this.ievalid[toEntry(getX(is), getY(is))];
	}
	
	
	public ItemEntity getItemEntity(McInvPos p){
		for(ItemEntity ie : entityList)
			if(ie.pos.equals(p))
				return ie;
		
		return null;
	}
	
	public ItemEntity getItemEntity(int i){
    	McInvPos pos = (McInvPos) McInvPos.tu.getTemp();
    	EVecInt p = pos.vec.getTemporary();
    	p.set(i % columnnum, i / columnnum);
    	pos.set(this, p);
    	
    	ItemEntity ie = getItemEntity(pos);
    	
    	p.remove(p);
    	pos.remove(pos);
    	
    	return ie;
	}
	
	
	/**
	 * Loads ItemEntity from NBT.
	 * */
	public void loadItemEntity(ItemContainer item, ItemStack is, int i){
    	ItemEntity ie;
    
    	this.ievalid[i] = true;
    	
    	boolean check;
    	
    	if(!is.hasTagCompound() || getIECompound(is) == null)
    		this.createNewItemEntity(item, is, i);
    	
    	setX(is, i % columnnum);
    	setY(is, i / columnnum);
    	setInventory(is, this.inv);
    	
    	ie = ItemEntity.createAndLoadEntity(getIECompound(is));
    	
    	if(ie == null){
    		this.setItemStack(i, null);
    		return;
    	}
    	
    	ie.setWorldObj(this);
    	
    	entityList.add(ie);
    	ie.validate();
    	
    	this.checkNeighbors(ie.pos);
	}
	
	
	
	/**
	 * Creates new Item Entity at i.
	 * */
	public void createNewItemEntity(ItemContainer item, ItemStack is, int i){
    	McInvPos pos = new McInvPos();
    	EVecInt p = pos.vec.getTemporary();
    	p.set(i % columnnum, i / columnnum);
    	pos.set(this, p);
    	
		if(!is.hasTagCompound())
    		((ItemContainer)is.getItem()).onCreated(is);
    	
    	ItemEntity ie;
    	
    	ie = item.createNewItemEntity(this);
    	ie.readFromNBT(getIECompound(is));
    	ie.setWorldObj(this);
    	ie.pos = pos;
    	
    	entityList.add(ie);
    	this.ievalid[i] = true;
    	setX(is, p.getCoord(0).toInt());
    	setY(is, p.getCoord(1).toInt());
    	setInventory(is, this.inv);
    	ie.validate();
    	
    	this.checkNeighbors(pos);
    	
    	p.remove(p);
   	}
	
	/**
	 * Deletes ItemEntity at i.
	 * */
	public void deleteItemEntity(int i){
    	McInvPos pos = (McInvPos) McInvPos.tu.getTemp();
    	EVecInt p = pos.vec.getTemporary();
    	p.set(i % columnnum, i / columnnum);
    	pos.set(this, p);
    	
    	ItemEntity ie = this.getItemEntity(i);
    	
    	entityList.remove(ie);
    	this.ievalid[i] = false;
    	if(this.prevstacks[i] != null)
    	{
    		ie.writeToNBT(getIECompound(this.prevstacks[i]));
    		delInventory(this.prevstacks[i]);
    	}
    	ie.invalidate();
    	
    	this.checkNeighbors(pos);
    	
    	pos.remove(pos);
    	p.remove(p);
	}
	
	/**
	 * Moves ItemEntity to j.
	 * */
	public void moveItemEntity(ItemEntity ie, int j){
    	McInvPos now = (McInvPos) McInvPos.tu.getTemp();
    	McInvPos old = (McInvPos) McInvPos.tu.getTemp();
    	EVecInt p = now.vec.getTemporary();
    	p.set(j % columnnum, j / columnnum);
    	now.set(this, p);
    	
    	old.set(ie.pos);
    	ie.move(now);
    	
    	ItemStack is = this.getItemStack(j);
    	setX(is, p.getCoord(0).toInt());
    	setY(is, p.getCoord(1).toInt());
    	
    	this.checkNeighbors(old);
    	this.checkNeighbors(now);
    	
    	now.remove(now);
    	old.remove(old);
    	
    	ievalid[this.toEntry(old.vec)] = false;
    	ievalid[j] = true;
    	
    	p.remove(p);
	}
	
	
    public void checkNeighbors(McInvPos p)
    {
        for(McInvDirection dir : McInvDirection.getAlldirs())
        {
        	McInvPos q = p.getDiffPos(dir.toDifference());
        	
        	ItemEntity ie = this.getItemEntity(q);
        	if(ie != null)
        		ie.onNeighborItemChange(p);
        }
    }

    
    /**
     * Updates item entities on the end of the tick.
     */
    public void updateEntities()
    {    	
        for (ItemEntity itementity : entityList)
        {
        	if(itementity == null)
        		continue;
        	
            if (!itementity.isInvalid())
            {
                try {
                	itementity.updateEntity();
                }
                catch (Throwable throwable2)
                {
                    CrashReport crashreport = CrashReport.makeCrashReport(throwable2, "Ticking item entity");
                    CrashReportCategory crashreportcategory = crashreport.makeCategory("Item entity being ticked");
                    itementity.func_85027_a(crashreportcategory);
                    if (ForgeModContainer.removeErroringTileEntities)
                    {
                        FMLLog.severe(crashreport.getCompleteReport());
                        itementity.invalidate();
                        this.setItemStack(itementity.pos, null);
                    }
                    else
                    {
                        throw new ReportedException(crashreport);
                    }
                }
            }
        }
        
        if(this.changed) {
        	inv.markDirty();
        	this.changed = false;
        }
    }
    
    /**
     * Checks Moved or Removed ItemEntity.
     * */
    public void checkMove(){
    	if(!inv.invchanged)
    		return;
    	
    	int x, y, j;
    	
    	//Save Moved Positions.
    	List<ItemEntity> moved = new ArrayList();
    	List<Integer> npos = new ArrayList();
    	
    	ItemStack st, bef;
		ItemEntity ie;
    	
    	for(int i = 0; i < inv.getSizeInventory(); i++){
    		st = this.getItemStack(i);
    		
    		try{
    		if(itemValid(st) && st.hasTagCompound() && getInvId(st).equals(inv.getInvId()) && hasItemEntity(st)){
    			x = getX(st);
    			y = getY(st);
    			j = toEntry(x, y);
    			
    			if(i != j && (ie = this.getItemEntity(j)) != null){
    				moved.add(ie);
    				npos.add(i);
    			}
    		}
    		}catch(Exception e)
    		{
    			e.printStackTrace();
    		}
    	}
    	
    	for(int i = 0; i < entityList.size(); i++){
    		ie = entityList.get(i);
    		
    		if(moved.contains(ie)) continue;
    		
    		j = toEntry(ie.pos.vec);
    		st = this.getItemStack(j);
    		bef = prevstacks[j];
    		
    		if(!this.itemValid(st) || (st.hasTagCompound() && !getInvId(st).equals(inv.getInvId())) || npos.contains(j))
    			this.deleteItemEntity(j);
    	}
    	
    	for(int i = 0; i < moved.size(); i++)
    		this.moveItemEntity(moved.get(i), npos.get(i));
    }
    
    /**
     * Checks Inserted ItemEntity.
     * */
    public void checkInsert(){    	
    	if(this.isCreated){
    		for(int i = 0; i < inv.getSizeInventory(); i++){
    			ItemStack st = this.getItemStack(i);
    			
    			if(this.itemValid(st))
    				this.loadItemEntity((ItemContainer) st.getItem(), st, i);
    			else this.ievalid[i] = false;
    			
    			this.prevstacks[i] = st;
    		}
    		
    		this.isCreated = false;
    		inv.invchanged = true;
    		
    		return;
    	}
    	
    	if(inv.invchanged){
    		int j = 0;
    		for(int i = 0; i < inv.getSizeInventory(); i++){
    			ItemStack st = this.getItemStack(i);
    			ItemEntity ie;
    			
    			if(itemValid(st))
    				if(!hasItemEntity(i))
    					this.createNewItemEntity((ItemContainer) st.getItem(), st, i);
    			
    			this.prevstacks[i] = st;
    		}
    	}
   	}
    
    
	
	public void saveNBTData(NBTTagCompound compound) {
		ItemStack is;
		
		for(ItemEntity ie : this.entityList) {
			is = this.getItemStack(ie.pos);
			
			if(is != null && is.hasTagCompound())
				ie.writeToNBT(getIECompound(is));
		}
		
		/*byte[] barr = MConverts.toByteArray(this.ievalid);
		compound.setByteArray("ievalids", barr);*/
	}

	
	public void loadNBTData(NBTTagCompound compound) {
		ItemStack is;
		
		for(ItemEntity ie : this.entityList) {
			is = this.getItemStack(ie.pos);
			
			if(is != null && is.hasTagCompound())
				ie.readFromNBT(getIECompound(is));
		}
		
		/*byte[] barr = compound.getByteArray("ievalids");
		
		for(byte i = 0; i < ievalid.length; i++)
			ievalid[i] = MConverts.getBoolean(barr, i);*/
	}
	

	public void onInventoryChanged() {
		changed = true;
	}
}
