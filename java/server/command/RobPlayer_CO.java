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
	
	private IModelFacade modelFacade;
	private int gameId;
	private RobPlayer_Params params;

	/**
	 * @param modelFacade The implementation of IModelFacde to be used.
	 * @param gameId Id of the game where the robbing will occur.
	 * @param params Parameters need for a player to rob another player.
	 */
	public RobPlayer_CO(IModelFacade modelFacade, int gameId,
			RobPlayer_Params params) {
		super();
		this.modelFacade = modelFacade;
		this.gameId = gameId;
		this.params = params;
	}

	@Override
	public Object execute() {
		// TODO Auto-generated method stub
		return null;
	}

}
