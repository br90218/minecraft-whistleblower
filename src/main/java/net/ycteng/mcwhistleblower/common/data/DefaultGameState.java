package net.ycteng.mcwhistleblower.common.data;

public class DefaultGameState implements IGameState {

	//TODO: maybe use enum?
	public static final int STATE_LOBBY = 0;
	public static final int STATE_GAME = 1;
	public static final int STATE_RESULTS = 2;
	
	private final int totalStates = STATE_RESULTS + 1;
	private int currentState;
	
	@Override
	public int getGameState() {
		return currentState;
	}

	@Override
	public void setGameState(int newState) {
		currentState = newState;
	}

	@Override
	public void toNextGameState() {
		currentState = (currentState + 1) % totalStates;
	}

	@Override
	public void runGameStateLogic() {
		// TODO Auto-generated method stub
	}

}
