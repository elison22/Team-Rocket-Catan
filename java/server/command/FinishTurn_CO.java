package command;

import facade.GameManager;
import main.Server;
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
	
	private FinishTurn_Params params;
    private ServerGame game;

	/**
	 * @param params Parameters needed to finish a player's turn.
	 */
	public FinishTurn_CO(FinishTurn_Params params, ServerGame game) {
		this.params = params;
        this.game = game;
	}

	@Override
	public boolean execute() {
        return game.finishTurn(params.getPlayerIndex());
	}

}
