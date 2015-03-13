package command;

import shared.dto.DiscardCards_Params;
import facade.IModelFacade;

/**
 * @author Chad
 *
 * Makes all necessary calls for a player to discard cards.
 */
@SuppressWarnings("unused")
public class DiscardCards_CO implements ICommandObject {
	
	private IModelFacade modelFacade;
	private int gameId;
	private DiscardCards_Params params;

	/**
	 * @param modelFacade The implementation of IModelFacde to be used.
	 * @param gameId Id of the game where the payer must discard cards;
	 * @param params Parameters needed for discarding cards.
	 */
	public DiscardCards_CO(IModelFacade modelFacade, int gameId,
			DiscardCards_Params params) {
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
