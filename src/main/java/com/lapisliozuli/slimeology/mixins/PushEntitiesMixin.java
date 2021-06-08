package com.lapisliozuli.slimeology.mixins;

import com.lapisliozuli.slimeology.blocks.ColouredSlimeBlocks;
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
import net.minecraft.util.math.*;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Iterator;
import java.util.List;

@Mixin(PistonBlockEntity.class)
public class PushEntitiesMixin extends BlockEntity {
    // Gives the launching behaviour to ColouredSlimeBlocks when used to push Entities via a Piston.
    public PushEntitiesMixin(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Shadow
    private static Box offsetHeadBox(BlockPos pos, Box box, PistonBlockEntity blockEntity) {
        return null;
    }

    @Shadow
    private static double getIntersectionSize(Box box, Direction direction, Box box2) {
        return 0;
    }

    @Shadow
    private static void moveEntity(Direction direction, Entity entity, double d, Direction direction2) {
    }

    @Shadow
    private static void push(BlockPos pos, Entity entity, Direction direction, double amount) {
    }

//    @Shadow
//    private BlockState getHeadBlockState() {
//        return null;
//    }

    /**
         * @author: Lapis Liozuli
         * @reason: Injecting to a target other than HEAD or TAIL is hard.
         */
    @Overwrite
    private static void pushEntities(World world, BlockPos pos, float f, PistonBlockEntity blockEntity) {
        Direction direction = blockEntity.getMovementDirection();

        // Access private field using internal method.
        BlockState pushedBlock = blockEntity.getPushedBlock();
        // Access private fields using PistonBlockAccessor.
        float progress = ((PistonBlockAccessor) blockEntity).getPistonProgress();
        boolean extending = ((PistonBlockAccessor) blockEntity).getExtending();
        boolean source = ((PistonBlockAccessor) blockEntity).getSource();


        double d = (double) (f - progress);
        VoxelShape voxelShape = ((PistonGetHeadBlockStateInvoker) blockEntity).invokeGetHeadBlockState().getCollisionShape(world, pos);
        if (!voxelShape.isEmpty()) {
            Box box = offsetHeadBox(pos, voxelShape.getBoundingBox(), blockEntity);
            List<Entity> list = world.getOtherEntities((Entity) null, Boxes.stretch(box, direction, d).union(box));
            if (!list.isEmpty()) {
                List<Box> list2 = voxelShape.getBoundingBoxes();
                boolean bl = pushedBlock.isOf(Blocks.SLIME_BLOCK)
                        | ColouredSlimeBlocks.colouredSlimeBlocksMap.containsValue(pushedBlock.getBlock());
                Iterator var12 = list.iterator();

                while (true) {
                    Entity entity;
                    while (true) {
                        do {
                            if (!var12.hasNext()) {
                                return;
                            }

                            entity = (Entity) var12.next();
                        } while (entity.getPistonBehavior() == PistonBehavior.IGNORE);

                        if (!bl) {
                            break;
                        }

                        if (!(entity instanceof ServerPlayerEntity)) {
                            Vec3d vec3d = entity.getVelocity();
                            double e = vec3d.x;
                            double g = vec3d.y;
                            double h = vec3d.z;
                            switch (direction.getAxis()) {
                                case X:
                                    e = (double) direction.getOffsetX();
                                    break;
                                case Y:
                                    g = (double) direction.getOffsetY();
                                    break;
                                case Z:
                                    h = (double) direction.getOffsetZ();
                            }

                            entity.setVelocity(e, g, h);
                            break;
                        }
                    }

                    double i = 0.0D;
                    Iterator var16 = list2.iterator();

                    while (var16.hasNext()) {
                        Box box2 = (Box) var16.next();
                        Box box3 = Boxes.stretch(offsetHeadBox(pos, box2, blockEntity), direction, d);
                        Box box4 = entity.getBoundingBox();
                        if (box3.intersects(box4)) {
                            i = Math.max(i, getIntersectionSize(box3, direction, box4));
                            if (i >= d) {
                                break;
                            }
                        }
                    }

                    if (!(i <= 0.0D)) {
                        i = Math.min(i, d) + 0.01D;
                        moveEntity(direction, entity, i, direction);
                        if (!extending && source) {
                            push(pos, entity, direction, d);
                        }
                    }
                }
            }
        }
    }
}
//
//    /**
//     * @author: Lapis Liozuli
//     * @reason: Injecting to a target other than HEAD or TAIL is hard.
//     */
//    @Overwrite
//    // Replace with Inject eventually.
//    public void pushEntities(float nextProgress) {
//        Direction direction = this.getMovementDirection();
//        double d = (double)(nextProgress - this.progress);
//        VoxelShape voxelShape = this.getHeadBlockState().getCollisionShape(this.world, this.getPos());
//        if (!voxelShape.isEmpty()) {
//            Box box = this.offsetHeadBox(voxelShape.getBoundingBox());
//            List<Entity> list = this.world.getOtherEntities((Entity)null, Boxes.stretch(box, direction, d).union(box));
//            if (!list.isEmpty()) {
//                List<Box> list2 = voxelShape.getBoundingBoxes();
//                // Check for both Slime Block and CSB
//                boolean bl = this.pushedBlock.isOf(Blocks.SLIME_BLOCK)
//                        | ColouredSlimeBlocks.colouredSlimeBlocksMap.containsValue(this.pushedBlock.getBlock());
//                Iterator var10 = list.iterator();
//
//                while(true) {
//                    Entity entity;
//                    do {
//                        if (!var10.hasNext()) {
//                            return;
//                        }
//
//                        entity = (Entity)var10.next();
//                    } while(entity.getPistonBehavior() == PistonBehavior.IGNORE);
//
//                    if (bl) {
//                        Vec3d vec3d = entity.getVelocity();
//                        double e = vec3d.x;
//                        double f = vec3d.y;
//                        double g = vec3d.z;
//                        switch(direction.getAxis()) {
//                            case X:
//                                e = (double)direction.getOffsetX();
//                                break;
//                            case Y:
//                                f = (double)direction.getOffsetY();
//                                break;
//                            case Z:
//                                g = (double)direction.getOffsetZ();
//                        }
//
//                        entity.setVelocity(e, f, g);
//                        if (entity instanceof ServerPlayerEntity) {
//                            ((ServerPlayerEntity)entity).networkHandler.sendPacket(new EntityVelocityUpdateS2CPacket(entity));
//                        }
//                    }
//
//                    double h = 0.0D;
//                    Iterator var14 = list2.iterator();
//
//                    while(var14.hasNext()) {
//                        Box box2 = (Box)var14.next();
//                        Box box3 = Boxes.stretch(this.offsetHeadBox(box2), direction, d);
//                        Box box4 = entity.getBoundingBox();
//                        if (box3.intersects(box4)) {
//                            h = Math.max(h, getIntersectionSize(box3, direction, box4));
//                            if (h >= d) {
//                                break;
//                            }
//                        }
//                    }
//
//                    if (h > 0.0D) {
//                        h = Math.min(h, d) + 0.01D;
//                        method_23672(direction, entity, h, direction);
//                        if (!this.extending && this.source) {
//                            this.push(entity, direction, d);
//                        }
//                    }
//                }
//            }
//        }
//    }
//}