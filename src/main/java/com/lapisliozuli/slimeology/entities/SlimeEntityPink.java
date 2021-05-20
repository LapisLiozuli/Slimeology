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

public class SlimeEntityPink extends SlimeEntityColoured {

    public SlimeEntityPink(EntityType<? extends SlimeEntityPink> arg, World arg2) {
        super(arg, arg2);
    }

    @Override
    protected ParticleEffect getParticles() {
        return new ItemStackParticleEffect(ParticleTypes.ITEM, new ItemStack(RegisterItems.SLIME_BALL_PINK));
    }

    public static final EntityType<SlimeEntityPink> SLIME_ENTITY_PINK = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(Slimeology.MOD_ID, "slime_entity_pink"),
            FabricEntityTypeBuilder.<SlimeEntityPink>create(SpawnGroup.MONSTER, SlimeEntityPink::new)
                    .dimensions(EntityDimensions.changing(2.04f, 2.04f))
                    .trackable(160,4).build());

    public static final EntityType secPointer = SLIME_ENTITY_PINK;


    // Adapted from SlimeEntityColoured.canSpawnSEC()
    public static boolean canSpawnSECPink(EntityType<? extends SlimeEntityPink> type, WorldAccess world, SpawnReason spawnReason,
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
            if (!Slimeology.CONFIG.secSpawning.ignoreMoon) {
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
                    return canMobSpawn(type, world, spawnReason, pos, random);}

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
