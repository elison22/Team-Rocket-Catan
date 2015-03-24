package command;

import model.sgame.ServerGame;
import shared.dto.RobPlayer_Params;
import facade.IModelFacade;

/**
 * @author Chad
 *
 * Makes all necessary calls for a player to rob another player in a given game.
 */
@SuppressWarnings("unused")
public class RobPlayer_CO implements ICommandObject {
	
	private ServerGame game;
	private RobPlayer_Params params;

	/**
	 * @param game
	 * @param params Parameters need for a player to rob another player.
	 */
	public RobPlayer_CO(RobPlayer_Params params, ServerGame game) {
		super();
		this.game = game;
		this.params = params;
	}

	@Override
	public boolean execute() {
        return game.doPlaceRobber(params.getPlayerIndex(), params.getVictimIndex(), params.getTargetLocation());
	}

}
