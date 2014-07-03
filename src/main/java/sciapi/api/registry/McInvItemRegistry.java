package sciapi.api.registry;

import java.util.*;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.Entity;
import sciapi.api.mc.inventory.*;
import sciapi.api.mc.inventory.entity.*;
import sciapi.api.mc.item.ItemEntity;

public class McInvItemRegistry {
	public static void registerInvManager(IMcInvManager manager, Side side){
		McInventoryManager.getInstance(side).addInvManager(manager);
		McManagerRegistry.registerManager(manager, side);
	}
	
	public static void registerEntityInventoryType(IMcEntityInvManager eit, Side side){
		registerInvManager(eit, side);
		McEntityInvManager.getInstance(side).eitlist.add(eit);
	}
	
    public static void registerItemEntity(Class<? extends ItemEntity> itemEntityClass, String id)
    {
        ItemEntity.addMapping(itemEntityClass, id);
    }
}
