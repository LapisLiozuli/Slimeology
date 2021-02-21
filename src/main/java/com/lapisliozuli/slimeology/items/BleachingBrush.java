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


    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        if (context.getSide() != Direction.DOWN && world.getBlockState(blockPos.up()).isAir()) {
            BlockState blockState = (BlockState) BLEACHABLE_BLOCKS.get(world.getBlockState(blockPos).getBlock());
            if (blockState != null & isWaterNearby(world, blockPos)) {
                PlayerEntity playerEntity = context.getPlayer();
                world.playSound(playerEntity, blockPos, SoundEvents.BLOCK_GRASS_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                if (!world.isClient) {
                    world.setBlockState(blockPos, blockState, 11);
                    if (playerEntity != null) {
                        context.getStack().damage(1, playerEntity, (p) -> {
                            p.sendToolBreakStatus(context.getHand());
                        });
                    }
                }
                return ActionResult.success(world.isClient);
            }
        }
        return ActionResult.PASS;
    }


    // Somehow need to refer to Moisture
    private static boolean isWaterNearby(WorldView world, BlockPos pos) {
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
        for (ColouredSlimeBlocks slimeblock : ColouredSlimeBlocks.colouredSlimeBlocksMap.values())
            BLEACHABLE_BLOCKS.put(slimeblock, Blocks.SLIME_BLOCK.getDefaultState());
        EFFECTIVE_BLOCKS = ImmutableSet.of();

    }

    public static MiningToolItem BLEACHING_BRUSH = new BleachingBrush(0, -1.0f,
            ToolMaterialSlimeball.INSTANCE, EFFECTIVE_BLOCKS, new Item.Settings().group(Slimeology.SLIMEOLOGY));

}

