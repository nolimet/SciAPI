package sciapi.api.mc.pos;

import sciapi.api.abstraction.pos.IDirection;
import sciapi.api.basis.storage.Pool;
import sciapi.api.def.euclidian.EVecInt;
import net.minecraft.util.Direction;
import net.minecraftforge.common.util.ForgeDirection;

public enum McDirection implements IDirection<EVecInt> {
	
    /** -Y */
    DOWN(0, -1, 0),

    /** +Y */
    UP(0, 1, 0),

    /** -Z */
    NORTH(0, 0, -1),

    /** +Z */
    SOUTH(0, 0, 1),

    /** -X */
    WEST(-1, 0, 0),

    /** +X */
    EAST(1, 0, 0);
    
	private EVecInt coord;
	
	public static final McDirection ALL_DIRS[] = {DOWN, UP, NORTH, SOUTH, WEST, EAST};
	
	McDirection(int x, int y, int z){
		coord = new EVecInt(x, y, z);
	}
	
	public static McDirection[] getAlldirs(){
		return ALL_DIRS;
	}

	@Override
	public IDirection<EVecInt> getDirection(int n) {
		return ALL_DIRS[n];
	}
	
	@Override
	public IDirection<EVecInt> getOpposite(){
		return getDirection(ForgeDirection.OPPOSITES[index()]);
	}
	
	public ForgeDirection toForgeDirection(){
		return ForgeDirection.getOrientation(index());
	}

	@Override
	public int index() {
		return ordinal();
	}

	@Override
	public EVecInt toDifference() {
		return coord;
	}

	@Override
	public int getindexlim() {
		return 6;
	}

}
