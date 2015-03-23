package command;

import model.sgame.ServerGame;
import facade.GameManager;
import facade.IModelFacade;

/**
 * @author Chad
 *
 * This class makes all the necessary calls to reset a joined game to the
 * initial placement phase.
 */
@SuppressWarnings("unused")
public class ResetGame_CO implements ICommandObject {
	
	private int gameId;
	private GameManager manager;
	
	/**
	 * @param gameId The id of the game to be reset. This will be sent as a 
	 * query parameter from the client.
	 */
	public ResetGame_CO(int gameId, GameManager manager) {
		this.gameId = gameId;
		this.manager = manager;
	}

	@Override
	public boolean execute() {
		ServerGame game = manager.getGame(gameId);
		if(game.resetGame())
			return true;
		else return false;
	}

}
