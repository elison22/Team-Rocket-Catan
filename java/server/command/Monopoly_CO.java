package command;

import shared.dto.Monopoly_Params;
import facade.IModelFacade;

/**
 * @author Chad
 *
 * Makes all necessary calls for a player to play the monopoly card.
 */
@SuppressWarnings("unused")
public class Monopoly_CO implements ICommandObject {
	
	private IModelFacade modelFacade;
	private int gameId;
	private Monopoly_Params params;

	/**
	 * @param modelFacade The implementation of IModelFacde to be used.
	 * @param gameId Id of the game where the monopoly card is to be played.
	 * @param params Parameters needed for a player to play the monopoly card.
	 */
	public Monopoly_CO(IModelFacade modelFacade, int gameId,
			Monopoly_Params params) {
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
