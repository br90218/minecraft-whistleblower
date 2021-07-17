package net.ycteng.mcwhistleblower.common.data;

import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

public class PlayerStateProvider implements ICapabilityProvider {
	private final PlayerState playerState = new PlayerState();
	private final LazyOptional<IPlayerState> playerStateOptional = LazyOptional.of(() -> this.playerState);
	
	
	public void invalidate() {
		playerStateOptional.invalidate();
	}


	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		return playerStateOptional.cast();
	}
}
