package command;

import shared.dto.BuildRoad_Params;
import facade.IModelFacade;

/**
 * @author Chad
 * 
 * Makes all necessary calls for a player to build a road.
 */
@SuppressWarnings("unused")
public class BuildRoad_CO implements ICommandObject {
	
	private int gameId;
	private BuildRoad_Params params;

	/**
	 * @param gameId The id of the game where the road is to be built.
	 * @param params Parameters needed for a player to build a road.
	 */
	public BuildRoad_CO(int gameId,
			BuildRoad_Params params) {
		super();
		this.gameId = gameId;
		this.params = params;
	}

	@Override
	public void execute() {
	}

}
