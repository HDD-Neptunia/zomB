package zombies.czombie;

import zombies.czombie.CZombie;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;

public class CZombieRenderer extends ZombieRenderer {
	
	public CZombieRenderer(EntityRendererProvider.Context context) {
		super(context);
		
		// this.addLayer(new HumanoidArmorLayer<>(this,
			//	 new HumanoidModel<>(context.bakeLayer(ModelLayers.ZOMBIE_OUTER_ARMOR)),
			//	 new HumanoidModel<>(context.bakeLayer(ModelLayers.ZOMBIE_OUTER_ARMOR))
				 
			//));
	    } 
	
	public static final ResourceLocation
	CUSTOM_TEXTURE = new ResourceLocation("undead",
			"assets/czombie.png");

	public ResourceLocation
		 getTextureLocation(CZombie entity) {
			return CUSTOM_TEXTURE;
	}
	
	}
	
	

