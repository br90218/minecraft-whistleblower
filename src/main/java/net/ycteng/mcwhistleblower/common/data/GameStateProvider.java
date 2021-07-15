package net.ycteng.mcwhistleblower.common.data;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class GameStateProvider implements ICapabilitySerializable<CompoundNBT> {

	private final DefaultGameState gameState = new DefaultGameState();
	private final LazyOptional<IGameState> gameStateOptional = LazyOptional.of(() -> gameState);
	
	public void invalidate() {
		gameStateOptional.invalidate();
	}
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		return gameStateOptional.cast();
	}

	@Override
	public CompoundNBT serializeNBT() {
		if (CapabilityGameState.WORLD_GAMESTATE == null) {
			return new CompoundNBT();
		}
		else {
			return (CompoundNBT) CapabilityGameState.WORLD_GAMESTATE.writeNBT(gameState, null);
		}
	}

	@Override
	public void deserializeNBT(CompoundNBT nbt) {
		if(CapabilityGameState.WORLD_GAMESTATE != null) {
			CapabilityGameState.WORLD_GAMESTATE.readNBT(gameState, null, nbt);
		}
	}

}
