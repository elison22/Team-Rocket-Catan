package command;

import shared.dto.RoadBuilding_Params;
import facade.IModelFacade;

/**
 * @author Chad
 *
 * Makes all necessary calls on the model facade for a player to play the
 * road building dev card.
 */
@SuppressWarnings("unused")
public class RoadBuilding_CO implements ICommandObject {
	
	private IModelFacade modelFacade;
	private int gameId;
	private RoadBuilding_Params params;

	/**
	 * @param modelFacade The implementation of IModelFacde to be used.
	 * @param gameId The id of the game where the card is to be played.
	 * @param params The Parameters needed to play the road building card.
	 */
	public RoadBuilding_CO(IModelFacade modelFacade, int gameId,
			RoadBuilding_Params params) {
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
