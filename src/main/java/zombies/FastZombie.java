package zombies;


import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;

public class FastZombie extends Zombie {

	public FastZombie(EntityType<? extends FastZombie> entityType, Level world) {
		super(entityType, world);
		// TODO Auto-generated constructor stub
	} 
	
//	public FastZombie(ServerLevel level) {
//		super(EntityType.ZOMBIE, level); //Replace EntityType.ZOMBIE with your custom entity type
//	}

	 public static AttributeSupplier.Builder createAttributes() {
		return AttributeSupplier.builder()
		// AttributeSupplier.Builder builder = AttributeSupplier.builder();
		//	.add(Attributes.MAX_HEALTH, 15.0)
		//	.add(Attributes.ATTACK_DAMAGE, 3.0)
			
			.add(Attributes.MOVEMENT_SPEED, 0.35);
		/*  this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(15.0);
	  	  this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(3.0);
	  	  this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(1.0);
	  */
	}
	
   public int spawnInterval = 11;
}
