package command;

import facade.IModelFacade;
import shared.dto.CreateGame_Params;

/**@author Chad
 *
 * This class makes all of the calls required to create a new game and add the
 * user as a player in that game.
 */
@SuppressWarnings("unused")
public class CreateGame_CO implements ICommandObject {
	
	private CreateGame_Params params;
	
	/**
	 * @param modelFacade The implementation of IModelFacde to be used.
	 * @param params The parameters required to create the game.
	 */
	public CreateGame_CO(CreateGame_Params params) {
		this.params = params;
	}

	@Override
	public boolean execute() {
		return false;
	}

}
