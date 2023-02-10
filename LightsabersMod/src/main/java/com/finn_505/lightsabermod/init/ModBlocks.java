package com.finn_505.lightsabermod.init;

import java.util.ArrayList;
import java.util.List;

import com.finn_505.lightsabermod.Main;
import com.finn_505.lightsabermod.blocks.BlockCrystal;
import com.finn_505.lightsabermod.blocks.saberforge.BlockSaberForge;
import com.finn_505.lightsabermod.lighting.MovingLightSource;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class ModBlocks {

	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	public static final BlockCrystal KYBER_CRYSTAL_BLOCK = new BlockCrystal("kyber_crystal_block", Material.GLASS);
	public static final BlockCrystal KYBER_CRYSTAL_BLOCK_BLUE = new BlockCrystal("kyber_crystal_block_blue", Material.GLASS, 1);
	public static final BlockCrystal KYBER_CRYSTAL_BLOCK_PURPLE = new BlockCrystal("kyber_crystal_block_purple", Material.GLASS, 2);
	public static final BlockCrystal KYBER_CRYSTAL_BLOCK_RED = new BlockCrystal("kyber_crystal_block_red", Material.GLASS, 3);
	
	public static final BlockCrystal[] CRYSTALS = {KYBER_CRYSTAL_BLOCK, KYBER_CRYSTAL_BLOCK_BLUE, KYBER_CRYSTAL_BLOCK_PURPLE, KYBER_CRYSTAL_BLOCK_RED};

	public static final Block LIGHT_SOURCE = new MovingLightSource("moving_light_source");
	public static final Block SABER_FORGE_OFF = new BlockSaberForge("saber_forge_off", false).setCreativeTab(Main.STARWARS);
	public static final Block SABER_FORGE_ON = new BlockSaberForge("saber_forge_on", true).setLightLevel(1.0F);
	
}
