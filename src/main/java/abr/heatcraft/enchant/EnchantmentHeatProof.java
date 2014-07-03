package abr.heatcraft.enchant;

import java.util.ArrayList;

import abr.heatcraft.api.enchant.ThermalEnchantableRegistry;

import com.google.common.collect.ObjectArrays;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.StatCollector;

/**
 * Heat-Proof.
 * */
public class EnchantmentHeatProof extends Enchantment {

	protected EnchantmentHeatProof(int effectId, int weight) {
		super(effectId, weight, EnumEnchantmentType.all);
		this.setName("heatProof");
	}


    /**
     * Returns the maximum level that the enchantment can have.
     */
    public int getMaxLevel()
    {
        return 6;
    }

    /**
     * Returns the minimal value of enchantability needed on the enchantment level passed.
     */
    public int getMinEnchantability(int par1)
    {
        return par1 * 5 - 2;
    }

    /**
     * Returns the maximum value of enchantability nedded on the enchantment level passed.
     */
    public int getMaxEnchantability(int par1)
    {
        return this.getMinEnchantability(par1) + 2;
    }

    /**
     * Calculates de damage protection of the enchantment based on level and damage source passed.
     */
    public int calcModifierDamage(int par1, DamageSource par2DamageSource)
    {
        return 0;
    }

    /**
     * Calculates de (magic) damage done by the enchantment on a living entity based on level and entity passed.
     */
    public float calcModifierLiving(int par1, EntityLivingBase par2EntityLivingBase)
    {
        return 0.0F;
    }

    /**
     * Determines if the enchantment passed can be applyied together with this enchantment.
     */
    public boolean canApplyTogether(Enchantment par1Enchantment)
    {
        return this != par1Enchantment;
    }

    public boolean canApply(ItemStack par1ItemStack)
    {
        return ThermalEnchantableRegistry.isThermalEnchantable(par1ItemStack.getItem());
    }

    /**
     * This applies specifically to applying at the enchanting table. The other method {@link #canApply(ItemStack)}
     * applies for <i>all possible</i> enchantments.
     * @param stack
     * @return
     */
    public boolean canApplyAtEnchantingTable(ItemStack stack)
    {
        return canApply(stack);
    }

    /**
     * Is this enchantment allowed to be enchanted on books via Enchantment Table
     * @return false to disable the vanilla feature
     */
    public boolean isAllowedOnBooks()
    {
        return true;
    }
    
    
    /**
     * Heat Loss Rate per level
     * */
    public static double getEffect(short lvl) {
    	return 1.0 - lvl / 8.0;
    }
}
