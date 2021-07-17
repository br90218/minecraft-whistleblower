package net.ycteng.mcwhistleblower.client.data;

import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.ycteng.mcwhistleblower.McWhistleblower;

public class LocalPlayerStateHandler {
	
	public static void onAttachCapabilitiesEvent(AttachCapabilitiesEvent<Entity> event) {
		if(event.getObject() instanceof ClientPlayerEntity) {
			McWhistleblower.LOGGER.debug("==============CLIENT ADD CAPA");
			LocalPlayerStateProvider localPlayerStateProvider = new LocalPlayerStateProvider();
			event.addCapability(new ResourceLocation(McWhistleblower.MODID, "localplayerstate"), localPlayerStateProvider);
			event.addListener(localPlayerStateProvider::invalidate);
		}
	}
}
