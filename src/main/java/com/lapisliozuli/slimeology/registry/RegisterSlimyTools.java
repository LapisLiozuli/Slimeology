package com.lapisliozuli.slimeology.registry;

import com.lapisliozuli.slimeology.Slimeology;
import com.lapisliozuli.slimeology.items.BleachingBrush;
import com.lapisliozuli.slimeology.items.SlimyToolBase;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class RegisterSlimyTools {
    // Slimotic Duplicator is a debug tool that has a chance to produce extra Slimeballs when breaking Slime Blocks.
    public static void registerSlimyTools() {
//        Registry.register(Registry.ITEM, new Identifier(Slimeology.MOD_ID, "slimotic_duplicator"), SlimyToolBase.SLIMOTIC_DUPLICATOR);
        Registry.register(Registry.ITEM, new Identifier(Slimeology.MOD_ID, "bleaching_brush"), BleachingBrush.BLEACHING_BRUSH);

    }
}
