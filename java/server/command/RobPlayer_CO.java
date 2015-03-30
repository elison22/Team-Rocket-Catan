package command;

import java.util.Random;

import facade.GameManager;
import model.sboard.ServerBoardException;
import model.sgame.ServerGame;
import shared.dto.RobPlayer_Params;

/**
 * @author Chad
 *
 * Makes all necessary calls for a player to rob another player in a given game.
 */
public class RobPlayer_CO implements ICommandObject {
	
	private String type;
	private RobPlayer_Params robPlayerParams;
	transient private ServerGame game;
	
	// The seed to be used when generating random numbers
	private Integer seed;

	/**
	 * @param game
	 * @param params Parameters need for a player to rob another player.
	 */
	public RobPlayer_CO(RobPlayer_Params params, ServerGame game, Integer seed) {
		super();
		type = "RobPlayer";
		this.game = game;
		this.robPlayerParams = params;
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
        	
            return game.doPlaceRobber(robPlayerParams.getPlayerIndex(), robPlayerParams.getVictimIndex(), robPlayerParams.getTargetLocation(), seed);
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
