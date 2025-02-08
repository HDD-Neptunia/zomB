package gui;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.mojang.blaze3d.vertex.PoseStack;

import bulk.SpawnerLogic;
import bulk.WaveManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.network.PacketDistributor;
import networking.Networking;
import networking.RoundUpdatePacket;

@Mod.EventBusSubscriber(modid = "zombie1", value = Dist.CLIENT)

public class onRenderHUD {
	
	private static int zombiesRemaining = 0;
	public static int currentRound = 0;
	private static boolean hudVisible = false;
	
	@SubscribeEvent
	public static void onRenderGameOverlay(RenderGameOverlayEvent.Text event) {
		
		if(!hudVisible) {
			return; //Don't render HUD if disabled
		}
		
		//Matrix stack for rendering
		PoseStack poseStack = event.getMatrixStack();
		
		//Get the MC instance and font renderer
		Minecraft mc = Minecraft.getInstance();
		Font font = mc.font;
		
		
		String roundString = Integer.toString(currentRound);
		String zombiesLeftText = "Zombies remaining: " + zombiesRemaining;
		String pointsText = "Points: ";
		
		//Example scoreboard entries (needs actual iterations)
		List<String> scoreboard = Collections.emptyList();
				
		//Choose coords
		int xPos = 10;
		int yPos = 10;
		
		
		//Draw the round text
		font.draw(poseStack, roundString, xPos, yPos, 0xFFFFFF);
		yPos += 10;
		
		font.draw(poseStack, zombiesLeftText, xPos, yPos, 0xFFFFFF);
		yPos += 10;
		
		font.draw(poseStack, pointsText, xPos, yPos, 0xFFFFFF);
		yPos += 15;
		
		font.draw(poseStack, "Scoreboard:", xPos, yPos, 0xFFD700);
		yPos += 10;
		
		

	
		
		for(String s : scoreboard) {
			font.draw(poseStack, s, xPos + 5, yPos, 0xFFFFFF);
			
				yPos += 10;
			}
		}
	
		public static void updateRound(int round, int zombiesLeft) {
			
		
			System.out.println("I'm sure this peace won't last for long..");
			currentRound=round;
			zombiesRemaining = zombiesLeft;
			hudVisible = true;
			
			
			if(zombiesRemaining == 0) {
				WaveManager.currentRound++;
				zombiesRemaining = 7 + (currentRound*4);
				Networking.CHANNEL.send(PacketDistributor.ALL.noArg(),
						new RoundUpdatePacket(currentRound, zombiesRemaining));
			}
		}
		
		public static void hideHUD() {
			hudVisible = false;
		}
	}

