package net.ycteng.mcwhistleblower.common.data;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.ycteng.mcwhistleblower.McWhistleblower;

public class PlayerStateHandler {
	
	public static void onAttachCapabilitiesEvent(AttachCapabilitiesEvent<Entity> event) {
		if(event.getObject() instanceof ServerPlayerEntity) {
			PlayerStateProvider playerStateProvider = new PlayerStateProvider();
			event.addCapability(new ResourceLocation(McWhistleblower.MODID, "playerstate"), playerStateProvider);
			event.addListener(playerStateProvider::invalidate);
		}
	}
}
