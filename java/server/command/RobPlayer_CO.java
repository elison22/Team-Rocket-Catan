package command;

import shared.dto.RobPlayer_Params;
import facade.IModelFacade;

/**
 * @author Chad
 *
 * Makes all necessary calls for a player to rob another player in a given game.
 */
@SuppressWarnings("unused")
public class RobPlayer_CO implements ICommandObject {
	
	private int gameId;
	private RobPlayer_Params params;

	/**
	 * @param gameId Id of the game where the robbing will occur.
	 * @param params Parameters need for a player to rob another player.
	 */
	public RobPlayer_CO(int gameId,
			RobPlayer_Params params) {
		super();
		this.gameId = gameId;
		this.params = params;
	}

	@Override
	public void execute() {
	}

}
