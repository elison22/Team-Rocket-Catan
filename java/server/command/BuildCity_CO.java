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
	
	private IModelFacade modelFacade;
	private int gameId;
	private BuildCity_Params params;

	/**
	 * @param modelFacade The implementation of IModelFacde to be used.
	 * @param gameId The id of the game where the city is to be built.
	 * @param params Parameters needed to build a city.
	 */
	public BuildCity_CO(IModelFacade modelFacade, int gameId,
			BuildCity_Params params) {
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
