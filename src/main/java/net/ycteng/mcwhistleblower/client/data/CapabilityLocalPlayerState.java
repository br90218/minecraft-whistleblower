package net.ycteng.mcwhistleblower.client.data;

import javax.annotation.Nullable;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityLocalPlayerState {

	public static Capability<ILocalPlayerState> LOCALPLAYERSTATE = null;
	
	@CapabilityInject(ILocalPlayerState.class)
	private static void onLocalPlayerStateInit(Capability<ILocalPlayerState> localPlayerState) {
		LOCALPLAYERSTATE = localPlayerState;
	}
	
	public static void register() {
		CapabilityManager.INSTANCE.register(ILocalPlayerState.class, new Storage(), LocalPlayerState::new);
	}
	
	public static class Storage implements Capability.IStorage<ILocalPlayerState> {

		@Nullable
		@Override
		public INBT writeNBT(Capability<ILocalPlayerState> capability, ILocalPlayerState instance, Direction side) {
			CompoundNBT nbt = new CompoundNBT();
			nbt.putInt("number", instance.getBackNumber());
			return nbt;
		}

		@Override
		public void readNBT(Capability<ILocalPlayerState> capability, ILocalPlayerState instance, Direction side,
				INBT nbt) {
			if(!(nbt instanceof CompoundNBT)) {
				throw new IllegalArgumentException("CapabilityPlayerState received a non-CompoundNBT structure.");
			}
			instance.setBackNumber(((CompoundNBT) nbt).getInt("number"));
			
		}
	}
}
