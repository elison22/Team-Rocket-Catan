package command;

import shared.dto.YearOfPlenty_Params;
import facade.IModelFacade;

/**
 * @author Chad
 *
 * Makes all necessary calls to play a Year of Plenty card.
 */
@SuppressWarnings("unused")
public class YearOfPlenty_CO implements ICommandObject {

	private IModelFacade modelFacade;
	private int gameId;
	private YearOfPlenty_Params params;
	
	/**
	 * @param modelFacade The implementation of IModelFacde to be used.
	 * @param gameId Id of the game to play a year of plenty card.
	 * @param params Parameters needed to play a Year of Plenty card.
	 */
	public YearOfPlenty_CO(IModelFacade modelFacade, int gameId, YearOfPlenty_Params params) {
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
