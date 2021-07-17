package net.ycteng.mcwhistleblower.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.ycteng.mcwhistleblower.McWhistleblower;

@Mod.EventBusSubscriber(modid = McWhistleblower.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class StartupClient 
{
	@SubscribeEvent
	public static void init(final FMLClientSetupEvent event)
	{
		
	}
}
