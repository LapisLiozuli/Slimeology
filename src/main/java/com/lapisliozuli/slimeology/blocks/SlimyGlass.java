package com.lapisliozuli.slimeology.blocks;

import com.lapisliozuli.slimeology.Slimeology;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractGlassBlock;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SlimyGlass extends AbstractGlassBlock {
    protected SlimyGlass() {
        super(FabricBlockSettings.copyOf(Blocks.GLASS).slipperiness(0.9F).nonOpaque());
    }

    public static final SlimyGlass SLIMY_GLASS = new SlimyGlass();


    public static void registerSlimyGlass() {
        Registry.register(Registry.BLOCK, new Identifier(Slimeology.MOD_ID, "slimy_glass"), SLIMY_GLASS);
        Registry.register(Registry.ITEM, new Identifier(Slimeology.MOD_ID, "slimy_glass"),
                new BlockItem(SLIMY_GLASS, new Item.Settings().group(Slimeology.SLIMEOLOGY)));
    }
    public static void renderSlimyGlass() {
        BlockRenderLayerMap.INSTANCE.putBlock(SLIMY_GLASS, RenderLayer.getTranslucent());
    }

}
