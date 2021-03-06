package net.ycteng.mcwhistleblower.common.network;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import net.ycteng.mcwhistleblower.McWhistleblower;

public class Networking {

	private static SimpleChannel INSTANCE;
	private static int ID = 0;
	
	private static int nextId() {
		return ID++;
	}
	
	public static void registerMessages() {
		INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(McWhistleblower.MODID, "mcwhistleblower"),
				() -> "1.0",
				s -> true,
				s -> true);
		
		INSTANCE.messageBuilder(UpdateSnitchingSlipContentPacket.class, nextId())
			.encoder((updateSnitchingSlipContentPacket, packetBuffer) -> UpdateSnitchingSlipContentPacket.encode(updateSnitchingSlipContentPacket, packetBuffer))
			.decoder(buf -> UpdateSnitchingSlipContentPacket.decode(buf))
			.consumer(PacketHandlerOnServer::onPacketReceived)
			.add();
		
		INSTANCE.messageBuilder(OpenSnitchingSlipGuiPacket.class, nextId())
			.encoder((openSnitchSlipGuiPacket, packetBuffer) -> OpenSnitchingSlipGuiPacket.encode(openSnitchSlipGuiPacket, packetBuffer))
			.decoder(buf -> OpenSnitchingSlipGuiPacket.decode(buf))
			.consumer(OpenSnitchingSlipGuiPacket::handle)
			.add();
		
		INSTANCE.messageBuilder(UpdatePlayerStateToLocalPacket.class, nextId())
			.encoder((updatePlayerStateToLocalPacket, packetBuffer) -> UpdatePlayerStateToLocalPacket.encode(updatePlayerStateToLocalPacket, packetBuffer))
			.decoder(buf -> UpdatePlayerStateToLocalPacket.decode(buf))
			.consumer(UpdatePlayerStateToLocalPacket::handle)
			.add();
	}
	
	public static void sendToClient(Object packet, ServerPlayerEntity player) {
		INSTANCE.sendTo(packet, player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);;
	}
	
	public static void sendToServer(Object packet) {
		INSTANCE.sendToServer(packet);
	}
	
}
