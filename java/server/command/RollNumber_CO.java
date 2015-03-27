package command;

import facade.GameManager;
import model.sgame.ServerGame;
import shared.dto.RollNumber_Params;

/**
 * @author Chad
 *
 * Does everything needed for a player to roll a given dice number.
 */

public class RollNumber_CO implements ICommandObject {
	
	private RollNumber_Params params;
    transient private ServerGame game;

	/**
	 * @param params needed for rolling a number.
	 */
	public RollNumber_CO(RollNumber_Params params, ServerGame game) {
		this.params = params;
        this.game = game;
	}
	
	public void setGame(ServerGame game)
	{
		this.game = game;
	}

	@Override
	public boolean execute() {
        return game.doRoll(params.getPlayerIndex(), params.getNumber());
	}

	@Override
	public void setGameManager(GameManager gameManager) {}
}
