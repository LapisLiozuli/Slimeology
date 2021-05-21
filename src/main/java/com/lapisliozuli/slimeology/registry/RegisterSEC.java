package com.lapisliozuli.slimeology.registry;

import com.lapisliozuli.slimeology.Slimeology;
import com.lapisliozuli.slimeology.entities.*;
import com.lapisliozuli.slimeology.mixins.SpawnRestrictionMixin;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.*;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.lapisliozuli.slimeology.entities.SlimeEntityColoured.SLIME_ENTITY_DEBUG;
import static com.lapisliozuli.slimeology.entities.SlimeEntityWhite.SLIME_ENTITY_WHITE;
import static com.lapisliozuli.slimeology.entities.SlimeEntityOrange.SLIME_ENTITY_ORANGE;
import static com.lapisliozuli.slimeology.entities.SlimeEntityMagenta.SLIME_ENTITY_MAGENTA;
import static com.lapisliozuli.slimeology.entities.SlimeEntityLightBlue.SLIME_ENTITY_LIGHT_BLUE;
import static com.lapisliozuli.slimeology.entities.SlimeEntityYellow.SLIME_ENTITY_YELLOW;
import static com.lapisliozuli.slimeology.entities.SlimeEntityLime.SLIME_ENTITY_LIME;
import static com.lapisliozuli.slimeology.entities.SlimeEntityPink.SLIME_ENTITY_PINK;
import static com.lapisliozuli.slimeology.entities.SlimeEntityGray.SLIME_ENTITY_GRAY;
import static com.lapisliozuli.slimeology.entities.SlimeEntityLightGray.SLIME_ENTITY_LIGHT_GRAY;
import static com.lapisliozuli.slimeology.entities.SlimeEntityCyan.SLIME_ENTITY_CYAN;
import static com.lapisliozuli.slimeology.entities.SlimeEntityPurple.SLIME_ENTITY_PURPLE;
import static com.lapisliozuli.slimeology.entities.SlimeEntityBlue.SLIME_ENTITY_BLUE;
import static com.lapisliozuli.slimeology.entities.SlimeEntityBrown.SLIME_ENTITY_BROWN;
import static com.lapisliozuli.slimeology.entities.SlimeEntityGreen.SLIME_ENTITY_GREEN;
import static com.lapisliozuli.slimeology.entities.SlimeEntityRed.SLIME_ENTITY_RED;
import static com.lapisliozuli.slimeology.entities.SlimeEntityBlack.SLIME_ENTITY_BLACK;


public class RegisterSEC {
    // This list enforces the order of the SECs
    public static List<EntityType> secForcedOrder = Stream.of(
            SLIME_ENTITY_DEBUG,
            SLIME_ENTITY_WHITE, SLIME_ENTITY_ORANGE, SLIME_ENTITY_MAGENTA, SLIME_ENTITY_LIGHT_BLUE,
            SLIME_ENTITY_YELLOW, SLIME_ENTITY_LIME, SLIME_ENTITY_PINK, SLIME_ENTITY_GRAY,
            SLIME_ENTITY_LIGHT_GRAY, SLIME_ENTITY_CYAN, SLIME_ENTITY_PURPLE, SLIME_ENTITY_BLUE,
            SLIME_ENTITY_BROWN, SLIME_ENTITY_GREEN, SLIME_ENTITY_RED, SLIME_ENTITY_BLACK
    ).collect(Collectors.toList());

    // This Map links registered SECs with their respective SpawnPredicate
    public static Map<EntityType, SpawnRestriction.SpawnPredicate> spawnPredicateImperative() {
        final Map<EntityType, SpawnRestriction.SpawnPredicate> secSpawnPredicatesMap = new HashMap<>();
        secSpawnPredicatesMap.put(SLIME_ENTITY_DEBUG, SlimeEntityColoured::canSpawnSEC);
        secSpawnPredicatesMap.put(SLIME_ENTITY_WHITE, SlimeEntityWhite::canSpawnSECWhite);
        secSpawnPredicatesMap.put(SLIME_ENTITY_ORANGE, SlimeEntityOrange::canSpawnSECOrange);
        secSpawnPredicatesMap.put(SLIME_ENTITY_MAGENTA, SlimeEntityMagenta::canSpawnSECMagenta);
        secSpawnPredicatesMap.put(SLIME_ENTITY_LIGHT_BLUE, SlimeEntityLightBlue::canSpawnSECLightBlue);
        secSpawnPredicatesMap.put(SLIME_ENTITY_YELLOW, SlimeEntityYellow::canSpawnSECYellow);
        secSpawnPredicatesMap.put(SLIME_ENTITY_LIME, SlimeEntityLime::canSpawnSECLime);
        secSpawnPredicatesMap.put(SLIME_ENTITY_PINK, SlimeEntityPink::canSpawnSECPink);
        secSpawnPredicatesMap.put(SLIME_ENTITY_GRAY, SlimeEntityGray::canSpawnSECGray);
        secSpawnPredicatesMap.put(SLIME_ENTITY_LIGHT_GRAY, SlimeEntityLightGray::canSpawnSECLightGray);
        secSpawnPredicatesMap.put(SLIME_ENTITY_CYAN, SlimeEntityCyan::canSpawnSECCyan);
        secSpawnPredicatesMap.put(SLIME_ENTITY_PURPLE, SlimeEntityPurple::canSpawnSECPurple);
        secSpawnPredicatesMap.put(SLIME_ENTITY_BLUE, SlimeEntityBlue::canSpawnSECBlue);
        secSpawnPredicatesMap.put(SLIME_ENTITY_BROWN, SlimeEntityBrown::canSpawnSECBrown);
        secSpawnPredicatesMap.put(SLIME_ENTITY_GREEN, SlimeEntityGreen::canSpawnSECGreen);
        secSpawnPredicatesMap.put(SLIME_ENTITY_RED, SlimeEntityRed::canSpawnSECRed);
        secSpawnPredicatesMap.put(SLIME_ENTITY_BLACK, SlimeEntityBlack::canSpawnSECBlack);
        return Collections.unmodifiableMap(secSpawnPredicatesMap);
    }
    public static final Map<EntityType, SpawnRestriction.SpawnPredicate> secSpawnPredicatesMap = spawnPredicateImperative();

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

    // This Map assigns a collection of spawnable biomes to each SEC.
    public static Map<EntityType, List<Biome>> allocatedBiomeImperative() {
        final Map<EntityType, List<Biome>> secAllocatedBiomeMap = new HashMap<>();
        secAllocatedBiomeMap.put(SLIME_ENTITY_DEBUG, Collections.emptyList());
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
                    List<EntityType> secCheckList = new ArrayList<EntityType>();
                    secCheckList.add(sec);
                    biomeAllocatedSECMap.put(biomePermitted, secCheckList);
                }
            }
        }
        // Probably also computationally demanding.
        return Collections.unmodifiableMap(biomeAllocatedSECMap);
    }
    // This Map checks each biome to see which SECs can spawn within it.
    public static final Map<Biome, List<EntityType>> biomeAllocatedSECMap = allocateSECToBiome();


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
//        SpawnRestrictionMixin.register(
//                SLIME_ENTITY_DEBUG,
//                SpawnRestriction.Location.ON_GROUND,
//                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
//                SlimeEntityColoured::canSpawnSEC);
//        for (Biome biomeReg : Registry.BIOME) {
//            EntityType k = SLIME_ENTITY_DEBUG;
//            List<Biome> v = convertConfigIDsToBiome(Slimeology.CONFIG.secBiomes.biomesForSECBlack);
//            checkBiome(biomeReg, k, v);
//            RegistryEntryAddedCallback.event(Registry.BIOME).register(
//                    (i, identifier, biome) -> {
//                        RegisterSEC.checkBiome(biomeReg, k, v);
//                    });
//        }

        secForcedOrder.forEach(entry->{
            FabricDefaultAttributeRegistry.register(entry, HostileEntity.createHostileAttributes());
//            // Basic version: SECs spawn in all Biomes.
//            for (Biome biome : Registry.BIOME) {
//
//                biome.getEntitySpawnList(entry.getSpawnGroup())
//                        .add(new Biome.SpawnEntry(entry, 100, 4, 4));
//            }
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
