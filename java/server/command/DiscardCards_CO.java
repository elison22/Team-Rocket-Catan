package command;

import facade.GameManager;
import main.Server;
import model.sgame.ServerGame;
import shared.dto.DiscardCards_Params;
import facade.IModelFacade;

/**
 * @author Chad
 *
 * Makes all necessary calls for a player to discard cards.
 */
@SuppressWarnings("unused")
public class DiscardCards_CO implements ICommandObject {
	
	private int gameId;
	private DiscardCards_Params params;
    private ServerGame game;

	/**
	 * @param gameId Id of the game where the payer must discard cards;
	 * @param params Parameters needed for discarding cards.
	 */
	public DiscardCards_CO(int gameId,
			DiscardCards_Params params, ServerGame game) {
		super();
		this.gameId = gameId;
		this.params = params;
        this.game = game;
	}

	@Override
	public boolean execute() {
        game.doDiscardCards(params.getPlayerIndex(), params.getDiscardedCards());
		return true;
	}

}
