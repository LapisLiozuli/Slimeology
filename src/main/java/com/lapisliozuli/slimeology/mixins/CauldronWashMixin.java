package com.lapisliozuli.slimeology.mixins;

import com.lapisliozuli.slimeology.blocks.ColouredSlimeBlocks;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CauldronBlock.class)
public abstract class CauldronWashMixin {
    @Final
    @Shadow
    public static IntProperty LEVEL;

    @Shadow
    public abstract void setLevel(World world, BlockPos pos, BlockState state, int level);

    @Inject(method = "onUse", at = @At(value = "HEAD"), cancellable = true)
    private void washColouredSlimeBlock(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable callbackInfoReturnable) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (!itemStack.isEmpty()) {
            Item item = itemStack.getItem();
            int i = (Integer) state.get(LEVEL);
            if (i > 0 && item instanceof BlockItem) {
                Block block = ((BlockItem) item).getBlock();
                if ((ColouredSlimeBlocks.colouredSlimeBlocksMap.containsValue(block)) && !world.isClient()) {
                    ItemStack itemStack5 = new ItemStack(Blocks.SLIME_BLOCK, 1);
                    player.setStackInHand(hand, itemStack5);
                    world.playSound((PlayerEntity)null, pos, SoundEvents.BLOCK_SLIME_BLOCK_STEP, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    this.setLevel(world, pos, state, i - 1);
//                    return ActionResult.SUCCESS;
                }
            }
        }
    }
}

//    @Inject(method = "onUse", at = @At(value = "RETURN", ordinal = 9), cancellable = true)
//    private void washColouredSlimeBlock(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable callbackInfoReturnable) {
//        ItemStack itemStack = player.getStackInHand(hand);
//        Item item = itemStack.getItem();
//        int waterLevel = (Integer) state.get(LEVEL);
//        if (waterLevel > 0 && item instanceof BlockItem) {
//            Block block = ((BlockItem) item).getBlock();
////            if (block instanceof ColouredSlimeBlocks && !world.isClient()) {
//            if ((ColouredSlimeBlocks.colouredSlimeBlocksMap.containsValue(block)) && !world.isClient()) {
//                ItemStack itemStack5 = new ItemStack(Blocks.SLIME_BLOCK, 1);
//                player.setStackInHand(hand, itemStack5);
//                this.setLevel(world, pos, state, waterLevel - 1);
////                    return ActionResult.SUCCESS;
//            }
//        }
//    }
//}
