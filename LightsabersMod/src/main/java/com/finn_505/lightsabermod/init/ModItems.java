package com.finn_505.lightsabermod.init;

import java.util.ArrayList;
import java.util.List;

import com.finn_505.lightsabermod.Main;
import com.finn_505.lightsabermod.items.Lightsaber;
import com.finn_505.lightsabermod.items.LightsaberHilt;
import com.finn_505.lightsabermod.items.focus.ItemFocus;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ModItems {

	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	public static final Item ITEM_LIGHTSABERHILTGRIP = newItemDefault("hilt_grip", Main.STARWARS);
	public static final Item ITEM_LIGHTSABERHILTTOP = newItemDefault("hilt_top", Main.STARWARS);
	public static final Item ITEM_LIGHTSABERHILTLENS = newItemDefault("hilt_lens", Main.STARWARS);
	
	public static final Item ITEM_FOCUS_PIECE = newItemDefault("focus_piece", Main.STARWARS);
	
	public static final Item ITEM_LIGHTSABERCRYSTAL = newItemDefault("lightsaber_crystal", Main.STARWARS);
	public static final Item ITEM_LIGHTSABERCRYSTAL_BLUE = newItemDefault("lightsaber_crystal_blue", Main.STARWARS);
	public static final Item ITEM_LIGHTSABERCRYSTAL_PURPLE = newItemDefault("lightsaber_crystal_purple", Main.STARWARS);
	public static final Item ITEM_LIGHTSABERCRYSTAL_RED = newItemDefault("lightsaber_crystal_red", Main.STARWARS);
	
	public static final Item ITEM_PADAWAN_FOCUS = new ItemFocus("padawan_focus", 200, 160, true, 1);
	public static final Item ITEM_MASTER_FOCUS = new ItemFocus("master_focus", 500, 480, true, 2);
	public static final Item ITEM_APPRENTICE_FOCUS = new ItemFocus("apprentice_focus", 200, 160, false, 1);
	public static final Item ITEM_LORD_FOCUS = new ItemFocus("lord_focus", 500, 480, false, 2);
	
	public static final Item ITEM_IGNITEDGREENLS = new Lightsaber("lightsaber_green_ignited", 0);
	public static final Item ITEM_GREENLS = new LightsaberHilt("lightsaber_green_hilt", 0);
	
	public static final Item ITEM_IGNITEDBLUELS = new Lightsaber("lightsaber_blue_ignited", 1);
	public static final Item ITEM_BLUELS = new LightsaberHilt("lightsaber_blue_hilt", 1);
	
	public static final Item ITEM_IGNITEDPURPLELS = new Lightsaber("lightsaber_purple_ignited", 2);
	public static final Item ITEM_PURPLELS = new LightsaberHilt("lightsaber_purple_hilt", 2);
	
	public static final Item ITEM_IGNITEDREDLS = new Lightsaber("lightsaber_red_ignited", 3);
	public static final Item ITEM_REDLS = new LightsaberHilt("lightsaber_red_hilt", 3);
	
	public static final Item[] CRYSTALS = {ITEM_LIGHTSABERCRYSTAL, ITEM_LIGHTSABERCRYSTAL_BLUE, ITEM_LIGHTSABERCRYSTAL_PURPLE, ITEM_LIGHTSABERCRYSTAL_RED};
	public static final Item[] HILTS = {ITEM_GREENLS, ITEM_BLUELS, ITEM_PURPLELS, ITEM_REDLS};
	public static final Item[] SABERS = {ITEM_IGNITEDGREENLS, ITEM_IGNITEDBLUELS, ITEM_IGNITEDPURPLELS, ITEM_IGNITEDREDLS};
	
	private static Item newItemDefault(String name, CreativeTabs tab)
	{
		Item i = new Item();
		i.setUnlocalizedName(name);
		i.setRegistryName(name);
		i.setCreativeTab(tab);
		
		ITEMS.add(i);
		
		return i;
	}
}

