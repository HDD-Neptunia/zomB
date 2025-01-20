package bulk;

import blocksbulk.BlocksInit;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class MOD_TAB {

		public static final CreativeModeTab MOD_TAB = new CreativeModeTab("mod_tab") {
			
			@Override
			public ItemStack makeIcon() {
				return new ItemStack(BlocksInit.CZOMBIE_SPAWNER.get());
			} 
		};
		
	}
		

