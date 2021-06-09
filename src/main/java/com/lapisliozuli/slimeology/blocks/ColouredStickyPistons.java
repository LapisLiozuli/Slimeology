package com.lapisliozuli.slimeology.blocks;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lapisliozuli.slimeology.Slimeology;
import com.lapisliozuli.slimeology.registry.RegisterBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.PistonBlockEntity;
import net.minecraft.block.enums.PistonType;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.block.piston.PistonHandler;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.tag.BlockTags;
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
import net.minecraft.world.event.GameEvent;

import java.util.*;

public class ColouredStickyPistons extends PistonBlock {

    public ColouredStickyPistons() {
        super(true, FabricBlockSettings.copyOf(Blocks.STICKY_PISTON));
    }
//    public static final ColouredStickyPistons STICKY_PISTON_DEBUG = new ColouredStickyPistons();
    public static final ColouredStickyPistons STICKY_PISTON_WHITE = new ColouredStickyPistons(),
            STICKY_PISTON_ORANGE = new ColouredStickyPistons(),
            STICKY_PISTON_MAGENTA = new ColouredStickyPistons(),
            STICKY_PISTON_LIGHT_BLUE = new ColouredStickyPistons(),
            STICKY_PISTON_YELLOW = new ColouredStickyPistons(),
            STICKY_PISTON_LIME = new ColouredStickyPistons(),
            STICKY_PISTON_PINK = new ColouredStickyPistons(),
            STICKY_PISTON_GRAY = new ColouredStickyPistons(),
            STICKY_PISTON_LIGHT_GRAY = new ColouredStickyPistons(),
            STICKY_PISTON_CYAN = new ColouredStickyPistons(),
            STICKY_PISTON_PURPLE = new ColouredStickyPistons(),
            STICKY_PISTON_BLUE = new ColouredStickyPistons(),
            STICKY_PISTON_BROWN = new ColouredStickyPistons(),
            STICKY_PISTON_GREEN = new ColouredStickyPistons(),
            STICKY_PISTON_RED = new ColouredStickyPistons(),
            STICKY_PISTON_BLACK = new ColouredStickyPistons();

    public static Map<String, ColouredStickyPistons> imperative() {
        final Map<String, ColouredStickyPistons> ColouredStickyPistonsMap = new HashMap<>();
//        ColouredStickyPistonsMap.put("piston_debug", STICKY_PISTON_DEBUG);
        ColouredStickyPistonsMap.put("piston_white", STICKY_PISTON_WHITE);
        ColouredStickyPistonsMap.put("piston_orange", STICKY_PISTON_ORANGE);
        ColouredStickyPistonsMap.put("piston_magenta", STICKY_PISTON_MAGENTA);
        ColouredStickyPistonsMap.put("piston_light_blue", STICKY_PISTON_LIGHT_BLUE);
        ColouredStickyPistonsMap.put("piston_yellow", STICKY_PISTON_YELLOW);
        ColouredStickyPistonsMap.put("piston_lime", STICKY_PISTON_LIME);
        ColouredStickyPistonsMap.put("piston_pink", STICKY_PISTON_PINK);
        ColouredStickyPistonsMap.put("piston_gray", STICKY_PISTON_GRAY);
        ColouredStickyPistonsMap.put("piston_light_gray", STICKY_PISTON_LIGHT_GRAY);
        ColouredStickyPistonsMap.put("piston_cyan", STICKY_PISTON_CYAN);
        ColouredStickyPistonsMap.put("piston_purple", STICKY_PISTON_PURPLE);
        ColouredStickyPistonsMap.put("piston_blue", STICKY_PISTON_BLUE);
        ColouredStickyPistonsMap.put("piston_brown", STICKY_PISTON_BROWN);
        ColouredStickyPistonsMap.put("piston_green", STICKY_PISTON_GREEN);
        ColouredStickyPistonsMap.put("piston_red", STICKY_PISTON_RED);
        ColouredStickyPistonsMap.put("piston_black", STICKY_PISTON_BLACK);
        return Collections.unmodifiableMap(ColouredStickyPistonsMap);
    }
    public static final Map<String, ColouredStickyPistons> ColouredStickyPistonsMap = imperative();

    public static final BooleanProperty EXTENDED;
    public static final int field_31373 = 0;
    public static final int field_31374 = 1;
    public static final int field_31375 = 2;
    public static final float field_31376 = 4.0F;
    protected static final VoxelShape EXTENDED_EAST_SHAPE;
    protected static final VoxelShape EXTENDED_WEST_SHAPE;
    protected static final VoxelShape EXTENDED_SOUTH_SHAPE;
    protected static final VoxelShape EXTENDED_NORTH_SHAPE;
    protected static final VoxelShape EXTENDED_UP_SHAPE;
    protected static final VoxelShape EXTENDED_DOWN_SHAPE;
    private final boolean sticky = true;

//    public PistonBlock(boolean sticky, AbstractBlock.Settings settings) {
//        super(settings);
//        this.setDefaultState((BlockState)((BlockState)((BlockState)this.stateManager.getDefaultState()).with(FACING, Direction.NORTH)).with(EXTENDED, false));
//        this.sticky = sticky;
//    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if ((Boolean)state.get(EXTENDED)) {
            switch((Direction)state.get(FACING)) {
                case DOWN:
                    return EXTENDED_DOWN_SHAPE;
                case UP:
                default:
                    return EXTENDED_UP_SHAPE;
                case NORTH:
                    return EXTENDED_NORTH_SHAPE;
                case SOUTH:
                    return EXTENDED_SOUTH_SHAPE;
                case WEST:
                    return EXTENDED_WEST_SHAPE;
                case EAST:
                    return EXTENDED_EAST_SHAPE;
            }
        } else {
            return VoxelShapes.fullCube();
        }
    }

    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        if (!world.isClient) {
            this.tryMove(world, pos, state);
        }

    }

    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        if (!world.isClient) {
            this.tryMove(world, pos, state);
        }

    }

    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        if (!oldState.isOf(state.getBlock())) {
            if (!world.isClient && world.getBlockEntity(pos) == null) {
                this.tryMove(world, pos, state);
            }

        }
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return (BlockState)((BlockState)this.getDefaultState().with(FACING, ctx.getPlayerLookDirection().getOpposite())).with(EXTENDED, false);
    }

    private void tryMove(World world, BlockPos pos, BlockState state) {
        Direction direction = (Direction)state.get(FACING);
        boolean bl = this.shouldExtend(world, pos, direction);
        if (bl && !(Boolean)state.get(EXTENDED)) {
            if ((new PistonHandler(world, pos, direction, true)).calculatePush()) {
                world.addSyncedBlockEvent(pos, this, 0, direction.getId());
            }
        } else if (!bl && (Boolean)state.get(EXTENDED)) {
            BlockPos blockPos = pos.offset((Direction)direction, 2);
            BlockState blockState = world.getBlockState(blockPos);
            int i = 1;
            if (blockState.isOf(Blocks.MOVING_PISTON) && blockState.get(FACING) == direction) {
                BlockEntity blockEntity = world.getBlockEntity(blockPos);
                if (blockEntity instanceof PistonBlockEntity) {
                    PistonBlockEntity pistonBlockEntity = (PistonBlockEntity)blockEntity;
                    if (pistonBlockEntity.isExtending() && (pistonBlockEntity.getProgress(0.0F) < 0.5F || world.getTime() == pistonBlockEntity.getSavedWorldTime() || ((ServerWorld)world).isInBlockTick())) {
                        i = 2;
                    }
                }
            }

            world.addSyncedBlockEvent(pos, this, i, direction.getId());
        }

    }

    private boolean shouldExtend(World world, BlockPos pos, Direction pistonFace) {
        Direction[] var4 = Direction.values();
        int var5 = var4.length;

        int var6;
        for(var6 = 0; var6 < var5; ++var6) {
            Direction direction = var4[var6];
            if (direction != pistonFace && world.isEmittingRedstonePower(pos.offset(direction), direction)) {
                return true;
            }
        }

        if (world.isEmittingRedstonePower(pos, Direction.DOWN)) {
            return true;
        } else {
            BlockPos blockPos = pos.up();
            Direction[] var10 = Direction.values();
            var6 = var10.length;

            for(int var11 = 0; var11 < var6; ++var11) {
                Direction direction2 = var10[var11];
                if (direction2 != Direction.DOWN && world.isEmittingRedstonePower(blockPos.offset(direction2), direction2)) {
                    return true;
                }
            }

            return false;
        }
    }

    public boolean onSyncedBlockEvent(BlockState state, World world, BlockPos pos, int type, int data) {
        Direction direction = (Direction)state.get(FACING);
        if (!world.isClient) {
            boolean bl = this.shouldExtend(world, pos, direction);
            if (bl && (type == 1 || type == 2)) {
                world.setBlockState(pos, (BlockState)state.with(EXTENDED, true), Block.NOTIFY_LISTENERS);
                return false;
            }

            if (!bl && type == 0) {
                return false;
            }
        }

        if (type == 0) {
            if (!this.move(world, pos, direction, true)) {
                return false;
            }

            world.setBlockState(pos, (BlockState)state.with(EXTENDED, true), Block.NOTIFY_ALL | Block.MOVED);
            world.playSound((PlayerEntity)null, pos, SoundEvents.BLOCK_PISTON_EXTEND, SoundCategory.BLOCKS, 0.5F, world.random.nextFloat() * 0.25F + 0.6F);
            world.emitGameEvent(GameEvent.PISTON_EXTEND, pos);
        } else if (type == 1 || type == 2) {
            BlockEntity blockEntity = world.getBlockEntity(pos.offset(direction));
            if (blockEntity instanceof PistonBlockEntity) {
                ((PistonBlockEntity)blockEntity).finish();
            }

            BlockState blockState = (BlockState)((BlockState)Blocks.MOVING_PISTON.getDefaultState().with(PistonExtensionBlock.FACING, direction)).with(PistonExtensionBlock.TYPE, this.sticky ? PistonType.STICKY : PistonType.DEFAULT);
            world.setBlockState(pos, blockState, Block.NO_REDRAW | Block.FORCE_STATE);
            // Creates PBE on retraction. Two creations: Server, then client.
            world.addBlockEntity(PistonExtensionBlock.createBlockEntityPiston(pos, blockState, (BlockState)this.getDefaultState().with(FACING, Direction.byId(data & 7)), direction, false, true));
            world.updateNeighbors(pos, blockState.getBlock());
            blockState.updateNeighbors(world, pos, Block.NOTIFY_LISTENERS);
            // CSP Head retract.
            if (this.sticky) {
                BlockPos blockPos = pos.add(direction.getOffsetX() * 2, direction.getOffsetY() * 2, direction.getOffsetZ() * 2);
                BlockState blockState2 = world.getBlockState(blockPos);
                boolean bl2 = false;
                if (blockState2.isOf(Blocks.MOVING_PISTON)) {
                    BlockEntity blockEntity2 = world.getBlockEntity(blockPos);
                    if (blockEntity2 instanceof PistonBlockEntity) {
                        PistonBlockEntity pistonBlockEntity = (PistonBlockEntity)blockEntity2;
                        if (pistonBlockEntity.getFacing() == direction && pistonBlockEntity.isExtending()) {
                            pistonBlockEntity.finish();
                            bl2 = true;
                        }
                    }
                }

                if (!bl2) {
                    if (type != 1
                            || blockState2.isAir()
                            || !isMovable(blockState2, world, blockPos, direction.getOpposite(), false, direction)
                            || blockState2.getPistonBehavior() != PistonBehavior.NORMAL
                            && !blockState2.isOf(Blocks.PISTON)
                            && !blockState2.isOf(Blocks.STICKY_PISTON)
                            && !ColouredStickyPistons.ColouredStickyPistonsMap.containsValue(blockState2.getBlock())) {
                        world.removeBlock(pos.offset(direction), false);
                    } else {
                        this.move(world, pos, direction, false);
                    }
                }
            } else {
                world.removeBlock(pos.offset(direction), false);
            }

            world.playSound((PlayerEntity)null, pos, SoundEvents.BLOCK_PISTON_CONTRACT, SoundCategory.BLOCKS, 0.5F, world.random.nextFloat() * 0.15F + 0.6F);
            world.emitGameEvent(GameEvent.PISTON_CONTRACT, pos);
        }

        return true;
    }

    public static boolean isMovable(BlockState state, World world, BlockPos pos, Direction direction, boolean canBreak, Direction pistonDir) {
        if (pos.getY() >= world.getBottomY() && pos.getY() <= world.getTopY() - 1 && world.getWorldBorder().contains(pos)) {
            if (state.isAir()) {
                return true;
            } else if (!state.isOf(Blocks.OBSIDIAN) && !state.isOf(Blocks.CRYING_OBSIDIAN) && !state.isOf(Blocks.RESPAWN_ANCHOR)) {
                if (direction == Direction.DOWN && pos.getY() == world.getBottomY()) {
                    return false;
                } else if (direction == Direction.UP && pos.getY() == world.getTopY() - 1) {
                    return false;
                } else {
                    if (!state.isOf(Blocks.PISTON) && !state.isOf(Blocks.STICKY_PISTON) && !ColouredStickyPistons.ColouredStickyPistonsMap.containsValue(state.getBlock())) {
                        if (state.getHardness(world, pos) == -1.0F) {
                            return false;
                        }

                        switch(state.getPistonBehavior()) {
                            case BLOCK:
                                return false;
                            case DESTROY:
                                return canBreak;
                            case PUSH_ONLY:
                                return direction == pistonDir;
                        }
                    } else if ((Boolean)state.get(EXTENDED)) {
                        return false;
                    }

                    return !state.hasBlockEntity();
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    // Fixed the textures for extension and extended, as well as the block-breaking animation/effects on extension.
    private boolean move(World world, BlockPos pos, Direction dir, boolean retract) {
        BlockPos offsetBlockPos = pos.offset(dir);
        Block selfPistonHead = RegisterBlocks.CSPLinkBlockToHeadMap.get(this);

        // If retract is false and the block in the offset-dir is a CSP head:
        // Always activates on extension.
        // Activates on retraction is a block is stuck.
        System.out.println(world.getBlockState(offsetBlockPos).getBlock());
        if (!retract && world.getBlockState(offsetBlockPos).isOf(selfPistonHead)) {
            // If disabled, then the stickiness no longer works and the block doesn't get pulled back.
            // Head breaking occurs whether an adjacent block is present or not.
            world.setBlockState(offsetBlockPos, Blocks.AIR.getDefaultState(), Block.NO_REDRAW | Block.FORCE_STATE);
        }

        PistonHandler pistonHandler = new PistonHandler(world, pos, dir, retract);
        // Seems to occur for breakable blocks. But also for sticky blocks (calls isBlockSticky() and isAdjacentBlockStuck()).
        // But this behaviour only occurred if I disabled the code in the previous if-clause.
        if (!pistonHandler.calculatePush()) {
            return false;
        } else {
            Map<BlockPos, BlockState> mapMovableBlocks = Maps.newHashMap();
            List<BlockPos> movableBlockPoses = pistonHandler.getMovedBlocks();
            List<BlockState> movableBlockStates = Lists.newArrayList();

            // If movable blocks are present, list will contain at least one element. Else, list is empty.
            for(int i = 0; i < movableBlockPoses.size(); ++i) {
                BlockPos blockPos2 = (BlockPos)movableBlockPoses.get(i);
                BlockState blockState = world.getBlockState(blockPos2);
                // Stores the blocks to be moved.
                movableBlockStates.add(blockState);
                // Map stores position and block.
                mapMovableBlocks.put(blockPos2, blockState);
            }

            List<BlockPos> breakableBlockPoses = pistonHandler.getBrokenBlocks();
            BlockState[] blockStates = new BlockState[movableBlockPoses.size() + breakableBlockPoses.size()];
            Direction direction = retract ? dir : dir.getOpposite();
            int j = 0;

            int l;
            BlockPos stuckBlockPos;
            BlockState stuckBlockState;
            // Checks for blocks that would be broken by pistons.
            for(l = breakableBlockPoses.size() - 1; l >= 0; --l) {
                stuckBlockPos = (BlockPos)breakableBlockPoses.get(l);
                stuckBlockState = world.getBlockState(stuckBlockPos);
                BlockEntity blockEntity = stuckBlockState.hasBlockEntity() ? world.getBlockEntity(stuckBlockPos) : null;
                dropStacks(stuckBlockState, world, stuckBlockPos, blockEntity);
                world.setBlockState(stuckBlockPos, Blocks.AIR.getDefaultState(), Block.NOTIFY_LISTENERS | Block.FORCE_STATE);
                // For some reason this only runs if the breakable block is not on fire.
                // Now it always reaches this?
                if (!stuckBlockState.isIn(BlockTags.FIRE)) {
                    world.addBlockBreakParticles(stuckBlockPos, stuckBlockState);
                }

                blockStates[j++] = stuckBlockState;
            }

            // Only runs if there are blocks to be moved (pushed/pulled).
            for(l = movableBlockPoses.size() - 1; l >= 0; --l) {
                stuckBlockPos = (BlockPos)movableBlockPoses.get(l);
                stuckBlockState = world.getBlockState(stuckBlockPos);
                stuckBlockPos = stuckBlockPos.offset(direction);
                mapMovableBlocks.remove(stuckBlockPos);
                BlockState blockState4 = (BlockState)Blocks.MOVING_PISTON.getDefaultState().with(FACING, dir);
                world.setBlockState(stuckBlockPos, blockState4, Block.NO_REDRAW | Block.MOVED);
                // PBE created twice for both extension and retraction: Server, then client.
                // When commented out, extension/retraction still works without stuck blocks.
                // Stuck block pushed out, converted to Moving Piston. Cannot be moved if another block place between CSP and MP. Use /setblock to clear. Block breaking on retraction.
                // Block pulled in, converted to MP adjacent to CSP. No block breaking on retraction. CSP can no longer extend.
                world.addBlockEntity(PistonExtensionBlock.createBlockEntityPiston(stuckBlockPos, blockState4, (BlockState)movableBlockStates.get(l), dir, retract, false));
                blockStates[j++] = stuckBlockState;
            }
            // Always runs on retraction. Runs twice, both before and after powering.
            // Always runs whether blocks are stuck or not.
            if (retract) {
                PistonType pistonType = this.sticky ? PistonType.STICKY : PistonType.DEFAULT;
                // If replace selfPistonHead with Moving Piston, then the texture will change to Sticky Piston while extending, then break.
                BlockState blockState5 = (BlockState)((BlockState)selfPistonHead.getDefaultState().with(PistonHeadBlock.FACING, dir)).with(PistonHeadBlock.TYPE, pistonType);
                stuckBlockState = (BlockState)((BlockState)Blocks.MOVING_PISTON.getDefaultState().with(PistonExtensionBlock.FACING, dir)).with(PistonExtensionBlock.TYPE, this.sticky ? PistonType.STICKY : PistonType.DEFAULT);
                // Activates twice on extension. Server, then client.
                mapMovableBlocks.remove(offsetBlockPos);
                world.setBlockState(offsetBlockPos, stuckBlockState, Block.NO_REDRAW | Block.MOVED);
                // When powered, CSP Head vanishes, but cannot place any blocks above CSP Base.
                // When unpowered, CSP Head undergoes retraction animation and sound. But MP created adjacent to CSP Head. No block breaking particles or sound.
                // With stuck block, block is pushed out on extension. Not pulled back on retraction as MP created. CSP Head can no longer extend.
                world.addBlockEntity(PistonExtensionBlock.createBlockEntityPiston(offsetBlockPos, stuckBlockState, blockState5, dir, true, true));
            }

            BlockState blockState7 = Blocks.AIR.getDefaultState();
            Iterator var25 = mapMovableBlocks.keySet().iterator();

            // Activates when non-air blocks are present.
            while(var25.hasNext()) {
                BlockPos blockPos5 = (BlockPos)var25.next();
                // If you disable this line, you get block duping.
                world.setBlockState(blockPos5, blockState7, Block.NOTIFY_LISTENERS | Block.FORCE_STATE | Block.MOVED);
            }

            var25 = mapMovableBlocks.entrySet().iterator();

            // Activates when non-air blocks are present.
            // But activation changes based on history of blocks placed.
            // After blocks have been placed, this clause doesn't run any more.
            BlockPos blockPos7;
            while(var25.hasNext()) {
                Map.Entry<BlockPos, BlockState> entry = (Map.Entry)var25.next();
                blockPos7 = (BlockPos)entry.getKey();
                BlockState blockState8 = (BlockState)entry.getValue();
                blockState8.prepare(world, blockPos7, 2);
                blockState7.updateNeighbors(world, blockPos7, Block.NOTIFY_LISTENERS);
                blockState7.prepare(world, blockPos7, 2);
            }

            j = 0;

            // Presence of block irrelevant. Activates on extension for air.
            // Activates on both extension and retraction for non-air blocks.
            // This also doesn't run any more.
            int n;
            for(n = breakableBlockPoses.size() - 1; n >= 0; --n) {
                stuckBlockState = blockStates[j++];
                blockPos7 = (BlockPos)breakableBlockPoses.get(n);
                stuckBlockState.prepare(world, blockPos7, 2);
                world.updateNeighborsAlways(blockPos7, stuckBlockState.getBlock());
            }

            // Presence of block irrelevant.
            // Runs on extension and retraction when non-air present.
            // Sometimes disabled.
            for(n = movableBlockPoses.size() - 1; n >= 0; --n) {
                world.updateNeighborsAlways((BlockPos)movableBlockPoses.get(n), blockStates[j++].getBlock());
            }

            // Presence of block irrelevant.
            if (retract) {
                world.updateNeighborsAlways(offsetBlockPos, selfPistonHead);
            }

            return true;
        }
    }

    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return (BlockState)state.with(FACING, rotation.rotate((Direction)state.get(FACING)));
    }

    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation((Direction)state.get(FACING)));
    }

//    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
//        builder.add(FACING, EXTENDED);
//    }

//    public boolean hasSidedTransparency(BlockState state) {
//        return (Boolean)state.get(EXTENDED);
//    }

    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }

    static {
        EXTENDED = Properties.EXTENDED;
        EXTENDED_EAST_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 12.0D, 16.0D, 16.0D);
        EXTENDED_WEST_SHAPE = Block.createCuboidShape(4.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
        EXTENDED_SOUTH_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 12.0D);
        EXTENDED_NORTH_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 4.0D, 16.0D, 16.0D, 16.0D);
        EXTENDED_UP_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D);
        EXTENDED_DOWN_SHAPE = Block.createCuboidShape(0.0D, 4.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    }


    // Same as csbRegister. May want to repackage the method.
    public static void cspRegister(ColouredStickyPistons colouredStickyPistons, String path) {
        // Block Register.
        Registry.register(Registry.BLOCK, new Identifier(Slimeology.MOD_ID, path), colouredStickyPistons);
        // Creative item menu tab.
        BlockItem cspBlockItem = new BlockItem(colouredStickyPistons, new Item.Settings().group(Slimeology.SLIMEOLOGY));
        // Item Register.
        Registry.register(Registry.ITEM, new Identifier(Slimeology.MOD_ID, path), cspBlockItem);
    }

    public static void registerColouredStickyPistons() {
        cspRegister(STICKY_PISTON_WHITE, "piston_white");
        cspRegister(STICKY_PISTON_ORANGE, "piston_orange");
        cspRegister(STICKY_PISTON_MAGENTA, "piston_magenta");
        cspRegister(STICKY_PISTON_LIGHT_BLUE, "piston_light_blue");
        cspRegister(STICKY_PISTON_YELLOW, "piston_yellow");
        cspRegister(STICKY_PISTON_LIME, "piston_lime");
        cspRegister(STICKY_PISTON_PINK, "piston_pink");
        cspRegister(STICKY_PISTON_GRAY, "piston_gray");
        cspRegister(STICKY_PISTON_LIGHT_GRAY, "piston_light_gray");
        cspRegister(STICKY_PISTON_CYAN, "piston_cyan");
        cspRegister(STICKY_PISTON_PURPLE, "piston_purple");
        cspRegister(STICKY_PISTON_BLUE, "piston_blue");
        cspRegister(STICKY_PISTON_BROWN, "piston_brown");
        cspRegister(STICKY_PISTON_GREEN, "piston_green");
        cspRegister(STICKY_PISTON_RED, "piston_red");
        cspRegister(STICKY_PISTON_BLACK, "piston_black");
    }
}
