package com.finn_505.lightsabermod.util.handlers;

import com.finn_505.lightsabermod.blocks.saberforge.ContainerSaberForge;
import com.finn_505.lightsabermod.blocks.saberforge.GuiSaberForge;
import com.finn_505.lightsabermod.blocks.saberforge.TileEntitySaberForge;
import com.finn_505.lightsabermod.items.focus.GuiForce;
import com.finn_505.lightsabermod.items.focus.GuiSithForce;
import com.finn_505.lightsabermod.util.Reference;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == Reference.GUI_SABER_FORGE) 
			return new ContainerSaberForge(player.inventory, (TileEntitySaberForge)world.getTileEntity(new BlockPos(x,y,z)));
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == Reference.GUI_SABER_FORGE) 
			return new GuiSaberForge(player.inventory, (TileEntitySaberForge)world.getTileEntity(new BlockPos(x,y,z)));
		if(ID == Reference.GUI_FORCE_ABILITIES)
			return new GuiForce();
		if(ID == Reference.GUI_SITH_ABILITIES)
			return new GuiSithForce();
		return null;
	}

}
