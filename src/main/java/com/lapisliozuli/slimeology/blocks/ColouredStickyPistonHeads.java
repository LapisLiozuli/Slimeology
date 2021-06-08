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
import net.minecraft.state.StateManager;
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

import java.util.Arrays;
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
    public static final float field_31377 = 4.0F;
    protected static final VoxelShape EAST_HEAD_SHAPE;
    protected static final VoxelShape WEST_HEAD_SHAPE;
    protected static final VoxelShape SOUTH_HEAD_SHAPE;
    protected static final VoxelShape NORTH_HEAD_SHAPE;
    protected static final VoxelShape UP_HEAD_SHAPE;
    protected static final VoxelShape DOWN_HEAD_SHAPE;
    protected static final float field_31378 = 2.0F;
    protected static final float field_31379 = 6.0F;
    protected static final float field_31380 = 10.0F;
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
    private static final VoxelShape[] SHORT_HEAD_SHAPES;
    private static final VoxelShape[] HEAD_SHAPES;

    private static VoxelShape[] getHeadShapes(boolean shortHead) {
        return (VoxelShape[]) Arrays.stream(Direction.values()).map((direction) -> {
            return getHeadShape(direction, shortHead);
        }).toArray((i) -> {
            return new VoxelShape[i];
        });
    }

    private static VoxelShape getHeadShape(Direction direction, boolean shortHead) {
        switch(direction) {
            case DOWN:
            default:
                return VoxelShapes.union(DOWN_HEAD_SHAPE, shortHead ? SHORT_DOWN_ARM_SHAPE : DOWN_ARM_SHAPE);
            case UP:
                return VoxelShapes.union(UP_HEAD_SHAPE, shortHead ? SHORT_UP_ARM_SHAPE : UP_ARM_SHAPE);
            case NORTH:
                return VoxelShapes.union(NORTH_HEAD_SHAPE, shortHead ? SHORT_NORTH_ARM_SHAPE : NORTH_ARM_SHAPE);
            case SOUTH:
                return VoxelShapes.union(SOUTH_HEAD_SHAPE, shortHead ? SHORT_SOUTH_ARM_SHAPE : SOUTH_ARM_SHAPE);
            case WEST:
                return VoxelShapes.union(WEST_HEAD_SHAPE, shortHead ? SHORT_WEST_ARM_SHAPE : WEST_ARM_SHAPE);
            case EAST:
                return VoxelShapes.union(EAST_HEAD_SHAPE, shortHead ? SHORT_EAST_ARM_SHAPE : EAST_ARM_SHAPE);
        }
    }

//    public PistonHeadBlock(AbstractBlock.Settings settings) {
//        super(settings);
//        this.setDefaultState((BlockState)((BlockState)((BlockState)((BlockState)this.stateManager.getDefaultState()).with(FACING, Direction.NORTH)).with(TYPE, PistonType.DEFAULT)).with(SHORT, false));
//    }

    public boolean hasSidedTransparency(BlockState state) {
        return true;
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return ((Boolean)state.get(SHORT) ? SHORT_HEAD_SHAPES : HEAD_SHAPES)[((Direction)state.get(FACING)).ordinal()];
    }

//    private boolean isAttached(BlockState headState, BlockState pistonState) {
//        Block block = headState.get(TYPE) == PistonType.DEFAULT ? Blocks.PISTON : Blocks.STICKY_PISTON;
//        return pistonState.isOf(block) && (Boolean)pistonState.get(PistonBlock.EXTENDED) && pistonState.get(FACING) == headState.get(FACING);
//    }

    private boolean isAttached(BlockState headState, BlockState pistonState) {
        // CSPs are always sticky.
        Block block = RegisterBlocks.CSPLinkBlockToHeadMap.inverse().get(this);
        return pistonState.isOf(block) && (Boolean)pistonState.get(ColouredStickyPistons.EXTENDED) && pistonState.get(FACING) == headState.get(FACING);
    }

    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!world.isClient && player.getAbilities().creativeMode) {
            BlockPos blockPos = pos.offset(((Direction)state.get(FACING)).getOpposite());
            if (this.isAttached(state, world.getBlockState(blockPos))) {
                world.breakBlock(blockPos, false);
            }
        }

        super.onBreak(world, pos, state, player);
    }

    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!state.isOf(newState.getBlock())) {
            super.onStateReplaced(state, world, pos, newState, moved);
            BlockPos blockPos = pos.offset(((Direction)state.get(FACING)).getOpposite());
            if (this.isAttached(state, world.getBlockState(blockPos))) {
                world.breakBlock(blockPos, true);
            }

        }
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return direction.getOpposite() == state.get(FACING) && !state.canPlaceAt(world, pos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos.offset(((Direction)state.get(FACING)).getOpposite()));
        return this.isAttached(state, blockState) || blockState.isOf(Blocks.MOVING_PISTON) && blockState.get(FACING) == state.get(FACING);
    }

    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        if (state.canPlaceAt(world, pos)) {
            BlockPos blockPos = pos.offset(((Direction)state.get(FACING)).getOpposite());
            world.getBlockState(blockPos).neighborUpdate(world, blockPos, block, fromPos, false);
        }

    }

    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        return new ItemStack(state.get(TYPE) == PistonType.STICKY ? Blocks.STICKY_PISTON : Blocks.PISTON);
    }

//    @Environment(EnvType.CLIENT)
//    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
//        return new ItemStack(this.asBlock());
//    }

    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return (BlockState)state.with(FACING, rotation.rotate((Direction)state.get(FACING)));
    }

    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation((Direction)state.get(FACING)));
    }

//    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
//        builder.add(FACING, TYPE, SHORT);
//    }

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
        SHORT_HEAD_SHAPES = getHeadShapes(true);
        HEAD_SHAPES = getHeadShapes(false);
    }


    public static void registerColouredStickyPistonHeads() {
        CSPHeadsMap.forEach((k, v) ->
                Registry.register(Registry.BLOCK, new Identifier(Slimeology.MOD_ID, k), v));
    }
}
