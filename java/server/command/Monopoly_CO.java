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
	
	private Monopoly_Params params;
    private ServerGame game;

	/**
	 * @param params Parameters needed for a player to play the monopoly card.
	 */
	public Monopoly_CO(Monopoly_Params params, ServerGame game) {
		super();
		this.params = params;
        this.game = game;
	}

	@Override
	public boolean execute() {
		return game.doMonopoly(
                params.getPlayerIndex(),
                ResourceType.convert(params.getResource())
        );
	}

}
