package net.ycteng.mcwhistleblower.common.data;

public interface IGameState {
	int getGameState();
	void setGameState(int newState);
	void toNextGameState();
	void runGameStateLogic();
}
