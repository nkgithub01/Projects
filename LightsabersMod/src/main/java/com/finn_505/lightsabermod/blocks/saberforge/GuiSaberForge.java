package com.finn_505.lightsabermod.blocks.saberforge;

import java.util.List;

import com.finn_505.lightsabermod.util.Reference;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GuiSaberForge extends GuiContainer{

	private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MOD_ID + ":textures/gui/saber_forge.png");
	private final InventoryPlayer player;
	private final TileEntitySaberForge tile;
	
	public GuiSaberForge(InventoryPlayer player, TileEntitySaberForge tile)
	{
		super(new ContainerSaberForge(player, tile));
		this.player = player;
		this.tile = tile;
	}
	
	
	@Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }
    
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String tileName = this.tile.getDisplayName().getUnformattedText();
		this.fontRenderer.drawString(tileName, this.xSize/2 - this.fontRenderer.getStringWidth(tileName)/2, 
				3, 4210752);
		this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedText(), 122, 
				this.ySize - 96 + 2, 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F,1.0F,1.0F,1.0F);
		this.mc.getTextureManager().bindTexture(TEXTURES);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		
		if(TileEntitySaberForge.isBurning(tile))
		{
			int k = this.getBurnLeftScaled(13);
			this.drawTexturedModalRect(this.guiLeft+10, this.guiTop+55+12-k, 176, 12-k, 14, k+1);
			int l = this.getCookProgressScaled(83);
			this.drawTexturedModalRect(this.guiLeft + 29, this.guiTop + 11, 0, 166, l, 64);
		}
	}
	
	private int getBurnLeftScaled(int pixels)
	{
		int i = this.tile.getField(1);
		if(i == 0) i = 200;
		return this.tile.getField(0) * pixels/i;
	}
	
	private int getCookProgressScaled(int pixels)
	{
		int i = this.tile.getField(2);
		int j = this.tile.getField(3);
		return j != 0 && i != 0 ? i*pixels/j : 0;
	}
}
