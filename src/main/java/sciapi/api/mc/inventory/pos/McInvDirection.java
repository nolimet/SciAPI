package sciapi.api.mc.inventory.pos;

import net.minecraft.util.Direction;
import sciapi.api.abstraction.pos.IDirection;
import sciapi.api.def.euclidian.EVecInt;
import sciapi.api.mc.pos.McDirection;

public enum McInvDirection implements IDirection<EVecInt> {
	
	DOWN(0, 1),
	
	LEFT(-1, 0),
	
	UP(0, -1),
	
	RIGHT(1, 0);

	public static McInvDirection[] ALL_DIRS = {DOWN, LEFT, UP, RIGHT};
	
	private EVecInt coord;
	
	McInvDirection(int x, int y){
		coord = new EVecInt(x, y);
	}
	
	public static McInvDirection[] getAlldirs(){
		return ALL_DIRS;
	}

	@Override
	public IDirection<EVecInt> getDirection(int n) {
		return ALL_DIRS[n];
	}
	
	@Override
	public IDirection<EVecInt> getOpposite(){
		return ALL_DIRS[(index() + 2) % 4];
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
		return 4;
	}
}
