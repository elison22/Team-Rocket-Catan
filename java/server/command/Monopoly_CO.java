package command;

import model.sgame.ServerGame;
import shared.definitions.ResourceType;
import shared.dto.Monopoly_Params;

/**
 * @author Chad
 *
 * Makes all necessary calls for a player to play the monopoly card.
 */
@SuppressWarnings("unused")
public class Monopoly_CO implements ICommandObject {
	
	private ServerGame game;
	private Monopoly_Params params;

	/**
	 * @param game Id of the game where the monopoly card is to be played.
	 * @param params Parameters needed for a player to play the monopoly card.
	 */
	public Monopoly_CO(ServerGame game,
			Monopoly_Params params) {
		super();
		this.game = game;
		this.params = params;
	}

	@Override
	public boolean execute() {
		return game.doMonopoly(
                params.getPlayerIndex(),
                ResourceType.convert(params.getResource())
        );
	}

}
