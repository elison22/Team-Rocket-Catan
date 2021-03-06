package command;

import facade.GameManager;
import model.sgame.ServerGame;
import shared.dto.FinishTurn_Params;

/**
 * @author Chad
 *
 * Tells the model to end the given player's turn.
 */
public class FinishTurn_CO implements ICommandObject {
	
	private String type;
	private FinishTurn_Params finishTurnParams;
    transient private ServerGame game;

	/**
	 * @param params Parameters needed to finish a player's turn.
	 */
	public FinishTurn_CO(FinishTurn_Params params, ServerGame game) {
		type = "FinishTurn";
		this.finishTurnParams = params;
        this.game = game;
	}
	
	public void setGame(ServerGame game)
	{
		this.game = game;
	}

	@Override
	public boolean execute() {
        return game.finishTurn(finishTurnParams.getPlayerIndex());
	}

	@Override
	public void setGameManager(GameManager gameManager) {}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
