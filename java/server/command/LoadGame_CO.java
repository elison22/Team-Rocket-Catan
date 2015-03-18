package command;

import shared.dto.LoadGame_Params;
import facade.IModelFacade;

/**
 * @author Chad
 *
 * This class does everything needed to load a game with the given parameters.
 */
@SuppressWarnings("unused")
public class LoadGame_CO implements ICommandObject {
	
	private LoadGame_Params params;

	/**
	 * @param modelFacade The implementation of IModelFacde to be used.
	 * @param params The parameters needed for loading a game.
	 */
	public LoadGame_CO(LoadGame_Params params) {
		this.params = params;
	}

	@Override
	public boolean execute() {
		return false;
	}

}
