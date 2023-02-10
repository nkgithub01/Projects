package com.finn_505.lightsabermod.entity.grip;

import java.util.List;
import java.util.UUID;

import com.finn_505.lightsabermod.Main;
import com.finn_505.lightsabermod.entity.lightsaber.EntityLightsaber;
import com.finn_505.lightsabermod.entity.lightsaber.EntityLightsaberBlue;
import com.finn_505.lightsabermod.entity.lightsaber.EntityLightsaberPurple;
import com.finn_505.lightsabermod.entity.lightsaber.EntityLightsaberRed;
import com.finn_505.lightsabermod.init.ModBlocks;
import com.finn_505.lightsabermod.init.ModItems;
import com.finn_505.lightsabermod.init.ModSoundEvents;
import com.finn_505.lightsabermod.util.force.ForceProvider;
import com.finn_505.lightsabermod.util.force.IForce;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityOwnable;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;

public class EntityGrip extends EntityFlying implements IEntityOwnable{

	public UUID ownerId;
	public double distance;
	public Entity rider;
	public boolean choke;
	
	public EntityGrip(World worldIn) {
		super(worldIn);
		this.setSize(0.1F, 0.1F);
		this.setEntityInvulnerable(true);
		this.isImmuneToFire = true;
		distance = 2;
		choke = false;
	}
	
	public EntityGrip(World worldIn, EntityPlayer Owner, Entity rider, boolean choke)
	{
		super(worldIn);
		this.setSize(0.5F, 0.5F);
		this.setOwnerId(Owner.getUniqueID());
		this.setEntityInvulnerable(true);
		this.isImmuneToFire = true;
		this.rider = rider;
		this.distance = this.getDistance(this.getOwner());
		this.choke = choke;
	}
	
	@Override
	public boolean getLeashed() {
		return false;
	}
	
	@Override
	public void onDeath(DamageSource cause) {
		return;
	}
	
	@Override
	protected void playStepSound(BlockPos pos, Block blockIn) {
		return;
	}
	
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(1000.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
    }
	
	@Override
	protected void initEntityAI() {
		super.initEntityAI();
	}
	
	public void onLivingUpdate()
    {
		if(this.getOwner() == null) return;
		if(this.ticksExisted == 1) this.distance = this.getDistance(this.getOwner());
		if(!this.isBeingRidden() || this.getDistance(this.getOwner()) > this.distance + 5
				|| (this.getOwner().getRidingEntity() != null && this.getOwner().getRidingEntity().equals(this.rider)) )
		{
			this.setDead();
			return;
		}
		if(this.getHealth() < 900)
		{
			this.setHealth(1000F);
		}
		double dist = this.distance;
		double x = this.getOwner().posX + this.getOwner().getLookVec().x * dist;
		double y = this.getOwner().posY + this.getOwner().getLookVec().y * dist + 1;
		double z = this.getOwner().posZ + this.getOwner().getLookVec().z * dist;
		if(!world.isBlockFullCube(new BlockPos(x,y,z)) && !world.isBlockFullCube(new BlockPos(x, y+1, z)))
		{
			this.setPosition(x, y, z);
		}
		else if(!world.isBlockFullCube(new BlockPos(x - 1,y,z)) && !world.isBlockFullCube(new BlockPos(x - 1, y+1, z)))
		{
			this.setPosition(x - 1, y, z);
		}
		else if(!world.isBlockFullCube(new BlockPos(x + 1,y,z)) && !world.isBlockFullCube(new BlockPos(x + 1, y+1, z)))
		{
			this.setPosition(x + 1, y, z);
		}
		else if(!world.isBlockFullCube(new BlockPos(x,y,z + 1)) && !world.isBlockFullCube(new BlockPos(x, y+1, z + 1)))
		{
			this.setPosition(x, y, z + 1);
		}
		else if(!world.isBlockFullCube(new BlockPos(x,y,z - 1)) && !world.isBlockFullCube(new BlockPos(x, y+1, z - 1)))
		{
			this.setPosition(x, y, z-1);
		}
		if(this.getOwner() == null || !(this.getOwner() instanceof EntityPlayer)) this.setDead();
		IForce force = this.getOwner().getCapability(ForceProvider.FORCE, null);
		force.setTicksSinceLastUsedForce(0);
		if(force.canUseForce(2))
		{
			force.useForce(2);
		}
		else
		{
			this.setDead();
		}
		if(this.choke && this.ticksExisted >= 60 && this.ticksExisted % 20 == 0 && !(this.rider instanceof EntityLightsaber || 
				this.rider instanceof EntityLightsaberBlue || this.rider instanceof EntityLightsaberPurple || 
				this.rider instanceof EntityLightsaberRed))
		{
			if(this.rider instanceof EntityLiving && this.getOwner() instanceof EntityPlayer)
			{
				this.rider.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer)this.getOwner()), 1.0F + ((this.ticksExisted - 60) /10F));
			}
			if(force.canUseForce(1))
			{
				force.useForce(1);
			}
			else
			{
				this.setDead();
			}
		}
    }
	
	@Override
	public boolean shouldRiderSit() {
		return false;
	}
	
	@Override
	protected boolean canBeRidden(Entity entityIn) {
		return !(entityIn instanceof EntityGrip);
	}
	
	@Override
	public boolean shouldDismountInWater(Entity rider) {
		return false;
	}
	
	public void setOwnerId(UUID ID)
	{
		this.ownerId = ID;
	}

	@Override
	public UUID getOwnerId() {
		return ownerId;
	}

	@Override
	public Entity getOwner() {
		if(ownerId != null)
		return world.getPlayerEntityByUUID(ownerId);
		return world.getClosestPlayerToEntity(this, 10.0D);
	}
}
