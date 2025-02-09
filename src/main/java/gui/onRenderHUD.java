package gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.mojang.blaze3d.vertex.PoseStack;

import bulk.SpawnerLogic;
import bulk.WaveManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.network.PacketDistributor;
import networking.Networking;
import networking.RoundUpdatePacket;
import pointsmanager.PointsManager;

@Mod.EventBusSubscriber(modid = "zombie1", value = Dist.CLIENT)

public class onRenderHUD {
	
	private static int zombiesRemaining = 0;
	public static int currentRound = WaveManager.currentRound;
	private static boolean hudVisible = false;
	private static final HashMap<UUID, Integer> playerScores = new HashMap<>();
	
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
	//	String pointsText = "Points: ";

		//Choose coords
		int xPos = 10;
		int yPos = 10;
		
		
		//Draw the round text
		font.draw(poseStack, roundString, xPos, yPos, 0xFFFFFF);
		yPos += 10;
		
		font.draw(poseStack, zombiesLeftText, xPos, yPos, 0xFFFFFF);
		yPos += 10;
		
		//font.draw(poseStack, pointsText, xPos, yPos, 0xFFFFFF);
		//yPos += 15;
		
		font.draw(poseStack, "Scoreboard:", xPos, yPos, 0xFFD700);
		yPos += 10;
		
		
		for (Map.Entry<UUID, Integer> entry : playerScores.entrySet()) {
			if (mc.player.level.getPlayerByUUID(entry.getKey()) != null) {
				String playerName = mc.player.level.getPlayerByUUID(entry.getKey()).getName().getString();
				font.draw(poseStack, playerName + ": " + entry.getValue(), xPos + 5, yPos, 0xFFFFFF);
				yPos += 10;
			}
		}
		/*

		for(Player player : Minecraft.getInstance().player.level.players()) {
			String playerName = player.getName().getString();
	int playerScore = PointsManager.getPlayerScore((ServerPlayer) player);
			scoreboard.add(playerName + ": " + playerScore);
			
				yPos += 10;
		}
		
		for(String s : scoreboard) {
			font.draw(poseStack, s, xPos + 5, yPos, 0xFFFFFF);
			
				yPos += 10;
			}*/
	}
	
		public static void updateRound(int round, int zombiesLeft) {
			
		
			System.out.println("I'm sure this peace won't last for long..");
			currentRound=round;
			zombiesRemaining = zombiesLeft;
			hudVisible = true;
			
			
			if(zombiesRemaining == 0) {
			//	WaveManager.currentRound++;
				//zombiesRemaining = 7;// + (currentRound*4);
				Networking.CHANNEL.send(PacketDistributor.ALL.noArg(),
						new RoundUpdatePacket(currentRound, zombiesRemaining));
			}
		}
		
		public static void updateScores(Map<UUID, Integer> scores) {
			playerScores.clear();
			playerScores.putAll(scores);
		}
		
		public static void hideHUD() {
			hudVisible = false;
		}
	}

