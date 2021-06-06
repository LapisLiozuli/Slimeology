package com.lapisliozuli.slimeology.items;

import com.google.common.collect.ImmutableSet;
import com.lapisliozuli.slimeology.Slimeology;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;
import net.minecraft.tag.Tag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Set;

public class SlimyToolBase extends MiningToolItem {
    private static final Tag<Block> EFFECTIVE_ON = ImmutableSet.of(Blocks.SLIME_BLOCK, Blocks.OAK_LOG);

    protected SlimyToolBase(int attackDamage, float attackSpeed, ToolMaterial material, Tag<Block> effectiveBlocks, Settings settings){
        super(attackDamage, attackSpeed, material, effectiveBlocks, settings);
    }


    @Override
    public boolean postMine(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity miner) {
        Block initial = state.getBlock();
        // !worldIn.isClient alone means that as long as I break any block with the tool, it takes 1 damage.
        // Even blocks above the harvest level, or modded blocks.
        // But when I add initial.equals(Blocks.SLIME_BLOCK, it takes no more damage at all.
        // Doesn't matter what the Blocks is, so I think this doesn't really work.
        // Slipperiness doesn't work either. (Ice has 0.98F, Slime has 0.8F)
        // Using slipperiness means that it also takes damage when breaking the modded slime blocks. That's convenient.
        if (initial.getSlipperiness() == 0.8F) {
            stack.damage(1, miner, (e) -> {
                e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND);
            });
        }
        return true;
    }

    public static MiningToolItem SLIMOTIC_DUPLICATOR = new SlimyToolBase(0, -1.0f,
            ToolMaterialSlimeball.INSTANCE, EFFECTIVE_ON, new Settings().group(Slimeology.SLIMEOLOGY));
}
