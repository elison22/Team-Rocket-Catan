package command;

import facade.GameManager;
import model.sgame.ServerGame;
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
    private GameManager gameManager;

	/**
	 * @param gameId Id of the game where the turn is ending.
	 * @param params Parameters needed to finish a player's turn.
	 */
	public FinishTurn_CO(int gameId, FinishTurn_Params params, GameManager gameManager) {
		this.gameId = gameId;
		this.params = params;
        this.gameManager = gameManager;
	}

	@Override
	public boolean execute() {
        ServerGame game = gameManager.getGame(gameId);
        game.finishTurn(params.getPlayerIndex());
		return true;
	}

}
