package command;

import shared.dto.BuyDevCard_Params;
import facade.IModelFacade;

/**
 * @author Chad
 *
 * Makes all needed facade calls for a player to buy a dev card.
 */
@SuppressWarnings("unused")
public class BuyDevCard_CO implements ICommandObject {

	private IModelFacade modelFacade;
	private int gameId;
	private BuyDevCard_Params params;
	
	/**
	 * @param modelFacade The implementation of IModelFacde to be used.
	 * @param gameId Id of the game the player is in.
	 * @param params Parameters for buying a dev card.
	 */
	public BuyDevCard_CO(IModelFacade modelFacade, int gameId, BuyDevCard_Params params) {
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
