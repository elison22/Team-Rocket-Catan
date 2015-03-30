package command;

import model.sgame.ServerGame;
import facade.GameManager;

/**
 * @author Chad
 *
 * This class makes all the necessary calls to reset a joined game to the
 * initial placement phase.
 */
public class ResetGame_CO implements ICommandObject {
	
	transient private ServerGame game;
	
	/**
	 * @param gameId The id of the game to be reset. This will be sent as a 
	 * query parameter from the client.
	 */
	public ResetGame_CO(int gameId, GameManager manager) {
		this.game = manager.getGame(gameId);
	}

	@Override
	public boolean execute() {
		return game.resetGame();
	}

	@Override
	public void setGame(ServerGame game) {}

	@Override
	public void setGameManager(GameManager gameManager) {}

}
