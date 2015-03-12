package command;

import facade.IModelFacade;

/**
 * @author Chad
 *
 * This object retrieves all of the commands that have been executed in a given
 * game.
 */
@SuppressWarnings("unused")
public class GetCommands_CO implements ICommandObject {
	
	private IModelFacade modelFacade;
	private int gameId;
	
	/**
	 * @param modelFacade The implementation of IModelFacde to be used.
	 * @param gameId The id of the game whose commands are needed.
	 */
	public GetCommands_CO(IModelFacade modelFacade, int gameId) {
		this.modelFacade = modelFacade;
		this.gameId = gameId;
	}

	@Override
	public Object execute() {
		// TODO Auto-generated method stub
		return null;
	}

}
