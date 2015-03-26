package command;

import facade.GameManager;
import model.sgame.ServerGame;
import shared.definitions.DevCardType;
import shared.dto.BuyDevCard_Params;
import facade.IModelFacade;

/**
 * @author Chad
 *
 * Makes all needed calls for a player to buy a dev card.
 */
@SuppressWarnings("unused")
public class BuyDevCard_CO implements ICommandObject {

	private BuyDevCard_Params params;
    private ServerGame game;
    private DevCardType devCardType;
	
	/**
	 * @param params Parameters for buying a dev card.
	 */
	public BuyDevCard_CO(BuyDevCard_Params params, ServerGame game) {
		this.params = params;
        this.game = game;
        devCardType = null;
	}
	
	public void setGame(ServerGame game)
	{
		this.game = game;
	}

	@Override
	public boolean execute() {
        devCardType = game.doBuyDevCard(params.getPlayerIndex(), devCardType);
        return true;
	}

}
