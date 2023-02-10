package com.finn_505.lightsabermod.items;

import java.util.List;

import org.lwjgl.input.Keyboard;

import com.finn_505.lightsabermod.Main;
import com.finn_505.lightsabermod.init.ModItems;
import com.finn_505.lightsabermod.init.ModSoundEvents;

import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class LightsaberHilt extends Item{

	public int color;
	
	public LightsaberHilt(String name) {
		this.maxStackSize = 1;
		this.setCreativeTab(Main.STARWARS);
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		
		ModItems.ITEMS.add(this);
	}

	public LightsaberHilt(String name, int color) {
		this.maxStackSize = 1;
		this.setCreativeTab(Main.STARWARS);
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.color = color;
		
		ModItems.ITEMS.add(this);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("Sneak + " + Minecraft.getMinecraft().gameSettings.keyBindUseItem.getDisplayName() + " to ignite");
		tooltip.add("Does not fare well in water or lava");
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
		int blockX = MathHelper.floor(playerIn.posX);
        int blockY = MathHelper.floor(playerIn.posY) + 1;
        int blockZ = MathHelper.floor(playerIn.posZ);
        BlockPos p = new BlockPos(blockX, blockY, blockZ);
		if(playerIn.isSneaking() && !((worldIn.getBlockState(p).getBlock().getDefaultState() == Blocks.WATER.getDefaultState())
						||(worldIn.getBlockState(p).getBlock().getDefaultState() == Blocks.LAVA.getDefaultState())
						||(worldIn.getBlockState(p).getBlock().getDefaultState() == Blocks.FLOWING_WATER.getDefaultState())
						||(worldIn.getBlockState(p).getBlock().getDefaultState() == Blocks.FLOWING_LAVA.getDefaultState())))
		{
			worldIn.playSound(playerIn, playerIn.getPosition(), ModSoundEvents.LIGHTSABER_ON, SoundCategory.NEUTRAL, 1.0F, 1.0F);
			playerIn.setHeldItem(handIn, new ItemStack(ModItems.SABERS[color], 1));
		}
        return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
    }
}
