package sciapi.api.mc.item.multiitem;

import sciapi.api.basis.math.BMath;
import sciapi.api.def.euclidian.EVecInt;

public class SimpleMITypeBuilder {
	SimpleMultiItemType smitype;
	
	public void init(Class<? extends MultiItem> miclass, String mid){
		smitype = new SimpleMultiItemType(miclass, mid);
	}
	
	public SimpleMultiItemType build(){
		SimpleMultiItemType built = smitype;
		smitype = null;
		return built;
	}
	
	
	/**
	 * Sets the point to certain type.
	 * @param type the type of the component
	 * @param x x coordinate of the point
	 * @param y y coordinate of the point
	 * */
	public void setPoint(IEMultiCompType type, int x, int y){
		EVecInt pos = new EVecInt(x, y);
		smitype.setPosItemType(pos, type);
	}
	
	/**
	 * Sets Rectangle Boundaries to certain type.
	 * @param type the type of the component
	 * @param leftx x coordinate of leftmost boundary
	 * @param topy y coordinate of topmost boundary
	 * @param xsize distance from leftmost to the rightmost boundaries
	 * @param ysize distance from topmost to the bottommost boundaries
	 * */
	public void setRectangle(IEMultiCompType type, int leftx, int topy, int xsize, int ysize){
		for(int i = 0; i < xsize; i++)
			for(int j = 0; j < ysize; j++)
				if(i == 0 || j == 0 || i == xsize-1 || j == ysize-1)
					setPoint(type, leftx + i, topy + j);
	}
	
	/**
	 * Sets Diamond-Shaped Boundaries to certain type.
	 * @param type the type of the component
	 * @param centerx x coordinate of center
	 * @param centery y coordinate of center
	 * @param size distance from center to the leftmost boundaries
	 * */
	public void setDiamond(IEMultiCompType type, int centerx, int centery, int size){
		for(int i = -size; i <= size; i++){
			int j = size - BMath.absi(i);
			
			setPoint(type, centerx + i, centery + j);
			setPoint(type, centerx + i, centery - j);
		}
	}
	
	
	/**
	 * Sets every positions filling the Rectangle to certain type.
	 * @param type the type of the component
	 * @param leftx x coordinate of leftmost boundary
	 * @param topy y coordinate of topmost boundary
	 * @param xsize distance from leftmost to the rightmost boundaries
	 * @param ysize distance from topmost to the bottommost boundaries
	 * */
	public void setRectangleFill(IEMultiCompType type, int leftx, int topy, int xsize, int ysize){		
		for(int i = 0; i < xsize; i++)
			for(int j = 0; j < ysize; j++)
				setPoint(type, leftx + i, topy + j);
	}
	
	/**
	 * Sets every positions filling the Diamond-Shaped region to certain type.
	 * @param type the type of the component
	 * @param centerx x coordinate of center
	 * @param centery y coordinate of center
	 * @param size distance from center to the leftmost boundaries
	 * */
	public void setDiamondFill(IEMultiCompType type, int centerx, int centery, int size){
		for(int k = 0; k <= size; k++)
			setDiamond(type, centerx, centery, k);
	}
}
