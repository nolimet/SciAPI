package sciapi.api.heat.def;

import sciapi.api.heat.IHeatComponent;
import sciapi.api.mc.pos.McDirection;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public abstract class DefSpHeatCompBlock extends BlockContainer {

	public DefSpHeatCompBlock(Material par2Material) {
		super(par2Material);
	}
	
	@Override
	public void onNeighborBlockChange(World par1World, int x, int y, int z, Block neighborBlock){
		DefSpHeatComponent hc = (DefSpHeatComponent)par1World.getTileEntity(x, y, z);
		for(McDirection mcdir : McDirection.getAlldirs())
			hc.getNeighborComponent(mcdir);
	}

}
