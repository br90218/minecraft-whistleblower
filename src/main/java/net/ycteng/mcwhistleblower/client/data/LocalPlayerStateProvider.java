package net.ycteng.mcwhistleblower.client.data;

import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

public class LocalPlayerStateProvider implements ICapabilityProvider {

	private final LocalPlayerState localPlayerState = new LocalPlayerState();
	private final LazyOptional<ILocalPlayerState> localPlayerStateOptional = LazyOptional.of(() -> this.localPlayerState);
	
	public void invalidate() {
		localPlayerStateOptional.invalidate();
	}
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		return localPlayerStateOptional.cast();
	}

}
