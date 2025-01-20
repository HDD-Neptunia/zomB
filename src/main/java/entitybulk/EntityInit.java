package entitybulk;


 
import com.O.zomB.Zombie1;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import zombies.CustomZombie;
import zombies.FastZombie;

public class EntityInit {
	
	//Register your entities
	
	
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = 
		DeferredRegister.create(ForgeRegistries.ENTITIES, Zombie1.MODID);
		

	
	public static final RegistryObject<EntityType<CustomZombie>> CZOMBIE = ENTITY_TYPES.register("czombie",
			() -> EntityType.Builder.of(CustomZombie::new, MobCategory.MONSTER)
				.sized(0.6F, 1.95F)
				.build(new ResourceLocation(Zombie1.MODID, "czombie").toString()));
			
	public static final RegistryObject<EntityType<FastZombie>> FASTZOMBIE = ENTITY_TYPES.register("fastzombie",
			() -> EntityType.Builder.of(FastZombie::new, MobCategory.MONSTER)
				.sized(0.6F, 1.7F)
				.build(new ResourceLocation(Zombie1.MODID, "fastzombie").toString()));
	
	
	//Register spawn egg
	
	 public static final DeferredRegister<Item> ITEM_TYPES = 
		        DeferredRegister.create(ForgeRegistries.ITEMS, Zombie1.MODID);

		  //  public static final RegistryObject<Item> CZOMBIE_SPAWN_EGG = ITEM_TYPES.register("czombie_spawn_egg",
		   //     () -> new ForgeSpawnEggItem(() -> CZOMBIE.get(), 0x1D1D1D, 0xFFAA00, new Item.Properties()));
		    
		  //  public static final RegistryObject<Item> FASTZOMBIE_SPAWN_EGG = ITEM_TYPES.register("fast_zombie_spawn_egg",
		    //	() -> new ForgeSpawnEggItem(() -> FASTZOMBIE.get(), 0xD1D1D1, 0xAAFF00, new Item.Properties()));
		    
		    //Register your entities and items
		    public static void register(IEventBus eventBus) {
		    	
		    	//Register custom entities
		    	ENTITY_TYPES.register(eventBus);
		    	
		    	ITEM_TYPES.register(eventBus); //Registers items like spawn eggs
		    	System.out.println("once");
	
		    }
}
	   /* 
		    //Event Handling for entity registration
		    @SubscribeEvent
		    public static void onRegisterEntities(RegistryEvent.Register<EntityType<?>> event) {
		    	ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
		    }
		    
  }

	/*	    //Event handling for item registration
		    @SubscribeEvent
		    public static void onRegisterItems(RegistryEvent.Register<Item> event) {
		    	ITEM_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
		    }
} */

