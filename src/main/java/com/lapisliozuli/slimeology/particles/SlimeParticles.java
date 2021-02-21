package com.lapisliozuli.slimeology.particles;

import net.minecraft.client.particle.CrackParticle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.ItemStack;

public class SlimeParticles extends CrackParticle {

    protected SlimeParticles(ClientWorld world, double x, double y, double z, ItemStack stack) {
        super(world, x, y, z, stack);
    }
}
