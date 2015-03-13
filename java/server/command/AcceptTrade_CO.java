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
	private int gameId;
	private AcceptTrade_Params params;

	/**
	 * @param gameId Id of the game where the trade is to be accepted (or not).
	 * @param params Parameters needed for a player to accept a trade.
	 */
	public AcceptTrade_CO(int gameId,
			AcceptTrade_Params params) {
		super();
		this.gameId = gameId;
		this.params = params;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
	}

}
