package com.finn_505.lightsabermod.items.focus;

import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import com.finn_505.lightsabermod.Main;
import com.finn_505.lightsabermod.init.ModSoundEvents;
import com.finn_505.lightsabermod.util.Reference;
import com.finn_505.lightsabermod.util.force.ForceProvider;
import com.finn_505.lightsabermod.util.packets.SetForceAbility;
import com.finn_505.lightsabermod.util.packets.SetPassive;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.ResourceLocation;

public class GuiForce extends GuiScreen{

	GuiButton passives;
	int prevPos;
	int currentPos;
	
	public GuiForce()
	{
		super();
		this.allowUserInput = true;
		this.prevPos = 0;
		this.currentPos = 0;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) 
	{
		this.drawDefaultBackground();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID +":textures/gui/force_abilities.png"));
		this.drawModalRectWithCustomSizedTexture(this.width/2 - 87, this.height/2 - 87, currentPos * 200, 0, 199, 199, 1000, 256);
		this.drawString(fontRenderer, "Force Abilities", this.width/2 - this.fontRenderer.getStringWidth("Force Abilities")/2, 10, 0xFFFFFF);
		this.drawString(fontRenderer, "Push", this.width/2 - this.fontRenderer.getStringWidth("Push")/2 + 44, this.height/2 - 43, 0xFFFFFF);
		this.drawString(fontRenderer, "Pull", this.width/2 - this.fontRenderer.getStringWidth("Pull")/2 - 44, this.height/2 - 43, 0xFFFFFF);
		this.drawString(fontRenderer, "Grip", this.width/2 - this.fontRenderer.getStringWidth("Grip")/2 - 44, this.height/2 + 43, 0xFFFFFF);
		this.drawString(fontRenderer, "Mind Trick", this.width/2 - this.fontRenderer.getStringWidth("Mind Trick")/2 + 44, this.height/2 + 43, 0xFFFFFF);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	public void updateScreen() 
	{
		super.updateScreen();
		if(this.prevPos != this.currentPos)
		{
			this.mc.player.playSound(ModSoundEvents.MENU_SELECT, 1.0F, 1.0F);
			prevPos = currentPos;
		}
		
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	
	@Override
	public void initGui() {
		this.buttonList.add(this.passives = new GuiButton(0, this.width/2 - 100, this.height/2 - 10, "Toggle Passives"));
		super.initGui();
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		if(button == this.passives)
		{
			this.mc.player.getCapability(ForceProvider.FORCE, null).setPassives(!this.mc.player.getCapability(ForceProvider.FORCE, null).getPassive());
			Main.network.sendToServer(new SetPassive(this.mc.player.getCapability(ForceProvider.FORCE, null).getPassive()));
		}
		super.actionPerformed(button);
	}
	
	@Override
	public void handleKeyboardInput() throws IOException {
		super.handleKeyboardInput();
		ArrayList<KeyBinding> binds = Main.keyBinds;
		int key = binds.get(2).getKeyCode();
		if(!Keyboard.getEventKeyState() && Keyboard.getEventKey() == key)
		{
			this.mc.player.closeScreen();
		}
	}
	
	@Override
	public void handleMouseInput() throws IOException 
	{
		int mouseX = getWidthScaled(Mouse.getEventX());
		int mouseY = getHeightScaled(Mouse.getEventY());
		if(mouseY <= height/2- 10)
		{
			if(mouseX >= this.width/2)
			{
				this.mc.player.getCapability(ForceProvider.FORCE, null).setEquippedAbility(1);
				Main.network.sendToServer(new SetForceAbility(1));
				currentPos = 1;
			}
			else if(mouseX < this.width/2)
			{
				this.mc.player.getCapability(ForceProvider.FORCE, null).setEquippedAbility(2);
				Main.network.sendToServer(new SetForceAbility(2));
				currentPos = 2;
			}
		}
		else if(mouseY > this.height/2 + 10)
		{
			if(mouseX >= this.width/2)
			{
				this.mc.player.getCapability(ForceProvider.FORCE, null).setEquippedAbility(3);
				Main.network.sendToServer(new SetForceAbility(3));
				currentPos = 3;
			}
			else if(mouseX < this.width/2)
			{
				this.mc.player.getCapability(ForceProvider.FORCE, null).setEquippedAbility(4);
				Main.network.sendToServer(new SetForceAbility(4));
				currentPos = 4;
			}
		}
		super.handleMouseInput();
	}
	
	private int getHeightScaled(int unscaledHeight)
	{
		double scaleFactor = (double)this.height/this.mc.displayHeight;		
		return (int)(scaleFactor*unscaledHeight);
	}
	
	private int getWidthScaled(int unscaledWidth)
	{
		double scaleFactor = (double)this.width/this.mc.displayWidth;
		
		return (int)(scaleFactor*unscaledWidth);
	}
}
