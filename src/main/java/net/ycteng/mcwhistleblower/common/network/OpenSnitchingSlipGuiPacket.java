package net.ycteng.mcwhistleblower.common.network;

import java.util.function.Supplier;

import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import net.ycteng.mcwhistleblower.common.gui.SnitchingSlipScreen;

public class OpenSnitchingSlipGuiPacket {
	
	private final String existingContent;
	private final ItemStack stack;
	
	public OpenSnitchingSlipGuiPacket(String existingContent, ItemStack stack) {
		this.existingContent = existingContent;
		this.stack = stack;
	}
	
	public static void encode(OpenSnitchingSlipGuiPacket packet, PacketBuffer buf) {
		buf.writeUtf(packet.existingContent);
		buf.writeItem(packet.stack);
	}
	
	public static OpenSnitchingSlipGuiPacket decode(PacketBuffer buf) {
		String content = buf.readUtf(Short.MAX_VALUE);
		ItemStack stack = buf.readItem();
		return new OpenSnitchingSlipGuiPacket(content, stack);
	}
	
	public boolean handle(Supplier<NetworkEvent.Context> context) {
		context.get().enqueueWork(() -> SnitchingSlipScreen.open(existingContent, stack));
		return true;
	}
}
