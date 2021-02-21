package com.lapisliozuli.slimeology.particles;

import com.lapisliozuli.slimeology.Slimeology;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.client.particle.CrackParticle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SlimeEntityColouredParticles extends CrackParticle {

    public SlimeEntityColouredParticles(ClientWorld world, double x, double y, double z, ItemStack stack) {
        super(world, x, y, z, stack);
    }

    public static DefaultParticleType register(String name, boolean alwaysShow) {
        return Registry.register(Registry.PARTICLE_TYPE, new Identifier(Slimeology.MOD_ID, name), FabricParticleTypes.simple(alwaysShow));
    }

    public static final DefaultParticleType SLIME_PARTICLE_DEBUG = register("slime_particle_debug", false);
    public static final DefaultParticleType SLIME_PARTICLE_WHITE = register("slime_particle_white", false);
    public static final DefaultParticleType SLIME_PARTICLE_ORANGE = register("slime_particle_orange", false);
    public static final DefaultParticleType SLIME_PARTICLE_MAGENTA = register("slime_particle_magenta", false);
    public static final DefaultParticleType SLIME_PARTICLE_LIGHT_BLUE = register("slime_particle_light_blue", false);
    public static final DefaultParticleType SLIME_PARTICLE_YELLOW = register("slime_particle_yellow", false);
    public static final DefaultParticleType SLIME_PARTICLE_LIME = register("slime_particle_lime", false);
    public static final DefaultParticleType SLIME_PARTICLE_PINK = register("slime_particle_pink", false);
    public static final DefaultParticleType SLIME_PARTICLE_GRAY = register("slime_particle_gray", false);
    public static final DefaultParticleType SLIME_PARTICLE_LIGHT_GRAY = register("slime_particle_light_gray", false);
    public static final DefaultParticleType SLIME_PARTICLE_CYAN = register("slime_particle_cyan", false);
    public static final DefaultParticleType SLIME_PARTICLE_PURPLE = register("slime_particle_purple", false);
    public static final DefaultParticleType SLIME_PARTICLE_BLUE = register("slime_particle_blue", false);
    public static final DefaultParticleType SLIME_PARTICLE_BROWN = register("slime_particle_brown", false);
    public static final DefaultParticleType SLIME_PARTICLE_GREEN = register("slime_particle_green", false);
    public static final DefaultParticleType SLIME_PARTICLE_RED = register("slime_particle_red", false);
    public static final DefaultParticleType SLIME_PARTICLE_BLACK = register("slime_particle_black", false);

}