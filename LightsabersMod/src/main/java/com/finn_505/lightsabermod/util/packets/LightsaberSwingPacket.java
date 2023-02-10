package com.finn_505.lightsabermod.util.packets;

import com.finn_505.lightsabermod.init.ModSoundEvents;
import com.finn_505.lightsabermod.util.force.ForceProvider;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class LightsaberSwingPacket implements IMessage{

	//public int state;
	
	public LightsaberSwingPacket() {}
	
	/*public ChangeSaberBlockState(int state)
	{
	}*/
	
	@Override
	public void fromBytes(ByteBuf buf) {
	}

	@Override
	public void toBytes(ByteBuf buf) {
	}
	
	public static class LightsaberSwingHandler implements IMessageHandler<LightsaberSwingPacket, IMessage>
	{

		@Override
		public IMessage onMessage(LightsaberSwingPacket message, MessageContext ctx) {
			IThreadListener mainThread = (WorldServer) ctx.getServerHandler().player.world;
			mainThread.addScheduledTask(new Runnable() {
				
				@Override
				public void run() {
					EntityPlayer player = ctx.getServerHandler().player;
					player.world.playSound(null, player.getPosition(), ModSoundEvents.LIGHTSABER_SWING, SoundCategory.NEUTRAL, 1.0F, 1.0F);
				}
			});
			return null;
		}
		
	}

}
