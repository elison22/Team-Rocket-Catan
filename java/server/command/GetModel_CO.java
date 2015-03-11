package command;

import facade.IModelFacade;

/**
 * @author Chad
 *
 * This class is responsible for retrieving a model's current state if the 
 * given version number is different than the server's.
 */
@SuppressWarnings("unused")
public class GetModel_CO implements ICommandObject {
	
	private IModelFacade modelFacade;
	private int version;
	private int gameId;
	
	/**
	 * @param modelFacade The implementation of IModelFacde to be used.
	 * @Param gameId The game's id.
	 * @param version The version number to be compared to the server's version
	 * number for the specified game.
	 */
	public GetModel_CO(IModelFacade modelFacade, int gameId, int version) {
		this.modelFacade = modelFacade;
		this.gameId = gameId;
		this.version = version;
	}

	@Override
	public Object execute() {
		return null;
	}

}
