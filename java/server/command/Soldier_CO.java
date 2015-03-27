package command;

import java.util.Random;

import model.sboard.ServerBoardException;
import model.sgame.ServerGame;
import shared.dto.Soldier_Params;

/**
 * @author Chad
 *
 * Makes all necessary calls for a player to play the soldier card.
 */
public class Soldier_CO implements ICommandObject {
	
	private Soldier_Params params;
    private ServerGame game;
    private Integer seed;

	/**
	 * @param params Parameters needed to play the soldier card.
	 */
	public Soldier_CO(ServerGame game, Soldier_Params params) {
		super();
		this.params = params;
        this.game = game;
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
        	
            return game.doSoldier(params.getPlayerIndex(), params.getVictimIndex(), params.getLocation(), seed);
        } catch (ServerBoardException e) {
            e.printStackTrace();
            return false;
        }
	}

}
