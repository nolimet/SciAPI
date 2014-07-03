package abr.heatcraft.renderer;

import org.lwjgl.opengl.GL11;

import abr.heatcraft.block.BlockHeatCauldron;
import abr.heatcraft.block.HBlocks;
import abr.heatcraft.tile.TileHeatCauldron;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class CauldronRenderer implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID,
			RenderBlocks renderer) {
		
		if(block != HBlocks.heatCauldron)
			return;
		
        Tessellator tessellator = Tessellator.instance;
        
        float f = 1.0f;
        float f1 = 0.5f;
        float f2 = 0.5f;
        float f3 = 0.5f;
        float f4;

        if (EntityRenderer.anaglyphEnable)
        {
            float f5 = (f1 * 30.0F + f2 * 59.0F + f3 * 11.0F) / 100.0F;
            f4 = (f1 * 30.0F + f2 * 70.0F) / 100.0F;
            float f6 = (f1 * 30.0F + f3 * 70.0F) / 100.0F;
            f1 = f5;
            f2 = f4;
            f3 = f6;
        }
        
        tessellator.setColorOpaque_F(f * f1, f * f2, f * f3);
        IIcon icon = block.getBlockTextureFromSide(2);
        f4 = 0.125F;
                
        IIcon icon1 = BlockHeatCauldron.getCauldronIcon("inner");
        
        block.setBlockBoundsForItemRender();
        renderer.setRenderBoundsFromBlock(block);
        GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, -1.0F, 0.0F);
        renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 0, 0));
        tessellator.draw();

        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 1, 0));
        tessellator.draw();

        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, -1.0F);
        renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 2, 0));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, 1.0F);
        renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 3, 0));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(-1.0F, 0.0F, 0.0F);
        renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 4, 0));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(1.0F, 0.0F, 0.0F);
        renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 5, 0));
        tessellator.draw();
        
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        renderer.renderFaceYPos(block, (double)0, (double)((float)0 - 1.0F + 0.25F), (double)0, icon1);
        tessellator.draw();
        
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, -1.0F, 0.0F);
        renderer.renderFaceYNeg(block, (double)0, (double)((float)0 + 1.0F - 0.75F), (double)0, icon1);
        tessellator.draw();
        
        tessellator.startDrawingQuads();
        tessellator.setNormal(1.0F, 0.0F, 0.0F);
        renderer.renderFaceXPos(block, (double)((float) - 1.0F + f4), (double)0, (double)0, icon);
        tessellator.draw();
        
        tessellator.startDrawingQuads();
        tessellator.setNormal(-1.0F, 0.0F, 0.0F);
        renderer.renderFaceXNeg(block, (double)((float) + 1.0F - f4), (double)0, (double)0, icon);
        tessellator.draw();
        
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, 1.0F);
        renderer.renderFaceZPos(block, (double)0, (double)0, (double)((float) - 1.0F + f4), icon);
        tessellator.draw();
        
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, -1.0F);
        renderer.renderFaceZNeg(block, (double)0, (double)0, (double)((float) + 1.0F - f4), icon);
        tessellator.draw();
                
        tessellator.setColorOpaque_F(1.0f, 1.0f, 1.0f);
    }

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer) {
		if(block != HBlocks.heatCauldron)
			return false;
		
        renderer.renderStandardBlock(block, x, y, z);
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
        float f = 1.0F;
        int l = block.colorMultiplier(world, x, y, z);
        float f1 = (float)(l >> 16 & 255) / 255.0F;
        float f2 = (float)(l >> 8 & 255) / 255.0F;
        float f3 = (float)(l & 255) / 255.0F;
        float f4;

        if (EntityRenderer.anaglyphEnable)
        {
            float f5 = (f1 * 30.0F + f2 * 59.0F + f3 * 11.0F) / 100.0F;
            f4 = (f1 * 30.0F + f2 * 70.0F) / 100.0F;
            float f6 = (f1 * 30.0F + f3 * 70.0F) / 100.0F;
            f1 = f5;
            f2 = f4;
            f3 = f6;
        }

        tessellator.setColorOpaque_F(f * f1, f * f2, f * f3);
        IIcon icon = block.getBlockTextureFromSide(2);
        f4 = 0.125F;
        renderer.renderFaceXPos(block, (double)((float)x - 1.0F + f4), (double)y, (double)z, icon);
        renderer.renderFaceXNeg(block, (double)((float)x + 1.0F - f4), (double)y, (double)z, icon);
        renderer.renderFaceZPos(block, (double)x, (double)y, (double)((float)z - 1.0F + f4), icon);
        renderer.renderFaceZNeg(block, (double)x, (double)y, (double)((float)z + 1.0F - f4), icon);
        IIcon icon1 = BlockHeatCauldron.getCauldronIcon("inner");
        renderer.renderFaceYPos(block, (double)x, (double)((float)y - 1.0F + 0.25F), (double)z, icon1);
        renderer.renderFaceYNeg(block, (double)x, (double)((float)y + 1.0F - 0.75F), (double)z, icon1);
        
        tessellator.setColorOpaque_F(1.0f, 1.0f, 1.0f);
        
        TileHeatCauldron cauldron = (TileHeatCauldron) world.getTileEntity(x, y, z);
        FluidStack fs = cauldron.getTankInfo(ForgeDirection.UP)[0].fluid;
        
        if(fs != null){
        	if(fs.amount > 0) {
        		IIcon icon2 = fs.getFluid().getIcon();
        		renderer.renderFaceYPos(block, (double)x, (double)((float)y - 1.0F + (6.0F + (float)fs.amount * 0.002F) / 16.0F), (double)z, icon2);
        	}
        }
        

        return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int m) {
		return true;
	}

	@Override
	public int getRenderId() {
		return 0;
	}

}
