package com.finn_505.lightsabermod.util.handlers;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.finn_505.lightsabermod.Main;
import com.finn_505.lightsabermod.entity.lightsaber.EntityLightsaber;
import com.finn_505.lightsabermod.init.ModSoundEvents;
import com.finn_505.lightsabermod.init.ModTriggers;
import com.finn_505.lightsabermod.util.Reference;
import com.finn_505.lightsabermod.util.force.ForceProvider;
import com.finn_505.lightsabermod.util.force.IForce;
import com.finn_505.lightsabermod.util.packets.forcesync.ForceSync;
import com.finn_505.lightsabermod.util.packets.forcesync.ForceSyncServer;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventBusHandler {

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void lightsaberDeflect (LivingAttackEvent event) throws InstantiationException, IllegalAccessException
	{
		if(!event.getEntity().world.isRemote)
		{
			DamageSource source = event.getSource();
			if(!(event.getEntity() instanceof EntityPlayer) || !(event.getSource().isProjectile())) return;
			EntityPlayer player = (EntityPlayer)event.getEntity();
			int blocking = player.getCapability(ForceProvider.FORCE, null).getBlocking();
			if(blocking == 0) return;
			Entity projectile = event.getSource().getImmediateSource();
			if(!(projectile instanceof EntityLightsaber))
			projectile.setDead();
			EnumFacing facing = player.getHorizontalFacing();
			EnumFacing face2 = projectile.getHorizontalFacing();
			if(!(projectile instanceof EntityFireball || projectile instanceof EntityLightsaber))
			{
				if(!facingDeflectableArrow(facing, face2)) return;
			}
			if((projectile instanceof EntityFireball || projectile instanceof EntityLightsaber) && !facingDeflectableFireball(facing, face2))return;
			double range = 1;
			double speedProj = Math.pow(projectile.motionX * projectile.motionX + projectile.motionY *projectile.motionY  + projectile.motionZ * projectile.motionZ, 1/2.0);
			player.world.playSound(null, player.getPosition(), ModSoundEvents.LIGHTSABER_BLOCK, SoundCategory.NEUTRAL, 1.0F, 1.0F);
			Constructor<Entity> projectile2Maker = null;
			if(projectile instanceof EntityLightsaber)
			{
				player.world.playSound(null, player.getPosition(), ModSoundEvents.LIGHTSABER_STRIKE, SoundCategory.NEUTRAL, 1.0F, 1.0F);
			}
			if(projectile instanceof EntityFireball)
			{
				EntityLargeFireball fireball = new EntityLargeFireball(player.world, player, player.getLookVec().x, 0, player.getLookVec().z);
				fireball.motionX = player.getLookVec().x;
				fireball.motionY = player.getLookVec().y;
				fireball.motionZ = player.getLookVec().z;
				fireball.posY++;
				player.world.spawnEntity(fireball);
			}
			else if(projectile instanceof EntityThrowable)
			{
				try {
					projectile2Maker = (Constructor<Entity>) projectile.getClass().getDeclaredConstructor(World.class, EntityLivingBase.class);
				} catch (NoSuchMethodException | SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(projectile2Maker != null)
				{
					Entity projectile2 = null;
					try {
						projectile2 = projectile2Maker.newInstance(player.world, player);
					} catch (IllegalArgumentException | InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					if(projectile2 != null)
					{
						projectile2.setPositionAndRotation(player.posX + player.getLookVec().x,projectile.posY, player.posZ + player.getLookVec().z, player.cameraYaw, player.cameraPitch);
						projectile2.setVelocity(player.getLookVec().x * speedProj, player.getLookVec().y * speedProj, player.getLookVec().z * speedProj);
						player.world.spawnEntity(projectile2);
					}
				}
				projectile.setInvisible(true);
			}
			else
			{
				try {
					projectile2Maker = (Constructor<Entity>) projectile.getClass().getDeclaredConstructor(World.class);
				} catch (NoSuchMethodException | SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(projectile2Maker != null)
				{
					Entity projectile2 = null;
					try {
						projectile2 = projectile2Maker.newInstance(player.world);
					} catch (IllegalArgumentException | InvocationTargetException e) {
						e.printStackTrace();
					}
					if(projectile2 != null)
					{
						projectile2.setPositionAndRotation(player.posX + player.getLookVec().x, projectile.posY, player.posZ + player.getLookVec().z, player.cameraYaw, player.cameraPitch);
						projectile2.setVelocity(player.getLookVec().x * speedProj, player.getLookVec().y * speedProj, player.getLookVec().z * speedProj);
						player.world.spawnEntity(projectile2);
					}
				}
				projectile.setInvisible(true);
			}
			ModTriggers.SABER_DEFLECT.trigger((EntityPlayerMP)(player));
			event.setCanceled(true);
		}
	}
	
	@SubscribeEvent
	public void releaseGrip(LivingAttackEvent event)
	{
		if(!(event.getEntity() instanceof EntityPlayer)) return;
		
		EntityPlayer player = (EntityPlayer)event.getEntity();
		IForce force = player.getCapability(ForceProvider.FORCE, null);
		if(!player.world.isRemote)
		{
			if(player.world.getEntityByID(force.getGripId()) != null)
			{
				player.world.getEntityByID(force.getGripId()).setDead();
			}
		}
	}
	
	private boolean facingDeflectableArrow(EnumFacing f1, EnumFacing f2)
	{
		if((f1 == EnumFacing.NORTH && f2 == EnumFacing.SOUTH)
		|| (f1 == EnumFacing.SOUTH && f2 == EnumFacing.NORTH)
		|| (f1 == EnumFacing.WEST && f2 == EnumFacing.WEST)
		|| (f1 == EnumFacing.EAST && f2 == EnumFacing.EAST)
		|| (f1 == EnumFacing.UP && f2 == EnumFacing.DOWN)
		|| (f1 == EnumFacing.DOWN && f2 == EnumFacing.UP)) 
			return true;
		
		return false;
	}
	
	private boolean facingDeflectableFireball(EnumFacing f1, EnumFacing f2)
	{
		if((f1 == EnumFacing.NORTH && f2 == EnumFacing.NORTH)
		|| (f1 == EnumFacing.SOUTH && f2 == EnumFacing.SOUTH)
		|| (f1 == EnumFacing.WEST && f2 == EnumFacing.WEST)
		|| (f1 == EnumFacing.EAST && f2 == EnumFacing.EAST)
		|| (f1 == EnumFacing.UP && f2 == EnumFacing.UP)
		|| (f1 == EnumFacing.DOWN && f2 == EnumFacing.DOWN)) 
			return true;
		
		return false;
	}
	
	@SubscribeEvent
	public void onFall(LivingFallEvent event)
	{
		if(event.getEntity().world.isRemote || !(event.getEntity() instanceof EntityPlayer)) return;
		EntityPlayer player = (EntityPlayer) event.getEntity();
		if(player.getCapability(ForceProvider.FORCE, null).getPassive())
		{
			event.setCanceled(true);
		}
	}
	
	@SubscribeEvent
	public void onVillagerKill(LivingDeathEvent e)
	{
		if(!(e.getSource().getTrueSource() instanceof EntityPlayer)) return;
		if(!(e.getEntity() instanceof EntityVillager)) return;
		EntityPlayer player = (EntityPlayer)e.getSource().getTrueSource();
		IForce force = player.getCapability(ForceProvider.FORCE, null);
		force.setHate(force.getHate() + 1);
		syncForceToClient(player);
		if(force.getHate() >= 10 && !player.world.isRemote)
		{
			ModTriggers.HATE_10_TRIGGER.trigger((EntityPlayerMP)player);
		}
	}
	
	public static final ResourceLocation BLOCKING = new ResourceLocation(Reference.MOD_ID, "blocking");
	
	
	@SubscribeEvent
	public void attachCapability(AttachCapabilitiesEvent<Entity> event)
	{
		if(!(event.getObject() instanceof EntityPlayer)) return;
		
		event.addCapability(BLOCKING, new ForceProvider());
	}
	
	
	/**
     * syncs force abilities by sending packet to server
     */
    public void syncForceToServer(EntityPlayer player)
    {
    	IForce force = player.getCapability(ForceProvider.FORCE, null);
    	Main.network.sendToServer(new ForceSyncServer(force.getBlocking(),
        		force.getHate(), force.getMaxForce(), force.getEquippedAbility(), force.getPassive(), force.getJumpAllowed(),
        		force.getForce(), force.getMaxPassiveForce(), force.getPassiveForce(), force.getGripId(), force.isUsingForce(), 
        		force.ticksSinceLastUsedForce(), force.getJumpStartHeight(), force.getMindTrickId(), force.getMindTrickTicks(), 
        		force.getTrickedTime(), force.isUsingLightning(), force.getBlasterCooldown()));
    }
    
    /**
     * syncs force abilities by sending packet to client
     */
    public void syncForceToClient(EntityPlayer player)
    {
    	IForce force = player.getCapability(ForceProvider.FORCE, null);
    	Main.network.sendTo(new ForceSync(force.getBlocking(),
        		force.getHate(), force.getMaxForce(), force.getEquippedAbility(), force.getPassive(), force.getJumpAllowed(),
        		force.getForce(), force.getMaxPassiveForce(), force.getPassiveForce(), force.getGripId(), force.isUsingForce(), 
        		force.ticksSinceLastUsedForce(), force.getJumpStartHeight(), force.getMindTrickId(), force.getMindTrickTicks(), 
        		force.getTrickedTime(), force.isUsingLightning(), force.getBlasterCooldown()), (EntityPlayerMP)player);
    }
}
