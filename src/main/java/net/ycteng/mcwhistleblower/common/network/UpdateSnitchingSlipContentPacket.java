package net.ycteng.mcwhistleblower.common.network;

import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;

public class UpdateSnitchingSlipContentPacket {

	private final ItemStack updatingStack;
	private final String updatingContent;
	
	public UpdateSnitchingSlipContentPacket(ItemStack updatingStack, String updatingContent) {
		this.updatingStack = updatingStack;
		this.updatingContent = updatingContent;
	}
	
	public static void encode(UpdateSnitchingSlipContentPacket packet, PacketBuffer buffer) {
		buffer.writeItem(packet.updatingStack);
		buffer.writeUtf(packet.updatingContent);
	}
	
	public static UpdateSnitchingSlipContentPacket decode(PacketBuffer buffer) {
		ItemStack updatingStack = buffer.readItem();
		String updatingContent = buffer.readUtf(Short.MAX_VALUE);
		return new UpdateSnitchingSlipContentPacket(updatingStack, updatingContent);
	}
	
	public ItemStack getUpdatingItemStack() {
		return updatingStack;
	}
	
	public String getUpdatingContent() {
		return updatingContent;
	}
}
