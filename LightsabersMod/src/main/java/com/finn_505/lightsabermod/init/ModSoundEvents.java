package com.finn_505.lightsabermod.init;

import com.finn_505.lightsabermod.util.Reference;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

@ObjectHolder(Reference.MOD_ID)
public class ModSoundEvents {

	@ObjectHolder("lightsaber_block")
	public static final SoundEvent LIGHTSABER_BLOCK = createSoundEvent("lightsaber_block");

	@ObjectHolder("lightsaber_swing")
	public static final SoundEvent LIGHTSABER_SWING = createSoundEvent("lightsaber_swing");
	
	@ObjectHolder("lightsaber_rotate")
	public static final SoundEvent LIGHTSABER_ROTATE = createSoundEvent("lightsaber_rotate");

	@ObjectHolder("lightsaber_on")
	public static final SoundEvent LIGHTSABER_ON = createSoundEvent("lightsaber_on");
	
	@ObjectHolder("lightsaber_off")
	public static final SoundEvent LIGHTSABER_OFF = createSoundEvent("lightsaber_off");
	
	@ObjectHolder("lightsaber_idle")
	public static final SoundEvent LIGHTSABER_IDLE = createSoundEvent("lightsaber_idle");
	
	@ObjectHolder("lightsaber_strike")
	public static final SoundEvent LIGHTSABER_STRIKE = createSoundEvent("lightsaber_strike");
	
	@ObjectHolder("menu_select")
	public static final SoundEvent MENU_SELECT = createSoundEvent("menu_select");
	
	@ObjectHolder("sith_lightning")
	public static final SoundEvent SITH_LIGHTNING = createSoundEvent("sith_lightning");
	
	@ObjectHolder("blaster")
	public static final SoundEvent BLASTER = createSoundEvent("blaster");

	/**
	 * Create a {@link SoundEvent}.
	 *
	 * @param soundName The SoundEvent's name without the testmod3 prefix
	 * @return The SoundEvent
	 */
	private static SoundEvent createSoundEvent(String soundName) {
		final ResourceLocation soundID = new ResourceLocation(Reference.MOD_ID, soundName);
		return new SoundEvent(soundID).setRegistryName(soundID);
	}

	@Mod.EventBusSubscriber
	public static class RegistrationHandler {
		@SubscribeEvent
		public static void registerSoundEvents(RegistryEvent.Register<SoundEvent> event) {
			event.getRegistry().registerAll(
					LIGHTSABER_BLOCK,
					LIGHTSABER_SWING,
					LIGHTSABER_ON,
					LIGHTSABER_OFF,
					LIGHTSABER_IDLE,
					LIGHTSABER_STRIKE,
					LIGHTSABER_ROTATE,
					MENU_SELECT,
					SITH_LIGHTNING,
					BLASTER
			);
		}
	}
}