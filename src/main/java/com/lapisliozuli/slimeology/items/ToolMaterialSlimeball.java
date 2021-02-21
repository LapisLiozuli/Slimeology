package com.lapisliozuli.slimeology.items;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

// Material stats by TotallyGamerJet at https://www.youtube.com/watch?v=o2W-W_AIpjk
public class ToolMaterialSlimeball implements ToolMaterial {

    public static final ToolMaterialSlimeball INSTANCE = new ToolMaterialSlimeball();

    @Override
    public int getDurability() {
        return 20;
        // Low value means that any changes will be obvious
        // Wood:59, Stone:131, Iron:250, Diamond:1561, Gold:32
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 2.0f;
        // No_tool: 1.0f, Wood:2.0f, Stone:4.0f, Iron:6.0f, Diamond:8.0f, Gold:12.0f
    }

    @Override
    public float getAttackDamage() {
        return 0.0f;
        // Wood:0.0f, Stone:1.0f, Iron:2.0f, Diamond:3.0f, Gold:0.0f
    }

    @Override
    public int getMiningLevel() {
        return 0;
        // Wood:0, Stone:1, Iron:2, Diamond:3, Gold:0
    }

    @Override
    public int getEnchantability() {
        return 0;
        // Wood:15, Stone:5, Iron:14, Diamond:10, Gold:22
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Items.SLIME_BALL);
    }
}