package com.lapisliozuli.slimeology.registry;

import com.lapisliozuli.slimeology.Slimeology;
import com.lapisliozuli.slimeology.entities.*;
import com.lapisliozuli.slimeology.mixins.SpawnRestrictionMixin;
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

    // Initialise the SLIME_ENTITY first.
    public static EntityType<SlimeEntityColoured> SLIME_ENTITY_DEBUG;
    public static EntityType<SlimeEntityColoured> SLIME_ENTITY_WHITE;
    public static EntityType<SlimeEntityColoured> SLIME_ENTITY_ORANGE;
    public static EntityType<SlimeEntityColoured> SLIME_ENTITY_MAGENTA;
    public static EntityType<SlimeEntityColoured> SLIME_ENTITY_LIGHT_BLUE;
    public static EntityType<SlimeEntityColoured> SLIME_ENTITY_YELLOW;
    public static EntityType<SlimeEntityColoured> SLIME_ENTITY_LIME;
    public static EntityType<SlimeEntityColoured> SLIME_ENTITY_PINK;
    public static EntityType<SlimeEntityColoured> SLIME_ENTITY_GRAY;
    public static EntityType<SlimeEntityColoured> SLIME_ENTITY_LIGHT_GRAY;
    public static EntityType<SlimeEntityColoured> SLIME_ENTITY_CYAN;
    public static EntityType<SlimeEntityColoured> SLIME_ENTITY_PURPLE;
    public static EntityType<SlimeEntityColoured> SLIME_ENTITY_BLUE;
    public static EntityType<SlimeEntityColoured> SLIME_ENTITY_BROWN;
    public static EntityType<SlimeEntityColoured> SLIME_ENTITY_GREEN;
    public static EntityType<SlimeEntityColoured> SLIME_ENTITY_RED;
    public static EntityType<SlimeEntityColoured> SLIME_ENTITY_BLACK;

//    public static EntityType<SlimeEntityColoured> bulkRegisterFETB(EntityType<SlimeEntityColoured> secFETB, String sec_path, Item slimeParticle) {
//        return Registry.register(
//                Registry.ENTITY_TYPE,
//                new Identifier(Slimeology.MOD_ID, sec_path),
//                FabricEntityTypeBuilder.<SlimeEntityColoured>create(SpawnGroup.MONSTER, (type, world) -> new SlimeEntityColoured(type, world, slimeParticle, secFETB))
//                        .dimensions(EntityDimensions.changing(2.04f, 2.04f))
//                        .trackable(160, 4).build());
//    }

    // Then put the SEC variable into a map with the paths, particle and itself as an input?
    // Try to input parameters for SEC class.
    static {
//        SLIME_ENTITY_DEBUG = bulkRegisterFETB(SLIME_ENTITY_DEBUG, "slime_entity_debug", RegisterItems.SLIME_BALL_DEBUG);
        SLIME_ENTITY_DEBUG = Registry.register(
                Registry.ENTITY_TYPE,
                new Identifier(Slimeology.MOD_ID, "slime_entity_debug"),
                FabricEntityTypeBuilder.<SlimeEntityColoured>create(SpawnGroup.MONSTER, (type, world) -> new SlimeEntityColoured(type, world, RegisterItems.SLIME_BALL_DEBUG, SLIME_ENTITY_DEBUG))
                        .dimensions(EntityDimensions.changing(2.04f, 2.04f))
                        .trackable(160, 4).build());

         SLIME_ENTITY_WHITE = Registry.register(
                Registry.ENTITY_TYPE,
                new Identifier(Slimeology.MOD_ID, "slime_entity_white"),
                FabricEntityTypeBuilder.<SlimeEntityColoured>create(SpawnGroup.MONSTER, (type, world) -> new SlimeEntityColoured(type, world, RegisterItems.SLIME_BALL_WHITE, SLIME_ENTITY_WHITE))
                        .dimensions(EntityDimensions.changing(2.04f, 2.04f))
                        .trackable(160, 4).build());


        SLIME_ENTITY_ORANGE = Registry.register(
                Registry.ENTITY_TYPE,
                new Identifier(Slimeology.MOD_ID, "slime_entity_orange"),
                FabricEntityTypeBuilder.<SlimeEntityColoured>create(SpawnGroup.MONSTER, (type, world) -> new SlimeEntityColoured(type, world, RegisterItems.SLIME_BALL_ORANGE, SLIME_ENTITY_ORANGE))
                        .dimensions(EntityDimensions.changing(2.04f, 2.04f))
                        .trackable(160, 4).build());

        SLIME_ENTITY_MAGENTA = Registry.register(
                Registry.ENTITY_TYPE,
                new Identifier(Slimeology.MOD_ID, "slime_entity_magenta"),
                FabricEntityTypeBuilder.<SlimeEntityColoured>create(SpawnGroup.MONSTER, (type, world) -> new SlimeEntityColoured(type, world, RegisterItems.SLIME_BALL_MAGENTA, SLIME_ENTITY_MAGENTA))
                        .dimensions(EntityDimensions.changing(2.04f, 2.04f))
                        .trackable(160, 4).build());

        SLIME_ENTITY_LIGHT_BLUE = Registry.register(
                Registry.ENTITY_TYPE,
                new Identifier(Slimeology.MOD_ID, "slime_entity_light_blue"),
                FabricEntityTypeBuilder.<SlimeEntityColoured>create(SpawnGroup.MONSTER, (type, world) -> new SlimeEntityColoured(type, world, RegisterItems.SLIME_BALL_LIGHT_BLUE, SLIME_ENTITY_LIGHT_BLUE))
                        .dimensions(EntityDimensions.changing(2.04f, 2.04f))
                        .trackable(160, 4).build());

        SLIME_ENTITY_YELLOW = Registry.register(
                Registry.ENTITY_TYPE,
                new Identifier(Slimeology.MOD_ID, "slime_entity_yellow"),
                FabricEntityTypeBuilder.<SlimeEntityColoured>create(SpawnGroup.MONSTER, (type, world) -> new SlimeEntityColoured(type, world, RegisterItems.SLIME_BALL_YELLOW, SLIME_ENTITY_YELLOW))
                        .dimensions(EntityDimensions.changing(2.04f, 2.04f))
                        .trackable(160, 4).build());

        SLIME_ENTITY_LIME = Registry.register(
                Registry.ENTITY_TYPE,
                new Identifier(Slimeology.MOD_ID, "slime_entity_lime"),
                FabricEntityTypeBuilder.<SlimeEntityColoured>create(SpawnGroup.MONSTER, (type, world) -> new SlimeEntityColoured(type, world, RegisterItems.SLIME_BALL_LIME, SLIME_ENTITY_LIME))
                        .dimensions(EntityDimensions.changing(2.04f, 2.04f))
                        .trackable(160, 4).build());

        SLIME_ENTITY_PINK = Registry.register(
                Registry.ENTITY_TYPE,
                new Identifier(Slimeology.MOD_ID, "slime_entity_pink"),
                FabricEntityTypeBuilder.<SlimeEntityColoured>create(SpawnGroup.MONSTER, (type, world) -> new SlimeEntityColoured(type, world, RegisterItems.SLIME_BALL_PINK, SLIME_ENTITY_PINK))
                        .dimensions(EntityDimensions.changing(2.04f, 2.04f))
                        .trackable(160, 4).build());

        SLIME_ENTITY_GRAY = Registry.register(
                Registry.ENTITY_TYPE,
                new Identifier(Slimeology.MOD_ID, "slime_entity_gray"),
                FabricEntityTypeBuilder.<SlimeEntityColoured>create(SpawnGroup.MONSTER, (type, world) -> new SlimeEntityColoured(type, world, RegisterItems.SLIME_BALL_GRAY, SLIME_ENTITY_GRAY))
                        .dimensions(EntityDimensions.changing(2.04f, 2.04f))
                        .trackable(160, 4).build());

        SLIME_ENTITY_LIGHT_GRAY = Registry.register(
                Registry.ENTITY_TYPE,
                new Identifier(Slimeology.MOD_ID, "slime_entity_light_gray"),
                FabricEntityTypeBuilder.<SlimeEntityColoured>create(SpawnGroup.MONSTER, (type, world) -> new SlimeEntityColoured(type, world, RegisterItems.SLIME_BALL_LIGHT_GRAY, SLIME_ENTITY_LIGHT_GRAY))
                        .dimensions(EntityDimensions.changing(2.04f, 2.04f))
                        .trackable(160, 4).build());

        SLIME_ENTITY_CYAN = Registry.register(
                Registry.ENTITY_TYPE,
                new Identifier(Slimeology.MOD_ID, "slime_entity_cyan"),
                FabricEntityTypeBuilder.<SlimeEntityColoured>create(SpawnGroup.MONSTER, (type, world) -> new SlimeEntityColoured(type, world, RegisterItems.SLIME_BALL_CYAN, SLIME_ENTITY_CYAN))
                        .dimensions(EntityDimensions.changing(2.04f, 2.04f))
                        .trackable(160, 4).build());

        SLIME_ENTITY_PURPLE = Registry.register(
                Registry.ENTITY_TYPE,
                new Identifier(Slimeology.MOD_ID, "slime_entity_purple"),
                FabricEntityTypeBuilder.<SlimeEntityColoured>create(SpawnGroup.MONSTER, (type, world) -> new SlimeEntityColoured(type, world, RegisterItems.SLIME_BALL_PURPLE, SLIME_ENTITY_PURPLE))
                        .dimensions(EntityDimensions.changing(2.04f, 2.04f))
                        .trackable(160, 4).build());

        SLIME_ENTITY_BLUE = Registry.register(
                Registry.ENTITY_TYPE,
                new Identifier(Slimeology.MOD_ID, "slime_entity_blue"),
                FabricEntityTypeBuilder.<SlimeEntityColoured>create(SpawnGroup.MONSTER, (type, world) -> new SlimeEntityColoured(type, world, RegisterItems.SLIME_BALL_BLUE, SLIME_ENTITY_BLUE))
                        .dimensions(EntityDimensions.changing(2.04f, 2.04f))
                        .trackable(160, 4).build());

        SLIME_ENTITY_BROWN = Registry.register(
                Registry.ENTITY_TYPE,
                new Identifier(Slimeology.MOD_ID, "slime_entity_brown"),
                FabricEntityTypeBuilder.<SlimeEntityColoured>create(SpawnGroup.MONSTER, (type, world) -> new SlimeEntityColoured(type, world, RegisterItems.SLIME_BALL_BROWN, SLIME_ENTITY_BROWN))
                        .dimensions(EntityDimensions.changing(2.04f, 2.04f))
                        .trackable(160, 4).build());

        SLIME_ENTITY_GREEN = Registry.register(
                Registry.ENTITY_TYPE,
                new Identifier(Slimeology.MOD_ID, "slime_entity_green"),
                FabricEntityTypeBuilder.<SlimeEntityColoured>create(SpawnGroup.MONSTER, (type, world) -> new SlimeEntityColoured(type, world, RegisterItems.SLIME_BALL_GREEN, SLIME_ENTITY_GREEN))
                        .dimensions(EntityDimensions.changing(2.04f, 2.04f))
                        .trackable(160, 4).build());

        SLIME_ENTITY_RED = Registry.register(
                Registry.ENTITY_TYPE,
                new Identifier(Slimeology.MOD_ID, "slime_entity_red"),
                FabricEntityTypeBuilder.<SlimeEntityColoured>create(SpawnGroup.MONSTER, (type, world) -> new SlimeEntityColoured(type, world, RegisterItems.SLIME_BALL_RED, SLIME_ENTITY_RED))
                        .dimensions(EntityDimensions.changing(2.04f, 2.04f))
                        .trackable(160, 4).build());

        SLIME_ENTITY_BLACK = Registry.register(
                Registry.ENTITY_TYPE,
                new Identifier(Slimeology.MOD_ID, "slime_entity_black"),
                FabricEntityTypeBuilder.<SlimeEntityColoured>create(SpawnGroup.MONSTER, (type, world) -> new SlimeEntityColoured(type, world, RegisterItems.SLIME_BALL_BLACK, SLIME_ENTITY_BLACK))
                        .dimensions(EntityDimensions.changing(2.04f, 2.04f))
                        .trackable(160, 4).build());
    }

    // ======================= DATA
    // This list enforces the order of the SECs
    public static List<EntityType> secForcedOrder = Stream.of(
//            SLIME_ENTITY_DEBUG
            SLIME_ENTITY_DEBUG,
            SLIME_ENTITY_WHITE, SLIME_ENTITY_ORANGE, SLIME_ENTITY_MAGENTA, SLIME_ENTITY_LIGHT_BLUE,
            SLIME_ENTITY_YELLOW, SLIME_ENTITY_LIME, SLIME_ENTITY_PINK, SLIME_ENTITY_GRAY,
            SLIME_ENTITY_LIGHT_GRAY, SLIME_ENTITY_CYAN, SLIME_ENTITY_PURPLE, SLIME_ENTITY_BLUE,
            SLIME_ENTITY_BROWN, SLIME_ENTITY_GREEN, SLIME_ENTITY_RED, SLIME_ENTITY_BLACK
    ).collect(Collectors.toList());

    // This Map links registered SECs with their respective SpawnPredicate
    // Hopefully can remove after the same canSpawnSEC can be applied to all SECs.
    public static Map<EntityType, SpawnRestriction.SpawnPredicate> spawnPredicateImperative() {
        final Map<EntityType, SpawnRestriction.SpawnPredicate> secSpawnPredicatesMap = new HashMap<>();
        secSpawnPredicatesMap.put(SLIME_ENTITY_DEBUG, SlimeEntityColoured::canSpawnSEC);
        secSpawnPredicatesMap.put(SLIME_ENTITY_WHITE, SlimeEntityColoured::canSpawnSEC);
        secSpawnPredicatesMap.put(SLIME_ENTITY_ORANGE, SlimeEntityColoured::canSpawnSEC);
        secSpawnPredicatesMap.put(SLIME_ENTITY_MAGENTA, SlimeEntityColoured::canSpawnSEC);
        secSpawnPredicatesMap.put(SLIME_ENTITY_LIGHT_BLUE, SlimeEntityColoured::canSpawnSEC);
        secSpawnPredicatesMap.put(SLIME_ENTITY_YELLOW, SlimeEntityColoured::canSpawnSEC);
        secSpawnPredicatesMap.put(SLIME_ENTITY_LIME, SlimeEntityColoured::canSpawnSEC);
        secSpawnPredicatesMap.put(SLIME_ENTITY_PINK, SlimeEntityColoured::canSpawnSEC);
        secSpawnPredicatesMap.put(SLIME_ENTITY_GRAY, SlimeEntityColoured::canSpawnSEC);
        secSpawnPredicatesMap.put(SLIME_ENTITY_LIGHT_GRAY, SlimeEntityColoured::canSpawnSEC);
        secSpawnPredicatesMap.put(SLIME_ENTITY_CYAN, SlimeEntityColoured::canSpawnSEC);
        secSpawnPredicatesMap.put(SLIME_ENTITY_PURPLE, SlimeEntityColoured::canSpawnSEC);
        secSpawnPredicatesMap.put(SLIME_ENTITY_BLUE, SlimeEntityColoured::canSpawnSEC);
        secSpawnPredicatesMap.put(SLIME_ENTITY_BROWN, SlimeEntityColoured::canSpawnSEC);
        secSpawnPredicatesMap.put(SLIME_ENTITY_GREEN, SlimeEntityColoured::canSpawnSEC);
        secSpawnPredicatesMap.put(SLIME_ENTITY_RED, SlimeEntityColoured::canSpawnSEC);
        secSpawnPredicatesMap.put(SLIME_ENTITY_BLACK, SlimeEntityColoured::canSpawnSEC);
        return Collections.unmodifiableMap(secSpawnPredicatesMap);
    }
    public static final Map<EntityType, SpawnRestriction.SpawnPredicate> secSpawnPredicatesMap = spawnPredicateImperative();

   // This Map assigns a collection of spawnable biomes to each SEC.
    public static Map<EntityType, List<Biome>> allocatedBiomeImperative() {
        final Map<EntityType, List<Biome>> secAllocatedBiomeMap = new HashMap<>();
        secAllocatedBiomeMap.put(SLIME_ENTITY_DEBUG, convertConfigIDsToBiomes("minecraft:forest, minecraft:snowy_tundra, minecraft:snowy_mountains, minecraft:snowy_beach, " +
                "minecraft:snowy_taiga_hills, minecraft:snowy_taiga_mountains, minecraft:plains, minecraft:desert, minecraft:desert_hills"));
//        secAllocatedBiomeMap.put(SLIME_ENTITY_DEBUG, Collections.emptyList());
//        secAllocatedBiomeMap.put(SLIME_ENTITY_WHITE, convertConfigIDsToBiomes(Slimeology.CONFIG.secBiomes.biomesForSECWhite));
//        secAllocatedBiomeMap.put(SLIME_ENTITY_ORANGE, convertConfigIDsToBiomes(Slimeology.CONFIG.secBiomes.biomesForSECOrange));
//        secAllocatedBiomeMap.put(SLIME_ENTITY_MAGENTA, convertConfigIDsToBiomes(Slimeology.CONFIG.secBiomes.biomesForSECMagenta));
//        secAllocatedBiomeMap.put(SLIME_ENTITY_LIGHT_BLUE, convertConfigIDsToBiomes(Slimeology.CONFIG.secBiomes.biomesForSECLightBlue));
//        secAllocatedBiomeMap.put(SLIME_ENTITY_YELLOW, convertConfigIDsToBiomes(Slimeology.CONFIG.secBiomes.biomesForSECYellow));
//        secAllocatedBiomeMap.put(SLIME_ENTITY_LIME, convertConfigIDsToBiomes(Slimeology.CONFIG.secBiomes.biomesForSECLime));
//        secAllocatedBiomeMap.put(SLIME_ENTITY_PINK, convertConfigIDsToBiomes(Slimeology.CONFIG.secBiomes.biomesForSECPink));
//        secAllocatedBiomeMap.put(SLIME_ENTITY_GRAY, convertConfigIDsToBiomes(Slimeology.CONFIG.secBiomes.biomesForSECGray));
//        secAllocatedBiomeMap.put(SLIME_ENTITY_LIGHT_GRAY, convertConfigIDsToBiomes(Slimeology.CONFIG.secBiomes.biomesForSECLightGray));
//        secAllocatedBiomeMap.put(SLIME_ENTITY_CYAN, convertConfigIDsToBiomes(Slimeology.CONFIG.secBiomes.biomesForSECCyan));
//        secAllocatedBiomeMap.put(SLIME_ENTITY_PURPLE, convertConfigIDsToBiomes(Slimeology.CONFIG.secBiomes.biomesForSECPurple));
//        secAllocatedBiomeMap.put(SLIME_ENTITY_BLUE, convertConfigIDsToBiomes(Slimeology.CONFIG.secBiomes.biomesForSECBlue));
//        secAllocatedBiomeMap.put(SLIME_ENTITY_BROWN, convertConfigIDsToBiomes(Slimeology.CONFIG.secBiomes.biomesForSECBrown));
//        secAllocatedBiomeMap.put(SLIME_ENTITY_GREEN, convertConfigIDsToBiomes(Slimeology.CONFIG.secBiomes.biomesForSECGreen));
//        secAllocatedBiomeMap.put(SLIME_ENTITY_RED, convertConfigIDsToBiomes(Slimeology.CONFIG.secBiomes.biomesForSECRed));
//        secAllocatedBiomeMap.put(SLIME_ENTITY_BLACK, convertConfigIDsToBiomes(Slimeology.CONFIG.secBiomes.biomesForSECBlack));
        return Collections.unmodifiableMap(secAllocatedBiomeMap);
    }
    public static final Map<EntityType, List<Biome>> secAllocatedBiomeMap = allocatedBiomeImperative();


    // This method reads secAllocatedBiomeMap to invert the SECs and biomes.
    public static Map<Biome, List<EntityType>> allocateSECToBiome() {
        final Map<Biome, List<EntityType>> biomeAllocatedSECMap = new HashMap<>();
        for (Map.Entry<EntityType, List<Biome>> entry : secAllocatedBiomeMap.entrySet()) {
            EntityType sec = entry.getKey();
            List<Biome> biome = entry.getValue();
            for (Biome biomePermitted : biome) {
                if (biomeAllocatedSECMap.containsKey(biomePermitted)) {
                    biomeAllocatedSECMap.get(biomePermitted).add(sec);
                }
                else {
                    List<EntityType> secAllocateList = new ArrayList<EntityType>();
                    secAllocateList.add(sec);
                    biomeAllocatedSECMap.put(biomePermitted, secAllocateList);
                }
            }
        }
        // Probably also computationally demanding.
        return Collections.unmodifiableMap(biomeAllocatedSECMap);
    }
    // This Map checks each biome to see which SECs can spawn within it.
    public static final Map<Biome, List<EntityType>> biomeAllocatedSECMap = allocateSECToBiome();

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
                    .add(new Biome.SpawnEntry(type, 1000, 4, 4));
        }
    }


    // Stops SECs from spawning in mid-air, and also limits their spawning using canSpawnSEC()
    public static void registerSpawnRestriction(EntityType type, SpawnRestriction.SpawnPredicate spawnPredicate) {
        SpawnRestrictionMixin.register(
                type,
                SpawnRestriction.Location.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                // This is the only place where canSpawnSEC is called, and maybe affect chunk spawning.
                spawnPredicate);
    }


    public static void registerSlimeologyEntityTypes() {
//        // Debug Slime only
//        FabricDefaultAttributeRegistry.register(SLIME_ENTITY_DEBUG, HostileEntity.createHostileAttributes());
//        FabricDefaultAttributeRegistry.register(SLIME_ENTITY_WHITE, HostileEntity.createHostileAttributes());
//        SpawnRestrictionMixin.register(
//                SLIME_ENTITY_DEBUG,
//                SpawnRestriction.Location.ON_GROUND,
//                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
//                SlimeEntityColoured::canSpawnSEC);
//        for (Biome biomeReg : Registry.BIOME) {
//            EntityType k = SLIME_ENTITY_DEBUG;
//            List<Biome> v = convertConfigIDsToBiomes("minecraft:forest, minecraft:snowy_tundra, minecraft:snowy_mountains, minecraft:snowy_beach, " +
//                    "minecraft:snowy_taiga_hills, minecraft:snowy_taiga_mountains, minecraft:plains, minecraft:desert, minecraft:desert_hills");
//            checkBiomeForSpawnEntry(biomeReg, k, v);
//            RegistryEntryAddedCallback.event(Registry.BIOME).register(
//                    (i, identifier, biome) -> {
//                        RegisterSEC.checkBiomeForSpawnEntry(biomeReg, k, v);
//                    });
//        }

        secForcedOrder.forEach(entry->{
            FabricDefaultAttributeRegistry.register(entry, HostileEntity.createHostileAttributes());
        });

        secSpawnPredicatesMap.forEach(RegisterSEC::registerSpawnRestriction);

        for (Map.Entry<EntityType, List<Biome>> entry : secAllocatedBiomeMap.entrySet()) {
            EntityType slime = entry.getKey();
            List<Biome> biomeList = entry.getValue();

            // Checks the biome and adds the callback
            for (Biome biomeReg : Registry.BIOME) {
                checkBiomeForSpawnEntry(biomeReg, slime, biomeList);
                RegistryEntryAddedCallback.event(Registry.BIOME).register(
                        (i, identifier, biome) -> {
                            RegisterSEC.checkBiomeForSpawnEntry(biomeReg, slime, biomeList);
                        });
            }
        }

    }
}
