package com.lapisliozuli.slimeology.items;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Lazy;

import java.util.function.Supplier;

// Copied this approach off Mythic Metals
public enum SlimeologicArmourMaterials implements ArmorMaterial {

    SLIMY_ARMOUR_MATERIAL("slimy_armour_material", 2, new int[]{1, 2, 3, 1}, 2, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 2.0F, 0.2F, () -> {
        return Ingredient.ofItems(Items.SLIME_BALL);
    }),
    STICKY_ARMOUR_MATERIAL("sticky_armour_material", 2, new int[]{1, 2, 3, 1}, 2, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 2.0F, 0.2F, () -> {
        return Ingredient.ofItems(Items.SLIME_BLOCK);
    });

//    // For RegisterIngots
//    public static final Item Slimy_Armour_Material = new Item(new Item.Settings().group(Slimeology.SLIMEOLOGY));
//    // Not sure if I want to make it like this? But just use this first to test the bulk approach.
//    public static final Item Sticky_Armour_Material = new Item(new Item.Settings().group(Slimeology.SLIMEOLOGY));

    // Why does it want to convert to local variable in constructor? Because I used class, not enum
    private static final int[] BASE_DURABILITY = new int[]{13, 15, 16, 11};
    private final String name;
    private final int durabilityMultiplier;
    private final int[] protectionAmounts;
    private final int enchantability;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final Lazy<Ingredient> repairIngredientSupplier;


    private SlimeologicArmourMaterials(String name, int durabilityMultiplier, int[] protectionAmounts, int enchantability, SoundEvent equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredientSupplier) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionAmounts = protectionAmounts;
        this.enchantability = enchantability;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredientSupplier = new Lazy<>(repairIngredientSupplier);
    }

    @Override
    public int getDurability(EquipmentSlot slot) {
        return BASE_DURABILITY[slot.getEntitySlotId()] * this.durabilityMultiplier;
    }

    public int getProtectionAmount(EquipmentSlot slot) {
        return this.protectionAmounts[slot.getEntitySlotId()];
    }

    public int getEnchantability() {
        return this.enchantability;
    }

    public SoundEvent getEquipSound() {
        return this.equipSound;
    }

    public Ingredient getRepairIngredient() {
        return (Ingredient)this.repairIngredientSupplier.get();
    }

//    @Environment(EnvType.CLIENT)
    public String getName() {
        return this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}