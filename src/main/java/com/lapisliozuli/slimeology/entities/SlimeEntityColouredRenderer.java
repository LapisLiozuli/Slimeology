package com.lapisliozuli.slimeology.entities;

import com.lapisliozuli.slimeology.Slimeology;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class SlimeEntityColouredRenderer extends MobEntityRenderer<SlimeEntityColoured, SlimeEntityColouredModel<SlimeEntityColoured>> {
    String name;
    public final String TEXTURES = "textures/entity/";


    public SlimeEntityColouredRenderer(EntityRendererFactory.Context context, String name) {
        super(context, new SlimeEntityColouredModel(context.getPart(EntityModelLayers.SLIME)), 0.25F);
        this.addFeature(new SlimeColouredOverlayFeatureRenderer(this, context.getModelLoader()));
        this.name = name;
    }


    public void render(SlimeEntityColoured slimeEntityColoured, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        this.shadowRadius = 0.25F * (float)slimeEntityColoured.getSize();
        super.render((SlimeEntityColoured) slimeEntityColoured, f, g, matrixStack, vertexConsumerProvider, i);
    }


    protected void scale(SlimeEntityColoured slimeEntityColoured, MatrixStack matrixStack, float f) {
        float g = 0.999F;
        matrixStack.scale(0.999F, 0.999F, 0.999F);
        matrixStack.translate(0.0D, 0.0010000000474974513D, 0.0D);
        float h = (float)slimeEntityColoured.getSize();
        float i = MathHelper.lerp(f, slimeEntityColoured.lastStretch, slimeEntityColoured.stretch) / (h * 0.5F + 1.0F);
        float j = 1.0F / (i + 1.0F);
        matrixStack.scale(j * h, 1.0F / j * h, j * h);
    }


    @Override
    public Identifier getTexture(final SlimeEntityColoured arg) {
        return new Identifier(Slimeology.MOD_ID, TEXTURES + this.name + ".png");
    }


    public static void registerSlimeEntityColouredRender(EntityType entityType, String name) {
        EntityRendererRegistry.INSTANCE.register(entityType, (context) -> {
            return new SlimeEntityColouredRenderer(context, name);
        });
    }
//    public static void registerSlimeEntityColouredRender(EntityType entityType, String name) {
//        EntityRendererRegistry.INSTANCE.register(entityType, (entityRenderDispatcher, context) -> {
//            return new SlimeEntityColouredRenderer(entityRenderDispatcher, name);
//        });
//    }
}

