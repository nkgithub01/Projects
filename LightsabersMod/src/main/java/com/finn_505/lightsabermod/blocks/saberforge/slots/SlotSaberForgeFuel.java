package com.finn_505.lightsabermod.blocks.saberforge.slots;

import com.finn_505.lightsabermod.blocks.saberforge.TileEntitySaberForge;

import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotSaberForgeFuel extends Slot{

	public SlotSaberForgeFuel(IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return TileEntitySaberForge.isItemFuel(stack) || isBucket(stack);
	}
	
	@Override
	public int getItemStackLimit(ItemStack stack) {
		return isBucket(stack) ? 1: super.getItemStackLimit(stack);
	}
	
	public static boolean isBucket(ItemStack stack)
	{
		return stack.getItem() == Items.BUCKET;
	}
}
