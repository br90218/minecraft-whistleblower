package net.ycteng.mcwhistleblower.common.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.common.util.LazyOptional;
import net.ycteng.mcwhistleblower.common.data.CapabilityPlayerState;
import net.ycteng.mcwhistleblower.common.data.IPlayerState;
import net.ycteng.mcwhistleblower.common.network.Networking;
import net.ycteng.mcwhistleblower.common.network.UpdatePlayerStateToLocalPacket;

public class ChangeBackNumberCommand {
	public static void register(CommandDispatcher<CommandSource> dispatcher) {
		LiteralArgumentBuilder<CommandSource> changeBackNumberCommand
		= Commands.literal("bnumber")
			.requires((commandSource) -> commandSource.hasPermission(0))
			.then(Commands.argument("number", IntegerArgumentType.integer())
				.executes(ChangeBackNumberCommand::changeBackNumber));
	
		dispatcher.register(changeBackNumberCommand);
	}
	
	static int changeBackNumber(CommandContext<CommandSource> commandContext) throws CommandSyntaxException {
		ServerPlayerEntity serverPlayer = commandContext.getSource().getPlayerOrException();
		int toNumber = IntegerArgumentType.getInteger(commandContext, "number");
		LazyOptional<IPlayerState> playerState = serverPlayer.getCapability(CapabilityPlayerState.PLAYERSTATE);
		playerState.resolve().get().setBackNumber(toNumber);
		UpdatePlayerStateToLocalPacket packet = new UpdatePlayerStateToLocalPacket(toNumber);
		Networking.sendToClient(packet, serverPlayer);
		return 0;
	}

}
