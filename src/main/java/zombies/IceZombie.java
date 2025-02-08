package zombies;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;

public class IceZombie extends Zombie {
	
	public IceZombie(EntityType<? extends Zombie> entityType, Level level) {
		super(entityType, level);
	}
	
	public static AttributeSupplier.Builder createAttributes() {
		return Zombie.createAttributes()
			.add(Attributes.MAX_HEALTH, 130.0)
			.add(Attributes.ATTACK_DAMAGE, 21)
			.add(Attributes.MOVEMENT_SPEED, 0.2);
	}
	
	private int spawnInterval = 17;
	
	@Override
	public void registerGoals() {
		super.registerGoals();
	}
}
