package com.example.examplemod;

import com.mojang.logging.LogUtils;

import init.ModEntities;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import zombies.czombie.CZombie;
import zombies.czombie.CZombieRenderer;
import zombies.czombie.SpawnZombieCommand;

import org.slf4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("undead")
public class Undead
{
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    
    
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, "undead");
    
    public static final RegistryObject<EntityType<CZombie>> CZOMBIE = ENTITY_TYPES.register("czombie",
    		() -> EntityType.Builder.of(CZombie::new, MobCategory.MONSTER)
    			.sized(0.6F, 1.95F)
    			.clientTrackingRange(35)
    			.build("czombie"));

    public Undead()
    {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);       
        
        ModEntities.ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        
        //Register DeferredRegister
        ENTITY_TYPES.register(modEventBus);
        
        //Register an event listener for entity types
        modEventBus.addListener(this::onAttributeCreate);
        
        ModEntities.ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        MinecraftForge.EVENT_BUS.register(this);
      
        
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.addListener(this::onRegisterCommand);
    }
   

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }
    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // Some example code to dispatch IMC to another mod
        InterModComms.sendTo("examplemod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }
    private void processIMC(final InterModProcessEvent event)
    {
        // Some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.messageSupplier().get()).
                collect(Collectors.toList()));
    }
    private void onRegisterCommand(RegisterCommandsEvent event) {
    	SpawnZombieCommand.register(event.getDispatcher());
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    private void onAttributeCreate(EntityAttributeCreationEvent event) {
    	//Add attributes to the custom zombie entity
    	event.put(CZOMBIE.get(), CZombie.createCZombieAttributes().build());
    }
    private void doClientStuff(final FMLClientSetupEvent event) {
    	
    //Register renderers
    	EntityRenderers.register(ModEntities.CZOMBIE.get(), CZombieRenderer::new);
    }
    
    
    
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)

  
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }
    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents
    {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent)
        {// Register a new block here
            LOGGER.info("HELLO from Register Block");
        }
        
        	
        }
    
   
    }

