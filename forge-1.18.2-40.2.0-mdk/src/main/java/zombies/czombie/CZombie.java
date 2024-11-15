package zombies.czombie;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;

public class CZombie extends Zombie {

	public CZombie(EntityType<? extends Zombie> type, Level level) {
		super(type, level);
		System.out.println("Constructor code for CZombie check-------------------------"
				+ "-------------------------------------------------------------------------------------------------------"
				+ "----------------------------------------------------------------------------------------------------------------------------------------------------------------");
		}
	
	public static AttributeSupplier.Builder
	createCZombieAttributes() {
		return Monster.createMonsterAttributes()
			.add(Attributes.MAX_HEALTH,
					40.0D)
			.add(Attributes.MOVEMENT_SPEED,
					0.25D)
		    .add(Attributes.ATTACK_DAMAGE, 3.5D);
				}
		
	};