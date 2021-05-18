package com.lapisliozuli.slimeology.mixins;

import com.lapisliozuli.slimeology.blocks.ColouredSlimeBlocks;
import com.lapisliozuli.slimeology.registry.RegisterBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.piston.PistonHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PistonHandler.class)
public class PistonHandlerInjectMixin {
    // Gives the ColouredSlimeBlocks their sticky behaviour, and non-stickiness to other types of sticky blocks respectively.
    @Inject(at = @At("HEAD"), method = "isBlockSticky", cancellable = true)
    private static void IsBlockStickyMixin(Block block, CallbackInfoReturnable callbackInfoReturnable) {
        // Use RegisterItems to keep a list of all sticky blocks at some point.
        // Both CSBs and Slimy variants will pull blocks along.
        if ((ColouredSlimeBlocks.colouredSlimeBlocksMap.containsValue(block))
                || (RegisterBlocks.blocksList.contains(block))) {
            callbackInfoReturnable.setReturnValue(true);
        }
    }

    // Kind of janky but it's following Mojang's logic0
    @Inject(at = @At("HEAD"), method = "isAdjacentBlockStuck", cancellable = true)
    private static void IsAdjacentBlockStuckMixin(Block arg1, Block arg2, CallbackInfoReturnable callbackInfoReturnable) {
        // Rainbow Slime Block will pull any Slimeology sticky block along.
        if ((arg1 == ColouredSlimeBlocks.SLIME_BLOCK_RAINBOW) | (arg2 == ColouredSlimeBlocks.SLIME_BLOCK_RAINBOW)) {
            callbackInfoReturnable.setReturnValue(true);
        }
        // CSBs will not stick to Slime Blocks, Honey Blocks or Slimeology sticky blocks.
        else if (((ColouredSlimeBlocks.colouredSlimeBlocksMap.containsValue(arg1)) &
                (arg2 == Blocks.HONEY_BLOCK | arg2 == Blocks.SLIME_BLOCK | RegisterBlocks.blocksList.contains(arg2)))
                | ((arg1 == Blocks.HONEY_BLOCK | arg1 == Blocks.SLIME_BLOCK | RegisterBlocks.blocksList.contains(arg1)) &
                ColouredSlimeBlocks.colouredSlimeBlocksMap.containsValue(arg2))) {
            callbackInfoReturnable.setReturnValue(false);
        }
        // Slimeology sticky blocks will not stick to Honey Blocks. Note: Can stick to Slime Blocks.
        else if ((RegisterBlocks.blocksList.contains(arg1) && arg2 == Blocks.HONEY_BLOCK) |
                (arg1 == Blocks.HONEY_BLOCK && RegisterBlocks.blocksList.contains(arg2))) {
            callbackInfoReturnable.setReturnValue(false);
        }
        // CSBs of different colours will not stick to each other.
        else if ((arg1 != arg2) & (ColouredSlimeBlocks.colouredSlimeBlocksMap.containsValue(arg1))
                & (ColouredSlimeBlocks.colouredSlimeBlocksMap.containsValue(arg2))) {
            callbackInfoReturnable.setReturnValue(false);
        }
    }
}

