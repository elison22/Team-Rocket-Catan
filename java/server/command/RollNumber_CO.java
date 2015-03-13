package command;

import shared.dto.RollNumber_Params;
import facade.IModelFacade;

/**
 * @author Chad
 *
 * Does everything needed for a player to roll a given dice number.
 */
@SuppressWarnings("unused")
public class RollNumber_CO implements ICommandObject {
	
	private IModelFacade modelFacade;
	private int gameId;
	private RollNumber_Params params;

	/**
	 * @param modelFacade The implementation of IModelFacde to be used.
	 * @param gameId Id of the game where the dice roll is happening.
	 * @param Parameters needed for rolling a number.
	 */
	public RollNumber_CO(IModelFacade modelFacade, int gameId, RollNumber_Params params) {
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
