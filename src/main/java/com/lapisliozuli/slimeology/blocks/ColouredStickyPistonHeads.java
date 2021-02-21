package com.lapisliozuli.slimeology.blocks;

import com.lapisliozuli.slimeology.Slimeology;
import com.lapisliozuli.slimeology.registry.RegisterBlocks;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.enums.PistonType;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ColouredStickyPistonHeads extends PistonHeadBlock {
    public ColouredStickyPistonHeads() {
        super(FabricBlockSettings.copyOf(Blocks.PISTON_HEAD));
    }
//    public static final ColouredStickyPistonHeads STICKY_PISTON_HEAD_DEBUG = new ColouredStickyPistonHeads();
    public static final ColouredStickyPistonHeads STICKY_PISTON_HEAD_WHITE = new ColouredStickyPistonHeads(),
            STICKY_PISTON_HEAD_ORANGE = new ColouredStickyPistonHeads(),
            STICKY_PISTON_HEAD_MAGENTA = new ColouredStickyPistonHeads(),
            STICKY_PISTON_HEAD_LIGHT_BLUE = new ColouredStickyPistonHeads(),
            STICKY_PISTON_HEAD_YELLOW = new ColouredStickyPistonHeads(),
            STICKY_PISTON_HEAD_LIME = new ColouredStickyPistonHeads(),
            STICKY_PISTON_HEAD_PINK = new ColouredStickyPistonHeads(),
            STICKY_PISTON_HEAD_GRAY = new ColouredStickyPistonHeads(),
            STICKY_PISTON_HEAD_LIGHT_GRAY = new ColouredStickyPistonHeads(),
            STICKY_PISTON_HEAD_CYAN = new ColouredStickyPistonHeads(),
            STICKY_PISTON_HEAD_PURPLE = new ColouredStickyPistonHeads(),
            STICKY_PISTON_HEAD_BLUE = new ColouredStickyPistonHeads(),
            STICKY_PISTON_HEAD_BROWN = new ColouredStickyPistonHeads(),
            STICKY_PISTON_HEAD_GREEN = new ColouredStickyPistonHeads(),
            STICKY_PISTON_HEAD_RED = new ColouredStickyPistonHeads(),
            STICKY_PISTON_HEAD_BLACK = new ColouredStickyPistonHeads();

    public static Map<String, ColouredStickyPistonHeads> imperativeCSPHeads() {
        final Map<String, ColouredStickyPistonHeads> CSPHeadsMap = new HashMap<>();
        CSPHeadsMap.put("piston_white_head", STICKY_PISTON_HEAD_WHITE);
        CSPHeadsMap.put("piston_orange_head", STICKY_PISTON_HEAD_ORANGE);
        CSPHeadsMap.put("piston_magenta_head", STICKY_PISTON_HEAD_MAGENTA);
        CSPHeadsMap.put("piston_light_blue_head", STICKY_PISTON_HEAD_LIGHT_BLUE);
        CSPHeadsMap.put("piston_yellow_head", STICKY_PISTON_HEAD_YELLOW);
        CSPHeadsMap.put("piston_lime_head", STICKY_PISTON_HEAD_LIME);
        CSPHeadsMap.put("piston_pink_head", STICKY_PISTON_HEAD_PINK);
        CSPHeadsMap.put("piston_gray_head", STICKY_PISTON_HEAD_GRAY);
        CSPHeadsMap.put("piston_light_gray_head", STICKY_PISTON_HEAD_LIGHT_GRAY);
        CSPHeadsMap.put("piston_cyan_head", STICKY_PISTON_HEAD_CYAN);
        CSPHeadsMap.put("piston_purple_head", STICKY_PISTON_HEAD_PURPLE);
        CSPHeadsMap.put("piston_blue_head", STICKY_PISTON_HEAD_BLUE);
        CSPHeadsMap.put("piston_brown_head", STICKY_PISTON_HEAD_BROWN);
        CSPHeadsMap.put("piston_green_head", STICKY_PISTON_HEAD_GREEN);
        CSPHeadsMap.put("piston_red_head", STICKY_PISTON_HEAD_RED);
        CSPHeadsMap.put("piston_black_head", STICKY_PISTON_HEAD_BLACK);
        return Collections.unmodifiableMap(CSPHeadsMap);
    }
    public static final Map<String, ColouredStickyPistonHeads> CSPHeadsMap = imperativeCSPHeads();

    public static final EnumProperty<PistonType> TYPE;
    public static final BooleanProperty SHORT;
    protected static final VoxelShape EAST_HEAD_SHAPE;
    protected static final VoxelShape WEST_HEAD_SHAPE;
    protected static final VoxelShape SOUTH_HEAD_SHAPE;
    protected static final VoxelShape NORTH_HEAD_SHAPE;
    protected static final VoxelShape UP_HEAD_SHAPE;
    protected static final VoxelShape DOWN_HEAD_SHAPE;
    protected static final VoxelShape UP_ARM_SHAPE;
    protected static final VoxelShape DOWN_ARM_SHAPE;
    protected static final VoxelShape SOUTH_ARM_SHAPE;
    protected static final VoxelShape NORTH_ARM_SHAPE;
    protected static final VoxelShape EAST_ARM_SHAPE;
    protected static final VoxelShape WEST_ARM_SHAPE;
    protected static final VoxelShape SHORT_UP_ARM_SHAPE;
    protected static final VoxelShape SHORT_DOWN_ARM_SHAPE;
    protected static final VoxelShape SHORT_SOUTH_ARM_SHAPE;
    protected static final VoxelShape SHORT_NORTH_ARM_SHAPE;
    protected static final VoxelShape SHORT_EAST_ARM_SHAPE;
    protected static final VoxelShape SHORT_WEST_ARM_SHAPE;


    private VoxelShape getHeadShape(BlockState state) {
        switch((Direction)state.get(FACING)) {
            case DOWN:
            default:
                return DOWN_HEAD_SHAPE;
            case UP:
                return UP_HEAD_SHAPE;
            case NORTH:
                return NORTH_HEAD_SHAPE;
            case SOUTH:
                return SOUTH_HEAD_SHAPE;
            case WEST:
                return WEST_HEAD_SHAPE;
            case EAST:
                return EAST_HEAD_SHAPE;
        }
    }

    public boolean hasSidedTransparency(BlockState state) {
        return true;
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.union(this.getHeadShape(state), this.getArmShape(state));
    }

    private VoxelShape getArmShape(BlockState state) {
        boolean bl = (Boolean)state.get(SHORT);
        switch((Direction)state.get(FACING)) {
            case DOWN:
            default:
                return bl ? SHORT_DOWN_ARM_SHAPE : DOWN_ARM_SHAPE;
            case UP:
                return bl ? SHORT_UP_ARM_SHAPE : UP_ARM_SHAPE;
            case NORTH:
                return bl ? SHORT_NORTH_ARM_SHAPE : NORTH_ARM_SHAPE;
            case SOUTH:
                return bl ? SHORT_SOUTH_ARM_SHAPE : SOUTH_ARM_SHAPE;
            case WEST:
                return bl ? SHORT_WEST_ARM_SHAPE : WEST_ARM_SHAPE;
            case EAST:
                return bl ? SHORT_EAST_ARM_SHAPE : EAST_ARM_SHAPE;
        }
    }

    private boolean method_26980(BlockState blockState, BlockState blockState2) {
        Block block = RegisterBlocks.CSPLinkBlockToHeadMap.inverse().get(this);
        return blockState2.isOf(block) && (Boolean)blockState2.get(ColouredStickyPistons.EXTENDED) && blockState2.get(FACING) == blockState.get(FACING);
    }

    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!world.isClient && player.abilities.creativeMode) {
            BlockPos blockPos = pos.offset(((Direction)state.get(FACING)).getOpposite());
            if (this.method_26980(state, world.getBlockState(blockPos))) {
                world.breakBlock(blockPos, false);
            }
        }

        super.onBreak(world, pos, state, player);
    }


    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState newState, WorldAccess world, BlockPos pos, BlockPos posFrom) {
        return direction.getOpposite() == state.get(FACING) && !state.canPlaceAt(world, pos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state, direction, newState, world, pos, posFrom);
    }

    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos.offset(((Direction)state.get(FACING)).getOpposite()));
        return this.method_26980(state, blockState) || blockState.isOf(Blocks.MOVING_PISTON) && blockState.get(FACING) == state.get(FACING);
    }

    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        if (state.canPlaceAt(world, pos)) {
            BlockPos blockPos = pos.offset(((Direction)state.get(FACING)).getOpposite());
            world.getBlockState(blockPos).neighborUpdate(world, blockPos, block, fromPos, false);
        }

    }

    @Environment(EnvType.CLIENT)
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        return new ItemStack(this.asBlock());
    }

    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return (BlockState)state.with(FACING, rotation.rotate((Direction)state.get(FACING)));
    }

    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation((Direction)state.get(FACING)));
    }


    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }

    static {
        TYPE = Properties.PISTON_TYPE;
        SHORT = Properties.SHORT;
        EAST_HEAD_SHAPE = Block.createCuboidShape(12.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
        WEST_HEAD_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 4.0D, 16.0D, 16.0D);
        SOUTH_HEAD_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 12.0D, 16.0D, 16.0D, 16.0D);
        NORTH_HEAD_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 4.0D);
        UP_HEAD_SHAPE = Block.createCuboidShape(0.0D, 12.0D, 0.0D, 16.0D, 16.0D, 16.0D);
        DOWN_HEAD_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D);
        UP_ARM_SHAPE = Block.createCuboidShape(6.0D, -4.0D, 6.0D, 10.0D, 12.0D, 10.0D);
        DOWN_ARM_SHAPE = Block.createCuboidShape(6.0D, 4.0D, 6.0D, 10.0D, 20.0D, 10.0D);
        SOUTH_ARM_SHAPE = Block.createCuboidShape(6.0D, 6.0D, -4.0D, 10.0D, 10.0D, 12.0D);
        NORTH_ARM_SHAPE = Block.createCuboidShape(6.0D, 6.0D, 4.0D, 10.0D, 10.0D, 20.0D);
        EAST_ARM_SHAPE = Block.createCuboidShape(-4.0D, 6.0D, 6.0D, 12.0D, 10.0D, 10.0D);
        WEST_ARM_SHAPE = Block.createCuboidShape(4.0D, 6.0D, 6.0D, 20.0D, 10.0D, 10.0D);
        SHORT_UP_ARM_SHAPE = Block.createCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 12.0D, 10.0D);
        SHORT_DOWN_ARM_SHAPE = Block.createCuboidShape(6.0D, 4.0D, 6.0D, 10.0D, 16.0D, 10.0D);
        SHORT_SOUTH_ARM_SHAPE = Block.createCuboidShape(6.0D, 6.0D, 0.0D, 10.0D, 10.0D, 12.0D);
        SHORT_NORTH_ARM_SHAPE = Block.createCuboidShape(6.0D, 6.0D, 4.0D, 10.0D, 10.0D, 16.0D);
        SHORT_EAST_ARM_SHAPE = Block.createCuboidShape(0.0D, 6.0D, 6.0D, 12.0D, 10.0D, 10.0D);
        SHORT_WEST_ARM_SHAPE = Block.createCuboidShape(4.0D, 6.0D, 6.0D, 16.0D, 10.0D, 10.0D);
    }


    public static void registerColouredStickyPistonHeads() {
        CSPHeadsMap.forEach((k, v) ->
                Registry.register(Registry.BLOCK, new Identifier(Slimeology.MOD_ID, k), v));
    }
}
