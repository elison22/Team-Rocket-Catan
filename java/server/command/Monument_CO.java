package command;

import shared.dto.Monument_Params;
import facade.IModelFacade;

/**
 * @author Chad
 *
 * Makes all necessary calls for a player to play the monument dev card.
 */
@SuppressWarnings("unused")
public class Monument_CO implements ICommandObject {
	
	private IModelFacade modelFacade;
	private int gameId;
	private Monument_Params params;

	/**
	 * @param modelFacade The implementation of IModelFacde to be used.
	 * @param gameId The id of the game where the card is to be played.
	 * @param params Parameters needed to play the monument card.
	 */
	public Monument_CO(IModelFacade modelFacade, int gameId, Monument_Params params) {
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
