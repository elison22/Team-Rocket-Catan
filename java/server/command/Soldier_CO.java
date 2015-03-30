package command;

import java.util.Random;

import facade.GameManager;
import model.sboard.ServerBoardException;
import model.sgame.ServerGame;
import shared.dto.Soldier_Params;

/**
 * @author Chad
 *
 * Makes all necessary calls for a player to play the soldier card.
 */
public class Soldier_CO implements ICommandObject {
	
	private String type;
	private Soldier_Params soldierParams;
    transient private ServerGame game;
    private Integer seed;

	/**
	 * @param params Parameters needed to play the soldier card.
	 */
	public Soldier_CO(Soldier_Params params, ServerGame game, Integer seed) {
		super();
		this.type = "Soldier";
		this.soldierParams = params;
        this.game = game;
        this.seed = seed;
	}
	
	public void setGame(ServerGame game)
	{
		this.game = game;
	}

	@Override
	public boolean execute() {

        try {
        	// If a seed hasn't been generated already
        	if (seed == null) {
        		// Generate and remember a random seed
        		Random random = new Random();
        		seed = random.nextInt();
        	}
        	
            return game.doSoldier(soldierParams.getPlayerIndex(), soldierParams.getVictimIndex(), soldierParams.getLocation(), seed);
        } catch (ServerBoardException e) {
            e.printStackTrace();
            return false;
        }
	}

	@Override
	public void setGameManager(GameManager gameManager) {}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
