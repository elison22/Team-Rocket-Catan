package command;

import shared.dto.MaritimeTrade_Params;
import facade.IModelFacade;

/**
 * @author Chad
 *
 * Makes all necessary calls for a player to make a maritime trade.
 */
@SuppressWarnings("unused")
public class MaritimeTrade_CO implements ICommandObject {
	
	private IModelFacade modelFacade;
	private int gameId;
	private MaritimeTrade_Params params;

	/**
	 * @param modelFacade The implementation of IModelFacde to be used.
	 * @param gameId Id of the game where the player is making a maritime trade.
	 * @param params Parameters needed for a player to make a maritime trade.
	 */
	public MaritimeTrade_CO(IModelFacade modelFacade, int gameId,
			MaritimeTrade_Params params) {
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
