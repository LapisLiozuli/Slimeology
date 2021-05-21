package com.lapisliozuli.slimeology.entities;

import com.google.common.collect.Ordering;
import com.lapisliozuli.slimeology.Slimeology;
import com.lapisliozuli.slimeology.registry.RegisterItems;
import com.lapisliozuli.slimeology.registry.RegisterSEC;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnReason;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Difficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkRandom;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SlimeEntityYellow extends SlimeEntityColoured {

    public SlimeEntityYellow(EntityType<? extends SlimeEntityYellow> arg, World arg2) {
        super(arg, arg2);
    }

    @Override
    protected ParticleEffect getParticles() {
        return new ItemStackParticleEffect(ParticleTypes.ITEM, new ItemStack(RegisterItems.SLIME_BALL_YELLOW));
    }

    public static final EntityType<SlimeEntityYellow> SLIME_ENTITY_YELLOW = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(Slimeology.MOD_ID, "slime_entity_yellow"),
            FabricEntityTypeBuilder.<SlimeEntityYellow>create(SpawnGroup.MONSTER, SlimeEntityYellow::new)
                    .dimensions(EntityDimensions.changing(2.04f, 2.04f))
                    .trackable(160,4).build());

    public static final EntityType secPointer = SLIME_ENTITY_YELLOW;


    // Adapted from SlimeEntityColoured.canSpawnSEC()
    public static boolean canSpawnSECYellow(EntityType<? extends SlimeEntityYellow> type, WorldAccess world, SpawnReason spawnReason,
                                      BlockPos pos, Random random) {
        if (world.getDifficulty() != Difficulty.PEACEFUL) {
            Biome biome = world.getBiome(pos);

            if (!(world instanceof ServerWorldAccess)) {
                return false;
            }

            ChunkPos chunkPos = new ChunkPos(pos);
            List<EntityType> biomeSiblings = RegisterSEC.biomeAllocatedSECMap.get(biome);
			if (biomeSiblings == null) {
				return false;
			}
            Collections.sort(biomeSiblings, Ordering.explicit(RegisterSEC.secForcedOrder));

            int secDyeIndex = RegisterSEC.secForcedOrder.indexOf(secPointer);
            Random slimeChunkSeed = ChunkRandom.getSlimeRandom(chunkPos.x, chunkPos.z, ((ServerWorldAccess)world).getSeed(), 987234911L);
            boolean bl = slimeChunkSeed.nextInt(10) == 0;
            // Highly configurable spawning conditions
            boolean moonCheck = random.nextFloat() < world.getMoonSize();
            if (!Slimeology.CONFIG.secSpawning.ignoreMoon) {
                moonCheck = true;
            }
            if (bl
                    && pos.getY() > Slimeology.CONFIG.secSpawning.heightFloor
                    && pos.getY() < Slimeology.CONFIG.secSpawning.heightCap
                    && random.nextFloat() <= Slimeology.CONFIG.secSpawning.spawnMultiplier
                    && moonCheck
                    && world.getLightLevel(pos) <= random.nextInt(8)) {
                int secChunkIndex = slimeChunkSeed.nextInt(16);
                if (secDyeIndex == secChunkIndex && biomeSiblings.contains(RegisterSEC.secForcedOrder.get(secChunkIndex))) {
                    if (Slimeology.CONFIG.secSpawning.spawnReporting) {
                        System.out.println("Spawning " + secPointer + " in core Slime Chunk " + chunkPos + " for biome " + biome + " using secChunkIndex " + secChunkIndex);
                    }
                    return canMobSpawn(type, world, spawnReason, pos, random);}

                else if (!biomeSiblings.contains(RegisterSEC.secForcedOrder.get(secChunkIndex))) {
                    int secSiblingIndex = biomeSiblings.indexOf(secPointer);
                    int secChunkReIndex = slimeChunkSeed.nextInt(biomeSiblings.size());
                    if (secSiblingIndex == secChunkReIndex) {
                        if (Slimeology.CONFIG.secSpawning.spawnReporting) {
                            System.out.println("Spawning " + secPointer + " in variable Slime Chunk " + chunkPos + " for biome " + biome + " using secChunkReIndex " + secChunkReIndex);
                            System.out.println("Allocated colour is " + RegisterSEC.secForcedOrder.get(secChunkIndex) + " while biomeSiblings are " + biomeSiblings);
                        }
                        return canMobSpawn(type, world, spawnReason, pos, random);
                    }
                }
            }
        }
        return false;
    }
}
