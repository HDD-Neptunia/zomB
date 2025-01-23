//   zomB - Create your own maps and survive this fast-paced, wave-style minigame.
//   Copyright (C) 2024 HDD-Neptunia

package itembulk;

import blocksbulk.BlocksInit;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import bulk.MOD_TAB;

public class ItemInit {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "zombie1");

	public static final RegistryObject<Item> CUSTOM_ZOMBIE_SPAWNER_ITEM = 
			ITEMS.register("czombie_spawner",
				() -> new BlockItem(BlocksInit.CZOMBIE_SPAWNER.get(),
						new Item.Properties().tab(MOD_TAB.MOD_TAB)));
	
	public static void register(IEventBus eventBus) {
		ITEMS.register(eventBus);
	}
}
