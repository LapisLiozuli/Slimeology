package com.lapisliozuli.slimeology.mixins;

import com.lapisliozuli.slimeology.blocks.ColouredSlimeBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.entity.PistonBlockEntity;
import net.minecraft.util.math.*;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;

@Mixin(PistonBlockEntity.class)
public class PushEntitiesMixin {
    private static boolean isBlockSlimy;
    private static Block pushedBlock;

    @Inject(
            method = "pushEntities",
//            at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z", shift = At.Shift.AFTER),
            at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z", shift = At.Shift.AFTER),
//            at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/entity/Entity;getPistonBehavior()Lnet/minecraft/block/piston/PistonBehavior;", shift = At.Shift.AFTER),
//            locals = LocalCapture.PRINT,
            locals = LocalCapture.CAPTURE_FAILSOFT,
            cancellable = true
    )
    private static void slimeology$changeBoolean(World world, BlockPos pos, float f, PistonBlockEntity blockEntity, CallbackInfo ci,
                                                 Direction direction, double d, VoxelShape voxelShape, Box box, List list, List list2,
                                                 boolean bl) {
        isBlockSlimy = bl;
        pushedBlock = blockEntity.getPushedBlock().getBlock();
//        System.out.println("changeBoolean");
//        System.out.println(isBlockSlimy);
//        System.out.println(pushedBlock);
    }

    @ModifyVariable(
            print = true,
            method = "pushEntities",
//            slice = ,
            at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z", shift = At.Shift.AFTER),
//            at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z"),
            index = 11,
            ordinal = 0
//            name = "bl"
    )
    private static boolean slimeology$pushEntitiesMixin(boolean bl) {
//    private static Boolean slimeology$pushEntitiesMixin(boolean bl) {
//    private static Boolean slimeology$pushEntitiesMixin(World world, BlockPos pos, float f, PistonBlockEntity blockEntity, CallbackInfo ci,
//                                                        Direction direction, double d, VoxelShape voxelShape, Box box, List list, List list2,
//                                                        boolean bl) {
        System.out.println("pushEntities");
        System.out.println(isBlockSlimy);
        System.out.println("+++++++");
        System.out.println(pushedBlock);
        return isBlockSlimy || ColouredSlimeBlocks.colouredSlimeBlocksMap.containsValue(pushedBlock);
    }
}
