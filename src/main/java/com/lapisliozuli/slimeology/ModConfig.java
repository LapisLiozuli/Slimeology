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
        public float spawnMultiplier = 0.5F;

        // Switch for the debug statements in canSpawnSEC(). Default false.
        // Seems better to check if an option is true rather than false.
        @Comment("Mainly for modders: Used in debugging of slime spawning.")
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

        @Comment("List of biome numeric IDs and biomes:" + "\n"
                + "(I'm sorry for the jankiness)" + "\n"
                + "0: minecraft:ocean, 1: minecraft:plains, 2: minecraft:desert, 3: minecraft:mountains, 4: minecraft:forest, 5: minecraft:taiga," + "\n"
                + "6: minecraft:swamp, 7: minecraft:river, 8: minecraft:nether_wastes, 9: minecraft:the_end, 10: minecraft:frozen_ocean," + "\n"
                + "11: minecraft:frozen_river, 12: minecraft:snowy_tundra, 13: minecraft:snowy_mountains, 14: minecraft:mushroom_fields, 15: minecraft:mushroom_field_shore," + "\n"
                + "16: minecraft:beach, 17: minecraft:desert_hills, 18: minecraft:wooded_hills, 19: minecraft:taiga_hills, 20: minecraft:mountain_edge," + "\n"
                + "21: minecraft:jungle, 22: minecraft:jungle_hills, 23: minecraft:jungle_edge, 24: minecraft:deep_ocean, 25: minecraft:stone_shore," + "\n"
                + "26: minecraft:snowy_beach, 27: minecraft:birch_forest, 28: minecraft:birch_forest_hills, 29: minecraft:dark_forest, 30: minecraft:snowy_taiga," + "\n"
                + "31: minecraft:snowy_taiga_hills, 32: minecraft:giant_tree_taiga, 33: minecraft:giant_tree_taiga_hills, 34: minecraft:wooded_mountains, 35: minecraft:savanna," + "\n"
                + "36: minecraft:savanna_plateau, 37: minecraft:badlands, 38: minecraft:wooded_badlands_plateau, 39: minecraft:badlands_plateau, 40: minecraft:small_end_islands," + "\n"
                + "41: minecraft:end_midlands, 42: minecraft:end_highlands, 43: minecraft:end_barrens, 44: minecraft:warm_ocean, 45: minecraft:lukewarm_ocean," + "\n"
                + "46: minecraft:cold_ocean, 47: minecraft:deep_warm_ocean, 48: minecraft:deep_lukewarm_ocean, 49: minecraft:deep_cold_ocean, 50: minecraft:deep_frozen_ocean," + "\n"
                + "127: minecraft:the_void, 129: minecraft:sunflower_plains, 130: minecraft:desert_lakes, 131: minecraft:gravelly_mountains, 132: minecraft:flower_forest," + "\n"
                + "133: minecraft:taiga_mountains, 134: minecraft:swamp_hills, 140: minecraft:ice_spikes, 149: minecraft:modified_jungle, 151: minecraft:modified_jungle_edge," + "\n"
                + "155: minecraft:tall_birch_forest, 156: minecraft:tall_birch_hills, 157: minecraft:dark_forest_hills, 158: minecraft:snowy_taiga_mountains, 160: minecraft:giant_spruce_taiga," + "\n"
                + "161: minecraft:giant_spruce_taiga_hills, 162: minecraft:modified_gravelly_mountains, 163: minecraft:shattered_savanna, 164: minecraft:shattered_savanna_plateau, 165: minecraft:eroded_badlands," + "\n"
                + "166: minecraft:modified_wooded_badlands_plateau, 167: minecraft:modified_badlands_plateau, 168: minecraft:bamboo_jungle, 169: minecraft:bamboo_jungle_hills, 170: minecraft:soul_sand_valley," + "\n"
                + "171: minecraft:crimson_forest, 172: minecraft:warped_forest, 173: minecraft:basalt_deltas")
        public String biomesForSECWhite = "4, 12, 13, 26, 31, 158";
        public String biomesForSECOrange = "2, 35";
        public String biomesForSECMagenta = "1";
        public String biomesForSECLightBlue = "10, 11, 19, 32, 33, 30, 133, 140, 161, 160";
        public String biomesForSECYellow = "2, 17";
        public String biomesForSECLime = "1, 36, 38, 163, 164, 168, 169";
        public String biomesForSECPink = "27, 28, 155, 156";
        public String biomesForSECGray = "3, 20, 131, 162";
        public String biomesForSECLightGray = "3, 25";
        public String biomesForSECCyan = "5";
        public String biomesForSECPurple = "4, 7";
        public String biomesForSECBlue = "7, 16, 130";
        public String biomesForSECBrown = "18, 34";
        public String biomesForSECGreen = "18, 21, 22, 23, 149, 151";
        public String biomesForSECRed = "1, 37, 38, 129, 165, 166, 167";
        public String biomesForSECBlack = "4, 29, 157";

        private SECBiomes() { }
    }

    public void registerConfig() {
        AutoConfig.register(ModConfig.class, Toml4jConfigSerializer::new);
    }

}
