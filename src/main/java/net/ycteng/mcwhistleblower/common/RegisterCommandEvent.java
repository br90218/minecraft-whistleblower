package net.ycteng.mcwhistleblower.common;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandSource;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.ycteng.mcwhistleblower.common.commands.ReportCommand;

@Mod.EventBusSubscriber(modid = "mc-whistleblower", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class RegisterCommandEvent 
{
	
}
