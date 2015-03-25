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
	
	private ServerGame game;
	private Monument_Params params;

	/**
	 * @param game The id of the game where the card is to be played.
	 * @param params Parameters needed to play the monument card.
	 */
	public Monument_CO(ServerGame game, Monument_Params params) {
		super();
		this.game = game;
		this.params = params;
	}

	@Override
	public boolean execute() {
		return game.doMonument(params.getPlayerIndex());
	}

}
