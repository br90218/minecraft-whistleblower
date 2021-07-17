package net.ycteng.mcwhistleblower.common.items;

import java.util.function.Supplier;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.ycteng.mcwhistleblower.McWhistleblower;
import net.minecraftforge.api.distmarker.Dist;

public enum TeamArmorMaterial implements IArmorMaterial {

	
	// I know this is bad, but there's no other way!
	TEAMARMOR_BASE("teamarmor", 1, new int[] { 1, 1, 1, 1 }, 1, SoundEvents.ARMOR_EQUIP_LEATHER, 1.0f, 1.0f, () -> {
		return Ingredient.of(Items.NETHERITE_INGOT);
	}),
	TEAMARMOR_001("teamarmor", 1, new int[] { 1, 8, 1, 1 }, 1, SoundEvents.ARMOR_EQUIP_LEATHER, 1.0f, 1.0f, () -> {
		return Ingredient.of(Items.NETHERITE_INGOT);
	}),
	TEAMARMOR_002("teamarmor", 1, new int[] { 1, 15, 1, 1 }, 1, SoundEvents.ARMOR_EQUIP_LEATHER, 1.0f, 1.0f, () -> {
		return Ingredient.of(Items.NETHERITE_INGOT);
	});

	private static final int[] HEALTH_PER_SLOT = new int[] { 13, 15, 16, 11 };
	private final String name;
	private final int durabilityMultiplier;
	private final int[] slotProtections;
	private final int enchantmentValue;
	private final SoundEvent sound;
	private final float toughness;
	private final float knockbackResistance;
	private final LazyValue<Ingredient> repairIngredient;

	private TeamArmorMaterial(String nameIn, int maxDamageFactorIn, int[] damageReductionAmountArrayIn,
			int enchantabilityIn, SoundEvent soundEventIn, float toughnessIn, float knockbackResistanceIn,
			Supplier<Ingredient> repairMaterialSupplier) {
		this.name = McWhistleblower.MODID + ":" + nameIn;
		this.durabilityMultiplier = maxDamageFactorIn;
		this.slotProtections = damageReductionAmountArrayIn;
		this.enchantmentValue = enchantabilityIn;
		this.sound = soundEventIn;
		this.toughness = toughnessIn;
		this.knockbackResistance = knockbackResistanceIn;
		this.repairIngredient = new LazyValue<>(repairMaterialSupplier);
	}

	@Override
	public int getDurabilityForSlot(EquipmentSlotType slot) {
		return HEALTH_PER_SLOT[slot.getIndex()] * durabilityMultiplier;
	}

	@Override
	public int getDefenseForSlot(EquipmentSlotType slot) {
		return slotProtections[slot.getIndex()];
	}

	@Override
	public int getEnchantmentValue() {
		return enchantmentValue;
	}

	@Override
	public SoundEvent getEquipSound() {
		return sound;
	}

	@Override
	public Ingredient getRepairIngredient() {
		return repairIngredient.get();
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public String getName() {
		return name;
	}

	@Override
	public float getToughness() {
		return toughness;
	}

	@Override
	public float getKnockbackResistance() {
		return knockbackResistance;
	}

}
