package com.lapisliozuli.slimeology.mixins;

import com.lapisliozuli.slimeology.blocks.ColouredSlimeBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.PistonBlockEntity;
import net.minecraft.util.math.*;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;

@Mixin(PistonBlockEntity.class)
public class PushEntitiesMixin {
    private boolean isBlockSlimy;
//    private Block accessedPushedBlock;
    @Shadow
    BlockState pushedBlock;

    @Inject(
            method = "pushEntities",
            at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z", shift = At.Shift.AFTER),
            locals = LocalCapture.CAPTURE_FAILSOFT,
//            locals = LocalCapture.PRINT,
            cancellable = true
    )
    private void slimeology$changeBoolean(float nextProgress, CallbackInfo ci, Direction direction, double d, VoxelShape voxelShape, Box box, List list, List list2, boolean bl) {
        isBlockSlimy = bl;
//        accessedPushedBlock = blockEntity.getPushedBlock().getBlock();
    }

    @ModifyVariable(
            print = true,
            method = "pushEntities",
            at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z", shift = At.Shift.AFTER),
            index = 9,
            ordinal = 0
    )
    private boolean slimeology$pushEntitiesMixin(boolean bl) {
        return isBlockSlimy || ColouredSlimeBlocks.colouredSlimeBlocksMap.containsValue(pushedBlock.getBlock());
    }
}
