package com.lapisliozuli.slimeology.items;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.lapisliozuli.slimeology.Slimeology;
import com.lapisliozuli.slimeology.blocks.ColouredSlimeBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class BleachingBrush extends MiningToolItem {
    protected BleachingBrush(float attackDamage, float attackSpeed, ToolMaterial material, Set<Block> effectiveBlocks, Settings settings) {
        super(attackDamage, attackSpeed, material, effectiveBlocks, settings);
    }

    private static final Set<Block> EFFECTIVE_BLOCKS;
    protected static final Map<Block, BlockState> BLEACHABLE_BLOCKS;

//    // Mimics how Hoes convert Dirt/Grass blocks to Farmland.
//    public ActionResult useOnBlock(ItemUsageContext context) {
//        World world = context.getWorld();
//        BlockPos blockPos = context.getBlockPos();
//        // Checks if the face is not the bottom face, and that there is Air above. (May remove second check because not thematic.)
//        if (context.getSide() != Direction.DOWN && world.getBlockState(blockPos.up()).isAir()) {
//            BlockState blockState = (BlockState) BLEACHABLE_BLOCKS.get(world.getBlockState(blockPos).getBlock());
//            // Checks if a Water Block is nearby.
//            if (blockState != null & isWaterNearby(world, blockPos)) {
//                PlayerEntity playerEntity = context.getPlayer();
//                // Creates sound of Grass block when Brush bleaches the CSB.
//                world.playSound(playerEntity, blockPos, SoundEvents.BLOCK_GRASS_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
//                if (!world.isClient) {
//                    // Converts Bleachable Block to Slime Block.
//                    world.setBlockState(blockPos, blockState, 11);
//                    // Is there a way to drop a Dye Item at the blockPos above the CSB i.e. BlockPos.up()?
//                    if (playerEntity != null) {
//                        // Reduces tool durability by 1.
//                        context.getStack().damage(1, playerEntity, (p) -> {
//                            p.sendToolBreakStatus(context.getHand());
//                        });
//                    }
//                }
//                return ActionResult.success(world.isClient);
//            }
//        }
//        return ActionResult.PASS;
//    }


    // Somehow need to refer to Moisture
    private static boolean isWaterNearby(WorldView world, BlockPos pos) {
        // Range is 1 block along horizontal axes.
        Iterator var2 = BlockPos.iterate(pos.add(-1, 0, -1), pos.add(1, 0, 1)).iterator();

        BlockPos blockPos;
        do {
            if (!var2.hasNext()) {
                return false;
            }

            blockPos = (BlockPos)var2.next();
        } while(!world.getFluidState(blockPos).isIn(FluidTags.WATER));

        return true;
    }

    static {
        BLEACHABLE_BLOCKS = Maps.newHashMap();
        // Adds all the Coloured Slime blocks to the list.
        for (ColouredSlimeBlocks colouredSlimeBlock : ColouredSlimeBlocks.colouredSlimeBlocksMap.values())
            // Links each Bleachable Block to the vanilla Slime Block.
            BLEACHABLE_BLOCKS.put(colouredSlimeBlock, Blocks.SLIME_BLOCK.getDefaultState());

        // Just saying that the Brush isn't effective at breaking any blocks.
        EFFECTIVE_BLOCKS = ImmutableSet.of();
    }

    public static MiningToolItem BLEACHING_BRUSH = new BleachingBrush(0, -1.0f,
            ToolMaterialSlimeball.INSTANCE, EFFECTIVE_BLOCKS, new Item.Settings().group(Slimeology.SLIMEOLOGY));

}

