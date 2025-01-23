//    zomB - Create your own maps and survive this fast-paced, wave-style minigame.
//    Copyright (C) 2024 HDD-Neptunia

package zombies;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;


public class CustomZombie extends Zombie {
	
	public CustomZombie(EntityType<? extends Zombie> entityType, Level level) {
		super(entityType, level);
	}
	
	public static AttributeSupplier.Builder createAttributes() {
		return Zombie.createAttributes()
	    .add(Attributes.MAX_HEALTH, 20.0)
		.add(Attributes.ATTACK_DAMAGE, 3.0)
		.add(Attributes.MOVEMENT_SPEED, 0.25);
	}
	
	private int spawnInterval = 1;
	
	@Override
	public void registerGoals() {
		super.registerGoals();
	}
}