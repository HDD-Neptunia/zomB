package networking;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Supplier;

import gui.HUDState;
import gui.onRenderHUD;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public class PointsPacket {
	
	private final Map<UUID, Integer> playerScores;

	public PointsPacket(Map<UUID, Integer> playerScores) {
		this.playerScores = playerScores;
	}
	
	//encoding is unnecessary but required by forge
	public static void encode(PointsPacket msg, FriendlyByteBuf buf) {
		buf.writeInt(msg.playerScores.size());
		for (Map.Entry<UUID, Integer> entry : msg.playerScores.entrySet()) {
			buf.writeUUID(entry.getKey());
			buf.writeInt(entry.getValue());
		}
	}
	
	public static PointsPacket decode(FriendlyByteBuf buf) {
		int size = buf.readInt();
		Map<UUID, Integer> playerScores = new HashMap<>();
		
		for (int i = 0; i < size; i++) {
			UUID playerUUID = buf.readUUID();
			int score = buf.readInt();
			playerScores.put(playerUUID, score);
		}
		return new PointsPacket(playerScores);
	}
	
	
	public static void handle(PointsPacket msg, Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(() -> {
			Minecraft mc = Minecraft.getInstance();
			if (mc.player == null) return;
			
			//Update HUD with scores
			onRenderHUD.updateScores(msg.playerScores);
			});
		
		ctx.get().setPacketHandled(true);
	} //Handle method is client only due to Minecraft.getInstance()
}
