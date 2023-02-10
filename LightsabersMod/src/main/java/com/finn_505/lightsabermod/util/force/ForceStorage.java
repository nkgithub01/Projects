package com.finn_505.lightsabermod.util.force;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class ForceStorage implements IStorage<IForce>{

	@Override
	public NBTBase writeNBT(Capability<IForce> capability, IForce instance, EnumFacing side) {
		NBTTagCompound c = new NBTTagCompound();
		c.setInteger("blocking", instance.getBlocking());
		c.setInteger("hate", instance.getHate());
		c.setInteger("maxForce", instance.getMaxForce());
		c.setInteger("currentAbility", instance.getEquippedAbility());
		c.setBoolean("passives", instance.getPassive());
		c.setBoolean("canJump", instance.getJumpAllowed());
		c.setInteger("force", instance.getForce());
		c.setInteger("maxPassive", instance.getMaxPassiveForce());
		c.setInteger("passiveForce", instance.getPassiveForce());
		c.setInteger("grip", instance.getGripId());
		c.setBoolean("usingAbility", instance.isUsingForce());
		c.setInteger("ticksSince", instance.ticksSinceLastUsedForce());
		c.setInteger("forceInJump", instance.getJumpStartHeight());
		c.setInteger("mindTrick", instance.getMindTrickId());
		c.setInteger("mindTicks", instance.getMindTrickTicks());
		c.setInteger("tricked", instance.getTrickedTime());
		c.setBoolean("lightning", instance.isUsingLightning());
		c.setInteger("blastercooldown", instance.getBlasterCooldown());
		return c;
	}

	@Override
	public void readNBT(Capability<IForce> capability, IForce instance, EnumFacing side,
			NBTBase nbt) {
		instance.setBlocking(((NBTTagCompound) nbt).getInteger("blocking"));
		instance.setHate(((NBTTagCompound) nbt).getInteger("hate"));
		instance.setMaxForce(((NBTTagCompound) nbt).getInteger("maxForce"));
		instance.setEquippedAbility(((NBTTagCompound) nbt).getInteger("currentAbility"));
		instance.setPassives(((NBTTagCompound) nbt).getBoolean("passives"));
		instance.setJumpAllowed(((NBTTagCompound) nbt).getBoolean("canJump"));
		instance.setForce(((NBTTagCompound) nbt).getInteger("force"));
		instance.setMaxPassiveForce(((NBTTagCompound) nbt).getInteger("maxPassive"));
		instance.setPassiveForce(((NBTTagCompound) nbt).getInteger("passiveForce"));
		instance.setGripId(((NBTTagCompound) nbt).getInteger("grip"));
		instance.setUsingForce(((NBTTagCompound) nbt).getBoolean("usingAbility"));
		instance.setTicksSinceLastUsedForce(((NBTTagCompound) nbt).getInteger("ticksSince"));
		instance.setJumpStartHeight(((NBTTagCompound) nbt).getInteger("forceInJump"));
		instance.setMindTrickId(((NBTTagCompound) nbt).getInteger("mindTrick"));
		instance.setMindTrickTicks(((NBTTagCompound) nbt).getInteger("mindTicks"));
		instance.setTrickedTime(((NBTTagCompound) nbt).getInteger("tricked"));
		instance.setUsingLightning(((NBTTagCompound) nbt).getBoolean("lightning"));
		instance.setBlasterCooldown(((NBTTagCompound) nbt).getInteger("blastercooldown"));
	}

}
