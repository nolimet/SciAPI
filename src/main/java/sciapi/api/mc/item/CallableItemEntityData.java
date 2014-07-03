package sciapi.api.mc.item;

import java.util.concurrent.Callable;

import net.minecraft.tileentity.TileEntity;

public class CallableItemEntityData implements Callable {
    final ItemEntity theItemEntity;

    CallableItemEntityData(ItemEntity par1ItemEntity)
    {
        this.theItemEntity = par1ItemEntity;
    }

    public String callTileEntityDataInfo()
    {
        int i = this.theItemEntity.worldObj.getItemDamage(this.theItemEntity.pos);

        if (i < 0)
        {
            return "Unknown? (Got " + i + ")";
        }
        else
        {
            String s = String.format("%4s", new Object[] {Integer.toBinaryString(i)}).replace(" ", "0");
            return String.format("%1$d / 0x%1$X / 0b%2$s", new Object[] {Integer.valueOf(i), s});
        }
    }

    public Object call()
    {
        return this.callTileEntityDataInfo();
    }
}
