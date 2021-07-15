package net.ycteng.mcwhistleblower.common.network;

import java.util.function.Supplier;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;
import net.ycteng.mcwhistleblower.McWhistleblower;

public class PacketHandlerOnServer {
	public static void onPacketReceived(UpdateSnitchingSlipContentPacket packet, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		LogicalSide sideReceived = context.getDirection().getReceptionSide();
		if(sideReceived != LogicalSide.SERVER) {
			McWhistleblower.LOGGER.warn("update snitching skip content packet received on wrong end");
			return;
		}
		context.enqueueWork(() -> updateSnitchingSlip(packet, context.getSender()));
	}
	
	static void updateSnitchingSlip(UpdateSnitchingSlipContentPacket packet, ServerPlayerEntity serverPlayerEntity) {
		ItemStack stack = serverPlayerEntity.getMainHandItem();
		String content = packet.getUpdatingContent();
		
		CompoundNBT nbt = stack.getTag();
		nbt.putString("content", content);
		stack.setTag(nbt);
	}
}
