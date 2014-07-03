package sciapi.api.mc.inventory.entity;

import java.util.*;

import cpw.mods.fml.relauncher.Side;
import sciapi.api.mc.IMcManager;
import sciapi.api.mc.inventory.*;
import net.minecraft.entity.Entity;

public class McEntityInvManager {
	
	private static EnumMap<Side, McEntityInvManager> instance = new EnumMap(Side.class);
	
	public static McEntityInvManager getInstance(Side side){
		if(!instance.containsKey(side))
			instance.put(side, new McEntityInvManager());
		return instance.get(side);
	}
	
	public List<IMcEntityInvManager> eitlist = new ArrayList();
	
	public McInventory getInventory(Entity e){
		for(IMcEntityInvManager eit : eitlist){
			if(eit.isEntityThistype(e))
				return eit.getInventory(e);
		}
		
		return null;
	}
}
