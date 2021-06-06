package com.lapisliozuli.slimeology.blocks;

import com.lapisliozuli.slimeology.Slimeology;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.*;


public class SlimySand extends FallingBlock {

    public SlimySand() {
        super(FabricBlockSettings.copyOf(Blocks.SAND).slipperiness(0.9F));
    }
    public static final SlimySand SLIMY_SAND = new SlimySand();


    // I want this to run also for any adjacent contacted blocks.
    // I think the unused parameters are needed for this method to shadow the parent method
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        // If the block can fall down during this tick, spawn a FallingBlockEntity
        if (canFallThrough(world.getBlockState(pos.down())) && pos.getY() >= 0) {
            // Why add 0.5D on both the x- and z-axes?
            FallingBlockEntity fallingBlockEntity = new FallingBlockEntity(world,
                    (double) pos.getX() + 0.5D, (double) pos.getY(),
                    (double) pos.getZ() + 0.5D, world.getBlockState(pos));
            this.configureFallingBlockEntity(fallingBlockEntity);
            world.spawnEntity(fallingBlockEntity);

            List<BlockPos> neighbouringBlockPos = checksSideBlocks(world, pos);
            neighbouringBlockPos.forEach(neighbourPos -> {
                if (canFallThrough(world.getBlockState(neighbourPos.down())) && neighbourPos.getY() >= 0) {
                    FallingBlockEntity neighbourFBE = new FallingBlockEntity(world,
                            (double) neighbourPos.getX() + 0.5D, (double) neighbourPos.getY(),
                            (double) neighbourPos.getZ() + 0.5D, world.getBlockState(neighbourPos));
                    this.configureFallingBlockEntity(neighbourFBE);
                    world.spawnEntity(neighbourFBE);
                }
            });
        }
    }


//    // One method to find all adjacent blocks
    private List<BlockPos> checksSideBlocks(World world, BlockPos pos) {
        // Make this return a list of blocks
        BlockPos.Mutable mutablePos = pos.mutableCopy();
        Direction[] xyzDirections = Direction.values();
        List<BlockPos> neighbouringBlockPos = new ArrayList<>(Collections.emptyList());

        for(int index = 0; index < xyzDirections.length; ++index) {
            Direction direction = xyzDirections[index];

            // Check for the sides
            // Also check if the block is not air or fluid.
            // I'm not sure why '&&' works instead of '||' but OK.
            if (direction != Direction.DOWN && direction != Direction.UP) {
                mutablePos.set(pos, direction);
                BlockState blockState = world.getBlockState(mutablePos);
                // Use DOWN as a placeholder value
                if ((blockState.getBlock() != Blocks.AIR) && PistonBlock.isMovable(blockState, world, mutablePos, direction, false, Direction.DOWN)) {
                    // I have to do this or else each entry will refer to the original blockState and keep changing.
                    neighbouringBlockPos.add(pos.mutableCopy().set(pos, direction));
                }
            }
        }
        return neighbouringBlockPos;
    }


    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        double d = Math.abs(entity.getVelocity().y);
        if (d < 0.1D && !entity.bypassesSteppingEffects()) {
            // 2 is a constant that makes walking on Slimy Sand slightly faster than walking on Slime Blocks.
            double e = 2 * (0.4D + d * 0.2D);
            entity.setVelocity(entity.getVelocity().multiply(e, 1.0D, e));
        }

        super.onSteppedOn(world, pos, state, entity);
    }

    public static void registerSlimySand() {
        Registry.register(Registry.BLOCK, new Identifier(Slimeology.MOD_ID, "slimy_sand"), SLIMY_SAND);
        Registry.register(Registry.ITEM, new Identifier(Slimeology.MOD_ID, "slimy_sand"),
                new BlockItem(SLIMY_SAND, new Item.Settings().group(Slimeology.SLIMEOLOGY)));
    }
}