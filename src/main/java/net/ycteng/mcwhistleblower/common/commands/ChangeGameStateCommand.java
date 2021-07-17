package net.ycteng.mcwhistleblower.common.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraftforge.common.util.LazyOptional;
import net.ycteng.mcwhistleblower.McWhistleblower;
import net.ycteng.mcwhistleblower.common.data.CapabilityGameState;
import net.ycteng.mcwhistleblower.common.data.CapabilityPlayerState;
import net.ycteng.mcwhistleblower.common.data.IGameState;
import net.ycteng.mcwhistleblower.common.data.IPlayerState;


public class ChangeGameStateCommand {
	public static void register(CommandDispatcher<CommandSource> dispatcher) {
		LiteralArgumentBuilder<CommandSource> changeGameStateCommand
		= Commands.literal("gamestate")
			.requires((commandSource) -> commandSource.hasPermission(0)).executes(ChangeGameStateCommand::changeState);
		
		dispatcher.register(changeGameStateCommand);
	}
	
	static int changeState(CommandContext<CommandSource> commandContext) throws CommandSyntaxException {
		McWhistleblower.LOGGER.debug("Received change state command");
		LazyOptional<IGameState> capability = commandContext.getSource().getServer().overworld().getCapability(CapabilityGameState.WORLD_GAMESTATE);
		IGameState gameState = capability.resolve().get();
		McWhistleblower.LOGGER.debug("current gamestate: " + gameState.getGameState());
		capability.resolve().get().toNextGameState();
		McWhistleblower.LOGGER.debug("current gamestate: " + capability.resolve().get().getGameState());
		return 0;
	}
}
