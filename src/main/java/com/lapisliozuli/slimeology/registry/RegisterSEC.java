package com.lapisliozuli.slimeology.registry;

import com.lapisliozuli.slimeology.items.SlimeBalls;
import com.lapisliozuli.slimeology.Slimeology;
import com.lapisliozuli.slimeology.entities.*;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.*;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RegisterSEC {
    public static EntityType<SlimeEntityColoured> bulkRegisterFETB(String sec_path, Item slimeParticle) {
//    public static EntityType<SlimeEntityColoured> bulkRegisterFETB(String sec_path, Item slimeParticle, int inputIndex) {
        return Registry.register(
                Registry.ENTITY_TYPE,
                new Identifier(Slimeology.MOD_ID, sec_path),
                FabricEntityTypeBuilder.<SlimeEntityColoured>create(SpawnGroup.MONSTER, (type, world) -> new SlimeEntityColoured(type, world, slimeParticle))
//                FabricEntityTypeBuilder.<SlimeEntityColoured>create(SpawnGroup.MONSTER, (type, world) -> new SlimeEntityColoured(type, world, slimeParticle, inputIndex))
                        .dimensions(EntityDimensions.changing(2.04f, 2.04f))
                        .trackable(160, 4).build());
    }

    public static int debugSlime = 1;
//    public static EntityType<SlimeEntityColoured> SLIME_ENTITY_DEBUG = bulkRegisterFETB("slime_entity_debug", SlimeBalls.SLIME_BALL_DEBUG, 0);
    public static EntityType<SlimeEntityColoured> SLIME_ENTITY_WHITE = bulkRegisterFETB("slime_entity_white", SlimeBalls.SLIME_BALL_WHITE);
    public static EntityType<SlimeEntityColoured> SLIME_ENTITY_ORANGE = bulkRegisterFETB("slime_entity_orange", SlimeBalls.SLIME_BALL_ORANGE);
    public static EntityType<SlimeEntityColoured> SLIME_ENTITY_MAGENTA = bulkRegisterFETB("slime_entity_magenta", SlimeBalls.SLIME_BALL_MAGENTA);
    public static EntityType<SlimeEntityColoured> SLIME_ENTITY_LIGHT_BLUE = bulkRegisterFETB("slime_entity_light_blue", SlimeBalls.SLIME_BALL_LIGHT_BLUE);
    public static EntityType<SlimeEntityColoured> SLIME_ENTITY_YELLOW = bulkRegisterFETB("slime_entity_yellow", SlimeBalls.SLIME_BALL_YELLOW);
    public static EntityType<SlimeEntityColoured> SLIME_ENTITY_LIME = bulkRegisterFETB("slime_entity_lime", SlimeBalls.SLIME_BALL_LIME);
    public static EntityType<SlimeEntityColoured> SLIME_ENTITY_PINK = bulkRegisterFETB("slime_entity_pink", SlimeBalls.SLIME_BALL_PINK);
    public static EntityType<SlimeEntityColoured> SLIME_ENTITY_GRAY = bulkRegisterFETB("slime_entity_gray", SlimeBalls.SLIME_BALL_GRAY);
    public static EntityType<SlimeEntityColoured> SLIME_ENTITY_LIGHT_GRAY = bulkRegisterFETB("slime_entity_light_gray", SlimeBalls.SLIME_BALL_LIGHT_GRAY);
    public static EntityType<SlimeEntityColoured> SLIME_ENTITY_CYAN = bulkRegisterFETB("slime_entity_cyan", SlimeBalls.SLIME_BALL_CYAN);
    public static EntityType<SlimeEntityColoured> SLIME_ENTITY_PURPLE = bulkRegisterFETB("slime_entity_purple", SlimeBalls.SLIME_BALL_PURPLE);
    public static EntityType<SlimeEntityColoured> SLIME_ENTITY_BLUE = bulkRegisterFETB("slime_entity_blue", SlimeBalls.SLIME_BALL_BLUE);
    public static EntityType<SlimeEntityColoured> SLIME_ENTITY_BROWN = bulkRegisterFETB("slime_entity_brown", SlimeBalls.SLIME_BALL_BROWN);
    public static EntityType<SlimeEntityColoured> SLIME_ENTITY_GREEN = bulkRegisterFETB("slime_entity_green", SlimeBalls.SLIME_BALL_GREEN);
    public static EntityType<SlimeEntityColoured> SLIME_ENTITY_RED = bulkRegisterFETB("slime_entity_red", SlimeBalls.SLIME_BALL_RED);
    public static EntityType<SlimeEntityColoured> SLIME_ENTITY_BLACK = bulkRegisterFETB("slime_entity_black", SlimeBalls.SLIME_BALL_BLACK);

    // ======================= DATA
    // This list enforces the order of the SECs
    public static List<EntityType<SlimeEntityColoured>> secForcedOrder = Stream.of(
//            SLIME_ENTITY_DEBUG
//            SLIME_ENTITY_DEBUG,
            SLIME_ENTITY_WHITE, SLIME_ENTITY_ORANGE, SLIME_ENTITY_MAGENTA, SLIME_ENTITY_LIGHT_BLUE,
            SLIME_ENTITY_YELLOW, SLIME_ENTITY_LIME, SLIME_ENTITY_PINK, SLIME_ENTITY_GRAY,
            SLIME_ENTITY_LIGHT_GRAY, SLIME_ENTITY_CYAN, SLIME_ENTITY_PURPLE, SLIME_ENTITY_BLUE,
            SLIME_ENTITY_BROWN, SLIME_ENTITY_GREEN, SLIME_ENTITY_RED, SLIME_ENTITY_BLACK
    ).collect(Collectors.toList());


    // This Map assigns a collection of spawnable biomes to each SEC.
    public static Map<EntityType<SlimeEntityColoured>, List<Biome>> allocatedBiomeImperative() {
        final Map<EntityType<SlimeEntityColoured>, List<Biome>> secAllocatedBiomeMap = new HashMap<>();
//        secAllocatedBiomeMap.put(SLIME_ENTITY_DEBUG, convertConfigIDsToBiomes("minecraft:forest, minecraft:snowy_tundra, minecraft:snowy_mountains, minecraft:snowy_beach, " +
//                "minecraft:snowy_taiga_hills, minecraft:snowy_taiga_mountains, minecraft:plains, minecraft:desert, minecraft:desert_hills"));
//        secAllocatedBiomeMap.put(SLIME_ENTITY_DEBUG, Collections.emptyList());
        secAllocatedBiomeMap.put(SLIME_ENTITY_WHITE, convertConfigIDsToBiomes(Slimeology.CONFIG.secBiomes.biomesForSECWhite));
        secAllocatedBiomeMap.put(SLIME_ENTITY_ORANGE, convertConfigIDsToBiomes(Slimeology.CONFIG.secBiomes.biomesForSECOrange));
        secAllocatedBiomeMap.put(SLIME_ENTITY_MAGENTA, convertConfigIDsToBiomes(Slimeology.CONFIG.secBiomes.biomesForSECMagenta));
        secAllocatedBiomeMap.put(SLIME_ENTITY_LIGHT_BLUE, convertConfigIDsToBiomes(Slimeology.CONFIG.secBiomes.biomesForSECLightBlue));
        secAllocatedBiomeMap.put(SLIME_ENTITY_YELLOW, convertConfigIDsToBiomes(Slimeology.CONFIG.secBiomes.biomesForSECYellow));
        secAllocatedBiomeMap.put(SLIME_ENTITY_LIME, convertConfigIDsToBiomes(Slimeology.CONFIG.secBiomes.biomesForSECLime));
        secAllocatedBiomeMap.put(SLIME_ENTITY_PINK, convertConfigIDsToBiomes(Slimeology.CONFIG.secBiomes.biomesForSECPink));
        secAllocatedBiomeMap.put(SLIME_ENTITY_GRAY, convertConfigIDsToBiomes(Slimeology.CONFIG.secBiomes.biomesForSECGray));
        secAllocatedBiomeMap.put(SLIME_ENTITY_LIGHT_GRAY, convertConfigIDsToBiomes(Slimeology.CONFIG.secBiomes.biomesForSECLightGray));
        secAllocatedBiomeMap.put(SLIME_ENTITY_CYAN, convertConfigIDsToBiomes(Slimeology.CONFIG.secBiomes.biomesForSECCyan));
        secAllocatedBiomeMap.put(SLIME_ENTITY_PURPLE, convertConfigIDsToBiomes(Slimeology.CONFIG.secBiomes.biomesForSECPurple));
        secAllocatedBiomeMap.put(SLIME_ENTITY_BLUE, convertConfigIDsToBiomes(Slimeology.CONFIG.secBiomes.biomesForSECBlue));
        secAllocatedBiomeMap.put(SLIME_ENTITY_BROWN, convertConfigIDsToBiomes(Slimeology.CONFIG.secBiomes.biomesForSECBrown));
        secAllocatedBiomeMap.put(SLIME_ENTITY_GREEN, convertConfigIDsToBiomes(Slimeology.CONFIG.secBiomes.biomesForSECGreen));
        secAllocatedBiomeMap.put(SLIME_ENTITY_RED, convertConfigIDsToBiomes(Slimeology.CONFIG.secBiomes.biomesForSECRed));
        secAllocatedBiomeMap.put(SLIME_ENTITY_BLACK, convertConfigIDsToBiomes(Slimeology.CONFIG.secBiomes.biomesForSECBlack));
        return Collections.unmodifiableMap(secAllocatedBiomeMap);
    }
    public static final Map<EntityType<SlimeEntityColoured>, List<Biome>> secAllocatedBiomeMap = allocatedBiomeImperative();


    // This method reads secAllocatedBiomeMap to invert the SECs and biomes.
    public static Map<Biome, List<EntityType<SlimeEntityColoured>>> allocateSECToBiome() {
        final Map<Biome, List<EntityType<SlimeEntityColoured>>> biomeAllocatedSECMap = new HashMap<>();
        for (Map.Entry<EntityType<SlimeEntityColoured>, List<Biome>> entry : secAllocatedBiomeMap.entrySet()) {
            EntityType sec = entry.getKey();
            List<Biome> biome = entry.getValue();
            for (Biome biomePermitted : biome) {
                if (biomeAllocatedSECMap.containsKey(biomePermitted)) {
                    biomeAllocatedSECMap.get(biomePermitted).add(sec);
                }
                else {
                    List<EntityType<SlimeEntityColoured>> secAllocateList = new ArrayList<EntityType<SlimeEntityColoured>>();
                    secAllocateList.add(sec);
                    biomeAllocatedSECMap.put(biomePermitted, secAllocateList);
                }
            }
        }
        // Probably also computationally demanding.
        return Collections.unmodifiableMap(biomeAllocatedSECMap);
    }
    // This Map checks each biome to see which SECs can spawn within it.
    public static final Map<Biome, List<EntityType<SlimeEntityColoured>>> biomeAllocatedSECMap = allocateSECToBiome();

    // ======================= METHODS
    // This method reads the string IDs from the configs and returns a list of Biomes.
    public static List<Biome> convertConfigIDsToBiomes(String stringyListInt) {
        List<Biome> configBiomeList = new ArrayList<>(Collections.emptyList());
        // Splits the string by the comma ',' to return a list of strings in the format 'minecraft:biome'
        List<String> stringIDList = Arrays.asList(stringyListInt.split("\\s*,\\s*"));
        stringIDList.forEach(entry->{
            // Converts the string into an Identifier
            configBiomeList.add(Registry.BIOME.get(Identifier.tryParse(entry)));
        });
        return configBiomeList;
    }


    // Adds SpawnEntry only if the biome is part of the SEC's BiomeList.
    public static void checkBiomeForSpawnEntry(Biome biome, EntityType type, List<Biome> biomeList) {
        if (biomeList.contains(biome)) {
            biome.getEntitySpawnList(type.getSpawnGroup())
                    // Might allow weight to be adjust for individual slimes at some point. Just include weight as an input.
                    .add(new Biome.SpawnEntry(type, Slimeology.CONFIG.secSpawning.spawnWeight, 4, 4));
        }
    }


    public static void regSpawn(EntityType<SlimeEntityColoured> inputSEC, String stringSECToBiomeList) {
        FabricDefaultAttributeRegistry.register(inputSEC, HostileEntity.createHostileAttributes());

        SpawnRestrictionMixin.register(
                inputSEC,
                SpawnRestriction.Location.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                SlimeEntityColoured::canSpawnSEC);

        List<Biome> biomeList = convertConfigIDsToBiomes(stringSECToBiomeList);

        // Iterate over the Registry of Biomes
        for (Biome biomeReg : Registry.BIOME) {
            checkBiomeForSpawnEntry(biomeReg, inputSEC, biomeList);
            RegistryEntryAddedCallback.event(Registry.BIOME).register(
                    (i, identifier, biome) -> {
                        RegisterSEC.checkBiomeForSpawnEntry(biomeReg, inputSEC, biomeList);
                    });
        }
    }


    public static void registerSlimeologyEntityTypes() {
//        // This actually disabled biome spawning for the Debug Slime without crashing.
//        regSpawn(SLIME_ENTITY_DEBUG, "");
        regSpawn(SLIME_ENTITY_WHITE, Slimeology.CONFIG.secBiomes.biomesForSECWhite);
        regSpawn(SLIME_ENTITY_ORANGE, Slimeology.CONFIG.secBiomes.biomesForSECOrange);
        regSpawn(SLIME_ENTITY_MAGENTA, Slimeology.CONFIG.secBiomes.biomesForSECMagenta);
        regSpawn(SLIME_ENTITY_LIGHT_BLUE, Slimeology.CONFIG.secBiomes.biomesForSECLightBlue);
        regSpawn(SLIME_ENTITY_YELLOW, Slimeology.CONFIG.secBiomes.biomesForSECYellow);
        regSpawn(SLIME_ENTITY_LIME, Slimeology.CONFIG.secBiomes.biomesForSECLime);
        regSpawn(SLIME_ENTITY_PINK, Slimeology.CONFIG.secBiomes.biomesForSECPink);
        regSpawn(SLIME_ENTITY_GRAY, Slimeology.CONFIG.secBiomes.biomesForSECGray);
        regSpawn(SLIME_ENTITY_LIGHT_GRAY, Slimeology.CONFIG.secBiomes.biomesForSECLightGray);
        regSpawn(SLIME_ENTITY_CYAN, Slimeology.CONFIG.secBiomes.biomesForSECCyan);
        regSpawn(SLIME_ENTITY_PURPLE, Slimeology.CONFIG.secBiomes.biomesForSECPurple);
        regSpawn(SLIME_ENTITY_BLUE, Slimeology.CONFIG.secBiomes.biomesForSECBlue);
        regSpawn(SLIME_ENTITY_BROWN, Slimeology.CONFIG.secBiomes.biomesForSECBrown);
        regSpawn(SLIME_ENTITY_GREEN, Slimeology.CONFIG.secBiomes.biomesForSECGreen);
        regSpawn(SLIME_ENTITY_RED, Slimeology.CONFIG.secBiomes.biomesForSECRed);
        regSpawn(SLIME_ENTITY_BLACK, Slimeology.CONFIG.secBiomes.biomesForSECBlack);
    }
}