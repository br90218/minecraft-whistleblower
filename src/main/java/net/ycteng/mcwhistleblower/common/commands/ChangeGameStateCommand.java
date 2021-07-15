package net.ycteng.mcwhistleblower.common.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.MessageArgument;
import net.ycteng.mcwhistleblower.common.data.CapabilityGameState;


public class ChangeGameStateCommand {
	public static void register(CommandDispatcher<CommandSource> dispatcher) {
		LiteralArgumentBuilder<CommandSource> changeGameStateCommand
		= Commands.literal("gamestate")
			.requires((commandSource) -> commandSource.hasPermission(2))
			.then(Commands.argument("tostate", MessageArgument.message())
					.executes(ChangeGameStateCommand::changeState)
				);
		dispatcher.register(changeGameStateCommand);
	}
	
	static int changeState(CommandContext<CommandSource> commandContext) {
		return 0;
	}
	
}
