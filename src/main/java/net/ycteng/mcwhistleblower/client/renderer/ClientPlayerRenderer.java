package net.ycteng.mcwhistleblower.client.renderer;

import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraftforge.client.event.RenderNameplateEvent;
import net.ycteng.mcwhistleblower.McWhistleblower;

public class ClientPlayerRenderer {
	public static void render(RenderNameplateEvent event) {
		Entity entity = event.getEntity();
		if(entity instanceof ClientPlayerEntity) {
			McWhistleblower.LOGGER.debug(entity.getScoreboardName());
		}
	}
}
