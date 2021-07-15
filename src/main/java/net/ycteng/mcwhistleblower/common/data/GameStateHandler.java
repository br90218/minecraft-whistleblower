package net.ycteng.mcwhistleblower.common.data;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.ycteng.mcwhistleblower.McWhistleblower;

public class GameStateHandler {
	
	public static void onAttachCapabilitiesEvent(AttachCapabilitiesEvent<World> event) {
		if(event.getObject() instanceof World) {
			GameStateProvider provider = new GameStateProvider();
			event.addCapability(new ResourceLocation(McWhistleblower.MODID, "gamestate"), provider);
			event.addListener(provider::invalidate);
		}
	}
}
