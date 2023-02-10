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

public class SetPassive implements IMessage{

	private boolean ability;
	
	public SetPassive() {}
	
	public SetPassive(boolean ability)
	{
		this.ability = ability;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) 
	{
		this.ability = buf.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf buf) 
	{
		buf.writeBoolean(ability);
	}
	
	public static class SetPassiveHandler implements IMessageHandler<SetPassive, IMessage>
	{

		@Override
		public IMessage onMessage(SetPassive message, MessageContext ctx) {
			IThreadListener mainThread = (WorldServer) ctx.getServerHandler().player.world;
			mainThread.addScheduledTask(new Runnable() {
				
				@Override
				public void run() {
					ctx.getServerHandler().player.getCapability(ForceProvider.FORCE, null).setPassives(message.ability);
				}
			});
			return null;
		}
		
	}

}
