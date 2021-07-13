package net.ycteng.mcwhistleblower.common;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.command.CommandSource;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.ycteng.mcwhistleblower.McWhistleblower;
import net.ycteng.mcwhistleblower.common.commands.ReportCommand;
import net.ycteng.mcwhistleblower.common.network.Networking;

@Mod.EventBusSubscriber(modid = McWhistleblower.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class StartupCommon 
{
	public static final ItemGroup ITEM_GROUP = new ItemGroup("mcwhistleblower") 
	{
		@Override
		public ItemStack makeIcon()
		{
			return new ItemStack(Registration.SNITCHINGMACHINEBLOCK.get());
		}
	};
	
	
	@SubscribeEvent
	public static void init(final FMLCommonSetupEvent event)
	{
		Networking.registerMessages();
	}

	@SubscribeEvent
	public static void onRegisterCommandEvent(RegisterCommandsEvent event) {
		CommandDispatcher<CommandSource> commandDispatcher = event.getDispatcher();
		ReportCommand.register(commandDispatcher);
	}
}
