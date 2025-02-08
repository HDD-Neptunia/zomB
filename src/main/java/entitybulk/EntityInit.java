//   zomB - Create your own maps and survive this fast-paced, wave-style minigame.
//   Copyright (C) 2024 HDD-Neptunia

package entitybulk;

import com.O.zomB.Zombie1;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import zombies.CustomZombie;
import zombies.FastZombie;
import zombies.IceZombie;


public class EntityInit {
	
	//Register your entities to a register
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
	
	public static final RegistryObject<EntityType<IceZombie>> ICEZOMBIE = ENTITY_TYPES.register("icezombies",
			() -> EntityType.Builder.of(IceZombie::new, MobCategory.MONSTER)
				.sized(0.5F, 2.0F)
				.build(new ResourceLocation(Zombie1.MODID, "icezombie").toString()));

		    //Register your entities and items to eventBus
		    public static void register(IEventBus eventBus) {
		    	
		    	//Register custom entities
		    	ENTITY_TYPES.register(eventBus);               //Check not being able to pause game					       //Set to nighttime
		    	System.out.println("once");
	
	}
}