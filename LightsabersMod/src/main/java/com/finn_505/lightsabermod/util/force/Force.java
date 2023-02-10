package com.finn_505.lightsabermod.util.force;

public class Force implements IForce{

	private int blocking = 0;
	private int hate = 0;
	private int maxForce = 200;
	private int equippedAbility = 0;
	private boolean passives = true;
	private boolean jumpAllowed = true;
	private int currentForce = 0;
	private int maxPassiveForce = 80;
	private int currentPassiveForce = 0;
	private int gripId = 0;
	private boolean usingForce = false;
	private int ticksSinceLastUse = Integer.MAX_VALUE;
	private int jumpStartHeight = 0;
	private int mindTrickId = 0;
	private int mindTrickTicks = 0;
	private int trickedTime = 0;
	private boolean usingLightning = false;
	private int blasterCooldown = 0;
	
	@Override
	public int getBlocking() {
		return blocking;
	}

	@Override
	public void setBlocking(int blocking) {
		this.blocking = blocking;
	}

	@Override
	public int getHate() {
		return hate;
	}

	@Override
	public void setHate(int hate) {
		this.hate = hate;
	}

	@Override
	public void setMaxForce(int force) {
		this.maxForce = force;
	}

	@Override
	public int getMaxForce() {
		return this.maxForce;
	}
	
	@Override
	public void setMaxPassiveForce(int force) {
		this.maxPassiveForce = force;
	}

	@Override
	public int getMaxPassiveForce() {
		return this.maxPassiveForce;
	}

	@Override
	/**
	 * set the current force ability the player is using
	 * @param 0 = no ability equipped, 1 = force pull, 2 = force push
	 */
	public void setEquippedAbility(int forceAbility) {
		this.equippedAbility = forceAbility;
	}

	@Override
	/**
	 * get the current force ability the player is using
	 * @return 0 = no ability equipped, 1 = force pull, 2 = force push
	 */
	public int getEquippedAbility() {
		return this.equippedAbility;
	}
	
	@Override
	public void setPassives(boolean onOff) {
		this.passives = onOff;
	}
	
	@Override
	public boolean getPassive() {
		return this.passives;
	}

	@Override
	public void setJumpAllowed(boolean jumpAllowed) {
		this.jumpAllowed = jumpAllowed;
	}

	@Override
	public boolean getJumpAllowed() {
		return this.jumpAllowed;
	}

	@Override
	public void useForce(int amount) {
		this.currentForce = Math.max(0, this.currentForce - amount);
		this.currentForce = Math.min(this.maxForce, this.currentForce);
	}
	
	@Override
	public boolean canUseForce(int amount) {
		return this.currentForce >= amount;
	}
	
	@Override
	public int getForce() {
		return this.currentForce;
	}
	
	@Override
	public void setForce(int amount) {
		this.currentForce = amount;
	}

	@Override
	public void usePassiveForce(int amount) {
		this.currentPassiveForce = Math.max(0, this.currentPassiveForce - amount);
		this.currentPassiveForce = Math.min(maxPassiveForce, this.currentPassiveForce);
	}

	@Override
	public boolean canUsePassiveForce(int amount) {
		return this.currentPassiveForce >= amount;
	}

	@Override
	public int getPassiveForce() {
		return this.currentPassiveForce;
	}

	@Override
	public void setPassiveForce(int amount) {
		this.currentPassiveForce = amount;
	}
	
	@Override
	public int getGripId() {
		return gripId;
	}
	
	@Override
	public void setGripId(int id) {
		this.gripId = id;
	}

	@Override
	public boolean isUsingForce() {
		return this.usingForce;
	}

	@Override
	public void setUsingForce(boolean using) {
		this.usingForce = using;
	}

	@Override
	public int ticksSinceLastUsedForce() {
		return this.ticksSinceLastUse;
	}

	@Override
	public void addTickSinceLastUsage() {
		this.ticksSinceLastUse++;
	}

	@Override
	public void setTicksSinceLastUsedForce(int amount) {
		this.ticksSinceLastUse = amount;
	}

	@Override
	public void setJumpStartHeight(int height) {
		this.jumpStartHeight = height;
	}

	
	@Override
	public int getJumpStartHeight() {
		return this.jumpStartHeight;
	}

	@Override
	public int getMindTrickId() {
		return this.mindTrickId;
	}

	@Override
	public void setMindTrickId(int id) {
		this.mindTrickId = id;
	}

	@Override
	public int getMindTrickTicks() {
		return this.mindTrickTicks;
	}

	@Override
	public void setMindTrickTicks(int amount) {
		this.mindTrickTicks = amount;
	}

	@Override
	public void decreaseMindTrickTicks(int amount) {
		this.mindTrickTicks = Math.max(0, this.mindTrickTicks - amount);
	}

	@Override
	public int getTrickedTime() {
		return this.trickedTime;
	}

	@Override
	public void setTrickedTime(int amount) {
		this.trickedTime = amount;
	}

	@Override
	public void decreaseTrickedTime(int amount) {
		this.trickedTime = Math.max(0, this.mindTrickTicks - amount);
	}

	@Override
	public boolean isUsingLightning() {
		return this.usingLightning;
	}

	@Override
	public void setUsingLightning(boolean using) {
		this.usingLightning = using;
	}
	
	@Override
	public int getBlasterCooldown() {
		return this.blasterCooldown;
	}
	
	@Override
	public void setBlasterCooldown(int time) {
		this.blasterCooldown = time;
	}
	
	@Override
	public void decrementBlasterCooldown(int amount) {
		this.blasterCooldown = Math.max(0, this.blasterCooldown - amount);
	}
}
