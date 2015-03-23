package command;

import facade.GameManager;
import model.sgame.ServerGame;
import shared.dto.OfferTrade_Params;
import facade.IModelFacade;

/**
 * @author Chad
 *
 * Makes all necessary calls for a player to offer a trade.
 */
@SuppressWarnings("unused")
public class OfferTrade_CO implements ICommandObject {
	
	private int gameId;
	private OfferTrade_Params params;
    private GameManager gameManager;

	/**
	 * @param gameId Id of the game where the trade is to be offered.
	 * @param params Parameters needed to offer a trade.
	 */
	public OfferTrade_CO(int gameId,
			OfferTrade_Params params, GameManager gameManager) {
		super();
		this.gameId = gameId;
		this.params = params;
        this.gameManager = gameManager;
	}

	@Override
	public boolean execute() {
        ServerGame game = gameManager.getGame(gameId);
        game.offerDomesticTrade(params.getPlayerIndex(), params.getReceiver(), params.getOfferedResources());
		return false;
	}

}
