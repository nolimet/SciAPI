package abr.heatcraft.fluid;

import abr.heatcraft.item.HItems;
import abr.heatcraft.item.ItemLiquidTank;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class HFluids {
	
	public static Fluid liqiron;
	
	public static Fluid liqgold;

	public static Fluid liqcopper;

	public static Fluid liqtin;
	
	public static Fluid liqlead;
	
	public static Fluid liqsilver;
	
	public static void Init(){
		liqiron = new OreFluid("liqiron");
		liqgold = new OreFluid("liqgold");
		liqcopper = new OreFluid("liqcopper");
		liqtin = new OreFluid("liqtin");
		liqlead = new OreFluid("liqlead");
		liqsilver = new OreFluid("liqsilver");
		
		FluidRegistry.registerFluid(liqiron);
		FluidRegistry.registerFluid(liqgold);
		FluidRegistry.registerFluid(liqcopper);
		FluidRegistry.registerFluid(liqtin);
		FluidRegistry.registerFluid(liqlead);
		FluidRegistry.registerFluid(liqsilver);
	}
	
	@SideOnly(Side.CLIENT)
	public static void onTextureInit(TextureMap map)
	{
		liqiron.setIcons(((ItemLiquidTank)HItems.liqtank).iron);
		liqgold.setIcons(((ItemLiquidTank)HItems.liqtank).gold);
		liqcopper.setIcons(((ItemLiquidTank)HItems.liqtank).copper);
		liqtin.setIcons(((ItemLiquidTank)HItems.liqtank).tin);
		liqlead.setIcons(((ItemLiquidTank)HItems.liqtank).lead);
		liqsilver.setIcons(((ItemLiquidTank)HItems.liqtank).silver);
	}
}
