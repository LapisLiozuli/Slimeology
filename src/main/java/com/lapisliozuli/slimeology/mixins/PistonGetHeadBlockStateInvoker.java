package com.lapisliozuli.slimeology.mixins;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.PistonBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(PistonBlockEntity.class)
public interface PistonGetHeadBlockStateInvoker {
    @Invoker
    abstract BlockState invokeGetHeadBlockState();
}

