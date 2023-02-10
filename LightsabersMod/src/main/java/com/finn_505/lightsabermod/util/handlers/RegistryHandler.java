package com.finn_505.lightsabermod.util.handlers;

import com.finn_505.lightsabermod.Main;
import com.finn_505.lightsabermod.init.EntityInit;
import com.finn_505.lightsabermod.init.ModBlocks;
import com.finn_505.lightsabermod.init.ModItems;
import com.finn_505.lightsabermod.util.force.ForceGuiOverlay;

import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@EventBusSubscriber
public class RegistryHandler {

	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
	}
	
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event)
	{
		for(Item i: ModItems.ITEMS)
		{
			Main.proxy.registerItemRenderer(i, 0, "inventory");
		}
		
		for(Block b: ModBlocks.BLOCKS)
		{
			Main.proxy.registerItemRenderer(Item.getItemFromBlock(b), 0, "inventory");
		}
	}
	
	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().registerAll(ModBlocks.BLOCKS.toArray(new Block[0]));
		TileEntityHandler.registerTileEntities();
	}
	
	public static void preInitRegistries()
	{
		EntityInit.registerEntities();
		RenderHandler.registerEntityRenders();
	}
	
	public static void initRegistries()
	{
		MinecraftForge.EVENT_BUS.register(new EHandler());
		MinecraftForge.EVENT_BUS.register(new EventBusHandler());
		MinecraftForge.TERRAIN_GEN_BUS.register(new TerrainHandler());
		MinecraftForge.EVENT_BUS.register(new TerrainHandler());
		MinecraftForge.EVENT_BUS.register(new ForceGuiOverlay());
		//FMLCommonHandler.instance().bus().register(new EHandler());
		NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new GuiHandler());
	}
}
