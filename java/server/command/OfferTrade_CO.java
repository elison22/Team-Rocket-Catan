package command;

import shared.dto.OfferTrade_Params;
import facade.IModelFacade;

/**
 * @author Chad
 *
 * Makes all necessary calls for a player to offer a trade.
 */
@SuppressWarnings("unused")
public class OfferTrade_CO implements ICommandObject {
	
	private IModelFacade modelFacade;
	private int gameId;
	private OfferTrade_Params params;

	/**
	 * @param modelFacade The implementation of IModelFacde to be used.
	 * @param gameId Id of the game where the trade is to be offered.
	 * @param params Parameters needed to offer a trade.
	 */
	public OfferTrade_CO(IModelFacade modelFacade, int gameId,
			OfferTrade_Params params) {
		super();
		this.modelFacade = modelFacade;
		this.gameId = gameId;
		this.params = params;
	}

	@Override
	public Object execute() {
		// TODO Auto-generated method stub
		return null;
	}

}