package zombies.czombie;

import com.mojang.brigadier.CommandDispatcher;

import init.ModEntities;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;

public class SpawnZombieCommand {
	public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("spawnczombie")
                .executes(context -> {
                    System.out.println("Spawn command executed");
                    ServerPlayer player = context.getSource().getPlayerOrException();
                    ServerLevel level = player.getLevel();
                    BlockPos position = player.blockPosition();

                    EntityType<CZombie> czombieType = ModEntities.CZOMBIE.get();
                    CZombie zombie = czombieType.create(level);
                    if (zombie != null) {
                        zombie.setPos(position.getX(), position.getY(), position.getZ());
                        level.addFreshEntity(zombie);
                        System.out.println("CZombie spawned successfully");
                        context.getSource().sendSuccess(new TextComponent("Spawned CZombie!"), true);
                    } else {
                        System.out.println("Failed to create CZombie entity");
                        context.getSource().sendFailure(new TextComponent("Failed to spawn CZombie."));
                    }
                    return 1;
                }));
    }
}