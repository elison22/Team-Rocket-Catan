package command;

import facade.GameManager;
import model.sgame.ServerGame;
import shared.dto.DiscardCards_Params;

/**
 * @author Chad
 *
 * Makes all necessary calls for a player to discard cards.
 */
public class DiscardCards_CO implements ICommandObject {
	
	private DiscardCards_Params params;
    transient private ServerGame game;

	/**
	 * @param params Parameters needed for discarding cards.
	 */
	public DiscardCards_CO(DiscardCards_Params params, ServerGame game) {
		super();
		this.params = params;
        this.game = game;
	}
	
	public void setGame(ServerGame game)
	{
		this.game = game;
	}

	@Override
	public boolean execute() {
        return game.doDiscardCards(params.getPlayerIndex(), params.getDiscardedCards());
	}

	@Override
	public void setGameManager(GameManager gameManager) {}

}
