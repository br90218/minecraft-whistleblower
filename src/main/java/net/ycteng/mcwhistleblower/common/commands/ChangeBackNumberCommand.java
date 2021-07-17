package net.ycteng.mcwhistleblower.common.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.ycteng.mcwhistleblower.McWhistleblower;

public class ChangeBackNumberCommand {
	public static void register(CommandDispatcher<CommandSource> dispatcher) {
		LiteralArgumentBuilder<CommandSource> changeBackNumberCommand
		= Commands.literal("bnumber")
			.requires((commandSource) -> commandSource.hasPermission(0)).executes(ChangeBackNumberCommand::changeBackNumber);
	
		dispatcher.register(changeBackNumberCommand);
	}
	
	static int changeBackNumber(CommandContext<CommandSource> commandContext) throws CommandSyntaxException {
		ServerPlayerEntity player = commandContext.getSource().getPlayerOrException();
		CompoundNBT cnbt = player.getPersistentData();
		int number = (cnbt.getInt("number") + 1) % 3;
		cnbt.putInt("number", number);
		McWhistleblower.LOGGER.debug(cnbt.getInt("number"));
		
		return 0;
	}

}
