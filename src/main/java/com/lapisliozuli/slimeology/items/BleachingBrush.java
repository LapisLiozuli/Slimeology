package com.lapisliozuli.slimeology.items;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.lapisliozuli.slimeology.Slimeology;
import com.lapisliozuli.slimeology.blocks.ColouredSlimeBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class BleachingBrush extends MiningToolItem {
    protected BleachingBrush(float attackDamage, float attackSpeed, ToolMaterial material, Set<Block> effectiveBlocks, Settings settings) {
        super(attackDamage, attackSpeed, material, effectiveBlocks, settings);
    }

    private static final Set<Block> EFFECTIVE_BLOCKS;
    static {
        // Just saying that the Brush isn't effective at breaking any blocks.
        EFFECTIVE_BLOCKS = ImmutableSet.of();
    }

    public static MiningToolItem BLEACHING_BRUSH = new BleachingBrush(0, -1.0f,
            ToolMaterialSlimeball.INSTANCE, EFFECTIVE_BLOCKS, new Item.Settings().group(Slimeology.SLIMEOLOGY));

}

