package command;

import model.sgame.ServerGame;
import facade.IModelFacade;

/**
 * @author Chad
 *
 * This object retrieves all of the commands that have been executed in a given
 * game.
 */
@SuppressWarnings("unused")
public class GetCommands_CO implements ICommandObject {
	
	private int gameId;
	
	/**
	 * @param modelFacade The implementation of IModelFacde to be used.
	 * @param gameId The id of the game whose commands are needed.
	 */
	public GetCommands_CO(int gameId) {
		this.gameId = gameId;
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
