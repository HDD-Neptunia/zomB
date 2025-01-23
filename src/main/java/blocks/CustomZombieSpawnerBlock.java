//   zomB - Create your own maps and survive this fast-paced, wave-style minigame.
//   Copyright (C) 2024 HDD-Neptunia

package blocks;

import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class CustomZombieSpawnerBlock extends Block implements EntityBlock {

	public CustomZombieSpawnerBlock(Properties properties) {
		super(properties);
	}
	
	private int healthMultipler;
	private int round;
	
	
	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new CustomZombieSpawnerBlockEntity(pos, state);
	}
	
	@Override
	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.MODEL;
	}
	
	@Override
	public boolean skipRendering(BlockState state, BlockState adjacentBlockState, Direction direction) {
		return false;
	}
}
