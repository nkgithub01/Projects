package com.finn_505.lightsabermod.util.packets;

import com.finn_505.lightsabermod.entity.lightsaber.EntityLightsaber;
import com.finn_505.lightsabermod.entity.lightsaber.EntityLightsaberBlue;
import com.finn_505.lightsabermod.entity.lightsaber.EntityLightsaberPurple;
import com.finn_505.lightsabermod.entity.lightsaber.EntityLightsaberRed;
import com.finn_505.lightsabermod.items.Lightsaber;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MainThrowGreen implements IMessage{

	
	public MainThrowGreen() {}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		// TODO Auto-generated method stub
	}

	@Override
	public void toBytes(ByteBuf buf) {
		// TODO Auto-generated method stub		
	}

	public static class MainThrowGreenHandler implements IMessageHandler<MainThrowGreen, IMessage>{

		@Override
		public IMessage onMessage(MainThrowGreen message, MessageContext ctx) {
			// TODO Auto-generated method stub
			IThreadListener mainthread = (WorldServer) ctx.getServerHandler().player.world;
			mainthread.addScheduledTask(new Runnable() {
				@Override 
				public void run()
				{
					EntityPlayer player = ctx.getServerHandler().player;
					World worldIn = player.world; 
					if(player.getHeldItemMainhand().getItem() instanceof Lightsaber)
					{
						EntityLiving entity;
						int color = ((Lightsaber)player.getHeldItemMainhand().getItem()).color;
						player.getHeldItemMainhand().setCount(0);
						
						if(color == 0)
						{
							entity = new EntityLightsaber(player.world, player, false);
						}
						else if(color == 1)
						{
							entity = new EntityLightsaberBlue(player.world, player, false);
						}
						else if(color == 2)
						{
							entity = new EntityLightsaberPurple(player.world, player, false);
						}
						else if(color == 3)
						{
							entity = new EntityLightsaberRed(player.world, player, false);
						}
						else
						{
							entity = new EntityLightsaber(player.world, player, false);
						}
						
						entity.setLocationAndAngles(player.getPosition().getX(), player.getPosition().getY(), 
								player.getPosition().getZ(), player.cameraYaw, player.cameraPitch);
						entity.rotationYawHead = entity.rotationYaw;
	                    entity.renderYawOffset = entity.rotationYaw;
	                    entity.onInitialSpawn(worldIn.getDifficultyForLocation(new BlockPos(entity)), (IEntityLivingData)null);
	                    worldIn.spawnEntity(entity);
	                    entity.playLivingSound();
					}
				}
			});
			return null;
		}

	}
}
