package com.lapisliozuli.slimeology.registry;

import com.lapisliozuli.slimeology.Slimeology;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class RegisterItems {
//    public static final Item SLIME_DEBUG_SPAWN_EGG = new SpawnEggItem(RegisterSEC.SLIME_ENTITY_DEBUG, 0xFF00FF, 0x000000, new Item.Settings().group(Slimeology.SLIMEOLOGY));
    public static final Item SLIME_WHITE_SPAWN_EGG = new SpawnEggItem(RegisterSEC.SLIME_ENTITY_WHITE, 0xF8F8F8, 0xDCDCDC, new Item.Settings().group(Slimeology.SLIMEOLOGY));
    public static final Item SLIME_ORANGE_SPAWN_EGG = new SpawnEggItem(RegisterSEC.SLIME_ENTITY_ORANGE, 0xFB771F, 0xD75E00, new Item.Settings().group(Slimeology.SLIMEOLOGY));
    public static final Item SLIME_MAGENTA_SPAWN_EGG = new SpawnEggItem(RegisterSEC.SLIME_ENTITY_MAGENTA, 0xD24BC9, 0xA52C99, new Item.Settings().group(Slimeology.SLIMEOLOGY));
    public static final Item SLIME_LIGHT_BLUE_SPAWN_EGG = new SpawnEggItem(RegisterSEC.SLIME_ENTITY_LIGHT_BLUE, 0x45B0E6, 0x1891B6, new Item.Settings().group(Slimeology.SLIMEOLOGY));
    public static final Item SLIME_YELLOW_SPAWN_EGG = new SpawnEggItem(RegisterSEC.SLIME_ENTITY_YELLOW, 0xFFD248, 0xD9B318, new Item.Settings().group(Slimeology.SLIMEOLOGY));
    public static final Item SLIME_LIME_SPAWN_EGG = new SpawnEggItem(RegisterSEC.SLIME_ENTITY_LIME, 0x8BC42B, 0x5EA500, new Item.Settings().group(Slimeology.SLIMEOLOGY));
    public static final Item SLIME_PINK_SPAWN_EGG = new SpawnEggItem(RegisterSEC.SLIME_ENTITY_PINK, 0xFE88B6, 0xD16986, new Item.Settings().group(Slimeology.SLIMEOLOGY));
    public static final Item SLIME_GRAY_SPAWN_EGG = new SpawnEggItem(RegisterSEC.SLIME_ENTITY_GRAY, 0x4F4F4F, 0x2B2B2B, new Item.Settings().group(Slimeology.SLIMEOLOGY));
    public static final Item SLIME_LIGHT_GRAY_SPAWN_EGG = new SpawnEggItem(RegisterSEC.SLIME_ENTITY_LIGHT_GRAY, 0x9E9E9E, 0x7B7B7B, new Item.Settings().group(Slimeology.SLIMEOLOGY));
    public static final Item SLIME_CYAN_SPAWN_EGG = new SpawnEggItem(RegisterSEC.SLIME_ENTITY_CYAN, 0x2199A8, 0x007A78, new Item.Settings().group(Slimeology.SLIMEOLOGY));
    public static final Item SLIME_PURPLE_SPAWN_EGG = new SpawnEggItem(RegisterSEC.SLIME_ENTITY_PURPLE, 0x942FC4, 0x671094, new Item.Settings().group(Slimeology.SLIMEOLOGY));
    public static final Item SLIME_BLUE_SPAWN_EGG = new SpawnEggItem(RegisterSEC.SLIME_ENTITY_BLUE, 0x4741B6, 0x1A2286, new Item.Settings().group(Slimeology.SLIMEOLOGY));
    public static final Item SLIME_BROWN_SPAWN_EGG = new SpawnEggItem(RegisterSEC.SLIME_ENTITY_BROWN, 0x8E513E, 0x61320E, new Item.Settings().group(Slimeology.SLIMEOLOGY));
    public static final Item SLIME_GREEN_SPAWN_EGG = new SpawnEggItem(RegisterSEC.SLIME_ENTITY_GREEN, 0x697922, 0x2C5A00, new Item.Settings().group(Slimeology.SLIMEOLOGY));
    public static final Item SLIME_RED_SPAWN_EGG = new SpawnEggItem(RegisterSEC.SLIME_ENTITY_RED, 0xBB2B32, 0x8E0C02, new Item.Settings().group(Slimeology.SLIMEOLOGY));
    public static final Item SLIME_BLACK_SPAWN_EGG = new SpawnEggItem(RegisterSEC.SLIME_ENTITY_BLACK, 0x1F1F1F, 0x000000, new Item.Settings().group(Slimeology.SLIMEOLOGY));



    public static void registerItems() {
//        Registry.register(Registry.ITEM, new Identifier(Slimeology.MOD_ID, "slime_debug_spawn_egg"), SLIME_DEBUG_SPAWN_EGG);
        Registry.register(Registry.ITEM, new Identifier(Slimeology.MOD_ID, "slime_white_spawn_egg"), SLIME_WHITE_SPAWN_EGG);
        Registry.register(Registry.ITEM, new Identifier(Slimeology.MOD_ID, "slime_orange_spawn_egg"), SLIME_ORANGE_SPAWN_EGG);
        Registry.register(Registry.ITEM, new Identifier(Slimeology.MOD_ID, "slime_magenta_spawn_egg"), SLIME_MAGENTA_SPAWN_EGG);
        Registry.register(Registry.ITEM, new Identifier(Slimeology.MOD_ID, "slime_light_blue_spawn_egg"), SLIME_LIGHT_BLUE_SPAWN_EGG);
        Registry.register(Registry.ITEM, new Identifier(Slimeology.MOD_ID, "slime_yellow_spawn_egg"), SLIME_YELLOW_SPAWN_EGG);
        Registry.register(Registry.ITEM, new Identifier(Slimeology.MOD_ID, "slime_lime_spawn_egg"), SLIME_LIME_SPAWN_EGG);
        Registry.register(Registry.ITEM, new Identifier(Slimeology.MOD_ID, "slime_pink_spawn_egg"), SLIME_PINK_SPAWN_EGG);
        Registry.register(Registry.ITEM, new Identifier(Slimeology.MOD_ID, "slime_gray_spawn_egg"), SLIME_GRAY_SPAWN_EGG);
        Registry.register(Registry.ITEM, new Identifier(Slimeology.MOD_ID, "slime_light_gray_spawn_egg"), SLIME_LIGHT_GRAY_SPAWN_EGG);
        Registry.register(Registry.ITEM, new Identifier(Slimeology.MOD_ID, "slime_cyan_spawn_egg"), SLIME_CYAN_SPAWN_EGG);
        Registry.register(Registry.ITEM, new Identifier(Slimeology.MOD_ID, "slime_purple_spawn_egg"), SLIME_PURPLE_SPAWN_EGG);
        Registry.register(Registry.ITEM, new Identifier(Slimeology.MOD_ID, "slime_blue_spawn_egg"), SLIME_BLUE_SPAWN_EGG);
        Registry.register(Registry.ITEM, new Identifier(Slimeology.MOD_ID, "slime_brown_spawn_egg"), SLIME_BROWN_SPAWN_EGG);
        Registry.register(Registry.ITEM, new Identifier(Slimeology.MOD_ID, "slime_green_spawn_egg"), SLIME_GREEN_SPAWN_EGG);
        Registry.register(Registry.ITEM, new Identifier(Slimeology.MOD_ID, "slime_red_spawn_egg"), SLIME_RED_SPAWN_EGG);
        Registry.register(Registry.ITEM, new Identifier(Slimeology.MOD_ID, "slime_black_spawn_egg"), SLIME_BLACK_SPAWN_EGG);
    }

}
