package rendering;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.resources.ResourceLocation;
import zombies.CustomZombie;


public class CZombieRenderer extends ZombieRenderer {
	private static final ResourceLocation TEXTURE = new ResourceLocation("zombie1", "textures/entity/custom_zombie.png");
	
	public CZombieRenderer(EntityRendererProvider.Context context) {
		super(context);
	}
	
	public ResourceLocation getTextureLocation(CustomZombie entity) {
		return TEXTURE;
	}
	
}
