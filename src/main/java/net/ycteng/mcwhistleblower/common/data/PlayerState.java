package net.ycteng.mcwhistleblower.common.data;

import java.util.Random;

public class PlayerState implements IPlayerState {
	
	private int backNumber;

	@Override
	public int getBackNumber() {
		return backNumber;
	}

	@Override
	public void setBackNumber() {
		Random rand = new Random();
		backNumber = rand.nextInt(3);
	}

	@Override
	public void setBackNumber(int number) {
		backNumber = number;
	}
}
