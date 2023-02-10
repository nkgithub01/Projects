package com.finn_505.lightsabermod.lighting;

import java.util.List;

import com.finn_505.lightsabermod.entity.lightning.EntityLightning;
import com.finn_505.lightsabermod.entity.lightsaber.EntityLightsaber;
import com.finn_505.lightsabermod.entity.lightsaber.EntityLightsaberBlue;
import com.finn_505.lightsabermod.entity.lightsaber.EntityLightsaberPurple;
import com.finn_505.lightsabermod.entity.lightsaber.EntityLightsaberRed;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileEntityMovingLightSource extends TileEntity implements ITickable
{
    public EntityPlayer thePlayer;
    public BlockPos p;
    public int ticksWaited;
    
    public TileEntityMovingLightSource()
    {
        // after constructing the tile entity instance, remember to call 
        // the setPlayer() method.
    	ticksWaited = 0;
    }
    
    /**
     * This controls whether the tile entity gets replaced whenever the block state 
     * is changed. Normally only want this when block actually is replaced.
     */
    @Override
    public boolean shouldRefresh(
          World world, 
          BlockPos pos, 
          IBlockState oldState, 
          IBlockState newSate)
    {
        return (oldState.getBlock() != newSate.getBlock());
    }

    public void update()
    {
    	//if(ticksWaited == 10)
    	//{
	        // check if player has moved away from the tile entity
	        EntityPlayer thePlayer = world.getClosestPlayer(
	              getPos().getX()+0.5D, 
	              getPos().getY()+0.5D, 
	              getPos().getZ()+0.5D, 
	              2.0D, false);
	        List<Entity> closeEntities = world.getEntitiesWithinAABBExcludingEntity(thePlayer, 
	        		new AxisAlignedBB(getPos().getX() - 1, getPos().getY() - 1, getPos().getZ() - 1, 
	        				getPos().getX() + 1, getPos().getY() + 1, getPos().getZ() + 1));
	        if (thePlayer == null)
	        {
	        	if(closeEntities.size() > 0  && !hasInstanceOfSaber(closeEntities))
	            {
	            	world.setBlockState(getPos(), Blocks.AIR.getDefaultState(), 1);
	            }
	        	else if(closeEntities.size() == 0)
	        	{
	        		world.setBlockState(getPos(), Blocks.AIR.getDefaultState(), 1);
	        	}
	        }
	        else if (thePlayer.getHeldItem(EnumHand.MAIN_HAND).getItem() != null 
	              && !MovingLightSource.isLightEmittingItem(
	                    thePlayer.getHeldItem(EnumHand.MAIN_HAND).getItem()) && (thePlayer.getHeldItem(EnumHand.OFF_HAND).getItem() != null 
	                            && !MovingLightSource.isLightEmittingItem(
	                                    thePlayer.getHeldItem(EnumHand.OFF_HAND).getItem())))
	        {
	        	if(closeEntities.size() > 0  && !hasInstanceOfSaber(closeEntities))
	            {
	            	world.setBlockState(getPos(), Blocks.AIR.getDefaultState(), 1);
	            }
	        	else if(closeEntities.size() == 0)
	        	{
	        		world.setBlockState(getPos(), Blocks.AIR.getDefaultState(), 1);
	        	}            
	        }
	        //ticksWaited = 0;
    	//}
    	//ticksWaited++;
    }  
    
    public void setPlayer(EntityPlayer parPlayer)
    {
        thePlayer = parPlayer;
    }
    
    public static boolean hasInstanceOfSaber(List<Entity> c)
    {
    	for(Entity e: c)
    	{
    		if(e instanceof EntityLightsaber || e instanceof EntityLightsaberBlue 
    				|| e instanceof EntityLightsaberPurple || e instanceof EntityLightsaberRed 
    				|| e instanceof EntityLightning)
    		{
    			return true;
    		}
    	}
    	return false;
    }
}