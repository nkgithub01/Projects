package com.finn_505.lightsabermod.entity.lightning;

import java.util.List;
import java.util.UUID;

import com.finn_505.lightsabermod.Main;
import com.finn_505.lightsabermod.init.ModBlocks;
import com.finn_505.lightsabermod.init.ModItems;
import com.finn_505.lightsabermod.init.ModSoundEvents;
import com.finn_505.lightsabermod.lighting.MovingLightSource;
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

public class EntityLightning extends EntityFlying implements IEntityOwnable{

	public UUID ownerId;
	
	public EntityLightning(World worldIn) {
		super(worldIn);
		this.setSize(0.1F, 1.0F);
		this.setEntityInvulnerable(true);
		this.isImmuneToFire = true;
	}
	
	public EntityLightning(World worldIn, EntityPlayer Owner)
	{
		super(worldIn);
		this.setSize(0.5F, 0.5F);
		this.setOwnerId(Owner.getUniqueID());
		this.setEntityInvulnerable(true);
		this.isImmuneToFire = true;
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
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
    }
	
	@Override
	protected void initEntityAI() {
		super.initEntityAI();
	}
	
	public void onLivingUpdate()
    {
		if(this.ownerId == null && this.world.playerEntities != null && this.world.playerEntities.size() > 0)
		{
			this.ownerId = this.world.playerEntities.get(0).getUniqueID();
		}
		if(this.ownerId == null) return;
		
		double x = this.getPlayerOwner().posX + this.getPlayerOwner().getLookVec().x/2;
		double y = this.getPlayerOwner().posY + this.getPlayerOwner().getLookVec().y/2;
		double z = this.getPlayerOwner().posZ + this.getPlayerOwner().getLookVec().z/2;
		
		
		this.setPosition(x, y + 0.5, z);
		this.setRotation(this.getPlayerOwner().rotationYaw, this.getPlayerOwner().rotationPitch);
		//this.getLookHelper().setLookPosition(x, y, z, 360, 360);
		//this.getLookHelper().onUpdateLook();
		if(world.isAirBlock(this.getPosition()))world.setBlockState(getPosition(), ModBlocks.LIGHT_SOURCE.getDefaultState());
		IForce force = this.getPlayerOwner().getCapability(ForceProvider.FORCE, null);
		if(!force.isUsingLightning()) this.setDead();
    }
	
	@Override
	public boolean shouldRiderSit() {
		return false;
	}
	
	@Override
	protected boolean canBeRidden(Entity entityIn) {
		return !(entityIn instanceof EntityLightning);
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

	public EntityPlayer getPlayerOwner()
	{
		if(this.ownerId == null)
		{
			return world.getClosestPlayerToEntity(this, 10.0D);
		}
		else if(world.getPlayerEntityByUUID(ownerId)!=null)
		{
			return world.getPlayerEntityByUUID(ownerId);
		}
		else
		{
			return world.playerEntities.get(0);
		}
	}
	
	@Override
	public Entity getOwner() {
		if(this.ownerId == null)
		{
			return world.getClosestPlayerToEntity(this, 10.0D);
		}
		else if(world.getPlayerEntityByUUID(ownerId)!=null)
		{
			return world.getPlayerEntityByUUID(ownerId);
		}
		else
		{
			return world.playerEntities.get(0);
		}
	}
}
