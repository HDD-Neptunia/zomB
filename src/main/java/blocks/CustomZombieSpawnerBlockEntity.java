package blocks;

import blocksbulk.BlockEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class CustomZombieSpawnerBlockEntity extends BlockEntity {

	//private final CustomZombieSpawnerBlock spawner = new CustomZombieSpawnerBlock(null);
	private int spawnCooldown = 20; //Example for now
	
	
	public CustomZombieSpawnerBlockEntity(BlockPos pos, BlockState state) {
		super(BlockEntityInit.CustomZombieSpawnerBlockEntity.get(), pos, state);
		// TODO Auto-generated constructor stub
	
	

	
	//@Override
	//public void tick() {
	//	if (level == null || level.isClientSide) {
	//		return;
	//	}
	//}

	if (spawnCooldown > 0) {
		spawnCooldown--;
		return;
	}
	
	//int currentRound = getCurrentRound();
	//spawner.spawnZombies(level, currentRound);
	
	//reset cooldown
	spawnCooldown = 20;
	}



}