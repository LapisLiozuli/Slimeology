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
import net.minecraft.item.ItemConvertible;
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
    private ItemConvertible slimeParticle;
//    private static int secDyeIndex;

    // Constructor with additional particle and SEC variable as parameters.
    public SlimeEntityColoured(
            final EntityType<? extends SlimeEntityColoured> arg,
            final World arg2,
            final ItemConvertible slimeParticleInput) {
//            final int inputIndex) {
        super(arg, arg2);
        this.slimeParticle = slimeParticleInput;
//        secDyeIndex = inputIndex;
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
        return new ItemStackParticleEffect(ParticleTypes.ITEM, new ItemStack(slimeParticle));
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
            List<EntityType<SlimeEntityColoured>> biomeSiblings = RegisterSEC.biomeAllocatedSECMap.get(biome);
            if (biomeSiblings == null) {
                return false;
            }
            // Sort the biomeSiblings based on the order given in secForcedOrder.
            Collections.sort(biomeSiblings, Ordering.explicit(RegisterSEC.secForcedOrder));

            // secDyeIndex is the index of the SEC within secForcedOrder.
//            EntityType<SlimeEntityColoured> secPointer = RegisterSEC.secForcedOrder.get(secDyeIndex);
            int secDyeIndex = RegisterSEC.secForcedOrder.indexOf(type);
            Random slimeChunkSeed = ChunkRandom.getSlimeRandom(chunkPos.x, chunkPos.z, ((ServerWorldAccess)world).getSeed(), 987234911L);
            // bl will always be the same because the seed and number of calls are the same.
            // On this 1st call of nextInt(), 10% of chunks become slime chunks.
            boolean bl = slimeChunkSeed.nextInt(10) == 0;

            // Highly configurable spawning conditions.
            boolean moonCheck = random.nextFloat() < world.getMoonSize();
            // Can ignore moon so that moonCheck always returns true.
            if (Slimeology.CONFIG.secSpawning.ignoreMoon) {
                moonCheck = true;
            }

            // Executing the checks for canMobSpawn() to run. Based off slime spawning in swamps.
            if (bl
                        && pos.getY() > Slimeology.CONFIG.secSpawning.heightFloor
                    && pos.getY() < Slimeology.CONFIG.secSpawning.heightCap
                    && random.nextFloat() <= Slimeology.CONFIG.secSpawning.spawnMultiplier
                    && moonCheck
                    && world.getLightLevel(pos) <= random.nextInt(8)) {
                // 2nd call of nextInt(), up to 16 for 16 colours.
                int secChunkIndex = slimeChunkSeed.nextInt(16);

                // First check if the SEC's index matches the chunk index. Then check if the SEC has been allocated for spawning within the biome.
//                if (secDyeIndex == secChunkIndex && biomeSiblings.contains(type)) {
//                if (biomeSiblings.contains(type)) {
                if (secDyeIndex == secChunkIndex) {
                    if (Slimeology.CONFIG.secSpawning.spawnReporting) {
                        System.out.println("SLIMEOLOGY: Spawning " + type + " in core Slime Chunk " + chunkPos + " for biome " + biome + " using secChunkIndex " + secChunkIndex);
                    }
                    return canMobSpawn(type, world, spawnReason, pos, random);
                }

                // If this SEC's index doesn't match the chunk index, then check if the SEC is included within the SECs allocated to the position's (not chunk's) biome.
                // But if this check also fails for this SEC, then no spawning occurs. That's fine because another type of SEC should pass the check and spawn.
                else if (!biomeSiblings.contains(RegisterSEC.secForcedOrder.get(secChunkIndex))) {
                    // Find the index of the SEC within the list of biome-spawnable slimes.
                    int secSiblingIndex = biomeSiblings.indexOf(type);
                    // Optional 3rd call of nextInt(). Uses the slime chunk seed to generate an index within the length-range of biomeSiblings.
                    int secChunkReIndex = slimeChunkSeed.nextInt(biomeSiblings.size());
                    // If the new randomly generated number matches the index, spawning will occur.
                    if (secSiblingIndex == secChunkReIndex) {
                        if (Slimeology.CONFIG.secSpawning.spawnReporting) {
                            System.out.println("SLIMEOLOGY: Spawning " + type + " in variable Slime Chunk " + chunkPos + " for biome " + biome + " using secChunkReIndex " + secChunkReIndex);
                            System.out.println("SLIMEOLOGY: Allocated colour is " + RegisterSEC.secForcedOrder.get(secChunkIndex) + " while biomeSiblings are " + biomeSiblings);
                        }
                        return canMobSpawn(type, world, spawnReason, pos, random);
                    }
                }
            }
        }
        return false;
    }
}