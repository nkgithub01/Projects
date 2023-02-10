package com.finn_505.lightsabermod.init;

import com.finn_505.lightsabermod.Main;
import com.finn_505.lightsabermod.entity.grip.EntityGrip;
import com.finn_505.lightsabermod.entity.lightning.EntityLightning;
import com.finn_505.lightsabermod.entity.lightsaber.EntityLightsaber;
import com.finn_505.lightsabermod.entity.lightsaber.EntityLightsaberBlue;
import com.finn_505.lightsabermod.entity.lightsaber.EntityLightsaberPurple;
import com.finn_505.lightsabermod.entity.lightsaber.EntityLightsaberRed;
import com.finn_505.lightsabermod.util.Reference;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityInit 
{
	public static void registerEntities()
	{
		registerEntityWithoutEgg("lightsaber_green", EntityLightsaber.class, Reference.ENTITY_LIGHTSABER, 50);
		registerEntityWithoutEgg("lightsaber_blue", EntityLightsaberBlue.class, Reference.ENTITY_LIGHTSABER_BLUE, 50);
		registerEntityWithoutEgg("lightsaber_purple", EntityLightsaberPurple.class, Reference.ENTITY_LIGHTSABER_PURPLE, 50);
		registerEntityWithoutEgg("lightsaber_red", EntityLightsaberRed.class, Reference.ENTITY_LIGHTSABER_RED, 50);
		registerEntityWithoutEgg("entity_grip", EntityGrip.class, Reference.ENTITY_GRIP, 50);
		registerEntityWithoutEgg("entity_lightning", EntityLightning.class, Reference.ENTITY_LIGHTNING, 50);
	}
	
	private static void registerEntity(String name, Class<? extends Entity> clazz, int id, int range, int color1, int color2)
	{
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" + name), clazz, name, id, Main.instance, range, 1, true, color1, color2);
	}
	
	private static void registerEntityWithoutEgg(String name, Class<? extends Entity> clazz, int id, int range)
	{
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" + name), clazz, name, id, Main.instance, range, 1, true);
	}
}
