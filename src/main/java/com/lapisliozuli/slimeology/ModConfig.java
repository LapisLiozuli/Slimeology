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
        public float spawnMultiplier = 1.0F;

        // Tweak the SpawnEntry weight. Default 1000 based on own testing.
        @Comment("Sets the spawning weight. 1000 works based on testing. 100 is weight for zombies and other hostile mobs. 1,000,000 crowds out other hostile mobs.")
        public int spawnWeight = 500;

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

        @Comment("List of all vanilla biomes:" +
                "\nminecraft:ocean, minecraft:plains, minecraft:desert, minecraft:mountains, minecraft:forest," +
                "\nminecraft:taiga, minecraft:swamp, minecraft:river, minecraft:frozen_ocean, minecraft:frozen_river," +
                "\nminecraft:snowy_tundra, minecraft:snowy_mountains, minecraft:beach, minecraft:desert_hills, minecraft:wooded_hills," +
                "\nminecraft:taiga_hills, minecraft:mountain_edge, minecraft:jungle, minecraft:jungle_hills, minecraft:jungle_edge," +
                "\nminecraft:deep_ocean, minecraft:stone_shore, minecraft:snowy_beach, minecraft:birch_forest, minecraft:birch_forest_hills," +
                "\nminecraft:dark_forest, minecraft:snowy_taiga, minecraft:snowy_taiga_hills, minecraft:giant_tree_taiga, minecraft:giant_tree_taiga_hills," +
                "\nminecraft:wooded_mountains, minecraft:savanna, minecraft:savanna_plateau, minecraft:badlands, minecraft:wooded_badlands_plateau," +
                "\nminecraft:badlands_plateau, minecraft:warm_ocean, minecraft:lukewarm_ocean, minecraft:cold_ocean, minecraft:deep_warm_ocean," +
                "\nminecraft:deep_lukewarm_ocean, minecraft:deep_cold_ocean, minecraft:deep_frozen_ocean, minecraft:sunflower_plains, minecraft:desert_lakes," +
                "\nminecraft:gravelly_mountains, minecraft:flower_forest, minecraft:taiga_mountains, minecraft:swamp_hills, minecraft:ice_spikes," +
                "\nminecraft:modified_jungle, minecraft:modified_jungle_edge, minecraft:tall_birch_forest, minecraft:tall_birch_hills, minecraft:dark_forest_hills," +
                "\nminecraft:snowy_taiga_mountains, minecraft:giant_spruce_taiga, minecraft:giant_spruce_taiga_hills, minecraft:modified_gravelly_mountains, minecraft:shattered_savanna," +
                "\nminecraft:shattered_savanna_plateau, minecraft:eroded_badlands, minecraft:modified_wooded_badlands_plateau, minecraft:modified_badlands_plateau, minecraft:bamboo_jungle," +
                "\nminecraft:bamboo_jungle_hills" +
                "\n(Sorry I couldn't put this wall of text lower)")

        // I have become the Mojank.
//        public String biomesForSECDebug = "";
        public String biomesForSECWhite = "minecraft:mountains, minecraft:forest, minecraft:taiga, minecraft:frozen_ocean, minecraft:snowy_tundra, minecraft:snowy_mountains, minecraft:taiga_hills, minecraft:snowy_beach, minecraft:birch_forest, minecraft:birch_forest_hills, minecraft:snowy_taiga, minecraft:snowy_taiga_hills, minecraft:giant_tree_taiga, minecraft:giant_tree_taiga_hills, minecraft:cold_ocean, minecraft:deep_cold_ocean, minecraft:deep_frozen_ocean, minecraft:sunflower_plains, minecraft:gravelly_mountains, minecraft:flower_forest, minecraft:taiga_mountains, minecraft:ice_spikes, minecraft:tall_birch_forest, minecraft:tall_birch_hills, minecraft:snowy_taiga_mountains, minecraft:giant_spruce_taiga, minecraft:giant_spruce_taiga_hills, minecraft:modified_gravelly_mountains";
        public String biomesForSECOrange = "minecraft:desert, minecraft:desert_hills, minecraft:savanna, minecraft:savanna_plateau, minecraft:badlands, minecraft:wooded_badlands_plateau, minecraft:badlands_plateau, minecraft:desert_lakes, minecraft:flower_forest, minecraft:shattered_savanna, minecraft:shattered_savanna_plateau, minecraft:eroded_badlands, minecraft:modified_wooded_badlands_plateau, minecraft:modified_badlands_plateau, minecraft:lush_caves";
        public String biomesForSECMagenta = "minecraft:plains, minecraft:dark_forest, minecraft:savanna, minecraft:savanna_plateau, minecraft:badlands, minecraft:wooded_badlands_plateau, minecraft:badlands_plateau, minecraft:flower_forest, minecraft:dark_forest_hills, minecraft:shattered_savanna, minecraft:shattered_savanna_plateau, minecraft:eroded_badlands, minecraft:modified_wooded_badlands_plateau, minecraft:modified_badlands_plateau";
        public String biomesForSECLightBlue = "minecraft:mountains, minecraft:frozen_ocean, minecraft:frozen_river, minecraft:snowy_mountains, minecraft:taiga_hills, minecraft:mountain_edge, minecraft:snowy_beach, minecraft:snowy_taiga_hills, minecraft:giant_tree_taiga_hills, minecraft:wooded_mountains, minecraft:cold_ocean, minecraft:deep_cold_ocean, minecraft:flower_forest, minecraft:ice_spikes";
        public String biomesForSECYellow = "minecraft:plains, minecraft:desert, minecraft:forest, minecraft:river, minecraft:frozen_river, minecraft:beach, minecraft:desert_hills, minecraft:snowy_beach, minecraft:birch_forest, minecraft:birch_forest_hills, minecraft:savanna, minecraft:savanna_plateau, minecraft:badlands, minecraft:wooded_badlands_plateau, minecraft:badlands_plateau, minecraft:sunflower_plains, minecraft:desert_lakes, minecraft:flower_forest, minecraft:tall_birch_forest, minecraft:tall_birch_hills, minecraft:shattered_savanna, minecraft:shattered_savanna_plateau, minecraft:eroded_badlands, minecraft:modified_wooded_badlands_plateau, minecraft:modified_badlands_plateau";
        public String biomesForSECLime = "minecraft:plains, minecraft:desert_hills, minecraft:wooded_hills, minecraft:mountain_edge, minecraft:jungle_hills, minecraft:jungle_edge, minecraft:birch_forest_hills, minecraft:lukewarm_ocean, minecraft:deep_lukewarm_ocean, minecraft:sunflower_plains, minecraft:flower_forest, minecraft:tall_birch_hills, minecraft:bamboo_jungle_hills";
        public String biomesForSECPink = "minecraft:desert_hills, minecraft:wooded_hills, minecraft:jungle_hills, minecraft:jungle_edge, minecraft:warm_ocean, minecraft:deep_warm_ocean, minecraft:sunflower_plains, minecraft:flower_forest, minecraft:modified_jungle, minecraft:dark_forest_hills, minecraft:bamboo_jungle_hills";
        public String biomesForSECGray = "minecraft:ocean, minecraft:mountains, minecraft:taiga, minecraft:snowy_tundra, minecraft:snowy_mountains, minecraft:beach, minecraft:mountain_edge, minecraft:jungle_edge, minecraft:stone_shore, minecraft:birch_forest, minecraft:giant_tree_taiga, minecraft:wooded_mountains, minecraft:warm_ocean, minecraft:lukewarm_ocean, minecraft:deep_warm_ocean, minecraft:deep_lukewarm_ocean, minecraft:deep_cold_ocean, minecraft:deep_frozen_ocean, minecraft:gravelly_mountains, minecraft:flower_forest, minecraft:tall_birch_forest, minecraft:modified_gravelly_mountains, minecraft:lush_caves";
        public String biomesForSECLightGray = "minecraft:river, minecraft:frozen_ocean, minecraft:frozen_river, minecraft:snowy_tundra, minecraft:snowy_mountains, minecraft:taiga_hills, minecraft:mountain_edge, minecraft:jungle_hills, minecraft:snowy_beach, minecraft:birch_forest, minecraft:birch_forest_hills, minecraft:snowy_taiga, minecraft:snowy_taiga_hills, minecraft:giant_tree_taiga_hills, minecraft:cold_ocean, minecraft:gravelly_mountains, minecraft:flower_forest, minecraft:taiga_mountains, minecraft:tall_birch_forest, minecraft:tall_birch_hills, minecraft:snowy_taiga_mountains, minecraft:giant_spruce_taiga, minecraft:giant_spruce_taiga_hills, minecraft:modified_gravelly_mountains";
        public String biomesForSECCyan = "minecraft:ocean, minecraft:taiga, minecraft:frozen_ocean, minecraft:snowy_tundra, minecraft:taiga_hills, minecraft:deep_ocean, minecraft:stone_shore, minecraft:snowy_taiga, minecraft:snowy_taiga_hills, minecraft:giant_tree_taiga, minecraft:giant_tree_taiga_hills, minecraft:warm_ocean, minecraft:cold_ocean, minecraft:deep_warm_ocean, minecraft:deep_cold_ocean, minecraft:deep_frozen_ocean, minecraft:gravelly_mountains, minecraft:flower_forest, minecraft:taiga_mountains, minecraft:ice_spikes, minecraft:snowy_taiga_mountains, minecraft:giant_spruce_taiga, minecraft:giant_spruce_taiga_hills, minecraft:modified_gravelly_mountains";
        public String biomesForSECPurple = "minecraft:ocean, minecraft:deep_ocean, minecraft:dark_forest, minecraft:lukewarm_ocean, minecraft:deep_warm_ocean, minecraft:deep_lukewarm_ocean, minecraft:flower_forest, minecraft:ice_spikes, minecraft:dark_forest_hills";
        public String biomesForSECBlue = "minecraft:ocean, minecraft:river, minecraft:beach, minecraft:deep_ocean, minecraft:stone_shore, minecraft:warm_ocean, minecraft:lukewarm_ocean, minecraft:deep_lukewarm_ocean, minecraft:deep_frozen_ocean, minecraft:desert_lakes, minecraft:flower_forest";
        public String biomesForSECBrown = "minecraft:taiga, minecraft:river, minecraft:frozen_river, minecraft:beach, minecraft:wooded_hills, minecraft:jungle, minecraft:stone_shore, minecraft:snowy_taiga, minecraft:giant_tree_taiga, minecraft:wooded_mountains, minecraft:flower_forest, minecraft:taiga_mountains, minecraft:modified_jungle, minecraft:modified_jungle_edge, minecraft:snowy_taiga_mountains, minecraft:giant_spruce_taiga, minecraft:giant_spruce_taiga_hills, minecraft:bamboo_jungle";
        public String biomesForSECGreen = "minecraft:desert, minecraft:forest, minecraft:wooded_hills, minecraft:jungle, minecraft:jungle_hills, minecraft:jungle_edge, minecraft:desert_lakes, minecraft:flower_forest, minecraft:modified_jungle, minecraft:modified_jungle_edge, minecraft:bamboo_jungle, minecraft:bamboo_jungle_hills, minecraft:lush_caves";
        public String biomesForSECRed = "minecraft:plains, minecraft:desert, minecraft:forest, minecraft:jungle, minecraft:dark_forest, minecraft:savanna, minecraft:savanna_plateau, minecraft:badlands, minecraft:wooded_badlands_plateau, minecraft:badlands_plateau, minecraft:flower_forest, minecraft:modified_jungle_edge, minecraft:shattered_savanna, minecraft:shattered_savanna_plateau, minecraft:eroded_badlands, minecraft:modified_wooded_badlands_plateau, minecraft:modified_badlands_plateau, minecraft:bamboo_jungle";
        public String biomesForSECBlack = "minecraft:mountains, minecraft:jungle, minecraft:deep_ocean, minecraft:dark_forest, minecraft:wooded_mountains, minecraft:flower_forest, minecraft:modified_jungle, minecraft:modified_jungle_edge, minecraft:dark_forest_hills, minecraft:bamboo_jungle, minecraft:bamboo_jungle_hills, minecraft:lush_caves";


        private SECBiomes() { }
    }

    public void registerConfig() {
        AutoConfig.register(ModConfig.class, Toml4jConfigSerializer::new);
    }

}
