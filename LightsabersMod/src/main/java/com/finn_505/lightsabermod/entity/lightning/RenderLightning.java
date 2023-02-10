package com.finn_505.lightsabermod.entity.lightning;

import org.lwjgl.opengl.GL11;

import com.finn_505.lightsabermod.util.Reference;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderLightning extends RenderLiving<EntityLightning>{

	public RenderLightning(RenderManager renderManager) {
		super(renderManager, new ModelLightning(), 0.0F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityLightning entity) {
		return new ResourceLocation(Reference.MOD_ID + ":textures/entity/entitylightning.png");
	}

	@Override
	protected void preRenderCallback(EntityLightning entitylivingbaseIn, float partialTickTime) {
		super.preRenderCallback(entitylivingbaseIn, partialTickTime);
		GL11.glScalef(1.0F, 1.0F, 3.0F);
	}
}
