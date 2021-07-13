package net.ycteng.mcwhistleblower.common.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.MessageArgument;
import net.minecraft.entity.Entity;
import net.minecraft.util.Util;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class ReportCommand 
{
	public static void register(CommandDispatcher<CommandSource> dispatcher)
	{
		LiteralArgumentBuilder<CommandSource> reportCommand
        = Commands.literal("report")
             .requires((commandSource) -> commandSource.hasPermission(0))
             .then(Commands.argument("message", MessageArgument.message())
                     .executes(ReportCommand::sendMessage)
                  );
		
		dispatcher.register(reportCommand);
	}
	
	static int sendMessage(CommandContext<CommandSource> commandContext) throws CommandSyntaxException
	{
		ITextComponent messageValue = MessageArgument.getMessage(commandContext, "message");
		TranslationTextComponent finalText = new TranslationTextComponent("chat.type.announcement",
				commandContext.getSource().getDisplayName(), "ooooooo");
		Entity entity = commandContext.getSource().getEntity();
		if (entity != null) {
		      commandContext.getSource().getServer().getPlayerList().broadcastMessage(finalText, ChatType.SYSTEM, entity.getUUID());
		    } else {
		      commandContext.getSource().getServer().getPlayerList().broadcastMessage(finalText, ChatType.SYSTEM, Util.NIL_UUID);
		    }
		
		return 1;
	}
}
