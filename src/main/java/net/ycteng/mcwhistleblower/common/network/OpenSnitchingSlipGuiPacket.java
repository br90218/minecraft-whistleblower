package net.ycteng.mcwhistleblower.common.network;

import java.util.function.Supplier;

import net.minecraftforge.fml.network.NetworkEvent;
import net.ycteng.mcwhistleblower.common.gui.SnitchingSlipScreen;
import net.ycteng.mcwhistleblower.common.items.SnitchingSlipEntity;

public class OpenSnitchingSlipGuiPacket {
	
	private SnitchingSlipEntity entity;
	
	public OpenSnitchingSlipGuiPacket(SnitchingSlipEntity entity) {
		this.entity = entity;
	}
	
	
	public boolean handle(Supplier<NetworkEvent.Context> context) {
		context.get().enqueueWork(SnitchingSlipScreen::open, entity);
		return true;
	}
}
