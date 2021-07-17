package net.ycteng.mcwhistleblower.common.network;

import java.util.function.Supplier;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.NetworkEvent;
import net.ycteng.mcwhistleblower.client.data.CapabilityLocalPlayerState;
import net.ycteng.mcwhistleblower.client.data.ILocalPlayerState;

public class UpdatePlayerStateToLocalPacket {
	
	private final int newBackNumber;
	
	public UpdatePlayerStateToLocalPacket(int backNumber) {
		this.newBackNumber = backNumber;
	}
	
	public static void encode(UpdatePlayerStateToLocalPacket packet, PacketBuffer buffer) {
		buffer.writeInt(packet.newBackNumber);
	}
	
	public static UpdatePlayerStateToLocalPacket decode(PacketBuffer buffer) {
		int readNumber = buffer.readInt();
		return new UpdatePlayerStateToLocalPacket(readNumber);
	}
	
	public boolean handle(Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			if(context.getDirection().getOriginationSide().isServer() && context.getDirection().getReceptionSide().isClient()) {
				ClientPlayerEntity clientPlayer = Minecraft.getInstance().player;
				LazyOptional<ILocalPlayerState> localPlayerState = clientPlayer.getCapability(CapabilityLocalPlayerState.LOCALPLAYERSTATE);
				localPlayerState.resolve().get().setBackNumber(newBackNumber);		
			}
		});
		return true;
	}
}
