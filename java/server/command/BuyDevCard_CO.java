package command;

import facade.GameManager;
import model.sgame.ServerGame;
import shared.dto.BuyDevCard_Params;
import facade.IModelFacade;

/**
 * @author Chad
 *
 * Makes all needed calls for a player to buy a dev card.
 */
@SuppressWarnings("unused")
public class BuyDevCard_CO implements ICommandObject {

	private int gameId;
	private BuyDevCard_Params params;
	
	/**
	 * @param gameId Id of the game the player is in.
	 * @param params Parameters for buying a dev card.
	 */
	public BuyDevCard_CO(int gameId, BuyDevCard_Params params, GameManager manager) {
		this.gameId = gameId;
		this.params = params;
	}

	@Override
	public boolean execute() {
		return false;
	}

}
