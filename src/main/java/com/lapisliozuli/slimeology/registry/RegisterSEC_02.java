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
//import net.minecraft.world.biome.Biome;
//
//import java.util.List;
//import java.util.Map;
//
//import static com.lapisliozuli.slimeology.entities.SlimeEntityColoured.SLIME_ENTITY_DEBUG;
//
//public class RegisterSEC_02 {
//
////    // Try to input parameters for SEC class.
////    public static final EntityType<SlimeEntityColoured> SLIME_ENTITY_DEBUG = Registry.register(
////            Registry.ENTITY_TYPE,
////            new Identifier(Slimeology.MOD_ID, "slime_entity_debug"),
////            FabricEntityTypeBuilder.<SlimeEntityColoured>create(SpawnGroup.MONSTER, SlimeEntityColoured::new)
////                    .dimensions(EntityDimensions.changing(2.04f, 2.04f))
////                    .trackable(160,4).build());
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
////        SpawnRestriction.register(
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
//        // Registers each SEC as a HostileEntity. Keep this.
//        secForcedOrder.forEach(entry->{
//            FabricDefaultAttributeRegistry.register(entry, HostileEntity.createHostileAttributes());
//        });
////
////        secSpawnPredicatesMap.forEach(RegisterSEC::registerSpawnRestriction);
////
////        for (Map.Entry<EntityType, List<Biome>> entry : secBiomeSpawnPermissionMap.entrySet()) {
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
