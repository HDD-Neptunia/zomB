package zombies;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;


public class CustomZombie extends Zombie {
	
	public CustomZombie(EntityType<? extends CustomZombie> entityType, Level level) {
		super(entityType, level);
	}
	

	//constructor to match expected signature
	
	//public CustomZombie(ServerLevel level) {
	//	super(EntityType.ZOMBIE, level); //Replace EntityType.ZOMBIE with your custom entity type
	//}
	
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
