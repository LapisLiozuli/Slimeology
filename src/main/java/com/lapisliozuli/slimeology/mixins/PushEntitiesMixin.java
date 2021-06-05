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
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Boxes;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Iterator;
import java.util.List;

@Mixin(PistonBlockEntity.class)
public abstract class PushEntitiesMixin extends BlockEntity {
    // Gives the launching behaviour to ColouredSlimeBlocks when used to push Entities via a Piston.

    @Shadow private BlockState pushedBlock;
    @Shadow private float progress;
    @Shadow private boolean extending;
    @Shadow private boolean source;
    @Shadow private static final ThreadLocal<Direction> field_12205 = ThreadLocal.withInitial(() -> {
        return null;
    });
    @Shadow protected abstract BlockState getHeadBlockState();
    @Shadow protected abstract Box offsetHeadBox(Box box);
    @Shadow public abstract Direction getMovementDirection();
    @Shadow public abstract void push(Entity entity, Direction direction, double amount);

    @Shadow
    public static double getIntersectionSize(Box box, Direction direction, Box box2) {
        switch(direction) {
            case EAST:
                return box.maxX - box2.minX;
            case WEST:
                return box2.maxX - box.minX;
            case UP:
            default:
                return box.maxY - box2.minY;
            case DOWN:
                return box2.maxY - box.minY;
            case SOUTH:
                return box.maxZ - box2.minZ;
            case NORTH:
                return box2.maxZ - box.minZ;
        }
    }
    @Shadow public static void method_23672(Direction direction, Entity entity, double d, Direction direction2) {
        field_12205.set(direction);
        entity.move(MovementType.PISTON, new Vec3d(d * (double)direction2.getOffsetX(), d * (double)direction2.getOffsetY(), d * (double)direction2.getOffsetZ()));
        field_12205.set((Direction)null);
    }


    public PushEntitiesMixin(BlockEntityType<?> type) {
        super(type);
    }

    /**
     * @author: Lapis Liozuli
     * @reason: Injecting to a target other than HEAD or TAIL is hard.
     */
    @Overwrite
    // Replace with Inject eventually.
    public void pushEntities(float nextProgress) {
        Direction direction = this.getMovementDirection();
        double d = (double)(nextProgress - this.progress);
        VoxelShape voxelShape = this.getHeadBlockState().getCollisionShape(this.world, this.getPos());
        if (!voxelShape.isEmpty()) {
            Box box = this.offsetHeadBox(voxelShape.getBoundingBox());
            List<Entity> list = this.world.getOtherEntities((Entity)null, Boxes.stretch(box, direction, d).union(box));
            if (!list.isEmpty()) {
                List<Box> list2 = voxelShape.getBoundingBoxes();
                // Check for both Slime Block and CSB
                boolean bl = this.pushedBlock.isOf(Blocks.SLIME_BLOCK)
                        | ColouredSlimeBlocks.colouredSlimeBlocksMap.containsValue(this.pushedBlock.getBlock());
                Iterator var10 = list.iterator();

                while(true) {
                    Entity entity;
                    do {
                        if (!var10.hasNext()) {
                            return;
                        }

                        entity = (Entity)var10.next();
                    } while(entity.getPistonBehavior() == PistonBehavior.IGNORE);

                    if (bl) {
                        Vec3d vec3d = entity.getVelocity();
                        double e = vec3d.x;
                        double f = vec3d.y;
                        double g = vec3d.z;
                        switch(direction.getAxis()) {
                            case X:
                                e = (double)direction.getOffsetX();
                                break;
                            case Y:
                                f = (double)direction.getOffsetY();
                                break;
                            case Z:
                                g = (double)direction.getOffsetZ();
                        }

                        entity.setVelocity(e, f, g);
                        if (entity instanceof ServerPlayerEntity) {
                            ((ServerPlayerEntity)entity).networkHandler.sendPacket(new EntityVelocityUpdateS2CPacket(entity));
                        }
                    }

                    double h = 0.0D;
                    Iterator var14 = list2.iterator();

                    while(var14.hasNext()) {
                        Box box2 = (Box)var14.next();
                        Box box3 = Boxes.stretch(this.offsetHeadBox(box2), direction, d);
                        Box box4 = entity.getBoundingBox();
                        if (box3.intersects(box4)) {
                            h = Math.max(h, getIntersectionSize(box3, direction, box4));
                            if (h >= d) {
                                break;
                            }
                        }
                    }

                    if (h > 0.0D) {
                        h = Math.min(h, d) + 0.01D;
                        method_23672(direction, entity, h, direction);
                        if (!this.extending && this.source) {
                            this.push(entity, direction, d);
                        }
                    }
                }
            }
        }
    }
}