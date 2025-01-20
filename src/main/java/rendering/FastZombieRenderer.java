package rendering;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.resources.ResourceLocation;
import zombies.CustomZombie;
import zombies.FastZombie;

public class FastZombieRenderer extends CZombieRenderer {
		private static final ResourceLocation TEXTURE = new ResourceLocation("zombie1", "textures/entity/fast_zombie.png");
		
		public FastZombieRenderer(EntityRendererProvider.Context context) {
			super(context);
		}
		
		public ResourceLocation getTextureLocation(FastZombie entity) {
			return TEXTURE;
		}
		
	}

