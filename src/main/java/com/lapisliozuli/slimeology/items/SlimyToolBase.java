package com.lapisliozuli.slimeology.items;

import com.google.common.collect.ImmutableSet;
import com.lapisliozuli.slimeology.Slimeology;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Set;

// Meant to be SlimeToolBase but I accidentally used it for a text file and can't reuse it
// Crafting recipe made with https://crafting.thedestruc7i0n.ca/
// I thought a shovel would suit this more based on the texture, but actually it functions more like a pick
// Only works on Slime Blocks, which would give unique drops
// Now that it's a pick, check if I can change the loot table by making cobble drop diamonds
// Then try making cobble give double drops
// Then try making cobble drop double diamonds
// Finally change the target block to slime blocks, and make them drop double of themselves.

public class SlimyToolBase extends MiningToolItem {
    private static final Set<Block> EFFECTIVE_ON = ImmutableSet.of(Blocks.SLIME_BLOCK, Blocks.OAK_LOG);

    protected SlimyToolBase(int attackDamage, float attackSpeed, ToolMaterial material, Set<Block> effectiveBlocks, Settings settings){
        super(attackDamage, attackSpeed, material, effectiveBlocks, settings);
    }

    // The class parameters are finally working. But the tool doesn't lose durability when breaking slime blocks.
    // I think it's because slime blocks have hardness 0.
    // Maybe I need to supersede this method by replacing the condition with whether the block broken is a slime block.
    @Override
    public boolean postMine(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity miner) {
        // I DID IT! I was using the wrong blockstate all along.
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
