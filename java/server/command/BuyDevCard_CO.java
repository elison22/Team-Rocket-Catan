package command;

import java.util.Random;

import facade.GameManager;
import model.sgame.ServerGame;
import shared.dto.BuyDevCard_Params;

/**
 * @author Chad
 *
 * Makes all needed calls for a player to buy a dev card.
 */
public class BuyDevCard_CO implements ICommandObject {

	private String type;
	private BuyDevCard_Params buyDevCardParams;
    transient private ServerGame game;
    private Integer seed;
	
	/**
	 * @param params Parameters for buying a dev card.
	 */
	public BuyDevCard_CO(BuyDevCard_Params params, ServerGame game, Integer seed) {
		this.type = "BuyDevCard";
		this.buyDevCardParams = params;
        this.game = game;
        this.seed = seed;
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
		
        return game.doBuyDevCard(buyDevCardParams.getPlayerIndex(), seed);
	}

	@Override
	public void setGameManager(GameManager gameManager) {}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getSeed() {
		return seed;
	}

	public void setSeed(Integer seed) {
		this.seed = seed;
	}
}
