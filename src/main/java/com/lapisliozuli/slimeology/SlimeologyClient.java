package com.lapisliozuli.slimeology;

import com.lapisliozuli.slimeology.blocks.ColouredSlimeBlocks;
import com.lapisliozuli.slimeology.blocks.SlimyGlass;
import com.lapisliozuli.slimeology.entities.SlimeRenderHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class SlimeologyClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        SlimeRenderHandler.registerSlimeEntityColoured();
        ColouredSlimeBlocks.renderSlimeBlocks();
        SlimyGlass.renderSlimyGlass();
    }
}