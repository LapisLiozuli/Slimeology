package com.lapisliozuli.slimeology;

import com.lapisliozuli.slimeology.registry.RegisterBlocks;
import com.lapisliozuli.slimeology.registry.RegisterSEC;
import com.lapisliozuli.slimeology.registry.RegisterSlimyTools;
import com.lapisliozuli.slimeology.items.*;
//import com.lapisliozuli.slimeology.particles.SlimeEntityColouredParticleFactory;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;


public class Slimeology implements ModInitializer {

    public static final String MOD_ID = "slimeology";
    public static final ItemGroup SLIMEOLOGY = FabricItemGroupBuilder.create(
            new Identifier(MOD_ID, "slimeology"))
            .icon(() -> new ItemStack(Items.SLIME_BALL))
            .build();
    public static ModConfig CONFIG;

    @Override
    public void onInitialize() {
        // Items and blocks
        SlimeBalls.registerSlimeBalls();
        RegisterBlocks.register();

        // Allows comments in config file
        AutoConfig.register(ModConfig.class, JanksonConfigSerializer::new);
        Slimeology.CONFIG = AutoConfig.getConfigHolder(ModConfig.class).getConfig();

        // For SlimeEntityColoured
        RegisterSEC.registerSlimeologyEntityTypes();
        SpawnEggSlime.registerSpawnEggSlime();

        // Non-stackables: Tools and Armour
        RegisterSlimyTools.registerSlimyTools();
//        RegisterArmourItems.registerArmourSlimy();

        // Maybe add LOGGER in future

    }
}
