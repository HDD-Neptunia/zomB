package cogs;

import net.minecraft.world.scores.criteria.ObjectiveCriteria;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.scores.Scoreboard;

public class ScoreboardHandlers {

	public static void createScoreboard(ServerPlayer player, MinecraftServer server) { 
		
		Scoreboard scoreboard =
				server.getScoreboard();
		Objective objective =
				scoreboard.getObjective("Points");
		
		if (objective == null) {
			objective = scoreboard.addObjective("Points", ObjectiveCriteria.DUMMY, 
					new TextComponent("Points"), ObjectiveCriteria.RenderType.INTEGER);
			scoreboard.setDisplayObjective(1, objective);
			
			
			scoreboard.getOrCreatePlayerScore(player.getScoreboardName(), objective).setScore(0);
		
		}
	}
		
		
		public static void addPoints(ServerPlayer player, int points, MinecraftServer server) {
			Scoreboard scoreboard = server.getScoreboard();
			Objective objective = scoreboard.getObjective("Points");
			
			if (objective != null) {
				
				scoreboard.getOrCreatePlayerScore(player.getName().getString(), objective).setScore(0);
			
		}
	}
}
