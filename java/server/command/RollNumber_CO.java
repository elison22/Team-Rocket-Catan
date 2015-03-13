package command;

import facade.IModelFacade;

/**
 * @author Chad
 *
 * Does everything needed for a player to roll a given dice number.
 */
@SuppressWarnings("unused")
public class RollNumber_CO implements ICommandObject {
	
	private IModelFacade modelFacade;
	private int gameId;

	/**
	 * @param modelFacade The implementation of IModelFacde to be used.
	 * @param gameId Id of the game where the dice roll is happening.
	 */
	public RollNumber_CO(IModelFacade modelFacade, int gameId) {
		this.modelFacade = modelFacade;
		this.gameId = gameId;
	}

	@Override
	public Object execute() {
		// TODO Auto-generated method stub
		return null;
	}

}
