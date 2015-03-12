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
	
	private IModelFacade modelFacade;
	private int gameId;
	
	/**
	 * @param modelFacade The implementation of IModelFacde to be used.
	 * @param gameId The id of the game to be reset. This will be sent as a 
	 * query parameter from the client.
	 */
	public ResetGame_CO(IModelFacade modelFacade, int gameId) {
		this.modelFacade = modelFacade;
		this.gameId = gameId;
	}

	@Override
	public Object execute() {
		// TODO Auto-generated method stub
		return null;
	}

}
