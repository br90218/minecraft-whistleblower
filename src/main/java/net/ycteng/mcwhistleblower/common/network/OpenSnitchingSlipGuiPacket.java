package net.ycteng.mcwhistleblower.common.network;

import java.util.function.Supplier;

import net.minecraftforge.fml.network.NetworkEvent;
import net.ycteng.mcwhistleblower.common.gui.SnitchingSlipScreen;

public class OpenSnitchingSlipGuiPacket {
	
	public boolean handle(Supplier<NetworkEvent.Context> context) {
		context.get().enqueueWork(SnitchingSlipScreen::open);
		return true;
	}
}
