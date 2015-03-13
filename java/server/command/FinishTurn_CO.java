package command;

import shared.dto.FinishTurn_Params;
import facade.IModelFacade;

/**
 * @author Chad
 *
 * Tells the model to end the given player's turn.
 */
@SuppressWarnings("unused")
public class FinishTurn_CO implements ICommandObject {
	
	private int gameId;
	private FinishTurn_Params params;

	/**
	 * @param gameId Id of the game where the turn is ending.
	 * @param params Parameters needed to finish a player's turn.
	 */
	public FinishTurn_CO(int gameId, FinishTurn_Params params) {
		this.gameId = gameId;
		this.params = params;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
	}

}
