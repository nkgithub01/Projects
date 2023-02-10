package com.finn_505.lightsabermod.util.force;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class ForceProvider implements ICapabilitySerializable<NBTBase>{

	@CapabilityInject(IForce.class)
	public static final Capability<IForce> FORCE = null;
	private IForce instance = FORCE.getDefaultInstance();
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == FORCE;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability == FORCE ? FORCE.<T> cast(this.instance) : null;
	}

	@Override
	public NBTBase serializeNBT() {
		return FORCE.getStorage().writeNBT(FORCE, instance, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
		FORCE.getStorage().readNBT(FORCE, instance, null, nbt);
	}

}
