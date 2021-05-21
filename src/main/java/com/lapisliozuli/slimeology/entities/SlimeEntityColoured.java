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
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTables;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.*;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkRandom;

import java.util.*;

// Copied from MagmaCubeEntity
public class SlimeEntityColoured extends SlimeEntity {
    public SlimeEntityColoured(final EntityType<? extends SlimeEntityColoured> arg, final World arg2) {
        super(arg, arg2);
    }

    public static boolean canSlimeEntityColouredSpawn(final EntityType<SlimeEntityColoured> type, final WorldAccess world, final SpawnReason spawnReason, final BlockPos pos, final Random random) {
        return world.getDifficulty() != Difficulty.PEACEFUL;
    }

    @Override
    public boolean canSpawn(final WorldView world) {
        return world.intersectsEntities(this) && !world.containsFluid(this.getBoundingBox());
    }

    @Override
    protected void setSize(final int size, final boolean heal) {
        super.setSize(size, heal);
    }

    @Override
    protected ParticleEffect getParticles() {
        return new ItemStackParticleEffect(ParticleTypes.ITEM, new ItemStack(RegisterItems.SLIME_BALL_DEBUG));
    }

    @Override
    protected Identifier getLootTableId() {
        return this.getSize() == 1 ? this.getType().getLootTableId() : LootTables.EMPTY;
    }

    @Override
    protected int getTicksUntilNextJump() {
        return super.getTicksUntilNextJump() * 4;
    }

    @Override
    protected boolean canAttack() {
        return this.canMoveVoluntarily();
    }

    public static final EntityType<SlimeEntityColoured> SLIME_ENTITY_DEBUG = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(Slimeology.MOD_ID, "slime_entity_debug"),
            FabricEntityTypeBuilder.<SlimeEntityColoured>create(SpawnGroup.MONSTER, SlimeEntityColoured::new)
                    .dimensions(EntityDimensions.changing(2.04f, 2.04f))
                    .trackable(160,4).build());

    // Need a pointer to the Registry.register() variable.
    private static final EntityType secPointer = SLIME_ENTITY_DEBUG;


    public static boolean canSpawnSEC(EntityType<? extends SlimeEntityColoured> type, WorldAccess world, SpawnReason spawnReason,
                                      BlockPos pos, Random random) {
        if (world.getDifficulty() != Difficulty.PEACEFUL) {
            // Find the biome of the position.
            Biome biome = world.getBiome(pos);
            if (!(world instanceof ServerWorldAccess)) {
                return false;
            }
            ChunkPos chunkPos = new ChunkPos(pos);

            // Get the list of coloured slimes that can spawn within the biome.
            List<EntityType> biomeSiblings = RegisterSEC.biomeAllocatedSECMap.get(biome);
            if (biomeSiblings == null) {
                return false;
            }
            // Sort the biomeSiblings based on the order given in secForcedOrder.
            Collections.sort(biomeSiblings, Ordering.explicit(RegisterSEC.secForcedOrder));

            // secDyeIndex is the index of the SEC within secForcedOrder.
            int secDyeIndex = RegisterSEC.secForcedOrder.indexOf(secPointer);
            Random slimeChunkSeed = ChunkRandom.getSlimeRandom(chunkPos.x, chunkPos.z, ((ServerWorldAccess)world).getSeed(), 987234911L);
            // bl will always be the same because the seed and number of calls are the same.
            // 10% of chunks become slime chunks.
            boolean bl = slimeChunkSeed.nextInt(10) == 0;

            // Highly configurable spawning conditions.
            boolean moonCheck = random.nextFloat() < world.getMoonSize();
            // Can ignore moon so that moonCheck always returns true.
            if (Slimeology.CONFIG.secSpawning.ignoreMoon) {
                moonCheck = true;
            }

            // Executing the checks for canMobSpawn().
            if (bl
                    && pos.getY() > Slimeology.CONFIG.secSpawning.heightFloor
                    && pos.getY() < Slimeology.CONFIG.secSpawning.heightCap
                    && random.nextFloat() <= Slimeology.CONFIG.secSpawning.spawnMultiplier
                    && moonCheck
                    && world.getLightLevel(pos) <= random.nextInt(8)) {
                // Same as with bl, up to 16 for 16 colours.
                int secChunkIndex = slimeChunkSeed.nextInt(16);
                // First check if the SEC's index matches the chunk index.
                // Then check if the SEC has been allocated for spawning within the biome.
                if (secDyeIndex == secChunkIndex && biomeSiblings.contains(RegisterSEC.secForcedOrder.get(secChunkIndex))) {
                    if (Slimeology.CONFIG.secSpawning.spawnReporting) {
                        System.out.println("Spawning " + secPointer + " in core Slime Chunk " + chunkPos + " for biome " + biome + " using secChunkIndex " + secChunkIndex);
                    }
                    return canMobSpawn(type, world, spawnReason, pos, random);
                }

                // Else, check if the spawnable slimes for that biome contains the SEC allocated based on the chunk number.
                else if (!biomeSiblings.contains(RegisterSEC.secForcedOrder.get(secChunkIndex))) {
                    // Find the index of the SEC within spawnable slimes.
                    int secSiblingIndex = biomeSiblings.indexOf(secPointer);
                    // Uses the slime chunk seed to generate a number in the length of biomeSiblings.
                    int secChunkReIndex = slimeChunkSeed.nextInt(biomeSiblings.size());
                    // If the new randomly generated number matches the index, spawning will occur.
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
