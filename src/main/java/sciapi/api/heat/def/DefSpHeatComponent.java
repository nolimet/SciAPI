package sciapi.api.heat.def;

import java.util.*;

import sciapi.api.abstraction.absutil.IInsBase;
import sciapi.api.abstraction.pos.*;
import sciapi.api.basis.*;
import sciapi.api.heat.*;
import sciapi.api.mc.*;
import sciapi.api.mc.pos.McDirection;
import sciapi.api.mc.pos.McWorld;
import sciapi.api.mc.pos.McWorldPos;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.*;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class DefSpHeatComponent extends TileEntity implements ISpHeatComponent {

	McWorldPos pos = new McWorldPos();
	
	/**
	 * Temperature of this Component.
	 * */
	protected double Temp;
	
	/**
	 * Heat Capacity, determines Temperature Change Rate to received Heat.
	 * */
	protected double HeatCapacity;
	
	/**
	 * Heat Transfer Rate. this may have size 6.
	 * */
	protected double transferRate[];
	
	/**
	 * determines if Heat-Transferable for the direction.
	 * */
	protected boolean isTransferable[];
	
	
	public Map<McDirection, IHeatComponent> neighbors = new HashMap();
	
	
	@Override
	public IAbsPosition getPosition() {
		pos.dimension = worldObj.provider.dimensionId;
		pos.vec.getCoord(0).set(this.xCoord);
		pos.vec.getCoord(1).set(this.yCoord);
		pos.vec.getCoord(2).set(this.zCoord);

		return pos;
	}

	@Override
	public IWorld getWorld() {
		return McWorld.instance();
	}

	@Override
	public double getTemperature() {
		return Temp;
	}

	@Override
	public boolean isHeatTransferable(IDirection dir) {
		return isTransferable[dir.index()];
	}

	@Override
	public double getHeatTransferRate(IDirection dir) {
		return transferRate[dir.index()];

	}

	@Override
	public Map<IAbsPosition, Double> getExternalTransferList() {
		return null;
	}
	

	@Override
	public IHeatComponent getNeighborComponent(IDirection dir) {
		if(!neighbors.containsKey(dir)){
			IHeatComponent hc = HeatCompCalcUnit.getOnPosition((IDiscreteWorld) this.getWorld(),
					this.getPosition().getDiffPos(dir.toDifference()));
			neighbors.put((McDirection) dir, hc);
		}
		
		return neighbors.get(dir);
	}
	
	
	private double reciHC = 0.0;

	@Override
	public void onHeatTransfer(double heat) {
		if(reciHC == 0.0)
			reciHC = 1 / HeatCapacity;
		Temp += ( heat * reciHC );
		
		this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}
	
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
	    super.readFromNBT(nbt);
	    Temp = nbt.getDouble("Temp");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
	    super.writeToNBT(nbt);
	    nbt.setDouble("Temp", Temp);
	}
	
	@Override
	public Packet getDescriptionPacket() {
	    NBTTagCompound tagCompound = new NBTTagCompound();
	    writeToNBT(tagCompound);
	    return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tagCompound);
	}
	
	@Override
	public void onDataPacket(NetworkManager networkManager, S35PacketUpdateTileEntity packet) {
		readFromNBT(packet.func_148857_g());
	}
	
	
	@Override
	public void validate()
	{
		if(!worldObj.isRemote)
			HeatSystem.getHeatSystem().addToSystem(this);
		
		super.validate();
	}

	@Override
	public void invalidate()
	{
		if(!worldObj.isRemote)
			HeatSystem.getHeatSystem().deleteFromSystem(this);
		
		super.invalidate();
	}
	
	@Override
	public void onChunkUnload() {
		if(!worldObj.isRemote)
			HeatSystem.getHeatSystem().deleteFromSystem(this);
		
		super.onChunkUnload();
	}
	
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof IPosObject){
			IPosObject hc = (IPosObject)o;
			
			if(this.getPosition().equals(hc.getPosition()))
				return true;
		}
		
		return false;
	}

	@Deprecated
	@Override
	public IInsBase getNew() {
		return null;
	}

	@Deprecated
	@Override
	public IInsBase set(IInsBase par) {
		return null;
	}

}
