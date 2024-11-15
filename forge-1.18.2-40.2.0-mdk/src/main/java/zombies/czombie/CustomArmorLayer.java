package zombies.czombie;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

public class CustomArmorLayer<T extends LivingEntity, M extends HumanoidModel<T>> extends RenderLayer<T, M> 
	{
		private final M model;

	public CustomArmorLayer(RenderLayerParent <T, M> parent, M model) {
		super(parent);
		this.model = model;
	}
	
	@Override
	public void render(PoseStack poseStack, MultiBufferSource multiBufferSource,
			int light, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				ResourceLocation resourceLocation = new ResourceLocation("undead", "textures/entity/czombie/czombie.png");
				var vertexConsumer = multiBufferSource.getBuffer(RenderType.entityCutout(resourceLocation));
					this.getParentModel().copyPropertiesTo(this.model);
					this.model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
					this.model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
					this.model.renderToBuffer(poseStack,  vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
				
				
				//renderModel(poseStack, multiBufferSource.getBuffer(RenderType.entityCutout(resourceLocation)), light, false, model, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch)
				 
				 }
}
