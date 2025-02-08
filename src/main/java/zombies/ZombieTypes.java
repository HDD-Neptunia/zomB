//   zomB - Create your own maps and survive this fast-paced, wave-style minigame.
//   Copyright (C) 2024 HDD-Neptunia

package zombies;

import java.util.Arrays;
import java.util.List;
import entitybulk.EntityInit;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;


public enum ZombieTypes {
	
		CZOMBIE(CustomZombie.class, EntityInit.CZOMBIE.get(), 20.0, 5, 1),
		FASTZOMBIE(FastZombie.class, EntityInit.FASTZOMBIE.get(), 15.0, 3, 11),  // 15.0, 3, 11);
		ICEZOMBIE(IceZombie.class, EntityInit.ICEZOMBIE.get(), 130, 21, 17);
	
		private final Class<? extends Mob> zombieClass;
		private final EntityType<? extends Mob> entityType;
		
		private final double health;
		private final int attackDamage;
		private final int spawnInterval;
		
		
		ZombieTypes(Class<? extends Mob> zombieClass, EntityType<? extends Mob> entityType, double health, int attackDamage, int spawnInterval) {
			this.zombieClass = zombieClass;
			this.entityType = entityType;
			this.health = health;
			this.attackDamage = attackDamage;
			this.spawnInterval = spawnInterval;
		}
		
		public Class<? extends Mob> getZombieClass() {
			return zombieClass;
		}
		
		public EntityType<? extends Mob> getEntityType() {
			return entityType;
		}
		
		
		public double getHealth() {
			return health;
		}
		
		public int getAttackDamage() {
			return attackDamage;
		}
		
		public int getSpawnInterval() {
			return spawnInterval;
		}
		
		public static List<ZombieTypes> getAllTypes() {
			return Arrays.asList(values());
		} static {
			for (ZombieTypes type : ZombieTypes.values()) {
				try {
					type.getZombieClass().getConstructor(EntityType.class, Level.class);
					System.out.println("Validated constructor for: " + type.name());
					} catch (NoSuchMethodException e) {
						System.err.println("ZombieType " + type.name() + " has an invalid constructor.");
				}
			}
		}
	}