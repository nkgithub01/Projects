package com.finn_505.lightsabermod.init;

import com.finn_505.lightsabermod.triggers.HateTrigger;

public class ModTriggers {

	public static final HateTrigger HATE_10_TRIGGER = new HateTrigger("player_hate");
	public static final HateTrigger SABER_CATCH = new HateTrigger("player_catch");
	public static final HateTrigger SABER_DEFLECT = new HateTrigger("player_deflect");

	
	public static final HateTrigger[] TRIGGERS = {HATE_10_TRIGGER, SABER_CATCH, SABER_DEFLECT};
}
