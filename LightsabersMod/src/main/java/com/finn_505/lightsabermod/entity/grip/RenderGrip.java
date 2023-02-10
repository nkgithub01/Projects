package com.finn_505.lightsabermod.entity.grip;

import org.lwjgl.opengl.GL11;

import com.finn_505.lightsabermod.entity.lightsaber.ModelLightsaber;
import com.finn_505.lightsabermod.util.Reference;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderGrip extends RenderLiving{

	
	public RenderGrip(RenderManager renderManager) {
		super(renderManager, new ModelGrip(), 0.0F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation("lm:textures/entity/e.png");
	}

	@Override
	protected void applyRotations(EntityLivingBase entityLiving, float p_77043_2_, float rotationYaw,
			float partialTicks) {
		super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
	}
}
