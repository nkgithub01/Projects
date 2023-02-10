package com.finn_505.lightsabermod.util.packets;

import com.finn_505.lightsabermod.entity.grip.EntityGrip;
import com.finn_505.lightsabermod.items.focus.ItemFocus;
import com.finn_505.lightsabermod.util.force.ForceProvider;
import com.finn_505.lightsabermod.util.force.IForce;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IThreadListener;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ForceUse implements IMessage{

	private int id;
	private int blockX;
	private int blockY;
	private int blockZ;
	private boolean hitEntity;
	private boolean toKill;
	
	public ForceUse() {}
	
	public ForceUse(int id)
	{
		this.id = id;
		this.hitEntity = true;
	}
	
	public ForceUse(boolean toKill)
	{
		this.toKill = toKill;
	}
	
	public ForceUse(BlockPos p)
	{
		this.blockX = p.getX();
		this.blockY = p.getY();
		this.blockZ = p.getZ();
		this.hitEntity = false;
		this.toKill = false;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) 
	{
		this.id = buf.readInt();
		this.blockX = buf.readInt();
		this.blockY = buf.readInt();
		this.blockZ = buf.readInt();
		this.hitEntity = buf.readBoolean();
		this.toKill = buf.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf buf) 
	{
		buf.writeInt(id);
		buf.writeInt(blockX);
		buf.writeInt(blockY);
		buf.writeInt(blockZ);
		buf.writeBoolean(hitEntity);
		buf.writeBoolean(toKill);
	}
	
	public static class ForceUseHandler implements IMessageHandler<ForceUse, IMessage>
	{

		@Override
		public IMessage onMessage(ForceUse message, MessageContext ctx) {
			IThreadListener mainThread = (WorldServer) ctx.getServerHandler().player.world;
			mainThread.addScheduledTask(new Runnable() {
				
				@Override
				public void run() {
					EntityPlayer player = ctx.getServerHandler().player;
					IForce force = player.getCapability(ForceProvider.FORCE, null);
					if(message.toKill && player.world.getEntityByID(force.getGripId()) != null)
					{
						player.world.getEntityByID(force.getGripId()).setDead();
					}
				}
			});
			return null;
		}
		
	}

}
