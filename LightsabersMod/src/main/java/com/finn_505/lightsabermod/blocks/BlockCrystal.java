package com.finn_505.lightsabermod.blocks;

import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import com.finn_505.lightsabermod.Main;
import com.finn_505.lightsabermod.init.ModBlocks;
import com.finn_505.lightsabermod.init.ModItems;
import com.google.common.base.Predicate;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCrystal extends Block implements ITileEntityProvider{

	public static final PropertyDirection FACING = PropertyDirection.create("facing", new Predicate<EnumFacing>()
    {
        public boolean apply(@Nullable EnumFacing p_apply_1_)
        {
            return true;
        }
    });    
    protected static final AxisAlignedBB AABB_BOTTOM_HALF = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);
    protected static final AxisAlignedBB AABB_TOP_HALF = new AxisAlignedBB(0.0D, 0.5D, 0.0D, 1.0D, 1.0D, 1.0D);
	protected static final AxisAlignedBB SOUTH = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.5D);
	protected static final AxisAlignedBB NORTH = new AxisAlignedBB(0.0D, 0.0D, 0.5D, 1.0D, 1.0D, 1.0D);
	protected static final AxisAlignedBB EAST = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.5D, 1.0D, 1.0D);
	protected static final AxisAlignedBB WEST = new AxisAlignedBB(0.5D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
	protected BlockPos anchor;
	protected BlockPos pos;
	public int color;

    
    
    
	public BlockCrystal(String name, Material material)
	{
		super(material);
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setCreativeTab(Main.STARWARS);
		this.setLightLevel(0.35F);
		this.setLightOpacity(1);
		this.setHarvestLevel("pickaxe", 2);
		this.setHardness(1.0F);
		this.setResistance(10.0F);
		setSoundType(SoundType.GLASS);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.UP));
		this.setTickRandomly(true);
		color = 0;
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	
	public BlockCrystal(String name, Material material, int color)
	{
		super(material);
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setCreativeTab(Main.STARWARS);
		this.setLightLevel(0.35F);
		this.setLightOpacity(1);
		this.setHarvestLevel("pickaxe", 2);
		this.setHardness(1.0F);
		this.setResistance(10.0F);
		setSoundType(SoundType.GLASS);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.UP));
		this.setTickRandomly(true);
		this.color = color;
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}

	
	@SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }
	
	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}
	
	@Override
	public boolean isFullBlock(IBlockState state)
    {
        return false;
    }
	
	@Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
	
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        //return AABB_BOTTOM_HALF;
		 switch ((EnumFacing)state.getValue(FACING))
	        {
	            case EAST:
	                return EAST;
	            case WEST:
	                return WEST;
	            case SOUTH:
	                return SOUTH;
	            case NORTH:
	                return NORTH;
	            case UP:
	                return AABB_BOTTOM_HALF;
	            case DOWN:
	            	return AABB_TOP_HALF;
	            default:
	            	return AABB_BOTTOM_HALF;
	        }
    }
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return ModItems.CRYSTALS[color];
    }
	
	@Override
	protected boolean canSilkHarvest() {
		return true;
	}
	
	@Override
	protected ItemStack getSilkTouchDrop(IBlockState state) {
		return new ItemStack(ModBlocks.CRYSTALS[color]);
	}
	
	@Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
	{
		if(!worldIn.isRemote)
		{
			TileEntityCrystal tile = (TileEntityCrystal) worldIn.getTileEntity(pos);
			if(tile.facing != null)
			worldIn.setBlockState(pos, this.getDefaultState().withProperty(FACING, tile.facing));
			//worldIn.scheduleUpdate(pos, worldIn.getBlockState(pos).getBlock(), 1);
		}
	}
	
	public void update(IBlockState state, World worldIn, BlockPos p)
	{
		if(!worldIn.isRemote)
		{
			TileEntityCrystal tile = (TileEntityCrystal) worldIn.getTileEntity(p);
			if(tile != null && tile.facing != null && tile.anchor != null)
			worldIn.setBlockState(p, this.getDefaultState().withProperty(FACING, tile.facing));
			//worldIn.scheduleUpdate(p, worldIn.getBlockState(p).getBlock(), 1);
		}
	}
	
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		if(anchor == null)
		{
			if (!worldIn.isAirBlock(pos.down()) && worldIn.isBlockFullCube(pos.down()))
			{
				this.anchor = pos.down();
				worldIn.setBlockState(pos, this.getDefaultState().withProperty(FACING, EnumFacing.UP));
			}	
			else if(!worldIn.isAirBlock(pos.north()) && worldIn.isBlockFullCube(pos.north()))
			{
				this.anchor = pos.north();
				worldIn.setBlockState(pos, this.getDefaultState().withProperty(FACING, EnumFacing.SOUTH));
			}
			else if(!worldIn.isAirBlock(pos.south()) && worldIn.isBlockFullCube(pos.south()))
			{
				this.anchor = pos.south();
				worldIn.setBlockState(pos, this.getDefaultState().withProperty(FACING, EnumFacing.NORTH));
			}
			else if(!worldIn.isAirBlock(pos.east()) && worldIn.isBlockFullCube(pos.east()))
			{
				this.anchor = pos.east();
				worldIn.setBlockState(pos, this.getDefaultState().withProperty(FACING, EnumFacing.WEST));
			}
			else if(!worldIn.isAirBlock(pos.west()) && worldIn.isBlockFullCube(pos.west()))
			{
				this.anchor = pos.west();
				worldIn.setBlockState(pos, this.getDefaultState().withProperty(FACING, EnumFacing.EAST));
			}
			if(!worldIn.isAirBlock(pos.up()) && worldIn.isBlockFullCube(pos.up()))
			{
				this.anchor = pos.up();
				worldIn.setBlockState(pos, this.getDefaultState().withProperty(FACING, EnumFacing.DOWN));
			}
			else 
			{
				this.anchor = pos.down();
				worldIn.setBlockState(pos, this.getDefaultState().withProperty(FACING, EnumFacing.UP));
			}
		}
	}
	
	@Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
		return worldIn.getBlockState(pos).getBlock().isReplaceable(worldIn, pos) && 
				((!worldIn.isAirBlock(pos.up()) && worldIn.isBlockFullCube(pos.up())) ||
				(!worldIn.isAirBlock(pos.down()) && worldIn.isBlockFullCube(pos.down())) ||		
				(!worldIn.isAirBlock(pos.east()) && worldIn.isBlockFullCube(pos.east())) ||
				(!worldIn.isAirBlock(pos.west()) && worldIn.isBlockFullCube(pos.west())) ||
				(!worldIn.isAirBlock(pos.north()) && worldIn.isBlockFullCube(pos.north())) ||
				(!worldIn.isAirBlock(pos.south()) && worldIn.isBlockFullCube(pos.south())) );
    }
	
	@Override
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
		if(canPlaceAtSide(worldIn, pos, facing))
		{
			return this.getDefaultState().withProperty(FACING, facing);
		}
		if(canPlaceAtSide(worldIn, pos, EnumFacing.DOWN))
		{
			return this.getDefaultState().withProperty(FACING, EnumFacing.DOWN);
		}
		else if(canPlaceAtSide(worldIn, pos, EnumFacing.UP))
		{
			return this.getDefaultState().withProperty(FACING, EnumFacing.UP);
		}
		else if(canPlaceAtSide(worldIn, pos, EnumFacing.SOUTH))
		{
			return this.getDefaultState().withProperty(FACING, EnumFacing.SOUTH);
		}
		else if(canPlaceAtSide(worldIn, pos, EnumFacing.NORTH))
		{
			return this.getDefaultState().withProperty(FACING, EnumFacing.NORTH);
		}
		else if(canPlaceAtSide(worldIn, pos, EnumFacing.WEST))
		{
			return this.getDefaultState().withProperty(FACING, EnumFacing.WEST);
		}
		else if(canPlaceAtSide(worldIn, pos, EnumFacing.EAST))
		{
			return this.getDefaultState().withProperty(FACING, EnumFacing.EAST);
		}
		else
		{
			return this.getDefaultState().withProperty(FACING, facing);
		}
    }
	
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos)
    {
		if(canPlaceAtSide(worldIn, pos, EnumFacing.DOWN))
		{
			return this.getDefaultState().withProperty(FACING, EnumFacing.DOWN);
		}
		else if(canPlaceAtSide(worldIn, pos, EnumFacing.NORTH))
		{
			return this.getDefaultState().withProperty(FACING, EnumFacing.NORTH);
		}
		else if(canPlaceAtSide(worldIn, pos, EnumFacing.SOUTH))
		{
			return this.getDefaultState().withProperty(FACING, EnumFacing.SOUTH);
		}
		else if(canPlaceAtSide(worldIn, pos, EnumFacing.EAST))
		{
			return this.getDefaultState().withProperty(FACING, EnumFacing.EAST);
		}
		else if(canPlaceAtSide(worldIn, pos, EnumFacing.WEST))
		{
			return this.getDefaultState().withProperty(FACING, EnumFacing.WEST);
		}
		else if(canPlaceAtSide(worldIn, pos, EnumFacing.UP))
		{
			return this.getDefaultState().withProperty(FACING, EnumFacing.UP);
		}
		else
		{
			return this.getDefaultState();
		}
    }
	
	@Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
		switch ((EnumFacing)state.getValue(FACING))
		{
		case UP:
			anchor = pos.down();
			break;
		case DOWN:
			anchor = pos.up();
			break;
		case EAST:
			anchor = pos.west();
			break;
		case WEST:
			anchor = pos.east();
			break;
		case NORTH:
			anchor = pos.south();
			break;
		case SOUTH:
			anchor = pos.north();
			break;
		default:
			break;
		}
	}
	
	private boolean canPlaceAtSide(World worldIn, BlockPos pos, EnumFacing facing)
	{
		if(facing.equals(EnumFacing.DOWN))
		{
			return worldIn.getBlockState(pos).getBlock().isReplaceable(worldIn, pos) && (!worldIn.isAirBlock(pos.up()) && worldIn.isBlockFullCube(pos.up()));
		}
		else if(facing.equals(EnumFacing.NORTH))
		{
			return worldIn.getBlockState(pos).getBlock().isReplaceable(worldIn, pos) && (!worldIn.isAirBlock(pos.south()) && worldIn.isBlockFullCube(pos.south()));
		}
		else if(facing.equals(EnumFacing.SOUTH))
		{
			return worldIn.getBlockState(pos).getBlock().isReplaceable(worldIn, pos) && (!worldIn.isAirBlock(pos.north()) && worldIn.isBlockFullCube(pos.north()));
		}
		else if(facing.equals(EnumFacing.EAST))
		{
			return worldIn.getBlockState(pos).getBlock().isReplaceable(worldIn, pos) && (!worldIn.isAirBlock(pos.west()) && worldIn.isBlockFullCube(pos.west()));
		}
		else if(facing.equals(EnumFacing.WEST))
		{
			return worldIn.getBlockState(pos).getBlock().isReplaceable(worldIn, pos) && (!worldIn.isAirBlock(pos.east()) && worldIn.isBlockFullCube(pos.east()));
		}
		else if(facing.equals(EnumFacing.UP))
		{
			return worldIn.getBlockState(pos).getBlock().isReplaceable(worldIn, pos) && (!worldIn.isAirBlock(pos.down()) && worldIn.isBlockFullCube(pos.down()));
		}
		else
		{
			return false;
		}
	}
	
	
	//placing helper
	private boolean canPlaceAt(World worldIn, BlockPos pos, EnumFacing facing)
    {
        BlockPos blockpos = pos.offset(facing.getOpposite());
        IBlockState iblockstate = worldIn.getBlockState(blockpos);
        Block block = iblockstate.getBlock();
        BlockFaceShape blockfaceshape = iblockstate.getBlockFaceShape(worldIn, blockpos, facing);

        if (facing.equals(EnumFacing.UP) && this.canPlaceBlockAt(worldIn, blockpos))
        {
            return true;
        }
        else if(facing.equals(EnumFacing.DOWN) && this.canPlaceBlockAt(worldIn, blockpos))
        {
        	return true;
        }
        else if (facing != EnumFacing.UP && facing != EnumFacing.DOWN)
        {
            return !isExceptBlockForAttachWithPiston(block) && blockfaceshape == BlockFaceShape.SOLID;
        }
        else
        {
            return false;
        }
    }
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		TileEntityCrystal tile = (TileEntityCrystal)worldIn.getTileEntity(pos);
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
	}
	
	//Blockstate helpers (mostly copied from BlockTorch.class)
	public IBlockState getStateFromMeta(int meta)
    {
        IBlockState iblockstate = this.getDefaultState();

        switch (meta)
        {
            case 1:
                iblockstate = iblockstate.withProperty(FACING, EnumFacing.EAST);
                break;
            case 2:
                iblockstate = iblockstate.withProperty(FACING, EnumFacing.WEST);
                break;
            case 3:
                iblockstate = iblockstate.withProperty(FACING, EnumFacing.SOUTH);
                break;
            case 4:
                iblockstate = iblockstate.withProperty(FACING, EnumFacing.NORTH);
                break;
            case 5:
            	iblockstate = iblockstate.withProperty(FACING, EnumFacing.UP);
                break;
            case 6:
            	iblockstate = iblockstate.withProperty(FACING, EnumFacing.DOWN);
            default:
                iblockstate = iblockstate.withProperty(FACING, EnumFacing.UP);
        }

        return iblockstate;
    }
	
	public int getMetaFromState(IBlockState state)
    {
        int i = 0;

        switch ((EnumFacing)state.getValue(FACING))
        {
            case EAST:
                i = i | 1;
                break;
            case WEST:
                i = i | 2;
                break;
            case SOUTH:
                i = i | 3;
                break;
            case NORTH:
                i = i | 4;
                break;
            case UP:
            	i = i | 5;
            	break;
            case DOWN:
            	i = i| 6;
            default:
                i = i | 6;
        }

        return i;
    }
	
	/**
     * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
    }

    /**
     * Returns the blockstate with the given mirror of the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {FACING});
    }

    /**
     * Get the geometry of the queried face at the given position and state. This is used to decide whether things like
     * buttons are allowed to be placed on the face, or how glass panes connect to the face, among other things.
     * <p>
     * Common values are {@code SOLID}, which is the default, and {@code UNDEFINED}, which represents something that
     * does not fit the other descriptions and will generally cause other things not to connect to the face.
     * 
     * @return an approximation of the form of the given face
     */
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return BlockFaceShape.UNDEFINED;
    }

    public Class<TileEntityCrystal> getTileEntityClass()
    {
    	return TileEntityCrystal.class;
    }
    
    @Override
    public boolean hasTileEntity() {
    	return true;
    }
    
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		if(this.anchor != null)
		return new TileEntityCrystal(this.anchor);
		return new TileEntityCrystal(color);
	}
}
