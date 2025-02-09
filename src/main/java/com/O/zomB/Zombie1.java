//   zomB - Create your own maps and survive this fast-paced, wave-style minigame.
//   Copyright (C) 2024 HDD-Neptunia

package com.O.zomB;

import com.mojang.logging.LogUtils;

import blocksbulk.BlockEntityInit;
import blocksbulk.BlocksInit;
import entitybulk.EntityInit;
import entitybulk.ModEntityAttributes;
import itembulk.ItemInit;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import networking.Networking;
import pointsmanager.PointsManager;
import zombies.CustomZombie;
import zombies.FastZombie;
import zombies.IceZombie;

import org.slf4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("zombie1")
public class Zombie1 {

	public static final String MODID = "zombie1";
	
	// Directly reference a slf4j logger
	private static final Logger LOGGER = LogUtils.getLogger();

	public Zombie1() {
		// Register the setup method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		// Register the enqueueIMC method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
		// Register the processIMC method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
		// Register ourselves for server and other game events we are interested in
	
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		
		EntityInit.register(eventBus);
		BlocksInit.register(eventBus);
		BlockEntityInit.register(eventBus);
		ItemInit.register(eventBus);

		MinecraftForge.EVENT_BUS.register(new PointsManager());
		MinecraftForge.EVENT_BUS.register(this);
	}

	private void setup(final FMLCommonSetupEvent event) {
		// some preinit code
		LOGGER.info("HELLO FROM PREINIT");
		LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
		Networking.register();
	}

	private void enqueueIMC(final InterModEnqueueEvent event) {
		// Some example code to dispatch IMC to another mod
		InterModComms.sendTo("zombie1", "helloworld", () -> {
			LOGGER.info("Hello world from the MDK");
			return "Hello world";
		});
	}
	
	private void processIMC(final InterModProcessEvent event) {
		// Some example code to receive and process InterModComms from other mods
		LOGGER.info("Got IMC {}",
				event.getIMCStream().map(m -> m.messageSupplier().get()).collect(Collectors.toList()));
	}

	// You can use SubscribeEvent and let the Event Bus discover methods to call
	@SubscribeEvent
	public void onServerStarting(ServerStartingEvent event) {
		// Do something when the server starts
		LOGGER.info("HELLO from server starting");
	}
	
  	public void registerEntityAttributes(EntityAttributeCreationEvent event) {
		event.put(EntityInit.CZOMBIE.get(), CustomZombie.createAttributes().build());
		event.put(EntityInit.FASTZOMBIE.get(), CustomZombie.createAttributes().build());
		event.put(EntityInit.ICEZOMBIE.get(), IceZombie.createAttributes().build());
		LOGGER.info("Custom Entity Attributes Registered");
	}   

	// You can use EventBusSubscriber to automatically subscribe events on the
	// contained class (this is subscribing to the MOD
	// Event bus for receiving Registry Events)
	
	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents {
		@SubscribeEvent
		public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
			// Register a new block here
			LOGGER.info("HELLO from Register Block");
	}
  }
}
