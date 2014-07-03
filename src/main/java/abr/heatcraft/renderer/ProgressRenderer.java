package abr.heatcraft.renderer;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer;

public class ProgressRenderer implements IItemRenderer {
    
	private static RenderItem renderItem = new RenderItem();
	private boolean bgbase;
	private int startx, starty, xsize, ysize;
	
	public ProgressRenderer(boolean pbgbase, int sx, int sy, int x_size, int y_size){
		bgbase = pbgbase;
		startx = sx;
		starty = sy;
		xsize = x_size;
		ysize = y_size;
	}
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return type == ItemRenderType.INVENTORY;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {        
        IIcon bgicon = item.getItem().getIcon(item, 0);
        IIcon progicon = item.getItem().getIcon(item, 1);

        if(bgbase && item.getItemDamage() == 0){
        	renderItem.renderIcon(0, 0, bgicon, 16, 16);
        	return;
        }
        
        int prog = item.getItemDamage() * ysize / item.getMaxDamage();
        
        int i = item.getItem().getColorFromItemStack(item, 0);
        float f5 = (float)(i >> 16 & 255) / 255.0F;
        float f4 = (float)(i >> 8 & 255) / 255.0F;
        float f6 = (float)(i & 255) / 255.0F;
        GL11.glColor4f(f5, f4, f6, 1.0F);
        
        renderItem.renderIcon(0, 0, bgicon, 16, 16);
        
        this.renderPartIcon(renderItem, startx, starty, progicon, xsize, ysize, 0, prog, 0, 0);
	}

	private void renderPartIcon(RenderItem render, int x, int y, IIcon partIcon, int xsize, int ysize, int startx, int starty, int endx, int endy){
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV((double)(x + startx), (double)(y + ysize - endy), (double)render.zLevel, (double)partIcon.getInterpolatedU(startx), (double)partIcon.getInterpolatedV(ysize - endy));
        tessellator.addVertexWithUV((double)(x + xsize - endx), (double)(y + ysize - endy), (double)render.zLevel, (double)partIcon.getInterpolatedU(xsize - endx), (double)partIcon.getInterpolatedV(ysize - endy));
        tessellator.addVertexWithUV((double)(x + xsize - endx), (double)(y + starty), (double)render.zLevel, (double)partIcon.getInterpolatedU(xsize - endx), (double)partIcon.getInterpolatedV(starty));
        tessellator.addVertexWithUV((double)(x + startx), (double)(y + starty), (double)render.zLevel, (double)partIcon.getInterpolatedU(startx), (double)partIcon.getInterpolatedV(starty));
        tessellator.draw();
	}
}