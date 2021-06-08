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
public class PistonHandlerInjectMixin {
    // Gives the ColouredSlimeBlocks their sticky behaviour.
    @Inject(at = @At("HEAD"), method = "isBlockSticky", cancellable = true)
    private static void IsBlockStickyMixin(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        // Use RegisterItems to keep a list of all sticky blocks at some point.
        // Both CSBs and Slimy variants will pull blocks along.
        if ((ColouredSlimeBlocks.colouredSlimeBlocksMap.containsValue(state.getBlock()))
                || (RegisterBlocks.blocksList.contains(state.getBlock()))) {
            cir.setReturnValue(true);
        }
    }

    // Gives the ColouredSlimeBlocks non-stickiness to other types of sticky blocks.
    // Kind of janky but it's following Mojang's logic.
    @Inject(at = @At("HEAD"), method = "isAdjacentBlockStuck", cancellable = true)
    private static void IsAdjacentBlockStuckMixin(BlockState state, BlockState adjacentState, CallbackInfoReturnable<Boolean> cir) {
        // Rainbow Slime Block will pull any Slimeology sticky block along.
        if ((state.getBlock() == ColouredSlimeBlocks.SLIME_BLOCK_RAINBOW) | adjacentState.getBlock() == ColouredSlimeBlocks.SLIME_BLOCK_RAINBOW) {
            cir.setReturnValue(true);
        }
        // CSBs will not stick to Slime Blocks, Honey Blocks or Slimeology sticky blocks.
        else if (((ColouredSlimeBlocks.colouredSlimeBlocksMap.containsValue(state.getBlock()))
                & (adjacentState.getBlock() == Blocks.HONEY_BLOCK | adjacentState.getBlock() == Blocks.SLIME_BLOCK | RegisterBlocks.blocksList.contains(adjacentState.getBlock())))
                // First condition and second condition are the same except state and adjacentState are swapped.
                | ((state.getBlock() == Blocks.HONEY_BLOCK | state.getBlock() == Blocks.SLIME_BLOCK | RegisterBlocks.blocksList.contains(state.getBlock()))
                & ColouredSlimeBlocks.colouredSlimeBlocksMap.containsValue(adjacentState.getBlock()))) {
            cir.setReturnValue(false);
        }
        // Slimeology sticky blocks will not stick to Honey Blocks. Note: Can stick to Slime Blocks.
        else if ((RegisterBlocks.blocksList.contains(state.getBlock()) && adjacentState.getBlock() == Blocks.HONEY_BLOCK) |
                (state.getBlock() == Blocks.HONEY_BLOCK) && (RegisterBlocks.blocksList.contains(adjacentState.getBlock()))) {
            cir.setReturnValue(false);
        }
        // CSBs of different colours will not stick to each other.
        else if ((state != adjacentState) & (ColouredSlimeBlocks.colouredSlimeBlocksMap.containsValue(state.getBlock()))
                & (ColouredSlimeBlocks.colouredSlimeBlocksMap.containsValue(adjacentState.getBlock()))) {
            cir.setReturnValue(false);
        }
    }
}

