package command;

import model.sgame.ServerGame;
import shared.dto.Soldier_Params;

/**
 * @author Chad
 *
 * Makes all necessary calls for a player to play the soldier card.
 */
@SuppressWarnings("unused")
public class Soldier_CO implements ICommandObject {
	
	private ServerGame game;
	private Soldier_Params params;

	/**
	 * @param game The id of the game where the card is to be played.
	 * @param params Parameters needed to play the soldier card.
	 */
	public Soldier_CO(ServerGame game, Soldier_Params params) {
		super();
		this.game = game;
		this.params = params;
	}

	@Override
	public boolean execute() {

        return game.doSoldier(
                params.getPlayerIndex(),
                params.getVictimIndex(),
                params.getLocation()
        );
	}

}
