package com.finn_505.lightsabermod.lighting;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.finn_505.lightsabermod.init.ModBlocks;
import com.finn_505.lightsabermod.init.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MovingLightSource extends BlockAir implements ITileEntityProvider{

	public static BlockPos pos;

	
	public static List<Item> lightSourceList = new ArrayList<Item>() {
        {
            for(Item i: ModItems.SABERS)
            {
            	add(i);
            }
        }
    };

    public MovingLightSource(String name)
    {
        super();
        setUnlocalizedName(name);
        setDefaultState(blockState.getBaseState());
        setTickRandomly(false);
        setLightLevel(1.0F);
        setRegistryName(name);
        ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }
    
    public static boolean isLightEmittingItem(Item parItem)
    {
        return lightSourceList.contains(parItem);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return null;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        return true;
    }


    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
    	this.pos = pos;
        return;
    }
    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        return;
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return getDefaultState();
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return 0;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public void onFallenUpon(
          World worldIn, 
          BlockPos pos, 
          Entity entityIn, 
          float fallDistance)
    {
        return;
    }

    @Override
    public boolean hasTileEntity() {
    	return true;
    }
    
    @Override
    public void onLanded(World worldIn, Entity entityIn)
    {
        return;
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this);
    }


	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		TileEntityMovingLightSource x = new TileEntityMovingLightSource();
		//if(worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 1.0D, false) != null)
			//x.setPlayer(worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 1.0D, false));
		return x;
	}
}

