package com.finn_505.lightsabermod;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import com.finn_505.lightsabermod.init.ModTriggers;
import com.finn_505.lightsabermod.proxy.CommonProxy;
import com.finn_505.lightsabermod.tabs.StarWarsTab;
import com.finn_505.lightsabermod.util.Reference;
import com.finn_505.lightsabermod.util.force.Force;
import com.finn_505.lightsabermod.util.force.ForceStorage;
import com.finn_505.lightsabermod.util.force.IForce;
import com.finn_505.lightsabermod.util.handlers.EHandler;
import com.finn_505.lightsabermod.util.handlers.EventBusHandler;
import com.finn_505.lightsabermod.util.handlers.GuiHandler;
import com.finn_505.lightsabermod.util.handlers.RegistryHandler;
import com.finn_505.lightsabermod.util.handlers.TerrainHandler;
import com.finn_505.lightsabermod.util.packets.ChangeSaberBlockState;
import com.finn_505.lightsabermod.util.packets.ChangeSaberBlockStateOff;
import com.finn_505.lightsabermod.util.packets.ForceUse;
import com.finn_505.lightsabermod.util.packets.forcesync.ForceSync;
import com.finn_505.lightsabermod.util.packets.forcesync.ForceSyncServer;
import com.finn_505.lightsabermod.util.packets.forcesync.ForceSyncServer.ForceSyncServerHandler;
import com.finn_505.lightsabermod.util.packets.ForceUse;
import com.finn_505.lightsabermod.util.packets.LightsaberSwingPacket;
import com.finn_505.lightsabermod.util.packets.MainThrowGreen;
import com.finn_505.lightsabermod.util.packets.OffThrowGreen;
import com.finn_505.lightsabermod.util.packets.SetForceAbility;
import com.finn_505.lightsabermod.util.packets.SetPassive;
import com.finn_505.lightsabermod.util.packets.setFloating;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.ICriterionTrigger;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.advancements.critereon.ItemPredicates;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid=Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)
public class Main {

	public static SimpleNetworkWrapper network;
	public static int packetID = 0;	
	public static final CreativeTabs STARWARS = new StarWarsTab("starWars");
	public static Method method;
	
	@Instance
	public static Main instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;
	
	public static ArrayList<KeyBinding> keyBinds;
	
	@EventHandler
	public static void PreInit(FMLPreInitializationEvent event)
	{
		RegistryHandler.preInitRegistries();
		network = NetworkRegistry.INSTANCE.newSimpleChannel("myChannel");
		registerMessagesToServer();
		registerMessagesToClient();
		CapabilityManager.INSTANCE.register(IForce.class,new ForceStorage(), Force.class);
	}
	@EventHandler
	public static void init(FMLInitializationEvent event)
	{
		RegistryHandler.initRegistries();
		keyBinds = new ArrayList<KeyBinding>();
		keyBinds.add(new KeyBinding("key.saberBlock.desc", Keyboard.KEY_R, "key.starWars.category"));
		keyBinds.add(new KeyBinding("key.throwSaber.desc", Keyboard.KEY_G, "key.starWars.category"));
		keyBinds.add(new KeyBinding("key.openForceGui.desc", Keyboard.KEY_GRAVE, "key.starWars.category"));
		
		for(KeyBinding key: keyBinds)
		{
			ClientRegistry.registerKeyBinding(key);
		}

        method = ReflectionHelper.findMethod(CriteriaTriggers.class, "register", "func_192118_a", ICriterionTrigger.class);

        method.setAccessible(true);

        for (int i=0; i < ModTriggers.TRIGGERS.length; i++)
        {
        	try {
				method.invoke(null, ModTriggers.TRIGGERS[i]);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
        } 
	}
	@EventHandler
	public static void PostInit(FMLPostInitializationEvent event)
	{
		
	}
	
	private static void registerMessagesToServer()
	{
		network.registerMessage(MainThrowGreen.MainThrowGreenHandler.class, MainThrowGreen.class, getNewId(), Side.SERVER);
		network.registerMessage(OffThrowGreen.OffThrowGreenHandler.class, OffThrowGreen.class, getNewId(), Side.SERVER);
		network.registerMessage(ChangeSaberBlockState.SaberBlockChangeHandler.class, ChangeSaberBlockState.class, getNewId(), Side.SERVER);
		network.registerMessage(ChangeSaberBlockStateOff.SaberBlockChangeOffHandler.class, ChangeSaberBlockStateOff.class, getNewId(), Side.SERVER);
		network.registerMessage(LightsaberSwingPacket.LightsaberSwingHandler.class, LightsaberSwingPacket.class, getNewId(), Side.SERVER);
		network.registerMessage(SetForceAbility.SetForceAbilityHandler.class, SetForceAbility.class, getNewId(), Side.SERVER);
		network.registerMessage(SetPassive.SetPassiveHandler.class, SetPassive.class, getNewId(), Side.SERVER);
		network.registerMessage(setFloating.SetFloatingHandler.class, setFloating.class, getNewId(), Side.SERVER);
		network.registerMessage(ForceUse.ForceUseHandler.class, ForceUse.class, getNewId(), Side.SERVER);
		network.registerMessage(ForceSyncServer.ForceSyncServerHandler.class, ForceSyncServer.class, getNewId(), Side.SERVER);
	}
	
	private static void registerMessagesToClient()
	{
		network.registerMessage(ForceSync.ForceSyncHandler.class, ForceSync.class, getNewId(), Side.CLIENT);
	}
	
	private static int id = -1;
	private static int getNewId()
	{
		id++;
		return id;
	}
}
