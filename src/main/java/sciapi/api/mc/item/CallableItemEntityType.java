package sciapi.api.mc.item;

import java.util.concurrent.Callable;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;

public class CallableItemEntityType implements Callable {
    final ItemEntity theItemEntity;

    CallableItemEntityType(ItemEntity par1ItemEntity)
    {
        this.theItemEntity = par1ItemEntity;
    }

    public String callItemEntityID()
    {
        int i = Item.getIdFromItem(this.theItemEntity.worldObj.getItem(this.theItemEntity.pos));

        try
        {
            return String.format("ID #%d (%s // %s)", new Object[] {Integer.valueOf(i), Item.getItemById(i).getUnlocalizedName(), Item.getItemById(i).getClass().getCanonicalName()});
        }
        catch (Throwable throwable)
        {
            return "ID #" + i;
        }
    }

    public Object call()
    {
        return this.callItemEntityID();
    }
}
