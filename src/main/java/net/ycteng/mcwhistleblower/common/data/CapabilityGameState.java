package net.ycteng.mcwhistleblower.common.data;

import javax.annotation.Nullable;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityGameState {
	
	@CapabilityInject(IGameState.class)
	public static Capability<IGameState> WORLD_GAMESTATE = null;
	
	public static void register() {
		CapabilityManager.INSTANCE.register(IGameState.class, new Storage(), DefaultGameState::new);
	}
	
	public static class Storage implements Capability.IStorage<IGameState> {

		@Override
		public void readNBT(Capability<IGameState> capability, IGameState instance, Direction side, INBT nbt) {
			int gameState = ((CompoundNBT) nbt).getInt("gameState");
			instance.setGameState(gameState);
			
		}

		@Nullable
		@Override
		public INBT writeNBT(Capability<IGameState> capability, IGameState instance, Direction side) {
			CompoundNBT tag = new CompoundNBT();
			tag.putInt("gameState", instance.getGameState());
			return tag;
		}
	}

}
