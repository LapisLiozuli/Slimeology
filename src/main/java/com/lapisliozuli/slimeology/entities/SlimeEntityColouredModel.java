package com.lapisliozuli.slimeology.entities;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.CompositeEntityModel;
import net.minecraft.entity.Entity;

// Copied from SlimeEntityModel
public class SlimeEntityColouredModel<T extends Entity> extends CompositeEntityModel<T> {

        private ModelPart innerCube;
        private ModelPart rightEye;
        private ModelPart leftEye;
        private ModelPart mouth;

        public SlimeEntityColouredModel(final int size) {
            this.innerCube = new ModelPart(this, 0, size);
            this.rightEye = new ModelPart(this, 32, 0);
            this.leftEye = new ModelPart(this, 32, 4);
            this.mouth = new ModelPart(this, 32, 8);
            if (size > 0) {
                this.innerCube.addCuboid(-3.0f, 17.0f, -3.0f, 6.0f, 6.0f, 6.0f);
                this.rightEye.addCuboid(-3.25f, 18.0f, -3.5f, 2.0f, 2.0f, 2.0f);
                this.leftEye.addCuboid(1.25f, 18.0f, -3.5f, 2.0f, 2.0f, 2.0f);
                this.mouth.addCuboid(0.0f, 21.0f, -3.5f, 1.0f, 1.0f, 1.0f);
            }
            else {
                this.innerCube.addCuboid(-4.0f, 16.0f, -4.0f, 8.0f, 8.0f, 8.0f);
            }
        }

        @Override
        public void setAngles(final T entity, final float limbAngle, final float limbDistance, final float animationProgress, final float headYaw, final float headPitch) {
        }

        @Override
        public Iterable<ModelPart> getParts() {
            return ImmutableList.of(this.innerCube, this.rightEye, this.leftEye, this.mouth);
        }
}

