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
	
	private Soldier_Params params;
    private ServerGame game;

	/**
	 * @param params Parameters needed to play the soldier card.
	 */
	public Soldier_CO(ServerGame game, Soldier_Params params) {
		super();
		this.params = params;
        this.game = game;
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
