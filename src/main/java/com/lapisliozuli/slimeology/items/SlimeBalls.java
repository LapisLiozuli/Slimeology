package com.lapisliozuli.slimeology.items;

import com.lapisliozuli.slimeology.Slimeology;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SlimeBalls {
    public static final Item SLIME_BALL_DEBUG = new Item(new Item.Settings().group(Slimeology.SLIMEOLOGY));
    public static final Item SLIME_BALL_WHITE = new Item(new Item.Settings().group(Slimeology.SLIMEOLOGY));
    public static final Item SLIME_BALL_ORANGE = new Item(new Item.Settings().group(Slimeology.SLIMEOLOGY));
    public static final Item SLIME_BALL_MAGENTA = new Item(new Item.Settings().group(Slimeology.SLIMEOLOGY));
    public static final Item SLIME_BALL_LIGHT_BLUE = new Item(new Item.Settings().group(Slimeology.SLIMEOLOGY));
    public static final Item SLIME_BALL_YELLOW = new Item(new Item.Settings().group(Slimeology.SLIMEOLOGY));
    public static final Item SLIME_BALL_LIME = new Item(new Item.Settings().group(Slimeology.SLIMEOLOGY));
    public static final Item SLIME_BALL_PINK = new Item(new Item.Settings().group(Slimeology.SLIMEOLOGY));
    public static final Item SLIME_BALL_GRAY = new Item(new Item.Settings().group(Slimeology.SLIMEOLOGY));
    public static final Item SLIME_BALL_LIGHT_GRAY = new Item(new Item.Settings().group(Slimeology.SLIMEOLOGY));
    public static final Item SLIME_BALL_CYAN = new Item(new Item.Settings().group(Slimeology.SLIMEOLOGY));
    public static final Item SLIME_BALL_PURPLE = new Item(new Item.Settings().group(Slimeology.SLIMEOLOGY));
    public static final Item SLIME_BALL_BLUE = new Item(new Item.Settings().group(Slimeology.SLIMEOLOGY));
    public static final Item SLIME_BALL_BROWN = new Item(new Item.Settings().group(Slimeology.SLIMEOLOGY));
    public static final Item SLIME_BALL_GREEN = new Item(new Item.Settings().group(Slimeology.SLIMEOLOGY));
    public static final Item SLIME_BALL_RED = new Item(new Item.Settings().group(Slimeology.SLIMEOLOGY));
    public static final Item SLIME_BALL_BLACK = new Item(new Item.Settings().group(Slimeology.SLIMEOLOGY));

    public static void registerSlimeBalls() {
        Registry.register(Registry.ITEM, new Identifier(Slimeology.MOD_ID, "slime_ball_debug"), SLIME_BALL_DEBUG);
        Registry.register(Registry.ITEM, new Identifier(Slimeology.MOD_ID, "slime_ball_white"), SLIME_BALL_WHITE);
        Registry.register(Registry.ITEM, new Identifier(Slimeology.MOD_ID, "slime_ball_orange"), SLIME_BALL_ORANGE);
        Registry.register(Registry.ITEM, new Identifier(Slimeology.MOD_ID, "slime_ball_magenta"), SLIME_BALL_MAGENTA);
        Registry.register(Registry.ITEM, new Identifier(Slimeology.MOD_ID, "slime_ball_light_blue"), SLIME_BALL_LIGHT_BLUE);
        Registry.register(Registry.ITEM, new Identifier(Slimeology.MOD_ID, "slime_ball_yellow"), SLIME_BALL_YELLOW);
        Registry.register(Registry.ITEM, new Identifier(Slimeology.MOD_ID, "slime_ball_lime"), SLIME_BALL_LIME);
        Registry.register(Registry.ITEM, new Identifier(Slimeology.MOD_ID, "slime_ball_pink"), SLIME_BALL_PINK);
        Registry.register(Registry.ITEM, new Identifier(Slimeology.MOD_ID, "slime_ball_gray"), SLIME_BALL_GRAY);
        Registry.register(Registry.ITEM, new Identifier(Slimeology.MOD_ID, "slime_ball_light_gray"), SLIME_BALL_LIGHT_GRAY);
        Registry.register(Registry.ITEM, new Identifier(Slimeology.MOD_ID, "slime_ball_cyan"), SLIME_BALL_CYAN);
        Registry.register(Registry.ITEM, new Identifier(Slimeology.MOD_ID, "slime_ball_purple"), SLIME_BALL_PURPLE);
        Registry.register(Registry.ITEM, new Identifier(Slimeology.MOD_ID, "slime_ball_blue"), SLIME_BALL_BLUE);
        Registry.register(Registry.ITEM, new Identifier(Slimeology.MOD_ID, "slime_ball_brown"), SLIME_BALL_BROWN);
        Registry.register(Registry.ITEM, new Identifier(Slimeology.MOD_ID, "slime_ball_green"), SLIME_BALL_GREEN);
        Registry.register(Registry.ITEM, new Identifier(Slimeology.MOD_ID, "slime_ball_red"), SLIME_BALL_RED);
        Registry.register(Registry.ITEM, new Identifier(Slimeology.MOD_ID, "slime_ball_black"), SLIME_BALL_BLACK);
    }

}