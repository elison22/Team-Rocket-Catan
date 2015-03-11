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
	
	private IModelFacade modelFacade;
	JoinGame_Params params;
	
	/**
	 * @param modelFacade The implementation of IModelFacde to be used.
	 * @param params The parameters needed to join a game.
	 */
	public JoinGame_CO(IModelFacade modelFacade, JoinGame_Params params) {
		this.modelFacade = modelFacade;
		this.params = params;
	}

	@Override
	public Object execute() {
		return null;
	}

}
