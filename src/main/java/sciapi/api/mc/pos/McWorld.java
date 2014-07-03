package sciapi.api.mc.pos;

import sciapi.api.abstraction.pos.*;
import sciapi.api.basis.euclidian.*;
import sciapi.api.def.euclidian.EVecInt;
import sciapi.api.heat.HeatSystem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

public class McWorld implements IDiscreteWorld<McWorldPos, EVecInt>{
	
	private static McWorld mc = new McWorld();
	
	public static McWorld instance(){
		return mc;
	}

	@Override
	public IPosObject getObjonPos(McWorldPos pos) {
		World worldObj = DimensionManager.getWorld(pos.dimension);
		
		TileEntity te = worldObj.getTileEntity(pos.vec.getCoord(0).toInt(),
				pos.vec.getCoord(1).toInt(),
				pos.vec.getCoord(2).toInt());
		
		if(te == null || !(te instanceof IPosObject)){
			return null;
		}
		
		return (IPosObject)te;
	}

	@Override
	public IDirection<EVecInt>[] getPossibleDirections() {
		return McDirection.getAlldirs();
	}

	@Override
	public boolean equals(Object o){
		if(o instanceof McWorld)
			return true;
		return false;
	}

}
