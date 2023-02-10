package com.finn_505.lightsabermod.util.force;

import java.lang.annotation.ElementType;

import org.lwjgl.opengl.GL11;

import com.finn_505.lightsabermod.Main;
import com.finn_505.lightsabermod.items.focus.ItemFocus;
import com.finn_505.lightsabermod.util.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ForceGuiOverlay extends Gui{

	private final ResourceLocation bars = new ResourceLocation(Reference.MOD_ID + ":textures/gui/gui_force_overlay.png");
	private int forceWidth = 103, forceHeight = 11;
	private int passiveWidth = 73, passiveHeight = 6;
	private int textureY = 0, barY = 19;
	
	@SubscribeEvent
	public void renderOverlay(RenderGameOverlayEvent event)
	{
		if(event.getType() == RenderGameOverlayEvent.ElementType.TEXT)
		{
			Minecraft mc = Minecraft.getMinecraft();
			if(mc.player.getHeldItemMainhand().getItem() instanceof ItemFocus || mc.player.getHeldItemOffhand().getItem() instanceof ItemFocus)
			{
				if(mc.player.getHeldItemMainhand().getItem() instanceof ItemFocus)
    			{
					ItemFocus focus = (ItemFocus)mc.player.getHeldItemMainhand().getItem();
					if(focus.getLevel() != 1)
    				{
    					textureY = 50;
    					barY = 50;
    					forceWidth = 188;
    					passiveWidth = 142;
    				}
					else
					{
						barY = 0;
						textureY = 0;
						forceWidth = 103;
						passiveWidth = 73;
					}
					//System.out.println("textureY: " + textureY);
    				if(focus.isJediFocus()) barY += 19; 
    				else barY += 32;
    			}
    			else
    			{
    				ItemFocus focus = (ItemFocus)mc.player.getHeldItemOffhand().getItem();
    				if(focus.getLevel() != 1)
    				{
    					textureY = 50;
    					barY = 50;
    					forceWidth = 188;
    					passiveWidth = 142;
    				}
    				else
    				{
    					barY = 0;
    					textureY = 0;
    					forceWidth = 103;
    					passiveWidth = 73;
    				}
    				if(focus.isJediFocus()) barY += 19;
    				else barY += 32;
    			}
				mc.renderEngine.bindTexture(bars);
				GL11.glPushMatrix();
				//empty bars
				drawTexturedModalRect(0, 0, 0, textureY, forceWidth, forceHeight + passiveHeight);
				//force bar
				drawTexturedModalRect(1, 1, 1,barY, getForceScaled(), forceHeight - 2);
				//passive bar
				drawTexturedModalRect(1, 1 + forceHeight, 1,barY + forceHeight - 2, getPassiveScaled(), passiveHeight - 2);
				GL11.glPopMatrix();
			}
		}
	}
	
	private int getForceScaled()
	{
		IForce force = Minecraft.getMinecraft().player.getCapability(ForceProvider.FORCE, null);
		double ratio = (double)force.getForce()/force.getMaxForce();
		return (int)(ratio * (forceWidth - 2));
	}
	
	private int getPassiveScaled()
	{
		IForce force = Minecraft.getMinecraft().player.getCapability(ForceProvider.FORCE, null);
		double ratio = (double)force.getPassiveForce()/force.getMaxPassiveForce();
		return (int)(ratio * (passiveWidth - 2));
	}
}
