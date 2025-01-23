//   zomB - Create your own maps and survive this fast-paced, wave-style minigame.
//   Copyright (C) 2024 HDD-Neptunia

package com.O.zomB;

import blocksbulk.BlocksInit;
import entitybulk.EntityInit;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import rendering.CZombieRenderer;
import rendering.FastZombieRenderer;

@Mod.EventBusSubscriber(modid = Zombie1.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventHandler {

	@SubscribeEvent
	public static void onClientSetup(FMLClientSetupEvent event) {
		//Register custom renderer for CZombie
		EntityRenderers.register(EntityInit.CZOMBIE.get(), CZombieRenderer::new);
		EntityRenderers.register(EntityInit.FASTZOMBIE.get(), FastZombieRenderer::new);
		
		//Transparency layer for custom spawner
		ItemBlockRenderTypes.setRenderLayer(BlocksInit.CZOMBIE_SPAWNER.get(), RenderType.cutout());
	}
}
