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
	
	private OfferTrade_Params params;
    private ServerGame game;

	/**
	 * @param params Parameters needed to offer a trade.
	 */
	public OfferTrade_CO(OfferTrade_Params params, ServerGame game) {
		super();
		this.params = params;
        this.game = game;
	}

	@Override
	public boolean execute() {
        return game.offerDomesticTrade(params.getPlayerIndex(), params.getReceiver(), params.getOfferedResources());
	}

}
