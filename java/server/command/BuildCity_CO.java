package command;

import shared.dto.BuildCity_Params;
import facade.IModelFacade;

/**
 * @author Chad
 *
 * Makes all necessary calls for a player to build a City.
 */
@SuppressWarnings("unused")
public class BuildCity_CO implements ICommandObject {
	
	private int gameId;
	private BuildCity_Params params;

	/**
	 * @param gameId The id of the game where the city is to be built.
	 * @param params Parameters needed to build a city.
	 */
	public BuildCity_CO(int gameId,
			BuildCity_Params params) {
		super();
		this.gameId = gameId;
		this.params = params;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
	}

}
