package com.lapisliozuli.slimeology.mixins;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.Heightmap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(SpawnRestriction.class)
public interface SpawnRestrictionMixin {
    // Used in spawning of SlimeEntityColoured.
    @Invoker("register")
    // IntelliJ MCDev complains about this but it still works.
    public static <T extends MobEntity> void register(
            EntityType<T> type,
            SpawnRestriction.Location location,
            Heightmap.Type heightmapType,
            SpawnRestriction.SpawnPredicate<T> predicate) {
        throw new AssertionError();
    }
}