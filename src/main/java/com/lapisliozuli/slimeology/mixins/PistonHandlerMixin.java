package com.lapisliozuli.slimeology.mixins;

import com.lapisliozuli.slimeology.blocks.ColouredSlimeBlocks;
import com.lapisliozuli.slimeology.registry.RegisterBlocks;
import net.minecraft.block.Block;
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
    private static void slimeology$isBlockStickyMixin(Block block, CallbackInfoReturnable<Boolean> cir) {
        // Use RegisterItems to keep a list of all sticky blocks at some point.
        // Both CSBs and Slimy variants will pull blocks along.
        if (ColouredSlimeBlocks.colouredSlimeBlocksMap.containsValue(block)
                || RegisterBlocks.blocksList.contains(block)) {
            cir.setReturnValue(true);
        }
    }

    // Gives the ColouredSlimeBlocks non-stickiness to other types of sticky blocks.
    // Kind of janky but it's following Mojang's logic.
    @Inject(at = @At("HEAD"), method = "isAdjacentBlockStuck", cancellable = true)
    private static void slimeology$isAdjacentBlockStuckMixin(Block block, Block adjacentBlock, CallbackInfoReturnable<Boolean> cir) {
        boolean isStateHoney = block == Blocks.HONEY_BLOCK;
        boolean isAdjacentStateHoney = adjacentBlock == Blocks.HONEY_BLOCK;
        boolean isStateCsb = ColouredSlimeBlocks.colouredSlimeBlocksMap.containsValue(block);
        boolean isAdjacentStateCsb = ColouredSlimeBlocks.colouredSlimeBlocksMap.containsValue(adjacentBlock);
        boolean isStateSlimyVariant = RegisterBlocks.blocksList.contains(block);
        boolean isAdjacentStateSlimyVariant = RegisterBlocks.blocksList.contains(adjacentBlock);

        // Rainbow Slime Block will pull any sticky block along.
        if (block == ColouredSlimeBlocks.SLIME_BLOCK_RAINBOW
                || adjacentBlock == ColouredSlimeBlocks.SLIME_BLOCK_RAINBOW) {
            cir.setReturnValue(true);
        }
        // CSBs will not stick to Slime Blocks, Honey Blocks or Slimeology sticky blocks.
        else if ((isStateCsb & (isAdjacentStateHoney || adjacentBlock == Blocks.SLIME_BLOCK || isAdjacentStateSlimyVariant))
                // First condition and second condition are the same except state and adjacentState are swapped.
                || ((isStateHoney || block == Blocks.SLIME_BLOCK || isStateSlimyVariant) & isAdjacentStateCsb)) {
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
        else if ((block != adjacentBlock)
                && (isStateCsb && isAdjacentStateCsb)) {
            cir.setReturnValue(false);
        }
    }
}

