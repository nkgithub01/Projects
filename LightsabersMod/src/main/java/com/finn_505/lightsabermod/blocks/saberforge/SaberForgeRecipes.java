package com.finn_505.lightsabermod.blocks.saberforge;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import com.finn_505.lightsabermod.init.ModBlocks;
import com.finn_505.lightsabermod.init.ModItems;
import com.google.common.collect.Maps;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SaberForgeRecipes
{
	private static final SaberForgeRecipes COOKING_BASE = new SaberForgeRecipes();
	private final Map<ArrayList<ItemStack>, ItemStack> cookingList = Maps.<ArrayList<ItemStack>, ItemStack>newHashMap();
	private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();
	
	public static SaberForgeRecipes instance()
	{
		return COOKING_BASE;
	}
	
	public SaberForgeRecipes()
	{
		//artificial lightsaber crystal
		this.addCooking(Items.DIAMOND, Items.REDSTONE, Items.EMERALD, ModItems.ITEM_LIGHTSABERCRYSTAL_RED , 1.0F);
		//artificial lightsaber crystal block
		this.addCooking(Item.getItemFromBlock(Blocks.DIAMOND_BLOCK), Item.getItemFromBlock(Blocks.REDSTONE_BLOCK), 
				Item.getItemFromBlock(Blocks.EMERALD_BLOCK), Item.getItemFromBlock(ModBlocks.KYBER_CRYSTAL_BLOCK_RED) , 1.0F);
		
		//lightsabers
		this.addCooking(ModItems.ITEM_LIGHTSABERHILTTOP, ModItems.ITEM_LIGHTSABERCRYSTAL, ModItems.ITEM_LIGHTSABERHILTGRIP, 
				ModItems.ITEM_GREENLS, 1.0F);
		this.addCooking(ModItems.ITEM_LIGHTSABERHILTTOP, ModItems.ITEM_LIGHTSABERCRYSTAL_BLUE, ModItems.ITEM_LIGHTSABERHILTGRIP, 
				ModItems.ITEM_BLUELS, 1.0F);
		this.addCooking(ModItems.ITEM_LIGHTSABERHILTTOP, ModItems.ITEM_LIGHTSABERCRYSTAL_PURPLE, ModItems.ITEM_LIGHTSABERHILTGRIP, 
				ModItems.ITEM_PURPLELS, 1.0F);
		this.addCooking(ModItems.ITEM_LIGHTSABERHILTTOP, ModItems.ITEM_LIGHTSABERCRYSTAL_RED, ModItems.ITEM_LIGHTSABERHILTGRIP, 
				ModItems.ITEM_REDLS, 1.0F);
		
		//level 1 force focus
		this.addCooking(ModItems.ITEM_FOCUS_PIECE, Item.getItemFromBlock(ModBlocks.KYBER_CRYSTAL_BLOCK_RED), ModItems.ITEM_FOCUS_PIECE, 
				ModItems.ITEM_APPRENTICE_FOCUS, 1.0F);
		this.addCooking(ModItems.ITEM_FOCUS_PIECE, Item.getItemFromBlock(ModBlocks.KYBER_CRYSTAL_BLOCK), ModItems.ITEM_FOCUS_PIECE, 
				ModItems.ITEM_PADAWAN_FOCUS, 1.0F);
		this.addCooking(ModItems.ITEM_FOCUS_PIECE, Item.getItemFromBlock(ModBlocks.KYBER_CRYSTAL_BLOCK_BLUE), ModItems.ITEM_FOCUS_PIECE, 
				ModItems.ITEM_PADAWAN_FOCUS, 1.0F);
		this.addCooking(ModItems.ITEM_FOCUS_PIECE, Item.getItemFromBlock(ModBlocks.KYBER_CRYSTAL_BLOCK_PURPLE), ModItems.ITEM_FOCUS_PIECE, 
				ModItems.ITEM_PADAWAN_FOCUS, 1.0F);
		
		//level 2 focus
		this.addCooking(ModItems.ITEM_FOCUS_PIECE, Item.getItemFromBlock(ModBlocks.KYBER_CRYSTAL_BLOCK_RED), ModItems.ITEM_APPRENTICE_FOCUS,
				ModItems.ITEM_LORD_FOCUS, 1.0F);
		this.addCooking(ModItems.ITEM_FOCUS_PIECE, Item.getItemFromBlock(ModBlocks.KYBER_CRYSTAL_BLOCK_BLUE), ModItems.ITEM_PADAWAN_FOCUS,
				ModItems.ITEM_MASTER_FOCUS, 1.0F);
		this.addCooking(ModItems.ITEM_FOCUS_PIECE, Item.getItemFromBlock(ModBlocks.KYBER_CRYSTAL_BLOCK), ModItems.ITEM_PADAWAN_FOCUS,
				ModItems.ITEM_MASTER_FOCUS, 1.0F);
		this.addCooking(ModItems.ITEM_FOCUS_PIECE, Item.getItemFromBlock(ModBlocks.KYBER_CRYSTAL_BLOCK_PURPLE), ModItems.ITEM_PADAWAN_FOCUS,
				ModItems.ITEM_MASTER_FOCUS, 1.0F);
	}
	
	public void addCooking(ItemStack input1, ItemStack input2, ItemStack input3, ItemStack stack, float experience)
	{
		this.addCookingRecipe(input1, input2, input3, stack, experience);
	}
	
	public void addCooking(Item input1, Item input2, Item input3, Item stack, float experience)
	{
		this.addCookingRecipe(new ItemStack(input1, 1, 32767), new ItemStack(input2, 1, 32767),
				new ItemStack(input3, 1, 32767),new ItemStack(stack, 1), experience);
	}
	public void addCooking(Item input1, Item input2, Item input3, ItemStack stack, float experience)
	{
		this.addCookingRecipe(new ItemStack(input1, 1, 32767), new ItemStack(input2, 1, 32767),
				new ItemStack(input3, 1, 32767),stack, experience);
	}
	
	public void addCookingRecipe(ItemStack input1, ItemStack input2, ItemStack input3, ItemStack stack, float experience)
	{
		if(getCookingResult(input1, input2, input3) != ItemStack.EMPTY)
		{
			net.minecraftforge.fml.common.FMLLog.info("ignored cooking recipe with conflicting input: {} = {}", input1, stack);
			return;
		}
		ArrayList<ItemStack> list = new ArrayList<ItemStack>();
		list.add(input1);
		list.add(input2);
		list.add(input3);
		this.cookingList.put(list, stack);
		this.experienceList.put(stack, Float.valueOf(experience));
	}
	
	public ItemStack getCookingResult(ItemStack stack1, ItemStack stack2, ItemStack stack3)
    {
        for (Entry<ArrayList<ItemStack>, ItemStack> entry : this.cookingList.entrySet())
        {
        	ArrayList<ItemStack> list = new ArrayList<ItemStack>();
        	list.add(stack1);
        	list.add(stack2);
        	list.add(stack3);
            if (this.compareItemStackArrayLists(list, entry.getKey()))
            {
                return entry.getValue();
            }
        }

        return ItemStack.EMPTY;
    }
	
	private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
    {
        return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
    }
	
	private boolean compareItemStackArrayLists(ArrayList<ItemStack> stack1, ArrayList<ItemStack> stack2)
    {
        if(stack1.size() != stack2.size()) return false;
        for(int i = 0; i < stack1.size(); i++)
        {
        	ItemStack itemStack1 = stack1.get(i);
        	ItemStack itemStack2 = stack2.get(i);
        	if(!compareItemStacks(itemStack1, itemStack2))
        		return false;
        }
        return true;
    }

    public Map<ArrayList<ItemStack>, ItemStack> getCookingList()
    {
        return this.cookingList;
    }

    public float getCookingExperience(ItemStack stack)
    {
        float ret = stack.getItem().getSmeltingExperience(stack);
        if (ret != -1) return ret;
        for (Entry<ItemStack, Float> entry : this.experienceList.entrySet())
        {
            if (this.compareItemStacks(stack, entry.getKey()))
            {
                return ((Float)entry.getValue()).floatValue();
            }
        }
        return 0.0F;
    }
}