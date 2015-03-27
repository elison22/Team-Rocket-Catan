package command;

import java.util.Random;

import model.sboard.ServerBoardException;
import model.sgame.ServerGame;
import shared.dto.RobPlayer_Params;

/**
 * @author Chad
 *
 * Makes all necessary calls for a player to rob another player in a given game.
 */
public class RobPlayer_CO implements ICommandObject {
	
	private RobPlayer_Params params;
	transient private ServerGame game;
	
	// The seed to be used when generating random numbers
	private Integer seed;

	/**
	 * @param game
	 * @param params Parameters need for a player to rob another player.
	 */
	public RobPlayer_CO(RobPlayer_Params params, ServerGame game) {
		super();
		this.game = game;
		this.params = params;
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
        	
            return game.doPlaceRobber(params.getPlayerIndex(), params.getVictimIndex(), params.getTargetLocation(), seed);
        } catch (ServerBoardException e) {
            e.printStackTrace();
            return false;
        }
	}

}
