package net.ycteng.mcwhistleblower.common;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.command.CommandSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.ycteng.mcwhistleblower.McWhistleblower;
import net.ycteng.mcwhistleblower.common.commands.ChangeBackNumberCommand;
import net.ycteng.mcwhistleblower.common.commands.ChangeGameStateCommand;
import net.ycteng.mcwhistleblower.common.commands.ReportCommand;
import net.ycteng.mcwhistleblower.common.data.CapabilityGameState;
import net.ycteng.mcwhistleblower.common.data.CapabilityPlayerState;
import net.ycteng.mcwhistleblower.common.data.GameStateHandler;
import net.ycteng.mcwhistleblower.common.data.PlayerStateHandler;
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
		CapabilityGameState.register();
		CapabilityPlayerState.register();
		
		MinecraftForge.EVENT_BUS.addGenericListener(World.class, GameStateHandler::onAttachCapabilitiesEvent);
		MinecraftForge.EVENT_BUS.addGenericListener(Entity.class, PlayerStateHandler::onAttachCapabilitiesEvent);
	}

	@SubscribeEvent
	public static void onRegisterCommandEvent(RegisterCommandsEvent event) {
		CommandDispatcher<CommandSource> commandDispatcher = event.getDispatcher();
		ReportCommand.register(commandDispatcher);
		ChangeGameStateCommand.register(commandDispatcher);
		ChangeBackNumberCommand.register(commandDispatcher);
	}
}
