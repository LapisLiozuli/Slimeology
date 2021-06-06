package com.lapisliozuli.slimeology.items;

import com.google.common.collect.ImmutableSet;
import com.lapisliozuli.slimeology.Slimeology;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.tag.Tag;

import java.util.Set;

public class BleachingBrush extends MiningToolItem {
    protected BleachingBrush(float attackDamage, float attackSpeed, ToolMaterial material, Tag<Block> effectiveBlocks, Settings settings) {
        super(attackDamage, attackSpeed, material, effectiveBlocks, settings);
    }

    private static final Tag<Block> EFFECTIVE_BLOCKS;
    static {
        // Just saying that the Brush isn't effective at breaking any blocks.
        EFFECTIVE_BLOCKS = ImmutableSet.of();
    }

    public static MiningToolItem BLEACHING_BRUSH = new BleachingBrush(0, -1.0f,
            ToolMaterialSlimeball.INSTANCE, EFFECTIVE_BLOCKS, new Item.Settings().group(Slimeology.SLIMEOLOGY));

}

