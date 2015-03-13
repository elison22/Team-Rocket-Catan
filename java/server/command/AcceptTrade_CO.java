package command;

import shared.dto.AcceptTrade_Params;
import facade.IModelFacade;

/**
 * @author Chad
 *
 * Makes all necessary calls for a player to accept a trade.
 */
@SuppressWarnings("unused")
public class AcceptTrade_CO implements ICommandObject {
	
	private IModelFacade modelFacade;
	private int gameId;
	private AcceptTrade_Params params;

	/**
	 * @param modelFacade The implementation of IModelFacde to be used.
	 * @param gameId Id of the game where the trade is to be accepted (or not).
	 * @param params Parameters needed for a player to accept a trade.
	 */
	public AcceptTrade_CO(IModelFacade modelFacade, int gameId,
			AcceptTrade_Params params) {
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
