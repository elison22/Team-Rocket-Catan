package command;

import facade.GameManager;
import model.sgame.ServerGame;
import shared.dto.RollNumber_Params;
import facade.IModelFacade;

/**
 * @author Chad
 *
 * Does everything needed for a player to roll a given dice number.
 */
@SuppressWarnings("unused")
public class RollNumber_CO implements ICommandObject {
	
	private int gameId;
	private RollNumber_Params params;
    private ServerGame game;

	/**
	 * @param gameId Id of the game where the dice roll is happening.
	 * @param params needed for rolling a number.
	 */
	public RollNumber_CO(int gameId, RollNumber_Params params, ServerGame game) {
		this.gameId = gameId;
		this.params = params;
        this.game = game;
	}

	@Override
	public boolean execute() {
        game.doRoll(params.getPlayerIndex(), params.getNumber());
		return true;
	}

}
