package com.lapisliozuli.slimeology.mixins;

import com.lapisliozuli.slimeology.blocks.ColouredSlimeBlocks;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.PistonBlockEntity;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MovementType;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.*;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Iterator;
import java.util.List;

@Mixin(PistonBlockEntity.class)
public class PushEntitiesMixin extends BlockEntity {
//    @Shadow private BlockState pushedBlock;

    public PushEntitiesMixin(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

//    @Shadow boolean bl;


    // Gives the launching behaviour to ColouredSlimeBlocks when used to push Entities via a Piston.

//    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z"), method = "pushEntities", require = 0, expect = 0)
//    private static boolean redirectOnSlimeBlockCheck(BlockState blockState, Block block) {
//        // Both return statements achieve the objective.
////        return blockState.isIn(TagRegistry.block(new Identifier("c", "slime_blocks")));
//        return blockState.isOf(Blocks.SLIME_BLOCK) || ColouredSlimeBlocks.colouredSlimeBlocksMap.containsValue(blockState.getBlock());
//    }


//    @ModifyVariable(
//            method = "pushEntities",
//            at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z"),
//            index = 1
//    )
//    private static boolean slimeology$changeBl(boolean bl, BlockState pushedBlock) {
//        return bl || ColouredSlimeBlocks.colouredSlimeBlocksMap.containsValue(pushedBlock.getBlock());
//    }
//    private static boolean slimeology$changeBl(BlockState blockState, Block block) {
//
//        return bl || ColouredSlimeBlocks.colouredSlimeBlocksMap.containsValue(pushedBlock.getBlock());
//    }



     @Inject(
            method = "pushEntities",
            at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z"),
//            at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z", shift = At.Shift.AFTER),
//            at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z", shift = At.Shift.AFTER),
//            at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z", shift = At.Shift.BY, by=2),
//             locals = LocalCapture.PRINT,
             cancellable = true
     )
//     private static void pushEntities(PistonBlockEntity blockEntity, boolean bl, CallbackInfo ci) {
//     private static void pushEntities(World world, BlockPos pos, float f, PistonBlockEntity blockEntity,
//                                      Direction direction, double d, VoxelShape voxelShape, Box box, List<Entity> list, List<Box> list2,
//                                      boolean bl, CallbackInfo ci) {
      private static void pushEntities(World world, BlockPos pos, float f, PistonBlockEntity blockEntity, CallbackInfo ci) {
//      private static void pushEntities(World world, BlockPos pos, float f, PistonBlockEntity blockEntity, CallbackInfo ci, boolean bl) {
        BlockState pushedBlock = blockEntity.getPushedBlock();
         boolean bl = pushedBlock.isOf(Blocks.SLIME_BLOCK);
         bl = bl || ColouredSlimeBlocks.colouredSlimeBlocksMap.containsValue(pushedBlock.getBlock());
         System.out.println("TEST");
     }

//     @Inject(
//            method = "pushEntities",
//            at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z"),
////            at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z", shift = At.Shift.AFTER),
////            at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z", shift = At.Shift.AFTER),
////            at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z", shift = At.Shift.BY, by=2),
//             locals = LocalCapture.PRINT,
//             cancellable = true
//     )
////     private static void pushEntities(PistonBlockEntity blockEntity, boolean bl, CallbackInfo ci) {
////     private static void pushEntities(World world, BlockPos pos, float f, PistonBlockEntity blockEntity,
////                                      Direction direction, double d, VoxelShape voxelShape, Box box, List<Entity> list, List<Box> list2,
////                                      boolean bl, CallbackInfo ci) {
//      private static void pushEntities(World world, BlockPos pos, float f, PistonBlockEntity blockEntity, CallbackInfo ci) {
////      private static void pushEntities(World world, BlockPos pos, float f, PistonBlockEntity blockEntity, CallbackInfo ci, boolean bl) {
//        BlockState pushedBlock = blockEntity.getPushedBlock();
////         boolean bl = pushedBlock.isOf(Blocks.SLIME_BLOCK);
//         bl = bl || ColouredSlimeBlocks.colouredSlimeBlocksMap.containsValue(pushedBlock.getBlock());
//         System.out.println("TEST");
//     }


}

//    public PushEntitiesMixin(BlockEntityType<?> type, BlockPos pos, BlockState state) {
//        super(type, pos, state);
//    }
//
//    @Shadow
//    private static Box offsetHeadBox(BlockPos pos, Box box, PistonBlockEntity blockEntity) {
//        return null;
//    }
//
//    @Shadow
//    private static double getIntersectionSize(Box box, Direction direction, Box box2) {
//        return 0;
//    }
//
//    @Shadow
//    private static void moveEntity(Direction direction, Entity entity, double d, Direction direction2) {
//    }
//
//    @Shadow
//    private static void push(BlockPos pos, Entity entity, Direction direction, double amount) {
//    }
//
////    @Shadow
////    private BlockState getHeadBlockState() {
////        return null;
////    }
//
//    /**
//         * @author: Lapis Liozuli
//         * @reason: Injecting to a target other than HEAD or TAIL is hard.
//         */
//    @Overwrite
//    private static void pushEntities(World world, BlockPos pos, float f, PistonBlockEntity blockEntity) {
//        Direction direction = blockEntity.getMovementDirection();
//
//        // Access private field using internal method.
//        BlockState pushedBlock = blockEntity.getPushedBlock();
//        // Access private fields using PistonBlockAccessor.
//        float progress = ((PistonBlockAccessor) blockEntity).getPistonProgress();
//        boolean extending = ((PistonBlockAccessor) blockEntity).getExtending();
//        boolean source = ((PistonBlockAccessor) blockEntity).getSource();
//
//
//        double d = (double) (f - progress);
//        VoxelShape voxelShape = ((PistonGetHeadBlockStateInvoker) blockEntity).invokeGetHeadBlockState().getCollisionShape(world, pos);
//        if (!voxelShape.isEmpty()) {
//            Box box = offsetHeadBox(pos, voxelShape.getBoundingBox(), blockEntity);
//            List<Entity> list = world.getOtherEntities((Entity) null, Boxes.stretch(box, direction, d).union(box));
//            if (!list.isEmpty()) {
//                List<Box> list2 = voxelShape.getBoundingBoxes();
//                boolean bl = pushedBlock.isOf(Blocks.SLIME_BLOCK)
//                        | ColouredSlimeBlocks.colouredSlimeBlocksMap.containsValue(pushedBlock.getBlock());
//                Iterator var12 = list.iterator();
//
//                while (true) {
//                    Entity entity;
//                    while (true) {
//                        do {
//                            if (!var12.hasNext()) {
//                                return;
//                            }
//
//                            entity = (Entity) var12.next();
//                        } while (entity.getPistonBehavior() == PistonBehavior.IGNORE);
//
//                        if (!bl) {
//                            break;
//                        }
//
//                        if (!(entity instanceof ServerPlayerEntity)) {
//                            Vec3d vec3d = entity.getVelocity();
//                            double e = vec3d.x;
//                            double g = vec3d.y;
//                            double h = vec3d.z;
//                            switch (direction.getAxis()) {
//                                case X:
//                                    e = (double) direction.getOffsetX();
//                                    break;
//                                case Y:
//                                    g = (double) direction.getOffsetY();
//                                    break;
//                                case Z:
//                                    h = (double) direction.getOffsetZ();
//                            }
//
//                            entity.setVelocity(e, g, h);
//                            break;
//                        }
//                    }
//
//                    double i = 0.0D;
//                    Iterator var16 = list2.iterator();
//
//                    while (var16.hasNext()) {
//                        Box box2 = (Box) var16.next();
//                        Box box3 = Boxes.stretch(offsetHeadBox(pos, box2, blockEntity), direction, d);
//                        Box box4 = entity.getBoundingBox();
//                        if (box3.intersects(box4)) {
//                            i = Math.max(i, getIntersectionSize(box3, direction, box4));
//                            if (i >= d) {
//                                break;
//                            }
//                        }
//                    }
//
//                    if (!(i <= 0.0D)) {
//                        i = Math.min(i, d) + 0.01D;
//                        moveEntity(direction, entity, i, direction);
//                        if (!extending && source) {
//                            push(pos, entity, direction, d);
//                        }
//                    }
//                }
//            }
//        }
//    }
//}