package com.lapisliozuli.slimeology.mixins;

import com.lapisliozuli.slimeology.blocks.ColouredStickyPistonHeads;
import com.lapisliozuli.slimeology.blocks.ColouredStickyPistons;
import com.lapisliozuli.slimeology.registry.RegisterBlocks;
import net.minecraft.block.*;
import net.minecraft.block.entity.PistonBlockEntity;
import net.minecraft.block.enums.PistonType;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockModelRenderer;
import net.minecraft.client.render.block.entity.PistonBlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(PistonBlockEntityRenderer.class)
public abstract class PistonBlockEntityRendererMixin {
    // Used to help render the animated textures of ColouredStickyPistons.
    @Shadow
    abstract void renderModel(BlockPos pos, BlockState state,
                              MatrixStack matrices, VertexConsumerProvider vertexConsumers,
                              World world, boolean cull, int overlay);

    /**
     * @author: Lapis Liozuli
     * @reason: Injecting to a target other than HEAD or TAIL is hard.
     */
    @Overwrite
    public void render(PistonBlockEntity pistonBlockEntity,
                       float f,
                       MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider,
                       int i,
                       int j) {
        World world = pistonBlockEntity.getWorld();
        if (world != null) {
            BlockPos blockPos = pistonBlockEntity.getPos().offset(pistonBlockEntity.getMovementDirection().getOpposite());
            BlockState blockState = pistonBlockEntity.getPushedBlock();
            if (!blockState.isAir()) {
                BlockModelRenderer.enableBrightnessCache();
                matrixStack.push();
                matrixStack.translate((double) pistonBlockEntity.getRenderOffsetX(f), (double) pistonBlockEntity.getRenderOffsetY(f), (double) pistonBlockEntity.getRenderOffsetZ(f));
                if (blockState.isOf(Blocks.PISTON_HEAD) && pistonBlockEntity.getProgress(f) <= 4.0F) {
                    blockState = (BlockState) blockState.with(PistonHeadBlock.SHORT, pistonBlockEntity.getProgress(f) <= 0.5F);
                    this.renderModel(blockPos, blockState, matrixStack, vertexConsumerProvider, world, false, j);
                } else if (pistonBlockEntity.isSource() && !pistonBlockEntity.isExtending()) {
                    // vvv This section was inserted to allow for CSP animated textures for retraction.
                    Block pistonBlock = blockState.getBlock();
                    PistonType pistonType = (blockState.isOf(Blocks.STICKY_PISTON)
                            || ColouredStickyPistons.ColouredStickyPistonsMap.containsValue(pistonBlock))
                            ? PistonType.STICKY : PistonType.DEFAULT;
                    if (ColouredStickyPistons.ColouredStickyPistonsMap.containsValue(pistonBlock)) {
                        // Replace all mentions to the default blocks to the new blocks.
                        BlockState blockState2 = (BlockState) ((BlockState) RegisterBlocks.CSPLinkBlockToHeadMap.get(pistonBlock)
                                .getDefaultState().with(ColouredStickyPistonHeads.TYPE, pistonType))
                                .with(ColouredStickyPistonHeads.FACING, (Direction) blockState.get(ColouredStickyPistons.FACING));
                        blockState2 = (BlockState) blockState2.with(ColouredStickyPistonHeads.SHORT, pistonBlockEntity.getProgress(f) >= 0.5F);
                        this.renderModel(blockPos, blockState2, matrixStack, vertexConsumerProvider, world, false, j);
                        BlockPos blockPos2 = blockPos.offset(pistonBlockEntity.getMovementDirection());
                        matrixStack.pop();
                        matrixStack.push();
                        blockState = (BlockState) blockState.with(ColouredStickyPistons.EXTENDED, true);
                        this.renderModel(blockPos2, blockState, matrixStack, vertexConsumerProvider, world, true, j);
                    }
                    // ^^^ Inserted section.
                    else {
                        BlockState blockState2 = (BlockState) ((BlockState) Blocks.PISTON_HEAD.getDefaultState().with(PistonHeadBlock.TYPE, pistonType)).with(PistonHeadBlock.FACING, (Direction) blockState.get(PistonBlock.FACING));
                        blockState2 = (BlockState) blockState2.with(PistonHeadBlock.SHORT, pistonBlockEntity.getProgress(f) >= 0.5F);
                        this.renderModel(blockPos, blockState2, matrixStack, vertexConsumerProvider, world, false, j);
                        BlockPos blockPos2 = blockPos.offset(pistonBlockEntity.getMovementDirection());
                        matrixStack.pop();
                        matrixStack.push();
                        blockState = (BlockState) blockState.with(PistonBlock.EXTENDED, true);
                        this.renderModel(blockPos2, blockState, matrixStack, vertexConsumerProvider, world, true, j);
                    }

                } else {
                    this.renderModel(blockPos, blockState, matrixStack, vertexConsumerProvider, world, false, j);
                }

                matrixStack.pop();
                BlockModelRenderer.disableBrightnessCache();
            }
        }
    }
}


//    /**
//     * @author
//     */
//    @Overwrite
//    // Need to convert this to an Inject at some point.
//    public void render(PistonBlockEntity pistonBlockEntity,
//                       float f,
//                       MatrixStack matrixStack,
//                       VertexConsumerProvider vertexConsumerProvider,
//                       int light,
//                       int j) {
//        World world = pistonBlockEntity.getWorld();
//        if (world != null) {
//            BlockPos blockPos = pistonBlockEntity.getPos().offset(pistonBlockEntity.getMovementDirection().getOpposite());
//            BlockState blockState = pistonBlockEntity.getPushedBlock();
//            if (!blockState.isAir() && pistonBlockEntity.getProgress(f) < 1.0F) {
//                BlockModelRenderer.enableBrightnessCache();
//                matrixStack.push();
//                matrixStack.translate((double)pistonBlockEntity.getRenderOffsetX(f), (double)pistonBlockEntity.getRenderOffsetY(f), (double)pistonBlockEntity.getRenderOffsetZ(f));
//                if (blockState.isOf(Blocks.PISTON_HEAD) && pistonBlockEntity.getProgress(f) <= 4.0F) {
//                    blockState = (BlockState)blockState.with(PistonHeadBlock.SHORT, pistonBlockEntity.getProgress(f) <= 0.5F);
//                    this.renderModel(blockPos, blockState, matrixStack, vertexConsumerProvider, world, false, j);
//                } else if (pistonBlockEntity.isSource() && !pistonBlockEntity.isExtending()) {
//                    // Change this line to allow for CSP animated textures for retraction.
//                    Block pistonBlock = blockState.getBlock();
//                    PistonType pistonType = (blockState.isOf(Blocks.STICKY_PISTON)
//                            || ColouredStickyPistons.ColouredStickyPistonsMap.containsValue(pistonBlock))
//                            ? PistonType.STICKY : PistonType.DEFAULT;
//                    if (ColouredStickyPistons.ColouredStickyPistonsMap.containsValue(pistonBlock)) {
//                        // Replace all mentions to the default blocks to the new blocks.
//                        BlockState blockState2 = (BlockState) ((BlockState) RegisterBlocks.CSPLinkBlockToHeadMap.get(pistonBlock)
//                                .getDefaultState().with(ColouredStickyPistonHeads.TYPE, pistonType))
//                                .with(ColouredStickyPistonHeads.FACING, blockState.get(ColouredStickyPistons.FACING));
//                        blockState2 = (BlockState) blockState2.with(ColouredStickyPistonHeads.SHORT, pistonBlockEntity.getProgress(f) >= 0.5F);
//                        this.renderModel(blockPos, blockState2, matrixStack, vertexConsumerProvider, world, false, j);
//                        BlockPos blockPos2 = blockPos.offset(pistonBlockEntity.getMovementDirection());
//                        matrixStack.pop();
//                        matrixStack.push();
//                        blockState = (BlockState) blockState.with(ColouredStickyPistons.EXTENDED, true);
//                        // End of new variables.
//                        this.renderModel(blockPos2, blockState, matrixStack, vertexConsumerProvider, world, true, j);
//                    }
//                    else {
//                        BlockState blockState2 = (BlockState) ((BlockState) Blocks.PISTON_HEAD.getDefaultState().with(PistonHeadBlock.TYPE, pistonType)).with(PistonHeadBlock.FACING, blockState.get(PistonBlock.FACING));
//                        blockState2 = (BlockState) blockState2.with(PistonHeadBlock.SHORT, pistonBlockEntity.getProgress(f) >= 0.5F);
//                        this.renderModel(blockPos, blockState2, matrixStack, vertexConsumerProvider, world, false, j);
//                        BlockPos blockPos2 = blockPos.offset(pistonBlockEntity.getMovementDirection());
//                        matrixStack.pop();
//                        matrixStack.push();
//                        blockState = (BlockState) blockState.with(PistonBlock.EXTENDED, true);
//                        this.renderModel(blockPos2, blockState, matrixStack, vertexConsumerProvider, world, true, j);
//                    }
//
//
//                } else {
//                    this.renderModel(blockPos, blockState, matrixStack, vertexConsumerProvider, world, false, j);
//                }
//
//                matrixStack.pop();
//                BlockModelRenderer.disableBrightnessCache();
//            }
//        }
//    }
//}
