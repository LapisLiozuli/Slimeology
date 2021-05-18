package com.lapisliozuli.slimeology.registry;

import com.lapisliozuli.slimeology.Slimeology;
import com.lapisliozuli.slimeology.entities.*;
import com.lapisliozuli.slimeology.mixins.SpawnRestrictionMixin;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.*;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;

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
    public static List<EntityType> secOrderedList = Stream.of(
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

    // This method reads the numeric IDs from the configs and returns a list of Biomes.
    // It also reads a List<Integer> disguised as a String so it's kinda cursed.
    public static List<Biome> convertConfigIDsToBiome(String stringyListInt) {
        List<Biome> configBiomeList = new ArrayList<>(Collections.emptyList());
        List<String> numericIDList = Arrays.asList(stringyListInt.split("\\s*,\\s*"));
        numericIDList.forEach(entry->{
            configBiomeList.add(Registry.BIOME.get(Integer.parseInt(entry)));
        });
//        System.out.println(configBiomeList);
        return configBiomeList;
    }

    // This Map sets the biome spawning permissions for each SEC
    public static Map<EntityType, List<Biome>> imperative() {
        final Map<EntityType, List<Biome>> secBiomeSpawnPermissionMap = new HashMap<>();
        secBiomeSpawnPermissionMap.put(SLIME_ENTITY_DEBUG, Collections.emptyList());
        secBiomeSpawnPermissionMap.put(SLIME_ENTITY_WHITE, convertConfigIDsToBiome(Slimeology.CONFIG.secBiomes.biomesForSECWhite));
        secBiomeSpawnPermissionMap.put(SLIME_ENTITY_ORANGE, convertConfigIDsToBiome(Slimeology.CONFIG.secBiomes.biomesForSECOrange));
        secBiomeSpawnPermissionMap.put(SLIME_ENTITY_MAGENTA, convertConfigIDsToBiome(Slimeology.CONFIG.secBiomes.biomesForSECMagenta));
        secBiomeSpawnPermissionMap.put(SLIME_ENTITY_LIGHT_BLUE, convertConfigIDsToBiome(Slimeology.CONFIG.secBiomes.biomesForSECLightBlue));
        secBiomeSpawnPermissionMap.put(SLIME_ENTITY_YELLOW, convertConfigIDsToBiome(Slimeology.CONFIG.secBiomes.biomesForSECYellow));
        secBiomeSpawnPermissionMap.put(SLIME_ENTITY_LIME, convertConfigIDsToBiome(Slimeology.CONFIG.secBiomes.biomesForSECLime));
        secBiomeSpawnPermissionMap.put(SLIME_ENTITY_PINK, convertConfigIDsToBiome(Slimeology.CONFIG.secBiomes.biomesForSECPink));
        secBiomeSpawnPermissionMap.put(SLIME_ENTITY_GRAY, convertConfigIDsToBiome(Slimeology.CONFIG.secBiomes.biomesForSECGray));
        secBiomeSpawnPermissionMap.put(SLIME_ENTITY_LIGHT_GRAY, convertConfigIDsToBiome(Slimeology.CONFIG.secBiomes.biomesForSECLightGray));
        secBiomeSpawnPermissionMap.put(SLIME_ENTITY_CYAN, convertConfigIDsToBiome(Slimeology.CONFIG.secBiomes.biomesForSECCyan));
        secBiomeSpawnPermissionMap.put(SLIME_ENTITY_PURPLE, convertConfigIDsToBiome(Slimeology.CONFIG.secBiomes.biomesForSECPurple));
        secBiomeSpawnPermissionMap.put(SLIME_ENTITY_BLUE, convertConfigIDsToBiome(Slimeology.CONFIG.secBiomes.biomesForSECBlue));
        secBiomeSpawnPermissionMap.put(SLIME_ENTITY_BROWN, convertConfigIDsToBiome(Slimeology.CONFIG.secBiomes.biomesForSECBrown));
        secBiomeSpawnPermissionMap.put(SLIME_ENTITY_GREEN, convertConfigIDsToBiome(Slimeology.CONFIG.secBiomes.biomesForSECGreen));
        secBiomeSpawnPermissionMap.put(SLIME_ENTITY_RED, convertConfigIDsToBiome(Slimeology.CONFIG.secBiomes.biomesForSECRed));
        secBiomeSpawnPermissionMap.put(SLIME_ENTITY_BLACK, convertConfigIDsToBiome(Slimeology.CONFIG.secBiomes.biomesForSECBlack));
        return Collections.unmodifiableMap(secBiomeSpawnPermissionMap);
    }
    public static final Map<EntityType, List<Biome>> secBiomeSpawnPermissionMap = imperative();

    // This map reads secBiomeSpawnPermissionMap to find the SECs that spawn in each biome
    public static Map<Biome, List<EntityType>> matchBiomeToSEC() {
        final Map<Biome, List<EntityType>> biomeSECSpawnCheckMap = new HashMap<>();
        for (Map.Entry<EntityType, List<Biome>> entry : secBiomeSpawnPermissionMap.entrySet()) {
            EntityType k = entry.getKey();
            List<Biome> v = entry.getValue();
            for (Biome biomePermitted : v) {
                if (biomeSECSpawnCheckMap.containsKey(biomePermitted)) {
                    biomeSECSpawnCheckMap.get(biomePermitted).add(k);
                }
                else {
                    List<EntityType> secCheckList = new ArrayList<EntityType>();
                    secCheckList.add(k);
                    biomeSECSpawnCheckMap.put(biomePermitted, secCheckList);
                }
            }
        }
        // Probably also computationally demanding.
        return Collections.unmodifiableMap(biomeSECSpawnCheckMap);
    }
    public static final Map<Biome, List<EntityType>> biomeSECSpawnCheckMap = matchBiomeToSEC();


    // Adds SpawnEntry only if the biome is part of the SEC's BiomeList.
    public static void checkBiome(Biome biome, EntityType type, List<Biome> biomeList) {
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

        secOrderedList.forEach(entry->{
            FabricDefaultAttributeRegistry.register(entry, HostileEntity.createHostileAttributes());
//            // Basic version: SECs spawn in all Biomes.
//            for (Biome biome : Registry.BIOME) {
//
//                biome.getEntitySpawnList(entry.getSpawnGroup())
//                        .add(new Biome.SpawnEntry(entry, 100, 4, 4));
//            }
        });

        secSpawnPredicatesMap.forEach(RegisterSEC::registerSpawnRestriction);

        for (Map.Entry<EntityType, List<Biome>> entry : secBiomeSpawnPermissionMap.entrySet()) {
            EntityType k = entry.getKey();
            List<Biome> v = entry.getValue();

            // Checks the biome and adds the callback
            for (Biome biomeReg : Registry.BIOME) {
                checkBiome(biomeReg, k, v);
                RegistryEntryAddedCallback.event(Registry.BIOME).register(
                        (i, identifier, biome) -> {
                            RegisterSEC.checkBiome(biomeReg, k, v);
                        });
            }
        }
    }
}
