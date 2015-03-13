package command;

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
	
	/**
	 * @param modelFacade The implementation of IModelFacde to be used.
	 * @param gameId Id of the game the player is in.
	 */
	public BuyDevCard_CO(IModelFacade modelFacade, int gameId) {
		this.modelFacade = modelFacade;
		this.gameId = gameId;
	}

	@Override
	public Object execute() {
		// TODO Auto-generated method stub
		return null;
	}

}
