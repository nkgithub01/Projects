package com.finn_505.lightsabermod.blocks;

import com.finn_505.lightsabermod.init.ModBlocks;
import com.finn_505.lightsabermod.init.ModItems;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileEntityCrystal extends TileEntity implements ITickable{

	public BlockPos anchor;
	public EnumFacing facing;
	public int color;

	public TileEntityCrystal()
	{}
	
	public TileEntityCrystal(int c)
	{
		color = c;
	}
	
	public TileEntityCrystal(BlockPos anchorPos)//, EnumFacing f)
	{
		anchor = anchorPos;
	}

	@Override
	public boolean shouldRefresh(
	          World world, 
	          BlockPos pos, 
	          IBlockState oldState, 
	          IBlockState newSate)
	{
		return (oldState.getBlock() != newSate.getBlock());
    }
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setInteger("x", anchor.getX());
		compound.setInteger("y", anchor.getY());
		compound.setInteger("z", anchor.getZ());
		compound.setInteger("facing", getIntFromFacing(facing));
		compound.setInteger("color", color);
		return super.writeToNBT(compound);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		anchor = new BlockPos(compound.getInteger("x"), compound.getInteger("y"), compound.getInteger("z"));
		facing = getFacingFromInt(compound.getInteger("facing"));
		color = compound.getInteger("color");
		super.readFromNBT(compound);
	}
	
	@Override
	public void onLoad() {
		super.onLoad();
		while(anchor == null || anchor.equals(this.getPos()))
		{
			
			if(!world.isAirBlock(pos.north()) && world.isBlockFullCube(pos.north()))
			{
				this.anchor = pos.north();
				facing = EnumFacing.SOUTH;
				markDirty();
			}
			else if(!world.isAirBlock(pos.south()) && world.isBlockFullCube(pos.south()))
			{
				this.anchor = pos.south();
				facing = EnumFacing.NORTH;
				markDirty();
			}
			else if(!world.isAirBlock(pos.east()) && world.isBlockFullCube(pos.east()))
			{
				this.anchor = pos.east();
				facing = EnumFacing.WEST;
				markDirty();
			}
			else if(!world.isAirBlock(pos.west()) && world.isBlockFullCube(pos.west()))
			{
				this.anchor = pos.west();
				facing = EnumFacing.EAST;
				markDirty();
			}
			else if(!world.isAirBlock(pos.down()) && world.isBlockFullCube(pos.down()))
			{
				this.anchor = pos.down();
				facing = EnumFacing.UP;
				markDirty();
			}
			else if(!world.isAirBlock(pos.up()) && world.isBlockFullCube(pos.up()))
			{
				this.anchor = pos.up();
				facing = EnumFacing.DOWN;
				markDirty();
				System.out.println("down");
			}
			else  
			{
				this.anchor = pos.down();
				facing = EnumFacing.UP;
				markDirty();
				world.setBlockState(pos, Blocks.AIR.getDefaultState());
			}
		}
		if(facing == null)
		{
			if(this.anchor.equals(this.getPos().north()))
			{
				facing = EnumFacing.SOUTH;
				markDirty();
			}
			else if(this.anchor.equals(this.getPos().south()))
			{
				facing = EnumFacing.NORTH;
				markDirty();
			}
			else if(this.anchor.equals(this.getPos().east()))
			{
				facing = EnumFacing.WEST;
				markDirty();
			}
			else if(this.anchor.equals(this.getPos().west()))
			{
				facing = EnumFacing.EAST;
				markDirty();
			}
			else if(this.anchor.equals(this.getPos().down()))
			{
				facing = EnumFacing.UP;
				markDirty();
			}
			else if(this.anchor.equals(this.getPos().up()))
			{
				facing = EnumFacing.DOWN;
				markDirty();
			}
			else 
			{
				facing = EnumFacing.UP;
				markDirty();
			}
		}
		System.out.println(pos.getX() + ", " + pos.getY() + ", " + pos.getZ());
	}
	
	@Override
	public void update() {
		if(anchor == null || anchor.equals(this.getPos()))
		{
			if (!world.isAirBlock(pos.down()) && world.isBlockFullCube(pos.down()))
			{
				this.anchor = pos.down();
				facing = EnumFacing.UP;
				//markDirty();
			}	
			else if(!world.isAirBlock(pos.north()) && world.isBlockFullCube(pos.north()))
			{
				this.anchor = pos.north();
				facing = EnumFacing.SOUTH;
				//markDirty();
			}
			else if(!world.isAirBlock(pos.south()) && world.isBlockFullCube(pos.south()))
			{
				this.anchor = pos.south();
				facing = EnumFacing.NORTH;
				//markDirty();
			}
			else if(!world.isAirBlock(pos.east()) && world.isBlockFullCube(pos.east()))
			{
				this.anchor = pos.east();
				facing = EnumFacing.WEST;
				//markDirty();
			}
			else if(!world.isAirBlock(pos.west()) && world.isBlockFullCube(pos.west()))
			{
				this.anchor = pos.west();
				facing = EnumFacing.EAST;
				//markDirty();
			}
			/*else if(!world.isAirBlock(pos.up()) && world.isBlockFullCube(pos.up()))
			{
				this.anchor = pos.up();
				facing = EnumFacing.DOWN;
				System.out.println("down");
				//markDirty();
			}*/
			else 
			{
				//this.anchor = pos.down();
				System.out.println(anchor.getX() + ", " + anchor.getY() + ", " + anchor.getZ());
				//facing = EnumFacing.UP;
				//markDirty();
				world.setBlockState(pos, Blocks.AIR.getDefaultState());
			}
		}
		//System.out.println("loaded!" + pos.getX() + ", " + pos.getY() + ", " + pos.getZ() + "  anchorPos: "  + 
				//anchor.getX() + ", " + anchor.getY() + ", " + anchor.getZ() + "facing: " + facing);
		if(anchor != null && world.isAirBlock(anchor) && !world.isRemote)
		{
			//world.spawnEntity(new EntityItem(world, this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), new ItemStack(ModItems.CRYSTALS[color])));
			world.setBlockState(this.getPos(), Blocks.AIR.getDefaultState());
			markDirty();
		}
		world.getBlockState(getPos()).neighborChanged(world, getPos(), blockType, anchor);
	}
	
	
	private static int getIntFromFacing(EnumFacing f)
	{
		switch(f)
		{
			case DOWN:
				return 0;
			case UP:
				return 1;
			case NORTH:
				return 2;
			case SOUTH:
				return 3;
			case EAST:
				return 4;
			case WEST:
				return 5;
			default:
				return 0;
		}
	}
	
	private static EnumFacing getFacingFromInt(int f)
	{
		switch(f)
		{
			case 0:
				return EnumFacing.DOWN;
			case 1:
				return EnumFacing.UP;
			case 2:
				return EnumFacing.NORTH;
			case 3:
				return EnumFacing.SOUTH;
			case 4:
				return EnumFacing.EAST;
			case 5:
				return EnumFacing.WEST;
			default:
				return EnumFacing.DOWN;
		}
	}
}
