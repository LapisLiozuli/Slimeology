package com.lapisliozuli.slimeology.particles;

import com.lapisliozuli.slimeology.items.SlimeBalls;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.particle.CrackParticle;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.DefaultParticleType;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SlimeEntityColouredParticleFactory {

    public static Map<DefaultParticleType, Item> imperative() {
        final Map<DefaultParticleType, Item> secParticlesMap = new HashMap<>();
        secParticlesMap.put(SlimeEntityColouredParticles.SLIME_PARTICLE_DEBUG, SlimeBalls.SLIME_BALL_DEBUG);
        secParticlesMap.put(SlimeEntityColouredParticles.SLIME_PARTICLE_WHITE, SlimeBalls.SLIME_BALL_WHITE);
        secParticlesMap.put(SlimeEntityColouredParticles.SLIME_PARTICLE_ORANGE, SlimeBalls.SLIME_BALL_ORANGE);
        secParticlesMap.put(SlimeEntityColouredParticles.SLIME_PARTICLE_MAGENTA, SlimeBalls.SLIME_BALL_MAGENTA);
        secParticlesMap.put(SlimeEntityColouredParticles.SLIME_PARTICLE_LIGHT_BLUE, SlimeBalls.SLIME_BALL_LIGHT_BLUE);
        secParticlesMap.put(SlimeEntityColouredParticles.SLIME_PARTICLE_YELLOW, SlimeBalls.SLIME_BALL_YELLOW);
        secParticlesMap.put(SlimeEntityColouredParticles.SLIME_PARTICLE_LIME, SlimeBalls.SLIME_BALL_LIME);
        secParticlesMap.put(SlimeEntityColouredParticles.SLIME_PARTICLE_PINK, SlimeBalls.SLIME_BALL_PINK);
        secParticlesMap.put(SlimeEntityColouredParticles.SLIME_PARTICLE_GRAY, SlimeBalls.SLIME_BALL_GRAY);
        secParticlesMap.put(SlimeEntityColouredParticles.SLIME_PARTICLE_LIGHT_GRAY, SlimeBalls.SLIME_BALL_LIGHT_GRAY);
        secParticlesMap.put(SlimeEntityColouredParticles.SLIME_PARTICLE_CYAN, SlimeBalls.SLIME_BALL_CYAN);
        secParticlesMap.put(SlimeEntityColouredParticles.SLIME_PARTICLE_PURPLE, SlimeBalls.SLIME_BALL_PURPLE);
        secParticlesMap.put(SlimeEntityColouredParticles.SLIME_PARTICLE_BLUE, SlimeBalls.SLIME_BALL_BLUE);
        secParticlesMap.put(SlimeEntityColouredParticles.SLIME_PARTICLE_BROWN, SlimeBalls.SLIME_BALL_BROWN);
        secParticlesMap.put(SlimeEntityColouredParticles.SLIME_PARTICLE_GREEN, SlimeBalls.SLIME_BALL_GREEN);
        secParticlesMap.put(SlimeEntityColouredParticles.SLIME_PARTICLE_RED, SlimeBalls.SLIME_BALL_RED);
        secParticlesMap.put(SlimeEntityColouredParticles.SLIME_PARTICLE_BLACK, SlimeBalls.SLIME_BALL_BLACK);
        return Collections.unmodifiableMap(secParticlesMap);
    }
    public static final Map<DefaultParticleType, Item> secParticlesMap = imperative();

    public static void registerParticleFactories() {

        for (Map.Entry<DefaultParticleType, Item> entry : secParticlesMap.entrySet()) {
            DefaultParticleType secParticle = entry.getKey();
            Item colouredSlimeball = entry.getValue();

            // Fabricord style where I extend CrackParticle
            ParticleFactoryRegistry.getInstance().register(secParticle, provider ->
                    (parameters, world, x, y, z, velocityX, velocityY, velocityZ) ->
                    {
                        SlimeEntityColouredParticles particle = new SlimeEntityColouredParticles(world, x, y, z,
                                new ItemStack(colouredSlimeball));
                        return particle;
                    });

//            // Astromine style that directly uses CrackParticle with AW
//            ParticleFactoryRegistry.getInstance().register(secParticle, provider ->
//                    (parameters, world, x, y, z, velocityX, velocityY, velocityZ) ->
//                            new CrackParticle(world, x, y, z, new ItemStack(colouredSlimeball)));
        }
    }
}
