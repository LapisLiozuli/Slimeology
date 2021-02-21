package com.lapisliozuli.slimeology.blocks;

import com.lapisliozuli.slimeology.Slimeology;
import com.lapisliozuli.slimeology.items.SlimyToolBase;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.UniformLootTableRange;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.*;


public class ColouredSlimeBlocks extends SlimeBlock {
    public ColouredSlimeBlocks() {
            super(FabricBlockSettings.copyOf(Blocks.SLIME_BLOCK).nonOpaque());
    }

    // Mass declare variables of the same type
    public static final ColouredSlimeBlocks SLIME_BLOCK_DEBUG = new ColouredSlimeBlocks(),
                                    SLIME_BLOCK_WHITE = new ColouredSlimeBlocks(),
                                    SLIME_BLOCK_ORANGE = new ColouredSlimeBlocks(),
                                    SLIME_BLOCK_MAGENTA = new ColouredSlimeBlocks(),
                                    SLIME_BLOCK_LIGHT_BLUE = new ColouredSlimeBlocks(),
                                    SLIME_BLOCK_YELLOW = new ColouredSlimeBlocks(),
                                    SLIME_BLOCK_LIME = new ColouredSlimeBlocks(),
                                    SLIME_BLOCK_PINK = new ColouredSlimeBlocks(),
                                    SLIME_BLOCK_GRAY = new ColouredSlimeBlocks(),
                                    SLIME_BLOCK_LIGHT_GRAY = new ColouredSlimeBlocks(),
                                    SLIME_BLOCK_CYAN = new ColouredSlimeBlocks(),
                                    SLIME_BLOCK_PURPLE = new ColouredSlimeBlocks(),
                                    SLIME_BLOCK_BLUE = new ColouredSlimeBlocks(),
                                    SLIME_BLOCK_BROWN = new ColouredSlimeBlocks(),
                                    SLIME_BLOCK_GREEN = new ColouredSlimeBlocks(),
                                    SLIME_BLOCK_RED = new ColouredSlimeBlocks(),
                                    SLIME_BLOCK_BLACK = new ColouredSlimeBlocks(),
                                    SLIME_BLOCK_RAINBOW = new ColouredSlimeBlocks();

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

    public static List<BlockItem> csbBlockItemsList = new ArrayList<>(Collections.emptyList());;

    // The block loot table can store conditions, but it's not strictly necessary to allow the block to drop itself in item form.
    private static final Identifier SLIME_BLOCK_LOOT_TABLE_ID = new Identifier("minecraft", "blocks/slime_block");

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

        // Sets loot table of Slime Block when broken by Slimotic Duplicator to drop extra slimeballs.
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
            if (SLIME_BLOCK_LOOT_TABLE_ID.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        // That's all I need. Amazing. So this is adding to the current loot table, not overriding it.
                        .rolls(UniformLootTableRange.between(0.0f, 4.0f))
                        .withEntry(ItemEntry.builder(Items.SLIME_BALL).build())
                        .conditionally(MatchToolLootCondition.builder(ItemPredicate.Builder.create().item(SlimyToolBase.SLIMOTIC_DUPLICATOR)));

                supplier.withPool(poolBuilder.build());
            }
        });
    }

    public static void renderSlimeBlocks() {
        // I need to segregate this or else the server will cry and refuse to run.
        // To iterate over values only.
        for (ColouredSlimeBlocks slimeblock : colouredSlimeBlocksMap.values())
            BlockRenderLayerMap.INSTANCE.putBlock(slimeblock, RenderLayer.getTranslucent());
    }
}
