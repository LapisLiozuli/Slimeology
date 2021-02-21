package com.lapisliozuli.slimeology.entities;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;

public class SlimeColouredOverlayFeatureRenderer<T extends LivingEntity> extends FeatureRenderer<T, SlimeEntityColouredModel<T>>
{
    private final EntityModel<T> model;

    public SlimeColouredOverlayFeatureRenderer(final FeatureRendererContext<T, SlimeEntityColouredModel<T>> arg) {
        super(arg);
        this.model = new SlimeEntityColouredModel<T>(0);
    }

    @Override
    public void render(final MatrixStack arg, final VertexConsumerProvider arg2, final int i, final T arg3, final float f, final float g, final float h, final float j, final float k, final float l) {
        if (arg3.isInvisible()) {
            return;
        }
        this.getContextModel().copyStateTo(this.model);
        this.model.animateModel(arg3, f, g, h);
        this.model.setAngles(arg3, f, g, j, k, l);
        final VertexConsumer lv = arg2.getBuffer(RenderLayer.getEntityTranslucent(this.getTexture(arg3)));
        this.model.render(arg, lv, i, LivingEntityRenderer.getOverlay(arg3, 0.0f), 1.0f, 1.0f, 1.0f, 1.0f);
    }
}
