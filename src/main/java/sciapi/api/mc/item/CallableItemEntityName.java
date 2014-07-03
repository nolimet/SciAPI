package sciapi.api.mc.item;

import java.util.concurrent.Callable;

import net.minecraft.tileentity.TileEntity;

public class CallableItemEntityName implements Callable {
    final ItemEntity theItemEntity;

    CallableItemEntityName(ItemEntity par1TileEntity)
    {
        this.theItemEntity = par1TileEntity;
    }

    public String callTileEntityName()
    {
        return (String)ItemEntity.getClassToNameMap().get(this.theItemEntity.getClass()) + " // " + this.theItemEntity.getClass().getCanonicalName();
    }

    public Object call()
    {
        return this.callTileEntityName();
    }
}
