package com.lapisliozuli.slimeology.registry;

import com.lapisliozuli.slimeology.items.SlimeBalls;
import com.lapisliozuli.slimeology.Slimeology;
import com.lapisliozuli.slimeology.entities.*;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.mixin.biome.BuiltinBiomesAccessor;
import net.fabricmc.fabric.mixin.object.builder.SpawnRestrictionAccessor;
import net.minecraft.entity.*;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RegisterSEC {
    public static EntityType<SlimeEntityColoured> bulkRegisterFETB(String sec_path, Item slimeParticle) {
        return Registry.register(
                Registry.ENTITY_TYPE,
                new Identifier(Slimeology.MOD_ID, sec_path),
                FabricEntityTypeBuilder.<SlimeEntityColoured>create(SpawnGroup.MONSTER, (type, world) -> new SlimeEntityColoured(type, world, slimeParticle))
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
//            SLIME_ENTITY_DEBUG,
            SLIME_ENTITY_WHITE, SLIME_ENTITY_ORANGE, SLIME_ENTITY_MAGENTA, SLIME_ENTITY_LIGHT_BLUE,
            SLIME_ENTITY_YELLOW, SLIME_ENTITY_LIME, SLIME_ENTITY_PINK, SLIME_ENTITY_GRAY,
            SLIME_ENTITY_LIGHT_GRAY, SLIME_ENTITY_CYAN, SLIME_ENTITY_PURPLE, SLIME_ENTITY_BLUE,
            SLIME_ENTITY_BROWN, SLIME_ENTITY_GREEN, SLIME_ENTITY_RED, SLIME_ENTITY_BLACK
    ).collect(Collectors.toList());


    // This Map assigns a collection of spawnable biomes to each SEC.
    public static Map<EntityType<SlimeEntityColoured>, List<RegistryKey<Biome>>> allocatedBiomeImperative() {
        final Map<EntityType<SlimeEntityColoured>, List<RegistryKey<Biome>>> secAllocatedBiomeMap = new HashMap<>();
//        secAllocatedBiomeMap.put(SLIME_ENTITY_DEBUG, convertConfigIDsToBiomes(Slimeology.CONFIG.secBiomes.biomesForSECDebug));
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
    public static final Map<EntityType<SlimeEntityColoured>, List<RegistryKey<Biome>>> secAllocatedBiomeMap = allocatedBiomeImperative();


    // This method reads secAllocatedBiomeMap to invert the SECs and biomes.
    public static Map<RegistryKey<Biome>, List<EntityType<SlimeEntityColoured>>> allocateSECToBiome() {
        final Map<RegistryKey<Biome>, List<EntityType<SlimeEntityColoured>>> biomeAllocatedSECMap = new HashMap<>();
        for (Map.Entry<EntityType<SlimeEntityColoured>, List<RegistryKey<Biome>>> entry : secAllocatedBiomeMap.entrySet()) {
            EntityType sec = entry.getKey();
            List<RegistryKey<Biome>> biomeRegKey = entry.getValue();
            for (RegistryKey<Biome> brkPermitted : biomeRegKey) {
                if (biomeAllocatedSECMap.containsKey(brkPermitted)) {
                    biomeAllocatedSECMap.get(brkPermitted).add(sec);
                }
                else {
                    List<EntityType<SlimeEntityColoured>> secAllocateList = new ArrayList<EntityType<SlimeEntityColoured>>();
                    secAllocateList.add(sec);
                    biomeAllocatedSECMap.put(brkPermitted, secAllocateList);
                }
            }
        }
        // Probably also computationally demanding.
        return (Map<RegistryKey<Biome>, List<EntityType<SlimeEntityColoured>>>) Collections.unmodifiableMap(biomeAllocatedSECMap);
    }
    // This Map checks each biome to see which SECs can spawn within it.
    public static final Map<RegistryKey<Biome>, List<EntityType<SlimeEntityColoured>>> biomeAllocatedSECMap = allocateSECToBiome();

    // ======================= METHODS
    // This method reads the string IDs from the configs and returns a list of Biomes.
    public static List<RegistryKey<Biome>> convertConfigIDsToBiomes(String stringyListInt) {
        List<RegistryKey<Biome>> configBiomeKeyList = new ArrayList<>(Collections.emptyList());
        // Splits the string by the comma ',' to return a list of strings in the format 'minecraft:biome'
        List<String> stringIDList = Arrays.asList(stringyListInt.split("\\s*,\\s*"));
        stringIDList.forEach(entry->{
            // Converts the string "namespace:biome" into a RegistryKey<Biome>.
            configBiomeKeyList.add(BuiltinRegistries.BIOME.getKey(BuiltinRegistries.BIOME.get(Identifier.tryParse(entry))).get());
        });
        return configBiomeKeyList;
    }


    public static void regSpawn(EntityType<SlimeEntityColoured> inputSEC, String stringSECToBiomeList, String sec_path) {
        FabricDefaultAttributeRegistry.register(inputSEC, HostileEntity.createHostileAttributes());

        SpawnRestrictionAccessor.callRegister(
                inputSEC,
                SpawnRestriction.Location.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                SlimeEntityColoured::canSpawnSEC);

        List<RegistryKey<Biome>> biomeRegKeyList = convertConfigIDsToBiomes(stringSECToBiomeList);

        // Iterate over the Registry of Biomes
        for (RegistryKey<Biome> biomeReg : BuiltinBiomesAccessor.getBY_RAW_ID().values()) {
            // Only runs if the allocated list of biomes contains the selected biome.
            if (biomeRegKeyList.contains(biomeReg)) {
                BiomeModifications.addSpawn(BiomeSelectors.includeByKey(biomeReg), SpawnGroup.MONSTER, inputSEC, Slimeology.CONFIG.secSpawning.spawnWeight,4, 4);
            }
        }
    }


    public static void registerSlimeologyEntityTypes() {
//        regSpawn(SLIME_ENTITY_DEBUG, Slimeology.CONFIG.secBiomes.biomesForSECDebug, "slime_entity_debug");
        regSpawn(SLIME_ENTITY_WHITE, Slimeology.CONFIG.secBiomes.biomesForSECWhite, "slime_entity_white");
        regSpawn(SLIME_ENTITY_ORANGE, Slimeology.CONFIG.secBiomes.biomesForSECOrange, "slime_entity_orange");
        regSpawn(SLIME_ENTITY_MAGENTA, Slimeology.CONFIG.secBiomes.biomesForSECMagenta, "slime_entity_magenta");
        regSpawn(SLIME_ENTITY_LIGHT_BLUE, Slimeology.CONFIG.secBiomes.biomesForSECLightBlue, "slime_entity_light_blue");
        regSpawn(SLIME_ENTITY_YELLOW, Slimeology.CONFIG.secBiomes.biomesForSECYellow, "slime_entity_yellow");
        regSpawn(SLIME_ENTITY_LIME, Slimeology.CONFIG.secBiomes.biomesForSECLime, "slime_entity_lime");
        regSpawn(SLIME_ENTITY_PINK, Slimeology.CONFIG.secBiomes.biomesForSECPink, "slime_entity_pink");
        regSpawn(SLIME_ENTITY_GRAY, Slimeology.CONFIG.secBiomes.biomesForSECGray, "slime_entity_gray");
        regSpawn(SLIME_ENTITY_LIGHT_GRAY, Slimeology.CONFIG.secBiomes.biomesForSECLightGray, "slime_entity_light_gray");
        regSpawn(SLIME_ENTITY_CYAN, Slimeology.CONFIG.secBiomes.biomesForSECCyan, "slime_entity_cyan");
        regSpawn(SLIME_ENTITY_PURPLE, Slimeology.CONFIG.secBiomes.biomesForSECPurple, "slime_entity_purple");
        regSpawn(SLIME_ENTITY_BLUE, Slimeology.CONFIG.secBiomes.biomesForSECBlue, "slime_entity_blue");
        regSpawn(SLIME_ENTITY_BROWN, Slimeology.CONFIG.secBiomes.biomesForSECBrown, "slime_entity_brown");
        regSpawn(SLIME_ENTITY_GREEN, Slimeology.CONFIG.secBiomes.biomesForSECGreen, "slime_entity_green");
        regSpawn(SLIME_ENTITY_RED, Slimeology.CONFIG.secBiomes.biomesForSECRed, "slime_entity_red");
        regSpawn(SLIME_ENTITY_BLACK, Slimeology.CONFIG.secBiomes.biomesForSECBlack, "slime_entity_black");

    }
}