package com.finn_505.lightsabermod.util.handlers;

import com.finn_505.lightsabermod.blocks.TileEntityCrystal;
import com.finn_505.lightsabermod.blocks.saberforge.TileEntitySaberForge;
import com.finn_505.lightsabermod.init.ModBlocks;
import com.finn_505.lightsabermod.lighting.TileEntityMovingLightSource;
import com.finn_505.lightsabermod.util.Reference;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler {

	public static void registerTileEntities()
	{
		GameRegistry.registerTileEntity(TileEntitySaberForge.class, new ResourceLocation(Reference.MOD_ID + ":saber_forge"));
		GameRegistry.registerTileEntity(TileEntityCrystal.class, ModBlocks.KYBER_CRYSTAL_BLOCK.getRegistryName());
		GameRegistry.registerTileEntity(TileEntityMovingLightSource.class, ModBlocks.LIGHT_SOURCE.getRegistryName());

	}
}
