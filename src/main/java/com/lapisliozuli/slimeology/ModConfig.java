package com.lapisliozuli.slimeology;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;
import me.sargunvohra.mcmods.autoconfig1u.serializer.Toml4jConfigSerializer;
import me.sargunvohra.mcmods.autoconfig1u.shadowed.blue.endless.jankson.Comment;

//class ModConfig {
@Config(name = Slimeology.MOD_ID)
public final class ModConfig implements ConfigData {

    @ConfigEntry.Category("coloured_slime_spawning")
    @ConfigEntry.Gui.TransitiveObject
    public SECSpawning secSpawning = new SECSpawning();
    public SECBiomes secBiomes = new SECBiomes();


    @Config(name = "coloured_slime_spawning")
    public static final class SECSpawning implements ConfigData {
        @SuppressWarnings("unused")
        @ConfigEntry.Gui.Excluded
//        // Not sure what this does.
//        public int configVersion = 0;

        // Sets min height for spawning. I don't know how this can affect Slime spawning, or if it'll break the game.
        @Comment("Sets the min height at which Coloured Slimes may spawn")
        @ConfigEntry.BoundedDiscrete(min = 40, max = 256)
        public int heightFloor = 50;

        // Sets max height for spawning
        @Comment("Sets the max height at which Coloured Slimes may spawn")
        @ConfigEntry.BoundedDiscrete(max = 256)
        public int heightCap = 256;

        // Toggles dependency on moon phase. Default of 1.0F, set to 0.0F to turn off. See if can change to boolean.
        // Maybe use '&&' to toggle.
        @Comment("Checks if moon phase influences Coloured Slime spawning")
        public boolean ignoreMoon = false;

        // Tweak the multiplier. Default 0.5F
        @Comment("Sets the multiplier ranging from 0.0 to 1.0. Vanilla uses 0.5.")
//        public float spawnMultiplier = 0.5F;
        public float spawnMultiplier = 1.0F;

        // Switch for the debug statements in canSpawnSEC(). Default false.
        // Seems better to check if an option is true rather than false.
        @Comment("Mainly for modders and server owners: Used in debugging of slime spawning.")
        public boolean spawnReporting = true;


        // Doesn't actually seem to allow daytime spawning.
//        // Light level cap. Default of 8. Raise to max 15
//        @Comment("Sets the max light level at which Coloured Slimes may spawn")
//        @ConfigEntry.BoundedDiscrete(max = 15)
//        public int lightCap = 15;

        private SECSpawning() { }
    }


    @Config(name = "coloured_slime_biomes")
    public static final class SECBiomes implements ConfigData {
//        @ConfigEntry.Gui.Excluded
//        public int configVersion = 0;

        public String biomesForSECWhite = "minecraft:forest, minecraft:snowy_tundra, minecraft:snowy_mountains, minecraft:snowy_beach, " +
                "minecraft:snowy_taiga_hills, minecraft:snowy_taiga_mountains";
        public String biomesForSECOrange = "minecraft:desert, minecraft:savanna";
        public String biomesForSECMagenta = "minecraft:plains";
        public String biomesForSECLightBlue = "minecraft:frozen_ocean, minecraft:frozen_river, minecraft:taiga_hills, minecraft:giant_tree_taiga, " +
                "minecraft:giant_tree_taiga_hills, minecraft:snowy_taiga, minecraft:taiga_mountains, minecraft:ice_spikes, " +
                "minecraft:giant_spruce_taiga_hills, minecraft:giant_spruce_taiga";
        public String biomesForSECYellow = "minecraft:desert, minecraft:desert_hills";
        public String biomesForSECLime = "minecraft:plains, minecraft:savanna_plateau, minecraft:wooded_badlands_plateau, minecraft:shattered_savanna, " +
                "minecraft:shattered_savanna_plateau, minecraft:bamboo_jungle, minecraft:bamboo_jungle_hills";
        public String biomesForSECPink = "minecraft:birch_forest, minecraft:birch_forest_hills, minecraft:tall_birch_forest, minecraft:tall_birch_hills";
        public String biomesForSECGray = "minecraft:mountains, minecraft:mountain_edge, minecraft:gravelly_mountains, minecraft:modified_gravelly_mountains";
        public String biomesForSECLightGray = "minecraft:mountains, minecraft:stone_shore";
        public String biomesForSECCyan = "minecraft:taiga";
        public String biomesForSECPurple = "minecraft:forest, minecraft:river";
        public String biomesForSECBlue = "minecraft:river, minecraft:beach, minecraft:desert_lakes";
        public String biomesForSECBrown = "minecraft:wooded_hills, minecraft:wooded_mountains";
        public String biomesForSECGreen = "minecraft:wooded_hills, minecraft:jungle, minecraft:jungle_hills, minecraft:jungle_edge, " +
                "minecraft:modified_jungle, minecraft:modified_jungle_edge";
        public String biomesForSECRed = "minecraft:plains, minecraft:badlands, minecraft:wooded_badlands_plateau, minecraft:sunflower_plains, " +
                "minecraft:eroded_badlands, minecraft:modified_wooded_badlands_plateau, minecraft:modified_badlands_plateau";
        public String biomesForSECBlack = "minecraft:forest, minecraft:dark_forest, minecraft:dark_forest_hills";

        private SECBiomes() { }
    }

    public void registerConfig() {
        AutoConfig.register(ModConfig.class, Toml4jConfigSerializer::new);
    }

}
