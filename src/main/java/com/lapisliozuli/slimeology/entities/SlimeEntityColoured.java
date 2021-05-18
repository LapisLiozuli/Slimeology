package com.lapisliozuli.slimeology.entities;


import com.google.common.collect.Ordering;
import com.lapisliozuli.slimeology.Slimeology;
import com.lapisliozuli.slimeology.items.SlimeBalls;
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
        return new ItemStackParticleEffect(ParticleTypes.ITEM, new ItemStack(SlimeBalls.SLIME_BALL_DEBUG));
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

    // Need a pointer to the Registry.register() variable
    private static final EntityType secPointer = SLIME_ENTITY_DEBUG;


    public static boolean canSpawnSEC(EntityType<? extends SlimeEntityColoured> type, WorldAccess world, SpawnReason spawnReason,
                                      BlockPos pos, Random random) {
        if (world.getDifficulty() != Difficulty.PEACEFUL) {
            Biome biome = world.getBiome(pos);

            if (!(world instanceof ServerWorldAccess)) {
                return false;
            }

            ChunkPos chunkPos = new ChunkPos(pos);
            List<EntityType> secSiblings = RegisterSEC.biomeSECSpawnCheckMap.get(biome);
            if (secSiblings == null) {
                return false;
            }
            Collections.sort(secSiblings, Ordering.explicit(RegisterSEC.secOrderedList));

            int secDyeOrder = RegisterSEC.secOrderedList.indexOf(secPointer);
            Random slimeChunkSeed = ChunkRandom.getSlimeRandom(chunkPos.x, chunkPos.z, ((ServerWorldAccess)world).getSeed(), 987234911L);
            boolean bl = slimeChunkSeed.nextInt(10) == 0;
            // Highly configurable spawning conditions
            boolean moonCheck = random.nextFloat() < world.getMoonSize();
            if (Slimeology.CONFIG.secSpawning.ignoreMoon) {
                moonCheck = true;
            }
            if (bl
                    && pos.getY() > Slimeology.CONFIG.secSpawning.heightFloor
                    && pos.getY() < Slimeology.CONFIG.secSpawning.heightCap
                    && random.nextFloat() <= Slimeology.CONFIG.secSpawning.spawnMultiplier
                    && moonCheck
                    && world.getLightLevel(pos) <= random.nextInt(8)) {
                int secChunkNumber = slimeChunkSeed.nextInt(16);
                if (secDyeOrder == secChunkNumber && secSiblings.contains(RegisterSEC.secOrderedList.get(secChunkNumber))) {
                    if (Slimeology.CONFIG.secSpawning.spawnReporting) {
                        System.out.println("Spawning " + secPointer + " in core Slime Chunk " + chunkPos + " for biome " + biome + " using secChunkNumber " + secChunkNumber);
                    }
                    return canMobSpawn(type, world, spawnReason, pos, random);
                }

                else if (!secSiblings.contains(RegisterSEC.secOrderedList.get(secChunkNumber))) {
                    int secBirthOrder = secSiblings.indexOf(secPointer);
                    int secChunkReroll = slimeChunkSeed.nextInt(secSiblings.size());
                    if (secBirthOrder == secChunkReroll) {
                        if (Slimeology.CONFIG.secSpawning.spawnReporting) {
                            System.out.println("Spawning " + secPointer + " in variable Slime Chunk " + chunkPos + " for biome " + biome + " using secChunkReroll " + secChunkReroll);
                            System.out.println("Allocated colour is " + RegisterSEC.secOrderedList.get(secChunkNumber) + " while secSiblings are " + secSiblings);
                        }
                        return canMobSpawn(type, world, spawnReason, pos, random);
                    }
                }
            }
        }
        return false;
    }

}
