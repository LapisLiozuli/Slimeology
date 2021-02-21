package com.lapisliozuli.slimeology.items;

import com.lapisliozuli.slimeology.Slimeology;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class RegisterArmourItems {
    // If you made a new material, this is where you would note it.

//    public static final Item SLIMY_MATERIAL = new ArmourItemSlimeball(new Item.Settings().group(Slimeology.SLIMEOLOGY));
    public static final Item SLIMY_MATERIAL_HELMET = new ArmorItem(SlimeologicArmourMaterials.SLIMY_ARMOUR_MATERIAL, EquipmentSlot.HEAD,
            new Item.Settings().group(Slimeology.SLIMEOLOGY));
    public static final Item SLIMY_MATERIAL_CHESTPLATE = new ArmorItem(SlimeologicArmourMaterials.SLIMY_ARMOUR_MATERIAL, EquipmentSlot.CHEST,
            new Item.Settings().group(Slimeology.SLIMEOLOGY));
    public static final Item SLIMY_MATERIAL_LEGGINGS = new ArmorItem(SlimeologicArmourMaterials.SLIMY_ARMOUR_MATERIAL, EquipmentSlot.LEGS,
            new Item.Settings().group(Slimeology.SLIMEOLOGY));
    public static final Item SLIMY_MATERIAL_BOOTS = new ArmorItem(SlimeologicArmourMaterials.SLIMY_ARMOUR_MATERIAL, EquipmentSlot.FEET,
            new Item.Settings().group(Slimeology.SLIMEOLOGY));
    public static final Item STICKY_MATERIAL_HELMET = new ArmorItem(SlimeologicArmourMaterials.STICKY_ARMOUR_MATERIAL, EquipmentSlot.HEAD,
            new Item.Settings().group(Slimeology.SLIMEOLOGY));
    public static final Item STICKY_MATERIAL_CHESTPLATE = new ArmorItem(SlimeologicArmourMaterials.STICKY_ARMOUR_MATERIAL, EquipmentSlot.CHEST,
            new Item.Settings().group(Slimeology.SLIMEOLOGY));
    public static final Item STICKY_MATERIAL_LEGGINGS = new ArmorItem(SlimeologicArmourMaterials.STICKY_ARMOUR_MATERIAL, EquipmentSlot.LEGS,
            new Item.Settings().group(Slimeology.SLIMEOLOGY));
    public static final Item STICKY_MATERIAL_BOOTS = new ArmorItem(SlimeologicArmourMaterials.STICKY_ARMOUR_MATERIAL, EquipmentSlot.FEET,
            new Item.Settings().group(Slimeology.SLIMEOLOGY));

    public static void registerArmourSlimy() {
        Registry.register(Registry.ITEM, new Identifier(Slimeology.MOD_ID, "slimy_material_helmet"), SLIMY_MATERIAL_HELMET);
        Registry.register(Registry.ITEM, new Identifier(Slimeology.MOD_ID, "slimy_material_chestplate"), SLIMY_MATERIAL_CHESTPLATE);
        Registry.register(Registry.ITEM, new Identifier(Slimeology.MOD_ID, "slimy_material_leggings"), SLIMY_MATERIAL_LEGGINGS);
        Registry.register(Registry.ITEM, new Identifier(Slimeology.MOD_ID, "slimy_material_boots"), SLIMY_MATERIAL_BOOTS);

        Registry.register(Registry.ITEM, new Identifier(Slimeology.MOD_ID, "sticky_material_helmet"), STICKY_MATERIAL_HELMET);
        Registry.register(Registry.ITEM, new Identifier(Slimeology.MOD_ID, "sticky_material_chestplate"), STICKY_MATERIAL_CHESTPLATE);
        Registry.register(Registry.ITEM, new Identifier(Slimeology.MOD_ID, "sticky_material_leggings"), STICKY_MATERIAL_LEGGINGS);
        Registry.register(Registry.ITEM, new Identifier(Slimeology.MOD_ID, "sticky_material_boots"), STICKY_MATERIAL_BOOTS);
    }

}
