package com.lapisliozuli.slimeology.entities;

import com.lapisliozuli.slimeology.Slimeology;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

// Copied from SlimeEntityRenderer
// Added this for rendering entities.
@Environment(EnvType.CLIENT)
public class SlimeEntityColouredRenderer extends MobEntityRenderer<SlimeEntityColoured, SlimeEntityColouredModel<SlimeEntityColoured>> {

    String name;
    public final String TEXTURES = "textures/entity/";

    public SlimeEntityColouredRenderer(final EntityRenderDispatcher arg, String name) {
        super(arg, new SlimeEntityColouredModel(16), 0.25f);
        this.addFeature((FeatureRenderer<SlimeEntityColoured, SlimeEntityColouredModel<SlimeEntityColoured>>)new SlimeColouredOverlayFeatureRenderer(this));
        this.name = name;
    }


    @Override
    public void render(final SlimeEntityColoured arg, final float f, final float g, final MatrixStack arg2, final VertexConsumerProvider arg3, final int i) {
        this.shadowRadius = 0.25f * arg.getSize();
        super.render(arg, f, g, arg2, arg3, i);
    }


    @Override
    protected void scale(final SlimeEntityColoured arg, final MatrixStack arg2, final float f) {
        final float g = 0.999f;
        arg2.scale(0.999f, 0.999f, 0.999f);
        arg2.translate(0.0, 0.0010000000474974513, 0.0);
        final float h = (float)arg.getSize();
        final float i = MathHelper.lerp(f, arg.lastStretch, arg.stretch) / (h * 0.5f + 1.0f);
        final float j = 1.0f / (i + 1.0f);
        arg2.scale(j * h, 1.0f / j * h, j * h);
    }


    @Override
//     How to join Strings in Java?
    public Identifier getTexture(final SlimeEntityColoured arg) {
        return new Identifier(Slimeology.MOD_ID, TEXTURES + this.name + ".png");
    }


    public static void registerSlimeEntityColouredRender(EntityType entityType, String name) {
        EntityRendererRegistry.INSTANCE.register(entityType, (entityRenderDispatcher, context) -> {
            return new SlimeEntityColouredRenderer(entityRenderDispatcher, name);
        });
    }
}

