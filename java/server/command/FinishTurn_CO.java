package command;

import shared.dto.FinishTurn_Params;
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
	private FinishTurn_Params params;

	/**
	 * @param modelFacade The implementation of IModelFacde to be used.
	 * @param gameId Id of the game where the turn is ending.
	 * @param params Parameters needed to finish a player's turn.
	 */
	public FinishTurn_CO(IModelFacade modelFacade, int gameId, FinishTurn_Params params) {
		this.modelFacade = modelFacade;
		this.gameId = gameId;
		this.params = params;
	}

	@Override
	public Object execute() {
		// TODO Auto-generated method stub
		return null;
	}

}
