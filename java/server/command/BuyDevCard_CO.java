package command;

import java.util.Random;

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
    transient private ServerGame game;
    private Integer seed;
	
	/**
	 * @param params Parameters for buying a dev card.
	 */
	public BuyDevCard_CO(BuyDevCard_Params params, ServerGame game) {
		this.params = params;
        this.game = game;
	}
	
	public void setGame(ServerGame game)
	{
		this.game = game;
	}

	@Override
	public boolean execute() {
		// If a seed hasn't been generated already
    	if (seed == null) {
    		// Generate and remember a random seed
    		Random random = new Random();
    		seed = random.nextInt();
    	}
		
        return game.doBuyDevCard(params.getPlayerIndex(), seed);
	}

}
