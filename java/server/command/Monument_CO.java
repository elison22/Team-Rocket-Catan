package command;

import model.sgame.ServerGame;
import shared.dto.Monument_Params;

/**
 * @author Chad
 *
 * Makes all necessary calls for a player to play the monument dev card.
 */
@SuppressWarnings("unused")
public class Monument_CO implements ICommandObject {
	
	private Monument_Params params;
    private ServerGame game;

	/**
	 * @param params Parameters needed to play the monument card.
	 */
	public Monument_CO(ServerGame game, Monument_Params params) {
		super();
		this.params = params;
        this.game = game;
	}

	@Override
	public boolean execute() {
		return game.doMonument(params.getPlayerIndex());
	}

}
