package com.finn_505.lightsabermod.util.packets;

import com.finn_505.lightsabermod.util.force.ForceProvider;

import io.netty.buffer.ByteBuf;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class setFloating implements IMessage{

	private boolean floating;
	private int time;
	
	public setFloating() {}
	
	public setFloating(boolean floating)
	{
		this.floating = floating;
		this.time = 1;
	}
	
	public setFloating(boolean floating, int time)
	{
		this.floating = floating;
		this.time = time;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) 
	{
		this.floating = buf.readBoolean();
		this.time = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) 
	{
		buf.writeBoolean(this.floating);
		buf.writeInt(this.time);
	}
	
	public static class SetFloatingHandler implements IMessageHandler<setFloating, IMessage>
	{

		@Override
		public IMessage onMessage(setFloating message, MessageContext ctx) {
			IThreadListener mainThread = (WorldServer) ctx.getServerHandler().player.world;
			mainThread.addScheduledTask(new Runnable() {
				
				@Override
				public void run() {
					if(message.floating)
					{
						ctx.getServerHandler().player.addPotionEffect(new PotionEffect(MobEffects.LEVITATION, message.time, 10, false, false));
						ctx.getServerHandler().player.getCapability(ForceProvider.FORCE, null).usePassiveForce(2);
					}
					else
					{
						ctx.getServerHandler().player.removeActivePotionEffect(MobEffects.LEVITATION);
						ctx.getServerHandler().player.getCapability(ForceProvider.FORCE, null).setJumpAllowed(false);
					}
				}
			});
			return null;
		}
		
	}

}
