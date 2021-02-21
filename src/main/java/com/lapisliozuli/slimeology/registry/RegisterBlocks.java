package com.lapisliozuli.slimeology.registry;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableBiMap;
import com.lapisliozuli.slimeology.blocks.*;
import net.minecraft.block.Block;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RegisterBlocks {
    // This list is used for IsStickyMixin and IsAdjacentBlockStuckMixin
    public static List<Block> blocksList = Stream.of(SlimySand.SLIMY_SAND, SlimyGlass.SLIMY_GLASS).collect(Collectors.toList());

    public static void register() {
        ColouredSlimeBlocks.registerSlimeBlocks();
//        ColouredSlimeBlocks.renderSlimeBlocks();
        SlimySand.registerSlimySand();
        SlimyGlass.registerSlimyGlass();
        // Two whole blocks just for Pistons...
        ColouredStickyPistons.registerColouredStickyPistons();
        ColouredStickyPistonHeads.registerColouredStickyPistonHeads();
    }

    // Link the CSP block and head
    public static BiMap<ColouredStickyPistons, ColouredStickyPistonHeads> imperativeCSPLinks() {
        final Map<ColouredStickyPistons, ColouredStickyPistonHeads> tempCSPLinkBlockToHeadMap = new HashMap<>();
//        tempCSPLinkBlockToHeadMap.put(ColouredStickyPistons.STICKY_PISTON_DEBUG, ColouredStickyPistonHeads.STICKY_PISTON_HEAD_DEBUG);
        tempCSPLinkBlockToHeadMap.put(ColouredStickyPistons.STICKY_PISTON_WHITE, ColouredStickyPistonHeads.STICKY_PISTON_HEAD_WHITE);
        tempCSPLinkBlockToHeadMap.put(ColouredStickyPistons.STICKY_PISTON_ORANGE, ColouredStickyPistonHeads.STICKY_PISTON_HEAD_ORANGE);
        tempCSPLinkBlockToHeadMap.put(ColouredStickyPistons.STICKY_PISTON_MAGENTA, ColouredStickyPistonHeads.STICKY_PISTON_HEAD_MAGENTA);
        tempCSPLinkBlockToHeadMap.put(ColouredStickyPistons.STICKY_PISTON_LIGHT_BLUE, ColouredStickyPistonHeads.STICKY_PISTON_HEAD_LIGHT_BLUE);
        tempCSPLinkBlockToHeadMap.put(ColouredStickyPistons.STICKY_PISTON_YELLOW, ColouredStickyPistonHeads.STICKY_PISTON_HEAD_YELLOW);
        tempCSPLinkBlockToHeadMap.put(ColouredStickyPistons.STICKY_PISTON_LIME, ColouredStickyPistonHeads.STICKY_PISTON_HEAD_LIME);
        tempCSPLinkBlockToHeadMap.put(ColouredStickyPistons.STICKY_PISTON_PINK, ColouredStickyPistonHeads.STICKY_PISTON_HEAD_PINK);
        tempCSPLinkBlockToHeadMap.put(ColouredStickyPistons.STICKY_PISTON_GRAY, ColouredStickyPistonHeads.STICKY_PISTON_HEAD_GRAY);
        tempCSPLinkBlockToHeadMap.put(ColouredStickyPistons.STICKY_PISTON_LIGHT_GRAY, ColouredStickyPistonHeads.STICKY_PISTON_HEAD_LIGHT_GRAY);
        tempCSPLinkBlockToHeadMap.put(ColouredStickyPistons.STICKY_PISTON_CYAN, ColouredStickyPistonHeads.STICKY_PISTON_HEAD_CYAN);
        tempCSPLinkBlockToHeadMap.put(ColouredStickyPistons.STICKY_PISTON_PURPLE, ColouredStickyPistonHeads.STICKY_PISTON_HEAD_PURPLE);
        tempCSPLinkBlockToHeadMap.put(ColouredStickyPistons.STICKY_PISTON_BLUE, ColouredStickyPistonHeads.STICKY_PISTON_HEAD_BLUE);
        tempCSPLinkBlockToHeadMap.put(ColouredStickyPistons.STICKY_PISTON_BROWN, ColouredStickyPistonHeads.STICKY_PISTON_HEAD_BROWN);
        tempCSPLinkBlockToHeadMap.put(ColouredStickyPistons.STICKY_PISTON_GREEN, ColouredStickyPistonHeads.STICKY_PISTON_HEAD_GREEN);
        tempCSPLinkBlockToHeadMap.put(ColouredStickyPistons.STICKY_PISTON_RED, ColouredStickyPistonHeads.STICKY_PISTON_HEAD_RED);
        tempCSPLinkBlockToHeadMap.put(ColouredStickyPistons.STICKY_PISTON_BLACK, ColouredStickyPistonHeads.STICKY_PISTON_HEAD_BLACK);
        return ImmutableBiMap.copyOf(Collections.unmodifiableMap(tempCSPLinkBlockToHeadMap));
    }
    public static final BiMap<ColouredStickyPistons, ColouredStickyPistonHeads> CSPLinkBlockToHeadMap = imperativeCSPLinks();
}
