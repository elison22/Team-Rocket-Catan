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
	
	private IModelFacade modelFacade;
	private CreateGame_Params params;
	
	/**
	 * @param modelFacade The implementation of IModelFacde to be used.
	 * @param params The parameters required to create the game.
	 */
	public CreateGame_CO(IModelFacade modelFacade, CreateGame_Params params) {
		this.modelFacade = modelFacade;
		this.params = params;
	}

	@Override
	public Object execute() {
		// Request the modelfacade to make a new game with the given params
		return null;
	}

}
