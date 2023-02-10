package com.finn_505.lightsabermod.util.packets.forcesync;

import java.util.ArrayList;

import com.finn_505.lightsabermod.util.force.ForceProvider;
import com.finn_505.lightsabermod.util.force.IForce;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ForceSyncServer implements IMessage{

	private int blocking, hate, maxForce, currentAbility, force, maxPassive, passiveForce, gripId, ticksSinceUse, forceForJump, mindTrick, mindTicks;
	private int trickedTime, cooldown;
	private boolean passives, canJump, usingAbility, lightning;
	
	public ForceSyncServer() {}
	
	public ForceSyncServer(int blocking, int hate, int maxForce, int currentAbility, boolean passives, boolean canJump, int force, int maxPassive, 
			int passiveForce, int gripId, boolean usingAbility, int ticksSinceUse, int forceForJump, int mindTrick, int mindTicks, int trickedTime,
			boolean lightning, int cooldown)
	{
		this.blocking = blocking;
		this.hate = hate;
		this.maxForce = maxForce;
		this.currentAbility = currentAbility;
		this.passives = passives;
		this.canJump = canJump;
		this.force = force;
		this.maxPassive = maxPassive;
		this.passiveForce = passiveForce;
		this.gripId = gripId;
		this.usingAbility = usingAbility;
		this.ticksSinceUse = ticksSinceUse;
		this.forceForJump = forceForJump;
		this.mindTrick = mindTrick;
		this.mindTicks = mindTicks;
		this.trickedTime = trickedTime;
		this.lightning = lightning;
		this.cooldown = cooldown;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) 
	{
		this.blocking = buf.readInt();
		this.hate = buf.readInt();
		this.maxForce = buf.readInt();
		this.currentAbility = buf.readInt();
		this.passives = buf.readBoolean();
		this.canJump = buf.readBoolean();
		this.force = buf.readInt();
		this.maxPassive = buf.readInt();
		this.passiveForce = buf.readInt();
		this.gripId = buf.readInt();
		this.usingAbility = buf.readBoolean();
		this.ticksSinceUse = buf.readInt();
		this.forceForJump = buf.readInt();
		this.mindTrick = buf.readInt();
		this.mindTicks = buf.readInt();
		this.trickedTime = buf.readInt();
		this.lightning = buf.readBoolean();
		this.cooldown = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) 
	{
		buf.writeInt(this.blocking);
		buf.writeInt(this.hate);
		buf.writeInt(this.maxForce);
		buf.writeInt(this.currentAbility);
		buf.writeBoolean(this.passives);
		buf.writeBoolean(this.canJump);
		buf.writeInt(this.force);
		buf.writeInt(this.maxPassive);
		buf.writeInt(this.passiveForce);
		buf.writeInt(this.gripId);
		buf.writeBoolean(this.usingAbility);
		buf.writeInt(this.ticksSinceUse);
		buf.writeInt(this.forceForJump);
		buf.writeInt(this.mindTrick);
		buf.writeInt(this.mindTicks);
		buf.writeInt(this.trickedTime);
		buf.writeBoolean(this.lightning);
		buf.writeInt(this.cooldown);
	}
	
	public static class ForceSyncServerHandler implements IMessageHandler<ForceSyncServer, IMessage>
	{

		@Override
		public IMessage onMessage(ForceSyncServer message, MessageContext ctx) {
			IThreadListener mainThread = (WorldServer) ctx.getServerHandler().player.world;
			mainThread.addScheduledTask(new Runnable() {
				
				@Override
				public void run() 
				{
					IForce player = ctx.getServerHandler().player.getCapability(ForceProvider.FORCE, null);
					player.setBlocking(message.blocking);
					player.setHate(message.hate);
					player.setMaxForce(message.maxForce);
					player.setEquippedAbility(message.currentAbility);
					player.setPassives(message.passives);
					player.setJumpAllowed(message.canJump);
					player.setForce(message.force);
					player.setMaxPassiveForce(message.maxPassive);
					player.setPassiveForce(message.passiveForce);
					player.setGripId(message.gripId);
					player.setUsingForce(message.usingAbility);
					player.setTicksSinceLastUsedForce(message.ticksSinceUse);
					player.setJumpStartHeight(message.forceForJump);
					player.setMindTrickId(message.mindTrick);
					player.setMindTrickTicks(message.mindTicks);
					player.setTrickedTime(message.trickedTime);
					player.setUsingLightning(message.lightning);
					player.setBlasterCooldown(message.cooldown);
				}
			});
			return null;
		}
		
	}

}
