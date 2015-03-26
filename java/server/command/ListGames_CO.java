package command;

import model.sgame.ServerGame;
import facade.GameManager;
import facade.IModelFacade;

/**
 * @author Chad
 *
 * This class retrieves a current list of all the games stored in the server,
 * including the players in each game and their colors.
 */
@SuppressWarnings("unused")
public class ListGames_CO implements ICommandObject {
	
	GameManager gameManager;

	public ListGames_CO(GameManager gameManager) {
		super();
		this.gameManager = gameManager;
	}

	@Override
	public boolean execute() {
		return false;
	}

	@Override
	public void setGame(ServerGame game) {
		// TODO Auto-generated method stub
		
	}
}
