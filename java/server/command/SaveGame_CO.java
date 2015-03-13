package command;

import shared.dto.SaveGame_Params;

/**
 * @author Chad
 *
 * This class makes all the necessary calls to save a game using the given 
 * parameters.
 */
public class SaveGame_CO implements ICommandObject {
	
	SaveGame_Params params;
	
	/**
	 * @param params The parameters needed to save a game.
	 */
	public SaveGame_CO(SaveGame_Params params) {
		this.params = params;
	}

	@Override
	public void execute() {
	}

}
