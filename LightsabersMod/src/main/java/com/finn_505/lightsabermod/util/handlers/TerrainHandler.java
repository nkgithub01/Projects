package com.finn_505.lightsabermod.util.handlers;

import javax.annotation.Nullable;

import com.finn_505.lightsabermod.blocks.BlockCrystal;
import com.finn_505.lightsabermod.blocks.TileEntityCrystal;
import com.finn_505.lightsabermod.init.ModBlocks;
import com.finn_505.lightsabermod.world.CustomCaves;
import com.google.common.base.Predicate;

import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TerrainHandler {

	public static final PropertyDirection FACING = PropertyDirection.create("facing", new Predicate<EnumFacing>()
    {
        public boolean apply(@Nullable EnumFacing p_apply_1_)
        {
            return true;
        }
    });   
	
	
	
	@SubscribeEvent
	public void onEvent(InitMapGenEvent event)
	{
		if(event.getType() == InitMapGenEvent.EventType.CAVE)
		{
			event.setNewGen(new CustomCaves());
		}
	}
	
	@SubscribeEvent
	public void onEvent(PopulateChunkEvent.Post e)
	{
		for(int i = 0; i < 16; i++)
		{
			for(int j = 0; j < 16; j++)
			{
				for(int k = 9; k < 40; k++)
				{
					BlockPos p = new BlockPos(e.getChunkX() * 16 + i, k, e.getChunkZ()*16 + j);
					World world = e.getWorld();
					if(world.getBlockState(p).getBlock() instanceof BlockCrystal)
					{
						BlockCrystal b = (BlockCrystal)(world.getBlockState(p).getBlock());
						TileEntityCrystal tile = (TileEntityCrystal) world.getTileEntity(p);
						BlockPos anchor = tile.anchor;
						EnumFacing facing = tile.facing;
						while(anchor == null || anchor.equals(p))
						{
							if(!world.isAirBlock(p.north()) && world.isBlockFullCube(p.north()))
							{
								anchor = p.north();
								facing = EnumFacing.SOUTH;
							}
							else if(!world.isAirBlock(p.south()) && world.isBlockFullCube(p.south()))
							{
								anchor = p.south();
								facing = EnumFacing.NORTH;
							}
							else if(!world.isAirBlock(p.east()) && world.isBlockFullCube(p.east()))
							{
								anchor = p.east();
								facing = EnumFacing.WEST;
							}
							else if(!world.isAirBlock(p.west()) && world.isBlockFullCube(p.west()))
							{
								anchor = p.west();
								facing = EnumFacing.EAST;
							}
							else if(!world.isAirBlock(p.down()) && world.isBlockFullCube(p.down()))
							{
								anchor = p.down();
								facing = EnumFacing.UP;
							}	
							else 
							{
								world.setBlockState(p, Blocks.AIR.getDefaultState());
								System.out.println("deleting");
							}
						}
						b.update(world.getBlockState(p), world, p);
						//world.setBlockState(p, ModBlocks.KYBER_CRYSTAL_BLOCK.getDefaultState().withProperty(FACING, facing));
					}
				}
			}
		}
	}
	
}
