package command;

import shared.dto.RoadBuilding_Params;
import facade.IModelFacade;

/**
 * @author Chad
 *
 * Makes all necessary calls on the model for a player to play the
 * road building dev card.
 */
@SuppressWarnings("unused")
public class RoadBuilding_CO implements ICommandObject {
	
	private int gameId;
	private RoadBuilding_Params params;

	/**
	 * @param gameId The id of the game where the card is to be played.
	 * @param params The Parameters needed to play the road building card.
	 */
	public RoadBuilding_CO(int gameId,
			RoadBuilding_Params params) {
		super();
		this.gameId = gameId;
		this.params = params;
	}

	@Override
	public void execute() {
	}

}
