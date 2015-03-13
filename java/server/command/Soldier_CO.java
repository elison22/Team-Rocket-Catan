package command;

import shared.dto.Soldier_Params;
import facade.IModelFacade;

/**
 * @author Chad
 *
 * Makes all necessary calls for a player to play the soldier card.
 */
@SuppressWarnings("unused")
public class Soldier_CO implements ICommandObject {
	
	private IModelFacade modelFacade;
	private int gameId;
	private Soldier_Params params;

	/**
	 * @param modelFacade The implementation of IModelFacde to be used.
	 * @param gameId The id of the game where the card is to be played.
	 * @param params Parameters needed to play the soldier card.
	 */
	public Soldier_CO(IModelFacade modelFacade, int gameId, Soldier_Params params) {
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
