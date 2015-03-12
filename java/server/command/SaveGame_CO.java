package command;

import shared.dto.SaveGame_Params;
import facade.IModelFacade;

/**
 * @author Chad
 *
 * This class makes all the necessary calls to save a game using the given 
 * parameters.
 */
public class SaveGame_CO implements ICommandObject {
	
	IModelFacade modelFacade;
	SaveGame_Params params;
	
	/**
	 * @param modelFacade The implementation of IModelFacde to be used.
	 * @param params The parameters needed to save a game.
	 */
	public SaveGame_CO(IModelFacade modelFacade, SaveGame_Params params) {
		this.modelFacade = modelFacade;
		this.params = params;
	}

	@Override
	public Object execute() {
		return null;
	}

}
