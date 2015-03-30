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
	
	private String type;
	private DiscardCards_Params discardCardsParams;
    transient private ServerGame game;

	/**
	 * @param params Parameters needed for discarding cards.
	 */
	public DiscardCards_CO(DiscardCards_Params params, ServerGame game) {
		super();
		this.type = "DiscardCards";
		this.discardCardsParams = params;
        this.game = game;
	}
	
	public void setGame(ServerGame game)
	{
		this.game = game;
	}

	@Override
	public boolean execute() {
        return game.doDiscardCards(discardCardsParams.getPlayerIndex(), discardCardsParams.getDiscardedCards());
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
