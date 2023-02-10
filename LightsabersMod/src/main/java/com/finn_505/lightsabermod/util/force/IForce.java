package com.finn_505.lightsabermod.util.force;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public interface IForce {
	
	int getBlocking();
	
	void setBlocking(int blocking);
	
	int getHate();
	
	void setHate(int hate);

	void setMaxForce(int force);
	
	int getMaxForce();
	
	void setMaxPassiveForce(int force);
	
	int getMaxPassiveForce();
	
	void setEquippedAbility(int forceAbility);
	
	int getEquippedAbility();
	
	void setPassives(boolean onOff);
	
	boolean getPassive();
	
	void setJumpAllowed(boolean jumpAllowed);
	
	boolean getJumpAllowed();
	
	void useForce(int amount);
	
	boolean canUseForce(int amount);
	
	int getForce();
	
	void setForce(int amount);
	
	void usePassiveForce(int amount);
	
	boolean canUsePassiveForce(int amount);
	
	int getPassiveForce();
	
	void setPassiveForce(int amount);
	
	void setGripId(int id);
	
	int getGripId();
	
	boolean isUsingForce();
	
	void setUsingForce(boolean using);
	
	int ticksSinceLastUsedForce();
	
	void addTickSinceLastUsage();
	
	void setTicksSinceLastUsedForce(int amount);
	
	void setJumpStartHeight(int height);
		
	int getJumpStartHeight();
	
	int getMindTrickId();
	
	void setMindTrickId(int id);
	
	int getMindTrickTicks();
	
	void setMindTrickTicks(int amount);
	
	void decreaseMindTrickTicks(int amount);
	
	int getTrickedTime();
	
	void setTrickedTime(int amount);
	
	void decreaseTrickedTime(int amount);
	
	boolean isUsingLightning();
	
	void setUsingLightning(boolean using);
	
	int getBlasterCooldown();
	
	void setBlasterCooldown(int time);
	
	void decrementBlasterCooldown(int amount);
}
