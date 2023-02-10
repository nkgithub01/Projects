package com.finn_505.lightsabermod.entity.lightsaber;

import org.lwjgl.opengl.GL11;

import com.finn_505.lightsabermod.util.Reference;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderLightsaber extends RenderLiving{

	public static final ResourceLocation[] TEXTURES = {
			new ResourceLocation(Reference.MOD_ID + ":textures/entity/entitylightsaber.png"),
			new ResourceLocation(Reference.MOD_ID + ":textures/entity/entitylightsaberblue.png"),
			new ResourceLocation(Reference.MOD_ID + ":textures/entity/entitylightsaberpurple.png"),
			new ResourceLocation(Reference.MOD_ID + ":textures/entity/entitylightsaberred.png"),
	};

	public int color;
	
	@Override
	protected void preRenderCallback(EntityLivingBase entitylivingbaseIn, float partialTickTime) {
		// TODO Auto-generated method stub
		super.preRenderCallback(entitylivingbaseIn, partialTickTime);
		GL11.glScalef(0.5F, 0.5F, 0.5F);
	}
	
	public RenderLightsaber(RenderManager renderManager) {
		super(renderManager, new ModelLightsaber(), 0.0F);
		color = 0;
		//GL11.glScalef(0.5F, 0.5F, 0.5F);
	}
	
	public RenderLightsaber(RenderManager renderManager, int color) {
		super(renderManager, new ModelLightsaber(), 0.0F);
		this.color = color;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return TEXTURES[color];
	}
	/*
	@Override
	protected void applyRotations(EntityLivingBase entityLiving, float p_77043_2_, float rotationYaw,
			float partialTicks) {
		super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
	}
	*/
}
