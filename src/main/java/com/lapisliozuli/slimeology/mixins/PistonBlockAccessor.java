package com.lapisliozuli.slimeology.mixins;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.PistonBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(PistonBlockEntity.class)
public interface PistonBlockAccessor {
    @Accessor("progress")
    float getPistonProgress();

    @Accessor("extending")
    boolean getExtending();

    @Accessor("source")
    boolean getSource();
}
