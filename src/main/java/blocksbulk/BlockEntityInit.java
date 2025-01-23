//   zomB - Create your own maps and survive this fast-paced, wave-style minigame.
//   Copyright (C) 2024 HDD-Neptunia

package blocksbulk;

import com.O.zomB.Zombie1;

import blocks.CustomZombieSpawnerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityInit {

	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Zombie1.MODID);
	
	public static final RegistryObject<BlockEntityType<CustomZombieSpawnerBlockEntity>> CustomZombieSpawnerBlockEntity = BLOCK_ENTITIES.register("czombie_spawner",
			 () -> 	BlockEntityType.Builder.of(CustomZombieSpawnerBlockEntity::new, BlocksInit.CZOMBIE_SPAWNER.get()).build(null));
	
	public static void register(IEventBus eventBus) {
		BLOCK_ENTITIES.register(eventBus);
	}
}

