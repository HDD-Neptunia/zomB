//   zomB - Create your own maps and survive this fast-paced, wave-style minigame.
//   Copyright (C) 2024 HDD-Neptunia

package gui;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.loading.FMLEnvironment;
import networking.RoundUpdatePacket;

public class GUICore extends Screen {

	public static boolean guiVisible = false;
	private static int currentRound;
	private static int zombiesRemaining = 0;
	
	public GUICore(Component title) {
		super(title);
	}
	
	public void openGUI() {
		if (FMLEnvironment.dist == Dist.CLIENT) {
			Minecraft.getInstance().setScreen(new GUICore(new TextComponent("Wave")));
		}
	}

	@Override
	public void onClose() {
		if (this.minecraft != null) {
			this.minecraft.setScreen(null);
		}
		super.onClose();
	}
	
	@Override
	public boolean isPauseScreen() {
		return false;
	}
	
	@Override
	public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
		if(keyCode == 256) {
			return true;
			
		}	return super.keyPressed(keyCode,  scanCode, modifiers);
	}
	
	public static void setVisible(boolean visible) {
		guiVisible = visible;
	}
	
	public static boolean isVisible() {
		return guiVisible;
	}
	
	
	@Override
	public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
		if(!guiVisible) {
			return;
		}
		
		//draw round number
		drawString(poseStack, Minecraft.getInstance().font,
				"Round " + currentRound, 10, 10, 0xFFFFFF);
	
	    drawString(poseStack, Minecraft.getInstance().font,
			    "Zombies Remaining: " + zombiesRemaining, 10, 25, 0xFFFFFF);

	    super.render(poseStack, mouseX,  mouseY, partialTicks);
	}
	
	public static void updateRound(int round) {
		currentRound = round;
	}

	public static void updateZombieRemaining(int zombiesLeft) {
		zombiesLeft = RoundUpdatePacket.zombiesLeft;
	}
}




