package com.finn_505.lightsabermod.blocks.saberforge;

import java.util.List;

import com.finn_505.lightsabermod.init.ModBlocks;
import com.finn_505.lightsabermod.init.ModItems;
import com.finn_505.lightsabermod.util.force.ForceProvider;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBoat;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntitySaberForge extends TileEntityLockable implements ITickable, ISidedInventory
{
	private static final int[] SLOTS_TOP = new int[] {0};
	private static final int[] SLOTS_BOTTOM = new int[] {2,1};
	private static final int[] SLOTS_SIDES = new int[] {1};
	private NonNullList<ItemStack> furnaceItemStacks = NonNullList.<ItemStack>withSize(5,ItemStack.EMPTY);
	
	private int furnaceBurnTime;
	private int currentItemBurnTime;
	private int cookTime;
	private int totalCookTime;
	private String furnaceCustomName;
	
	@Override
	public int getSizeInventory() {
		return this.furnaceItemStacks.size();
	}
	
	@Override
	public boolean isEmpty() {
		for(ItemStack stack: this.furnaceItemStacks)
		{
			if(!stack.isEmpty())
				return false;
		}
		return true;
	}
	
	@Override
	public ItemStack getStackInSlot(int index) {
		return this.furnaceItemStacks.get(index);
	}
	
	@Override
	public ItemStack decrStackSize(int index, int count) {
		return ItemStackHelper.getAndSplit(furnaceItemStacks, index, count);
	}
	
	@Override
	public ItemStack removeStackFromSlot(int index) {
		return ItemStackHelper.getAndRemove(furnaceItemStacks, index);
	}
	
	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		ItemStack itemStack = this.furnaceItemStacks.get(index);
		boolean flag = !stack.isEmpty() && stack.isItemEqual(itemStack) && itemStack.areItemStackTagsEqual(stack, itemStack);
		this.furnaceItemStacks.set(index, stack);
		if(stack.getCount() > this.getInventoryStackLimit())
		{
			stack.setCount(this.getInventoryStackLimit()); 
		}
		
		if((index == 0 || index == 3 || index == 4)&& !flag)
		{
			this.totalCookTime = this.getCookTime(stack);
			this.cookTime = 0;
			this.markDirty();
		}
	}
	
	@Override
	public String getName() {
		return this.hasCustomName() ? this.furnaceCustomName : "container.saber_forge";
	}
	
	@Override
	public boolean hasCustomName() {
		return this.furnaceCustomName != null && !this.furnaceCustomName.isEmpty();
	}
	
	public void setfurnaceCustomName(String furnaceCustomName) {
		this.furnaceCustomName = furnaceCustomName;
	}
	
	public static void registerFixesFurnace(DataFixer fixer)
	{
		fixer.registerWalker(FixTypes.BLOCK_ENTITY, new ItemStackDataLists(TileEntitySaberForge.class, new String[] {"items"}));
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.furnaceItemStacks = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, furnaceItemStacks);
		this.furnaceBurnTime = compound.getInteger("BurnTime");
		this.cookTime = compound.getInteger("CookTime");
		this.totalCookTime = compound.getInteger("CookTimeTotal");
		this.currentItemBurnTime = getItemBurnTime((ItemStack)this.furnaceItemStacks.get(1));
		
		if(compound.hasKey("CustomName", 8)) this.setfurnaceCustomName(compound.getString("CustomName"));
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) 
	{
		super.writeToNBT(compound);
		compound.setInteger("BurnTime", (short)this.furnaceBurnTime);
		compound.setInteger("CookTime", (short)this.cookTime);
		compound.setInteger("CookTimeTotal", (short)this.totalCookTime);
		ItemStackHelper.saveAllItems(compound, this.furnaceItemStacks);
		
		if(this.hasCustomName()) compound.setString("CustomName", this.furnaceCustomName);
		return compound;
	}
	
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}
	
	public boolean isBurning()
	{
		return this.furnaceBurnTime > 0;
	}
	
	@SideOnly(Side.CLIENT)
	public static boolean isBurning(IInventory inventory)
	{
		return inventory.getField(0) > 0;
	}
	
	public void update()
    {
        boolean flag = this.isBurning();
        boolean flag1 = false;
        if (this.isBurning())
        {
            --this.furnaceBurnTime;
        }

        if (!this.world.isRemote)
        {
            ItemStack itemstack = this.furnaceItemStacks.get(1);

            if (this.isBurning() || !itemstack.isEmpty() && !((ItemStack)this.furnaceItemStacks.get(0)).isEmpty())
            {
                if (!this.isBurning() && this.canSmelt())
                {
                    this.furnaceBurnTime = getItemBurnTime(itemstack);
                    this.currentItemBurnTime = this.furnaceBurnTime;

                    if (this.isBurning())
                    {
                        flag1 = true;

                        if (!itemstack.isEmpty())
                        {
                            Item item = itemstack.getItem();
                            itemstack.shrink(1);

                            if (itemstack.isEmpty())
                            {
                                ItemStack item1 = item.getContainerItem(itemstack);
                                this.furnaceItemStacks.set(1, item1);
                            }
                        }
                    }
                }

                if (this.isBurning() && this.canSmelt())
                {
                    ++this.cookTime;

                    if (this.cookTime == this.totalCookTime)
                    {
                        this.cookTime = 0;
                        this.totalCookTime = this.getCookTime(this.furnaceItemStacks.get(0));
                        this.smeltItem();
                        flag1 = true;
                    }
                }
                else
                {
                    //this.cookTime = 0;
                	this.cookTime = Math.max(0, this.cookTime - 1);
                }
            }
            else if (!this.isBurning() && this.cookTime > 0)
            {
                this.cookTime = MathHelper.clamp(this.cookTime - 2, 0, this.totalCookTime);
            }

            if (flag != this.isBurning())
            {
                flag1 = true;
                BlockSaberForge.setState(this.isBurning(), this.world, this.pos);
            }
        }

        if (flag1)
        {
            this.markDirty();
        }
    }
    
    public int getCookTime(ItemStack stack)
    {
    	return 300;
    }
    
    private boolean canSmelt()
    {
        if (((ItemStack)this.furnaceItemStacks.get(0)).isEmpty())
        {
            return false;
        }
        else
        {
            ItemStack itemstack = SaberForgeRecipes.instance().getCookingResult(this.furnaceItemStacks.get(0),
            									this.furnaceItemStacks.get(3),this.furnaceItemStacks.get(4));

            if(doesSmeltingRequireHate(itemstack))
            {
            	List<EntityPlayer> players = world.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(this.pos.getX() - 1, this.pos.getY() - 2, this.pos.getZ() - 1,
            			this.pos.getX() + 2, this.pos.getY() + 2, this.pos.getZ() + 2));
            	boolean hasHate = false;
            	for(EntityPlayer player: players)
            	{
            		int hate = player.getCapability(ForceProvider.FORCE, null).getHate();
            		if(hate >= 10)
            		{
            			hasHate = true;
            			break;
            		}
            	}
            	if(!hasHate) return false;
            }
            
            if (itemstack.isEmpty())
            {
                return false;
            }
            else
            {
                ItemStack itemstack1 = this.furnaceItemStacks.get(2);

                if (itemstack1.isEmpty())
                {
                    return true;
                }
                else if (!itemstack1.isItemEqual(itemstack))
                {
                    return false;
                }
                else if (itemstack1.getCount() + itemstack.getCount() <= this.getInventoryStackLimit() && itemstack1.getCount() + itemstack.getCount() <= itemstack1.getMaxStackSize())  
                {
                    return true;
                }
                else
                {
                    return itemstack1.getCount() + itemstack.getCount() <= itemstack.getMaxStackSize(); 
                }
            }
        }
    }
    
    public void smeltItem()
    {
        if (this.canSmelt())
        {
            ItemStack input1 = this.furnaceItemStacks.get(0);
            ItemStack input2 = this.furnaceItemStacks.get(3);
            ItemStack input3 = this.furnaceItemStacks.get(4);
            ItemStack itemstack1 = SaberForgeRecipes.instance().getCookingResult(input1, input2, input3);
            ItemStack itemstack2 = this.furnaceItemStacks.get(2);

            if (itemstack2.isEmpty())
            {
                this.furnaceItemStacks.set(2, itemstack1.copy());
            }
            else if (itemstack2.getItem() == itemstack1.getItem())
            {
                itemstack2.grow(itemstack1.getCount());
            }

            input1.shrink(1);
            input2.shrink(1);
            input3.shrink(1);
        }
    }
    
    public static int getItemBurnTime(ItemStack stack)
    {
    	if(stack.getItem() == Items.LAVA_BUCKET)
    	{
    		return 300;
    	}
    	return 0;
    }
    
    public static boolean isItemFuel(ItemStack stack)
    {
    	return getItemBurnTime(stack) > 0;
    }
    
    @Override
    public boolean isUsableByPlayer(EntityPlayer player) 
    {
    	if(this.world.getTileEntity(this.pos) != this)
    	{
    		return false;
    	}
    	else
    	{
    		return player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
    	}
    }
    
    public void openInventory(EntityPlayer player)
    {
    	
    }

    public void closeInventory(EntityPlayer player)
    {
    	
    }
    
    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) 
    {
    	if(index == 2)
    	{
    		return false;
    	}
    	else if(index != 1)
    	{
    		return true;
    	}
    	else
    	{
    		return isItemFuel(stack);
    	}
    }
    
    public int[] getSlotsForFace(EnumFacing side)
    {
    	if(side == EnumFacing.DOWN)
    	{
    		return SLOTS_BOTTOM;
    	}
    	else
    	{
    		return side == EnumFacing.UP ? SLOTS_TOP : SLOTS_SIDES;
    	}
    }
    
    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) 
    {
    	return this.isItemValidForSlot(index, itemStackIn);
    }
    
    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) 
    {
    	if(direction == EnumFacing.DOWN && index == 1)
    	{
    		Item item = stack.getItem();
    		if(item != Items.WATER_BUCKET && item != Items.BUCKET)
    		{
    			return false;
    		}
    	}
    	return true;
    }
    
    @Override
    public String getGuiID() 
    {
    	return "lm:saber_forge";
    }
    
    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) 
    {
    	return new ContainerSaberForge(playerInventory, this);
    }
    
    public int getField(int id)
    {
        switch (id)
        {
            case 0:
                return this.furnaceBurnTime;
            case 1:
                return this.currentItemBurnTime;
            case 2:
                return this.cookTime;
            case 3:
                return this.totalCookTime;
            default:
                return 0;
        }
    }

    public void setField(int id, int value)
    {
        switch (id)
        {
            case 0:
                this.furnaceBurnTime = value;
                break;
            case 1:
                this.currentItemBurnTime = value;
                break;
            case 2:
                this.cookTime = value;
                break;
            case 3:
                this.totalCookTime = value;
                break;
            default:
            	break;
        }
    }
    
    @Override
    public int getFieldCount() 
    {
    	return 4;
    }
    
    @Override
    public void clear() 
    {
    	this.furnaceItemStacks.clear();
    }
    
    net.minecraftforge.items.IItemHandler handlerTop = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.UP);
    net.minecraftforge.items.IItemHandler handlerBottom = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.DOWN);
    net.minecraftforge.items.IItemHandler handlerSide = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.WEST);

	@Override
    @javax.annotation.Nullable
    public <T> T getCapability(net.minecraftforge.common.capabilities.Capability<T> capability, @javax.annotation.Nullable net.minecraft.util.EnumFacing facing)
    {
        if (facing != null && capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            if (facing == EnumFacing.DOWN)
                return (T) handlerBottom;
            else if (facing == EnumFacing.UP)
                return (T) handlerTop;
            else
                return (T) handlerSide;
        return super.getCapability(capability, facing);
    }
	
	public boolean doesSmeltingRequireHate(ItemStack itemstack)
	{
		return itemstack.getItem() == ModItems.ITEM_LIGHTSABERCRYSTAL_RED ||
        		itemstack.getItem() == Item.getItemFromBlock(ModBlocks.KYBER_CRYSTAL_BLOCK_RED) ||
        		itemstack.getItem() == ModItems.ITEM_REDLS ||
        		itemstack.getItem() == ModItems.ITEM_APPRENTICE_FOCUS ||
        		itemstack.getItem() == ModItems.ITEM_LORD_FOCUS;
	}
}
