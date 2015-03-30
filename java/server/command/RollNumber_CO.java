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
	
	private String type;
	private RollNumber_Params rollNumberParams;
    transient private ServerGame game;

	/**
	 * @param params needed for rolling a number.
	 */
	public RollNumber_CO(RollNumber_Params params, ServerGame game) {
		type = "RollNumber";
		this.rollNumberParams = params;
        this.game = game;
	}
	
	public void setGame(ServerGame game)
	{
		this.game = game;
	}

	@Override
	public boolean execute() {
		if (rollNumberParams.getNumber() >= 2 && rollNumberParams.getNumber() <= 12)
			return game.doRoll(rollNumberParams.getPlayerIndex(), rollNumberParams.getNumber());
		return false;
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
