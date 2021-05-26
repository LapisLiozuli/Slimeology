//package com.lapisliozuli.slimeology.registry;
//
//import com.lapisliozuli.slimeology.Slimeology;
//import com.lapisliozuli.slimeology.entities.SlimeEntityColoured;
//import com.lapisliozuli.slimeology.mixins.SpawnRestrictionMixin;
//import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
//import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
//import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
//import net.minecraft.entity.EntityDimensions;
//import net.minecraft.entity.EntityType;
//import net.minecraft.entity.SpawnGroup;
//import net.minecraft.entity.SpawnRestriction;
//import net.minecraft.entity.mob.HostileEntity;
//import net.minecraft.util.Identifier;
//import net.minecraft.util.registry.Registry;
//import net.minecraft.world.Heightmap;
//import net.minecraft.world.biome.Biome;
//
//import java.util.List;
//import java.util.Map;
//
//public class RegisterSEC_02 {
//    // Initialise the variable first.
//    public static EntityType<SlimeEntityColoured> SLIME_ENTITY_DEBUG;
//    // Then put the SEC variable into a map with the paths, particle and itself as an input?
//
//    // Try to input parameters for SEC class.
//    static {
//        SLIME_ENTITY_DEBUG = Registry.register(
//                Registry.ENTITY_TYPE,
//                new Identifier(Slimeology.MOD_ID, "slime_entity_debug"),
//                FabricEntityTypeBuilder.<SlimeEntityColoured>create(SpawnGroup.MONSTER, (arg, arg2) -> new SlimeEntityColoured(arg, arg2, RegisterItems.SLIME_BALL_DEBUG, SLIME_ENTITY_DEBUG))
//                        .dimensions(EntityDimensions.changing(2.04f, 2.04f))
//                        .trackable(160, 4).build());
//    }
//
//
//    public static void checkBiomeForSpawnEntry(Biome biome, EntityType type, List<Biome> biomeList) {
//        if (biomeList.contains(biome)) {
//            biome.getEntitySpawnList(type.getSpawnGroup())
//                    .add(new Biome.SpawnEntry(type, 1000, 4, 4));
//        }
//    }
//
//
//    public static void registerSlimeologyEntityTypes() {
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
//
//        // KEEP. Registers each SEC as a HostileEntity.
//        secForcedOrder.forEach(entry->{
//            FabricDefaultAttributeRegistry.register(entry, HostileEntity.createHostileAttributes());
//        });
//
//        // KEEP. But likely to be rewritten once canSpawnSEC() is standardised.
//        secSpawnPredicatesMap.forEach(RegisterSEC::registerSpawnRestriction);
////
////        for (Map.Entry<EntityType, List<Biome>> entry : secAllocatedBiomeMap.entrySet()) {
////            EntityType k = entry.getKey();
////            List<Biome> v = entry.getValue();
////
////            // Checks the biome and adds the callback
////            for (Biome biomeReg : Registry.BIOME) {
////                checkBiomeForSpawnEntry(biomeReg, k, v);
////                RegistryEntryAddedCallback.event(Registry.BIOME).register(
////                        (i, identifier, biome) -> {
////                            RegisterSEC.checkBiomeForSpawnEntry(biomeReg, k, v);
////                        });
////            }
////        }
//    }
//}
