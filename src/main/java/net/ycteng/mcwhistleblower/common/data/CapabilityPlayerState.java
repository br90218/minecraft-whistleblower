package net.ycteng.mcwhistleblower.common.data;

import javax.annotation.Nullable;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.ycteng.mcwhistleblower.McWhistleblower;

public class CapabilityPlayerState {

	public static Capability<IPlayerState> PLAYERSTATE = null;
	
	@CapabilityInject(IPlayerState.class)
	private static void onPlayerStateInit(Capability<IPlayerState> playerState) {
		McWhistleblower.LOGGER.info("This player has no player states, adding one.");
		PLAYERSTATE = playerState;
	}
	
	public static void register() {
		CapabilityManager.INSTANCE.register(IPlayerState.class, new Storage(), PlayerState::new);
	}
	
	public static class Storage implements Capability.IStorage<IPlayerState> {
		
		@Nullable
		@Override
		public INBT writeNBT(Capability<IPlayerState> capability, IPlayerState instance, Direction side) {
			CompoundNBT nbt = new CompoundNBT();
			//TODO: change this to an enum. don't use string
			nbt.putInt("number", instance.getBackNumber());
			return nbt;
		}

		@Override
		public void readNBT(Capability<IPlayerState> capability, IPlayerState instance, Direction side, INBT nbt) {
			if(!(nbt instanceof CompoundNBT)) {
				throw new IllegalArgumentException("CapabilityPlayerState received a non-CompoundNBT structure.");
			}
			instance.setBackNumber(((CompoundNBT) nbt).getInt("number"));
		}
	}
}
