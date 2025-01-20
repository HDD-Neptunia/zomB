package blocksbulk;

import blocks.CustomZombieSpawnerBlock;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlocksInit {
	
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "zombie1");
	
	
	//THIS IS THE ISSUE
	public static final RegistryObject<Block> CZOMBIE_SPAWNER = BLOCKS.register("czombie_spawner",
			() -> new CustomZombieSpawnerBlock(Block.Properties.of(Material.METAL)
					.strength(3.5f)
					.noOcclusion()));
	
			
			//Blockinit register also causing issues? maybe?
			public static void register(IEventBus eventBus) {
		BLOCKS.register(eventBus);
	}
}
