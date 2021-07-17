package net.ycteng.mcwhistleblower.client.data;

public class LocalPlayerState implements ILocalPlayerState {
	private int backNumber;

	public LocalPlayerState() {
		backNumber = 1;
	}
	
	@Override
	public int getBackNumber() {
		return backNumber;
	}

	@Override
	public void setBackNumber(int number) {
		backNumber = number;
	}
	
	
}
