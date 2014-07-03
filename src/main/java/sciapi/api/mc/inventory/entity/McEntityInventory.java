package sciapi.api.mc.inventory.entity;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import sciapi.api.mc.inventory.McInventory;
import sciapi.api.mc.inventory.pos.McInvWorld;

public abstract class McEntityInventory extends McInventory implements IExtendedEntityProperties {
	public Entity entity;
	
	public McEntityInventory(Entity e){
		entity = e;
	}
	
	@Override
	@Deprecated
	public void init(Entity entity, World world){
		
	}

}
