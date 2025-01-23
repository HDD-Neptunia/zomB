//   zomB - Create your own maps and survive this fast-paced, wave-style minigame.
//   Copyright (C) 2024 HDD-Neptunia

package blocks;

import blocksbulk.BlockEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class CustomZombieSpawnerBlockEntity extends BlockEntity {

	private int spawnCooldown = 20; //Example for now
	
	
	public CustomZombieSpawnerBlockEntity(BlockPos pos, BlockState state) {
		super(BlockEntityInit.CustomZombieSpawnerBlockEntity.get(), pos, state);
	
	if (spawnCooldown > 0) {
		spawnCooldown--;
		return;
	}
	
	//reset cooldown
	spawnCooldown = 20;
	}
}