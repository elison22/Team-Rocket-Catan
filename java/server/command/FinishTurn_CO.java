package command;

import model.sgame.ServerGame;
import shared.dto.FinishTurn_Params;

/**
 * @author Chad
 *
 * Tells the model to end the given player's turn.
 */
public class FinishTurn_CO implements ICommandObject {
	
	private FinishTurn_Params params;
    transient private ServerGame game;

	/**
	 * @param params Parameters needed to finish a player's turn.
	 */
	public FinishTurn_CO(FinishTurn_Params params, ServerGame game) {
		this.params = params;
        this.game = game;
	}
	
	public void setGame(ServerGame game)
	{
		this.game = game;
	}

	@Override
	public boolean execute() {
        return game.finishTurn(params.getPlayerIndex());
	}

}
