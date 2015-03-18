package command;

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
	
	/**
	 * @param gameId The id of the game to be reset. This will be sent as a 
	 * query parameter from the client.
	 */
	public ResetGame_CO(int gameId) {
		this.gameId = gameId;
	}

	@Override
	public boolean execute() {
		return false;
	}

}
