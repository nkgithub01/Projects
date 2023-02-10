package com.finn_505.lightsabermod.util.handlers;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;

import com.finn_505.lightsabermod.Main;
import com.finn_505.lightsabermod.entity.lightning.EntityLightning;
import com.finn_505.lightsabermod.init.ModBlocks;
import com.finn_505.lightsabermod.init.ModItems;
import com.finn_505.lightsabermod.init.ModSoundEvents;
import com.finn_505.lightsabermod.items.Lightsaber;
import com.finn_505.lightsabermod.items.focus.ItemFocus;
import com.finn_505.lightsabermod.lighting.MovingLightSource;
import com.finn_505.lightsabermod.util.Reference;
import com.finn_505.lightsabermod.util.force.ForceProvider;
import com.finn_505.lightsabermod.util.force.IForce;
import com.finn_505.lightsabermod.util.packets.ChangeSaberBlockState;
import com.finn_505.lightsabermod.util.packets.ChangeSaberBlockStateOff;
import com.finn_505.lightsabermod.util.packets.ForceUse;
import com.finn_505.lightsabermod.util.packets.MainThrowGreen;
import com.finn_505.lightsabermod.util.packets.OffThrowGreen;
import com.finn_505.lightsabermod.util.packets.setFloating;
import com.finn_505.lightsabermod.util.packets.forcesync.ForceSync;
import com.finn_505.lightsabermod.util.packets.forcesync.ForceSyncServer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.MouseInputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EHandler {
	
	public static boolean keyPress1 = false;
	public static int ticksElapsed;
	
    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
	public void onEvent(PlayerTickEvent event)
	{
    	IForce force = event.player.getCapability(ForceProvider.FORCE, null);
    	
    	if(!(event.player.getHeldItemMainhand().getItem() instanceof ItemFocus || event.player.getHeldItemOffhand().getItem() instanceof ItemFocus))
    	{
    		force.setPassives(false);
    	}
    	
    	if(event.player.getHeldItemMainhand().getItem() instanceof ItemFocus)
    	{
    		ItemFocus focus = ((ItemFocus)event.player.getHeldItemMainhand().getItem());
    		force.setMaxPassiveForce(focus.getMaxPassiveForce());
    		force.setMaxForce(focus.getMaxForce());
    		if(focus.isJediFocus() && force.getEquippedAbility() > 4) force.setEquippedAbility(0);
    		else if(!focus.isJediFocus() && force.getEquippedAbility() < 3) force.setEquippedAbility(0);
    	}
    	else if(event.player.getHeldItemOffhand().getItem() instanceof ItemFocus)
    	{
    		ItemFocus focus = ((ItemFocus)event.player.getHeldItemOffhand().getItem());
    		force.setMaxPassiveForce(focus.getMaxPassiveForce());
    		force.setMaxForce(focus.getMaxForce());
    		if(focus.isJediFocus() && force.getEquippedAbility() > 4) force.setEquippedAbility(0);
    		else if(!focus.isJediFocus() && force.getEquippedAbility() < 3) force.setEquippedAbility(0);
    	}

    	if(event.player.onGround)
	    {
	    	force.setJumpAllowed(true);
	    	force.setJumpStartHeight((int)event.player.posY);
	    }
    	
    	if(force.getMaxForce() == 200)
    	{
    		if(event.player.posY > force.getJumpStartHeight() + 10)
        	{
        		force.setJumpAllowed(false);
        	}
    	}
    	else
    	{
    		if(event.player.posY > force.getJumpStartHeight() + 15)
        	{
        		force.setJumpAllowed(false);
        	}
    	}
    	
    	if(event.player.world.isBlockFullCube(new BlockPos(event.player.posX, event.player.posY + 2, event.player.posZ)))
    	{
    		force.setJumpAllowed(false);
    	}
    	
    	if(!force.getPassive() || (event.player.onGround && !event.player.isSprinting()))
    	{
    		force.usePassiveForce(-1);
    	}
    	
    	if(force.getTrickedTime() > 0 && event.player.world.isRemote)
    	{
    		surroundPlayerWithParticles(EnumParticleTypes.CLOUD, event.player);
    	}
    	if(force.getTrickedTime() > 0)
    	{
    		force.decreaseTrickedTime(1);
    	}
    	
	    if(event.phase == TickEvent.Phase.START && !event.player.world.isRemote)
	    {
	    	if(force.getBlocking() == 1)
	    	{
	    		event.player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 2, 2, false, false));
	    	}
	    	
	    	if(force.getBlocking() == 1 && 
	    			!(event.player.getHeldItemMainhand().getItem() instanceof Lightsaber || event.player.getHeldItemOffhand().getItem() instanceof Lightsaber))
	    	{
	    		Main.network.sendToServer(new ChangeSaberBlockStateOff());
	    	}
	    	
	        if ((event.player.getHeldItem(EnumHand.MAIN_HAND) != null &&MovingLightSource.isLightEmittingItem(
	              event.player.getHeldItem(EnumHand.MAIN_HAND).getItem())) ||
	        		(event.player.getHeldItem(EnumHand.OFF_HAND) != null &&MovingLightSource.isLightEmittingItem(
	  	                  event.player.getHeldItem(EnumHand.OFF_HAND).getItem())))
	        {	
	       		event.player.world.playSound(null, event.player.getPosition(), ModSoundEvents.LIGHTSABER_IDLE, SoundCategory.NEUTRAL, 0.7F, 1.0F);
	            int blockX = MathHelper.floor(event.player.posX);
	            int blockY = MathHelper.floor(event.player.posY-0.2D - 
	                  event.player.getYOffset());
	            int blockZ = MathHelper.floor(event.player.posZ);
	            // place light at head level
	            BlockPos blockLocation = new BlockPos(blockX, blockY, blockZ).up();
	            if (event.player.world.getBlockState(blockLocation).getBlock() == 
	                  Blocks.AIR)
	            {
	                event.player.world.setBlockState(
	                      blockLocation, 
	                      ModBlocks.LIGHT_SOURCE.getDefaultState());
	            }
	            else if (event.player.world.getBlockState(
	                  blockLocation.add(
	                        event.player.getLookVec().x, 
	                        event.player.getLookVec().y, 
	                        event.player.getLookVec().z)).getBlock() == Blocks.AIR)
	            {
	                event.player.world.setBlockState(
	                      blockLocation.add(
	                            event.player.getLookVec().x, 
	                            event.player.getLookVec().y, 
	                            event.player.getLookVec().z), 
	                            ModBlocks.LIGHT_SOURCE.getDefaultState());
	            }
	        }
	        
	        if((event.player.getHeldItem(EnumHand.MAIN_HAND) != null && (event.player.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof Lightsaber)))
	        {
	        	int blockX = MathHelper.floor(event.player.posX);
	            int blockY = MathHelper.floor(event.player.posY) + 1;
	            int blockZ = MathHelper.floor(event.player.posZ);
	            World world = event.player.world;
	            BlockPos p = new BlockPos(blockX, blockY, blockZ);
	            if((world.getBlockState(p).getBlock().getDefaultState() == Blocks.WATER.getDefaultState())
						||(world.getBlockState(p).getBlock().getDefaultState() == Blocks.LAVA.getDefaultState())
						||(world.getBlockState(p).getBlock().getDefaultState() == Blocks.FLOWING_WATER.getDefaultState())
						||(world.getBlockState(p).getBlock().getDefaultState() == Blocks.FLOWING_LAVA.getDefaultState()))
	            {
	            	
	            	event.player.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(ModItems.HILTS[((Lightsaber)event.player.getHeldItemMainhand().getItem()).color], 1));
	            }
	        }
	        
	        if((event.player.getHeldItem(EnumHand.OFF_HAND) != null && (event.player.getHeldItem(EnumHand.OFF_HAND).getItem() instanceof Lightsaber)))
	        {
	        	int blockX = MathHelper.floor(event.player.posX);
	            int blockY = MathHelper.floor(event.player.posY) + 1;
	            int blockZ = MathHelper.floor(event.player.posZ);
	            World world = event.player.world;
	            BlockPos p = new BlockPos(blockX, blockY, blockZ);
	            if((world.getBlockState(p).getBlock().getDefaultState() == Blocks.WATER.getDefaultState())
						||(world.getBlockState(p).getBlock().getDefaultState() == Blocks.LAVA.getDefaultState())
						||(world.getBlockState(p).getBlock().getDefaultState() == Blocks.FLOWING_WATER.getDefaultState())
						||(world.getBlockState(p).getBlock().getDefaultState() == Blocks.FLOWING_LAVA.getDefaultState()))
	            {
	            	event.player.setHeldItem(EnumHand.OFF_HAND, new ItemStack(ModItems.HILTS[((Lightsaber)event.player.getHeldItemOffhand().getItem()).color], 1));
	            }
	        }
	        
	        
	        double dist = 10;
			double x = event.player.posX + event.player.getLookVec().x * dist;
			double y = event.player.posY + event.player.getLookVec().y * dist + 1;
			double z = event.player.posZ + event.player.getLookVec().z * dist;
			double x0 = event.player.posX;
			double y0 = event.player.posY + 2;
			double z0 = event.player.posZ;
			while(Math.abs(x0 - x) < 1)
			{
				x0 += 0.5;
				x -= 0.5;
			}
			while(Math.abs(y0 - y) < 1)
			{
				y0 += 0.5;
				y -= 0.5;
			}
			while(Math.abs(z0 - z) < 1)
			{
				z0 += 0.5;
				z -= 0.5;
			}
			AxisAlignedBB box = new AxisAlignedBB(new BlockPos(x0, y0, z0), new BlockPos(x,y,z));
			List<Entity> entities = event.player.world.getEntitiesWithinAABBExcludingEntity(event.player, box);
			Entity entityIn = null;
			if(entities.size() > 0) entityIn = entities.get(entities.size() - 1);
			if(entityIn == null || !(entityIn instanceof EntityLiving)) 
			{
				force.setUsingLightning(false);
			}
	        	        
	        if(force.getPassive() && force.canUsePassiveForce(1))
	        {
        		if(event.player.isSprinting())
        		{
    	        	event.player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 3, 3, false, false));
    	        	force.usePassiveForce(1);
        		}
	        }
	        force.addTickSinceLastUsage();
	        if(force.ticksSinceLastUsedForce() > 100)
	        {
	        	force.useForce(-3);
	        }
	        else if(force.ticksSinceLastUsedForce() < 0)
	        {
	        	force.setTicksSinceLastUsedForce(0);
	        }
	        if(force.isUsingForce()) force.setBlocking(0);
	        if(force.getBlasterCooldown() > 0) force.decrementBlasterCooldown(1);
	        syncForceToClient(event.player);
	    }
	}
    
    
    
    
    @SubscribeEvent
    public void onEvent(ClientTickEvent event)
    {
    	EntityPlayer player = Minecraft.getMinecraft().player;
    	if(player == null) return;
    	IForce force = player.getCapability(ForceProvider.FORCE, null);
    	if(force.getPassive())
        {
    		if(Minecraft.getMinecraft().gameSettings.keyBindJump.isKeyDown() && force.getJumpAllowed() && force.canUsePassiveForce(2))
    		{
    			player.addPotionEffect(new PotionEffect(MobEffects.LEVITATION,1, 10, false, false));
    			Main.network.sendToServer(new setFloating(true, 1));
    			force.usePassiveForce(2);
    		}
    		else if(!player.onGround) 
    		{
    			player.removeActivePotionEffect(MobEffects.LEVITATION);
    			Main.network.sendToServer(new setFloating(false));
    			force.setJumpAllowed(false);
    		}
        }
    	if(player.getHeldItemMainhand().getItem() instanceof ItemFocus || player.getHeldItemOffhand().getItem() instanceof ItemFocus)
    	{
    		if(!Minecraft.getMinecraft().gameSettings.keyBindUseItem.isKeyDown() && (force.isUsingForce() || force.isUsingLightning()))
    		{
    			force.setUsingLightning(false);
    			force.setUsingForce(false);
    			Main.network.sendToServer(new ForceUse(true));
    			syncForceToServer(player);
    		}
    	}
    	else if(force.isUsingForce() || force.isUsingLightning())
    	{
    		force.setUsingLightning(false);
    		force.setUsingForce(false);
			Main.network.sendToServer(new ForceUse(true));
			syncForceToServer(player);
    	}
    }
    
    @SideOnly(Side.CLIENT)
    @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
    public void onKeyPress(KeyInputEvent event)
    {
    	EntityPlayer player = Minecraft.getMinecraft().player;
    	ArrayList<KeyBinding> keyBinds = Main.keyBinds;
    	
    	//throw lightsaber
    	if(keyBinds.get(1).isKeyDown())
    	{
    		if(player.getHeldItemOffhand().getItem() instanceof Lightsaber)
    		{
    			Main.network.sendToServer(new OffThrowGreen());
    		}
    		else if(player.getHeldItemMainhand().getItem() instanceof Lightsaber)
    		{
    			Main.network.sendToServer(new MainThrowGreen());
    		}
    	}
    	
    	//force abilities gui
    	if(keyBinds.get(2).isPressed())
    	{
    		if(player.getHeldItemMainhand().getItem() instanceof ItemFocus)
    		{
    			int id;
				if(((ItemFocus)player.getHeldItemMainhand().getItem()).isJediFocus()) id = Reference.GUI_FORCE_ABILITIES;
				else id = Reference.GUI_SITH_ABILITIES;
				player.openGui(Main.instance, id, Minecraft.getMinecraft().world, 
    					player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ());
    		}
    		else if(player.getHeldItemOffhand().getItem() instanceof ItemFocus)
    		{
    			int id;
				if(((ItemFocus)player.getHeldItemOffhand().getItem()).isJediFocus()) id = Reference.GUI_FORCE_ABILITIES;
				else id = Reference.GUI_SITH_ABILITIES;
				player.openGui(Main.instance, id, Minecraft.getMinecraft().world, 
    					player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ());
    		}
    	}
    	
    	//block with lightsaber
    	if(keyBinds.get(0).isKeyDown() 
    			&& (player.getHeldItemMainhand().getItem() instanceof Lightsaber || player.getHeldItemOffhand().getItem() instanceof Lightsaber))
    	{
    		Main.network.sendToServer(new ChangeSaberBlockState());
			player.getCapability(ForceProvider.FORCE, null).setBlocking(1);
    	}
    	else
    	{
    		Main.network.sendToServer(new ChangeSaberBlockStateOff());
			player.getCapability(ForceProvider.FORCE, null).setBlocking(0);
    	}
    }
    
    
    @SubscribeEvent
    public void MindTrickEvent(LivingUpdateEvent event)
    {
    	boolean mindTricked = false;
    	EntityPlayer tricker = null;
    	for(int i = 0; i < event.getEntity().world.playerEntities.size(); i++)
    	{
    		IForce force = event.getEntity().world.playerEntities.get(i).getCapability(ForceProvider.FORCE, null);
    		if(force.getMindTrickId() == event.getEntity().getEntityId())
    		{
    			mindTricked = true;
    			tricker = event.getEntity().world.playerEntities.get(i);
    		}
    	}
    	if(!mindTricked) return;
    	IForce force = tricker.getCapability(ForceProvider.FORCE, null);
    	if(force.getMindTrickTicks() == 0)
    	{
    		return;
    	}
    	if(event.getEntity() instanceof EntityMob)
    	{
    		((EntityMob)event.getEntity()).setAttackTarget(null);
    	}
    	force.decreaseMindTrickTicks(1);
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
    
    
    public void surroundPlayerWithParticles(EnumParticleTypes particleType, EntityPlayer player)
    {
    	player.world.spawnAlwaysVisibleParticle(particleType.getParticleID(), player.posX + 0.5, 
				player.posY + player.eyeHeight + 0.3, player.posZ + 0.5, 0.0, 0.0, 0.0, 1);
		player.world.spawnAlwaysVisibleParticle(particleType.getParticleID(), player.posX + 0.5, 
				player.posY + player.eyeHeight + 0.3, player.posZ, 0.0, 0.0, 0.0, 1);
		player.world.spawnAlwaysVisibleParticle(particleType.getParticleID(), player.posX + 0.5, 
				player.posY + player.eyeHeight + 0.3, player.posZ - 0.5, 0.0, 0.0, 0.0, 1);
		player.world.spawnAlwaysVisibleParticle(particleType.getParticleID(), player.posX + 0.5, 
				player.posY + player.eyeHeight, player.posZ + 0.5, 0.0, 0.0, 0.0, 1);
		player.world.spawnAlwaysVisibleParticle(particleType.getParticleID(), player.posX + 0.5, 
				player.posY + player.eyeHeight, player.posZ, 0.0, 0.0, 0.0, 1);
		player.world.spawnAlwaysVisibleParticle(particleType.getParticleID(), player.posX + 0.5, 
				player.posY + player.eyeHeight, player.posZ - 0.5, 0.0, 0.0, 0.0, 1);
		player.world.spawnAlwaysVisibleParticle(particleType.getParticleID(), player.posX + 0.5, 
				player.posY + player.eyeHeight - 0.5, player.posZ + 0.5, 0.0, 0.0, 0.0, 1);
		player.world.spawnAlwaysVisibleParticle(particleType.getParticleID(), player.posX + 0.5, 
				player.posY + player.eyeHeight - 0.5, player.posZ, 0.0, 0.0, 0.0, 1);
		player.world.spawnAlwaysVisibleParticle(particleType.getParticleID(), player.posX + 0.5, 
				player.posY + player.eyeHeight - 0.5, player.posZ - 0.5, 0.0, 0.0, 0.0, 1);
		
		player.world.spawnAlwaysVisibleParticle(particleType.getParticleID(), player.posX + 0.5, 
				player.posY + player.eyeHeight + 0.6, player.posZ + 0.5, 0.0, 0.0, 0.0, 1);
		player.world.spawnAlwaysVisibleParticle(particleType.getParticleID(), player.posX + 0.5, 
				player.posY + player.eyeHeight + 0.6, player.posZ, 0.0, 0.0, 0.0, 1);
		player.world.spawnAlwaysVisibleParticle(particleType.getParticleID(), player.posX + 0.5, 
				player.posY + player.eyeHeight + 0.6, player.posZ - 0.5, 0.0, 0.0, 0.0, 1);
		player.world.spawnAlwaysVisibleParticle(particleType.getParticleID(), player.posX + 0.5, 
				player.posY + player.eyeHeight, player.posZ + 0.5, 0.0, 0.0, 0.0, 1);
		player.world.spawnAlwaysVisibleParticle(particleType.getParticleID(), player.posX + 0.5, 
				player.posY + player.eyeHeight, player.posZ, 0.0, 0.0, 0.0, 1);
		player.world.spawnAlwaysVisibleParticle(particleType.getParticleID(), player.posX + 0.5, 
				player.posY + player.eyeHeight, player.posZ - 0.5, 0.0, 0.0, 0.0, 1);
		player.world.spawnAlwaysVisibleParticle(particleType.getParticleID(), player.posX + 0.5, 
				player.posY + player.eyeHeight - 0.5, player.posZ + 0.5, 0.0, 0.0, 0.0, 1);
		player.world.spawnAlwaysVisibleParticle(particleType.getParticleID(), player.posX + 0.5, 
				player.posY + player.eyeHeight - 0.5, player.posZ, 0.0, 0.0, 0.0, 1);
		player.world.spawnAlwaysVisibleParticle(particleType.getParticleID(), player.posX + 0.5, 
				player.posY + player.eyeHeight - 0.5, player.posZ - 0.5, 0.0, 0.0, 0.0, 1);
		
		player.world.spawnAlwaysVisibleParticle(particleType.getParticleID(), player.posX, 
				player.posY + player.eyeHeight + 0.3, player.posZ + 0.5, 0.0, 0.0, 0.0, 1);
		player.world.spawnAlwaysVisibleParticle(particleType.getParticleID(), player.posX, 
				player.posY + player.eyeHeight + 0.3, player.posZ, 0.0, 0.0, 0.0, 1);
		player.world.spawnAlwaysVisibleParticle(particleType.getParticleID(), player.posX, 
				player.posY + player.eyeHeight + 0.3, player.posZ - 0.5, 0.0, 0.0, 0.0, 1);
		player.world.spawnAlwaysVisibleParticle(particleType.getParticleID(), player.posX, 
				player.posY + player.eyeHeight, player.posZ + 0.5, 0.0, 0.0, 0.0, 1);
		player.world.spawnAlwaysVisibleParticle(particleType.getParticleID(), player.posX, 
				player.posY + player.eyeHeight, player.posZ, 0.0, 0.0, 0.0, 1);
		player.world.spawnAlwaysVisibleParticle(particleType.getParticleID(), player.posX, 
				player.posY + player.eyeHeight + 0.6, player.posZ, 0.0, 0.0, 0.0, 1);
		player.world.spawnAlwaysVisibleParticle(particleType.getParticleID(), player.posX, 
				player.posY + player.eyeHeight, player.posZ - 0.5, 0.0, 0.0, 0.0, 1);
		player.world.spawnAlwaysVisibleParticle(particleType.getParticleID(), player.posX, 
				player.posY + player.eyeHeight - 0.5, player.posZ + 0.5, 0.0, 0.0, 0.0, 1);
		player.world.spawnAlwaysVisibleParticle(particleType.getParticleID(), player.posX, 
				player.posY + player.eyeHeight - 0.5, player.posZ, 0.0, 0.0, 0.0, 1);
		player.world.spawnAlwaysVisibleParticle(particleType.getParticleID(), player.posX, 
				player.posY + player.eyeHeight - 0.5, player.posZ - 0.5, 0.0, 0.0, 0.0, 1);
		
		player.world.spawnAlwaysVisibleParticle(particleType.getParticleID(), player.posX - 0.5, 
				player.posY + player.eyeHeight + 0.3, player.posZ + 0.5, 0.0, 0.0, 0.0, 1);
		player.world.spawnAlwaysVisibleParticle(particleType.getParticleID(), player.posX - 0.5, 
				player.posY + player.eyeHeight + 0.3, player.posZ, 0.0, 0.0, 0.0, 1);
		player.world.spawnAlwaysVisibleParticle(particleType.getParticleID(), player.posX - 0.5, 
				player.posY + player.eyeHeight + 0.3, player.posZ - 0.5, 0.0, 0.0, 0.0, 1);
		player.world.spawnAlwaysVisibleParticle(particleType.getParticleID(), player.posX - 0.5, 
				player.posY + player.eyeHeight, player.posZ + 0.5, 0.0, 0.0, 0.0, 1);
		player.world.spawnAlwaysVisibleParticle(particleType.getParticleID(), player.posX - 0.5, 
				player.posY + player.eyeHeight, player.posZ, 0.0, 0.0, 0.0, 1);
		player.world.spawnAlwaysVisibleParticle(particleType.getParticleID(), player.posX - 0.5, 
				player.posY + player.eyeHeight, player.posZ - 0.5, 0.0, 0.0, 0.0, 1);
		player.world.spawnAlwaysVisibleParticle(particleType.getParticleID(), player.posX - 0.5, 
				player.posY + player.eyeHeight - 0.5, player.posZ + 0.5, 0.0, 0.0, 0.0, 1);
		player.world.spawnAlwaysVisibleParticle(particleType.getParticleID(), player.posX - 0.5, 
				player.posY + player.eyeHeight - 0.5, player.posZ, 0.0, 0.0, 0.0, 1);
		player.world.spawnAlwaysVisibleParticle(particleType.getParticleID(), player.posX - 0.5, 
				player.posY + player.eyeHeight - 0.5, player.posZ - 0.5, 0.0, 0.0, 0.0, 1);
    }
}
