package com.lapisliozuli.slimeology.mixins;

import com.lapisliozuli.slimeology.blocks.ColouredSlimeBlocks;
import com.lapisliozuli.slimeology.registry.RegisterBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.piston.PistonHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PistonHandler.class)
public class PistonHandlerMixin {
    // Gives sticky behaviour to the ColouredSlimeBlocks and Slimy variants.
    @Inject(at = @At("HEAD"), method = "isBlockSticky", cancellable = true)
    private static void slimeology$isBlockStickyMixin(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        // Use RegisterItems to keep a list of all sticky blocks at some point.
        // Both CSBs and Slimy variants will pull blocks along.
        if (ColouredSlimeBlocks.colouredSlimeBlocksMap.containsValue(state.getBlock())
                || RegisterBlocks.blocksList.contains(state.getBlock())) {
            cir.setReturnValue(true);
        }
    }

    // Gives the ColouredSlimeBlocks non-stickiness to other types of sticky blocks.
    // Kind of janky but it's following Mojang's logic.
    @Inject(at = @At("HEAD"), method = "isAdjacentBlockStuck", cancellable = true)
    private static void slimeology$isAdjacentBlockStuckMixin(BlockState state, BlockState adjacentState, CallbackInfoReturnable<Boolean> cir) {
        boolean isStateHoney = state.getBlock() == Blocks.HONEY_BLOCK;
        boolean isAdjacentStateHoney = adjacentState.getBlock() == Blocks.HONEY_BLOCK;
        boolean isStateCsb = ColouredSlimeBlocks.colouredSlimeBlocksMap.containsValue(state.getBlock());
        boolean isAdjacentStateCsb = ColouredSlimeBlocks.colouredSlimeBlocksMap.containsValue(adjacentState.getBlock());
        boolean isStateSlimyVariant = RegisterBlocks.blocksList.contains(state.getBlock());
        boolean isAdjacentStateSlimyVariant = RegisterBlocks.blocksList.contains(adjacentState.getBlock());

        // Rainbow Slime Block will pull any sticky block along.
        if (state.getBlock() == ColouredSlimeBlocks.SLIME_BLOCK_RAINBOW
                || adjacentState.getBlock() == ColouredSlimeBlocks.SLIME_BLOCK_RAINBOW) {
            cir.setReturnValue(true);
        }
        // CSBs will not stick to Slime Blocks, Honey Blocks or Slimeology sticky blocks.
        else if ((isStateCsb & (isAdjacentStateHoney || adjacentState.getBlock() == Blocks.SLIME_BLOCK || isAdjacentStateSlimyVariant))
                // First condition and second condition are the same except state and adjacentState are swapped.
                || ((isStateHoney || state.getBlock() == Blocks.SLIME_BLOCK || isStateSlimyVariant) & isAdjacentStateCsb)) {
            cir.setReturnValue(false);
        }
        // Slimeology sticky blocks will not stick to Honey Blocks. Note: Can stick to Slime Blocks.
        // Check for the combo of a Sticky variant and a Honey Block.
        else if ((isStateSlimyVariant && isAdjacentStateHoney)
                || (isStateHoney && isAdjacentStateSlimyVariant)) {
            cir.setReturnValue(false);
        }
        // CSBs of different colours will not stick to each other.
        // Both blocks must be different. (Technically this check applies to previous checks too, but better to avoid nested ifs.)
        else if ((state != adjacentState)
                && (isStateCsb && isAdjacentStateCsb)) {
            cir.setReturnValue(false);
        }
    }
}

