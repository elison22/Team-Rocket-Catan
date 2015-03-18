package command;

import facade.IModelFacade;
import shared.dto.JoinGame_Params;

/**
 * @author Chad
 *
 * This class is responsible for calling all required methods for a player to
 * join a game with the given parameters.
 */

@SuppressWarnings("unused")
public class JoinGame_CO implements ICommandObject {
	
	private JoinGame_Params params;
	
	/**
	 * @param params The parameters needed to join a game.
	 */
	public JoinGame_CO(JoinGame_Params params) {
		this.params = params;
	}

	@Override
	public boolean execute() {
		return false;
	}

}
