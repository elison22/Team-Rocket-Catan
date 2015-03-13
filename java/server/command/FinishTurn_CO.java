package command;

import facade.IModelFacade;

/**
 * @author Chad
 *
 * Tells the facade to end the given player's turn.
 */
@SuppressWarnings("unused")
public class FinishTurn_CO implements ICommandObject {
	
	private IModelFacade modelFacade;
	private int gameId;

	/**
	 * @param modelFacade The implementation of IModelFacde to be used.
	 * @param gameId Id of the game where the turn is ending.
	 */
	public FinishTurn_CO(IModelFacade modelFacade, int gameId) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object execute() {
		// TODO Auto-generated method stub
		return null;
	}

}
