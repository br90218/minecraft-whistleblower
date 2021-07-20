package net.ycteng.mcwhistleblower.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.ycteng.mcwhistleblower.McWhistleblower;
import net.ycteng.mcwhistleblower.client.renderer.ClientPlayerRenderer;
import net.ycteng.mcwhistleblower.common.Registration;
import net.ycteng.mcwhistleblower.common.creatures.AlertmanRenderer;

@Mod.EventBusSubscriber(modid = McWhistleblower.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class StartupClient 
{
	@SubscribeEvent
	public static void init(final FMLClientSetupEvent event)
	{
		RenderingRegistry.registerEntityRenderingHandler(Registration.ALERTMAN.get(), AlertmanRenderer::new);
		MinecraftForge.EVENT_BUS.addListener(ClientPlayerRenderer::render);
	}
}
