package com.finn_505.lightsabermod.util.packets;

import com.finn_505.lightsabermod.util.force.ForceProvider;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ChangeSaberBlockState implements IMessage{

	
	public ChangeSaberBlockState() {}
	
	@Override
	public void fromBytes(ByteBuf buf) {
	}

	@Override
	public void toBytes(ByteBuf buf) {
	}
	
	public static class SaberBlockChangeHandler implements IMessageHandler<ChangeSaberBlockState, IMessage>
	{

		@Override
		public IMessage onMessage(ChangeSaberBlockState message, MessageContext ctx) {
			IThreadListener mainThread = (WorldServer) ctx.getServerHandler().player.world;
			mainThread.addScheduledTask(new Runnable() {
				
				@Override
				public void run() {
					EntityPlayer player = ctx.getServerHandler().player;
					player.getCapability(ForceProvider.FORCE, null).setBlocking(1);
				}
			});
			return null;
		}
		
	}

}
