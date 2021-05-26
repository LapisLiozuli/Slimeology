package com.lapisliozuli.slimeology.blocks;

import com.lapisliozuli.slimeology.Slimeology;
import com.lapisliozuli.slimeology.items.BleachingBrush;
import com.lapisliozuli.slimeology.items.SlimeBalls;
import com.lapisliozuli.slimeology.items.SlimyToolBase;
import com.lapisliozuli.slimeology.registry.RegisterItems;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.UniformLootTableRange;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

import java.util.*;


public class ColouredSlimeBlocks extends SlimeBlock {
    private Item csbDyeItem;

    public ColouredSlimeBlocks(Item csbDyeItem) {
            super(FabricBlockSettings.copyOf(Blocks.SLIME_BLOCK).nonOpaque());
        this.csbDyeItem = csbDyeItem;
    }

    // Mass declare variables of the same type
    public static final ColouredSlimeBlocks SLIME_BLOCK_DEBUG = new ColouredSlimeBlocks(SlimeBalls.SLIME_BALL_DEBUG),
                                    SLIME_BLOCK_WHITE = new ColouredSlimeBlocks(Items.WHITE_DYE),
                                    SLIME_BLOCK_ORANGE = new ColouredSlimeBlocks(Items.ORANGE_DYE),
                                    SLIME_BLOCK_MAGENTA = new ColouredSlimeBlocks(Items.MAGENTA_DYE),
                                    SLIME_BLOCK_LIGHT_BLUE = new ColouredSlimeBlocks(Items.LIGHT_BLUE_DYE),
                                    SLIME_BLOCK_YELLOW = new ColouredSlimeBlocks(Items.YELLOW_DYE),
                                    SLIME_BLOCK_LIME = new ColouredSlimeBlocks(Items.LIME_DYE),
                                    SLIME_BLOCK_PINK = new ColouredSlimeBlocks(Items.PINK_DYE),
                                    SLIME_BLOCK_GRAY = new ColouredSlimeBlocks(Items.GRAY_DYE),
                                    SLIME_BLOCK_LIGHT_GRAY = new ColouredSlimeBlocks(Items.LIGHT_GRAY_DYE),
                                    SLIME_BLOCK_CYAN = new ColouredSlimeBlocks(Items.CYAN_DYE),
                                    SLIME_BLOCK_PURPLE = new ColouredSlimeBlocks(Items.PURPLE_DYE),
                                    SLIME_BLOCK_BLUE = new ColouredSlimeBlocks(Items.BLUE_DYE),
                                    SLIME_BLOCK_BROWN = new ColouredSlimeBlocks(Items.BROWN_DYE),
                                    SLIME_BLOCK_GREEN = new ColouredSlimeBlocks(Items.GREEN_DYE),
                                    SLIME_BLOCK_RED = new ColouredSlimeBlocks(Items.RED_DYE),
                                    SLIME_BLOCK_BLACK = new ColouredSlimeBlocks(Items.BLACK_DYE),
                                    // Maybe drop a random dye eventually?
                                    SLIME_BLOCK_RAINBOW = new ColouredSlimeBlocks(Items.SLIME_BALL);

    // Puts all Slimeblocks into a map for bulk handling.
    public static Map<String, ColouredSlimeBlocks> imperative() {
        final Map<String, ColouredSlimeBlocks> colouredSlimeBlocksMap = new HashMap<>();
        colouredSlimeBlocksMap.put("slime_block_debug", SLIME_BLOCK_DEBUG);
        colouredSlimeBlocksMap.put("slime_block_white", SLIME_BLOCK_WHITE);
        colouredSlimeBlocksMap.put("slime_block_orange", SLIME_BLOCK_ORANGE);
        colouredSlimeBlocksMap.put("slime_block_magenta", SLIME_BLOCK_MAGENTA);
        colouredSlimeBlocksMap.put("slime_block_light_blue", SLIME_BLOCK_LIGHT_BLUE);
        colouredSlimeBlocksMap.put("slime_block_yellow", SLIME_BLOCK_YELLOW);
        colouredSlimeBlocksMap.put("slime_block_lime", SLIME_BLOCK_LIME);
        colouredSlimeBlocksMap.put("slime_block_pink", SLIME_BLOCK_PINK);
        colouredSlimeBlocksMap.put("slime_block_gray", SLIME_BLOCK_GRAY);
        colouredSlimeBlocksMap.put("slime_block_light_gray", SLIME_BLOCK_LIGHT_GRAY);
        colouredSlimeBlocksMap.put("slime_block_cyan", SLIME_BLOCK_CYAN);
        colouredSlimeBlocksMap.put("slime_block_purple", SLIME_BLOCK_PURPLE);
        colouredSlimeBlocksMap.put("slime_block_blue", SLIME_BLOCK_BLUE);
        colouredSlimeBlocksMap.put("slime_block_brown", SLIME_BLOCK_BROWN);
        colouredSlimeBlocksMap.put("slime_block_green", SLIME_BLOCK_GREEN);
        colouredSlimeBlocksMap.put("slime_block_red", SLIME_BLOCK_RED);
        colouredSlimeBlocksMap.put("slime_block_black", SLIME_BLOCK_BLACK);
        colouredSlimeBlocksMap.put("slime_block_rainbow", SLIME_BLOCK_RAINBOW);
        return Collections.unmodifiableMap(colouredSlimeBlocksMap);
    }
    public static final Map<String, ColouredSlimeBlocks> colouredSlimeBlocksMap = imperative();


    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);
        // Checks for Bleaching Brush.
        if (itemStack.getItem() == BleachingBrush.BLEACHING_BRUSH) {
            // Checks for Water Block nearby.
            if (!world.isClient && isWaterNearby(world, pos)) {
                Direction direction = hit.getSide();
                Direction direction2 = direction.getAxis() == Direction.Axis.Y ? player.getHorizontalFacing().getOpposite() : direction;
                // Creates sound.
                world.playSound((PlayerEntity)null, pos, SoundEvents.BLOCK_GRASS_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.setBlockState(pos, (BlockState)Blocks.SLIME_BLOCK.getDefaultState(), 11);
                // Drops dyeItem.
                ItemEntity itemEntity = new ItemEntity(world, (double)pos.getX() + 0.5D + (double)direction2.getOffsetX() * 0.65D, (double)pos.getY() + 0.1D, (double)pos.getZ() + 0.5D + (double)direction2.getOffsetZ() * 0.65D, new ItemStack(csbDyeItem, 1));
                itemEntity.setVelocity(0.05D * (double)direction2.getOffsetX() + world.random.nextDouble() * 0.02D, 0.05D, 0.05D * (double)direction2.getOffsetZ() + world.random.nextDouble() * 0.02D);
                world.spawnEntity(itemEntity);
                itemStack.damage(1, player, (playerEntity) -> {
                    playerEntity.sendToolBreakStatus(hand);
                });
            }

            return ActionResult.success(world.isClient);
        } else {
            return super.onUse(state, world, pos, player, hand, hit);
        }
    }


    // Somehow need to refer to Moisture
    private static boolean isWaterNearby(WorldView world, BlockPos pos) {
        // Range is 1 block along horizontal axes.
        Iterator var2 = BlockPos.iterate(pos.add(-1, 0, -1), pos.add(1, 0, 1)).iterator();

        BlockPos blockPos;
        do {
            if (!var2.hasNext()) {
                return false;
            }

            blockPos = (BlockPos)var2.next();
        } while(!world.getFluidState(blockPos).isIn(FluidTags.WATER));

        return true;
    }

    public static List<BlockItem> csbBlockItemsList = new ArrayList<>(Collections.emptyList());;

//    // The block loot table can store conditions, but it's not strictly necessary to allow the block to drop itself in item form.
//    private static final Identifier SLIME_BLOCK_LOOT_TABLE_ID = new Identifier("minecraft", "blocks/slime_block");

    public static void registerSlimeBlocks() {
        colouredSlimeBlocksMap.forEach((k,v) ->
                Registry.register(Registry.BLOCK, new Identifier(Slimeology.MOD_ID, k), v));

        // Add the CSB BlockItems to a List.
        for (Map.Entry<String, ColouredSlimeBlocks> iden : colouredSlimeBlocksMap.entrySet()) {
            BlockItem csbBlockItem;
            csbBlockItem = new BlockItem(iden.getValue(), new Item.Settings().group(Slimeology.SLIMEOLOGY));
            Registry.register(Registry.ITEM, new Identifier(Slimeology.MOD_ID, iden.getKey()), csbBlockItem);
            csbBlockItemsList.add(csbBlockItem);

        }

//        // Shouldn't this code be under the SlimyToolBase (Slimotic Duplicator)?
//        // Sets loot table of Slime Block when broken by Slimotic Duplicator to drop extra slimeballs.
//        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
//            if (SLIME_BLOCK_LOOT_TABLE_ID.equals(id)) {
//                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
//                        // That's all I need. Amazing. So this is adding to the current loot table, not overriding it.
//                        .rolls(UniformLootTableRange.between(0.0f, 4.0f))
//                        .withEntry(ItemEntry.builder(Items.SLIME_BALL).build())
//                        .conditionally(MatchToolLootCondition.builder(ItemPredicate.Builder.create().item(SlimyToolBase.SLIMOTIC_DUPLICATOR)));
//
//                supplier.withPool(poolBuilder.build());
//            }
//        });
    }

    public static void renderSlimeBlocks() {
        // I need to segregate this or else the server will cry and refuse to run.
        // To iterate over values only.
        for (ColouredSlimeBlocks slimeblock : colouredSlimeBlocksMap.values())
            BlockRenderLayerMap.INSTANCE.putBlock(slimeblock, RenderLayer.getTranslucent());
    }
}
