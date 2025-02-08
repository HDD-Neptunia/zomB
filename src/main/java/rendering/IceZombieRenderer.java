package rendering;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import zombies.IceZombie;

public class IceZombieRenderer extends CZombieRenderer {
	private static final ResourceLocation TEXTURE = new ResourceLocation("zombie1", "textures/entity/ice_zombie.png");
	
	public IceZombieRenderer(EntityRendererProvider.Context context) {
		super(context);
	}
	
	public ResourceLocation getTextureLocation(IceZombie entity) {
		return TEXTURE;
	}
}
