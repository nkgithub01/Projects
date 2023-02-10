package com.finn_505.lightsabermod.util.handlers;

import com.finn_505.lightsabermod.entity.grip.EntityGrip;
import com.finn_505.lightsabermod.entity.grip.RenderGrip;
import com.finn_505.lightsabermod.entity.lightning.EntityLightning;
import com.finn_505.lightsabermod.entity.lightning.RenderLightning;
import com.finn_505.lightsabermod.entity.lightsaber.EntityLightsaber;
import com.finn_505.lightsabermod.entity.lightsaber.EntityLightsaberBlue;
import com.finn_505.lightsabermod.entity.lightsaber.EntityLightsaberPurple;
import com.finn_505.lightsabermod.entity.lightsaber.EntityLightsaberRed;
import com.finn_505.lightsabermod.entity.lightsaber.RenderLightsaber;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler 
{
	public static void registerEntityRenders()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityLightsaber.class, new IRenderFactory<EntityLightsaber>()
		{
			@Override
			public Render<? super EntityLightsaber> createRenderFor(RenderManager manager) {
				return new RenderLightsaber(manager, 0);
			}
		});
		
		RenderingRegistry.registerEntityRenderingHandler(EntityLightsaberBlue.class, new IRenderFactory<EntityLightsaberBlue>()
		{
			@Override
			public Render<? super EntityLightsaberBlue> createRenderFor(RenderManager manager) {
				return new RenderLightsaber(manager, 1);
			}
		});
		
		RenderingRegistry.registerEntityRenderingHandler(EntityLightsaberPurple.class, new IRenderFactory<EntityLightsaberPurple>()
		{
			@Override
			public Render<? super EntityLightsaberPurple> createRenderFor(RenderManager manager) {
				return new RenderLightsaber(manager, 2);
			}
		});
		
		RenderingRegistry.registerEntityRenderingHandler(EntityLightsaberRed.class, new IRenderFactory<EntityLightsaberRed>()
		{
			@Override
			public Render<? super EntityLightsaberRed> createRenderFor(RenderManager manager) {
				return new RenderLightsaber(manager, 3);
			}
		});
		
		RenderingRegistry.registerEntityRenderingHandler(EntityGrip.class, new IRenderFactory<EntityGrip>()
		{
			@Override
			public Render<? super EntityGrip> createRenderFor(RenderManager manager) {
				return new RenderGrip(manager);
			}
		});
		
		RenderingRegistry.registerEntityRenderingHandler(EntityLightning.class, new IRenderFactory<EntityLightning>()
		{
			@Override
			public Render<? super EntityLightning> createRenderFor(RenderManager manager) {
				return new RenderLightning(manager);
			}
		});
	}
}
