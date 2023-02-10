package com.finn_505.lightsabermod.tabs;

import com.finn_505.lightsabermod.init.ModItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class StarWarsTab extends CreativeTabs{

	public StarWarsTab(String label) 
	{
		super(label);
	}
	
	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(ModItems.ITEM_LIGHTSABERCRYSTAL);
	}
}
