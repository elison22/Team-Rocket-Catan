package command;

import shared.dto.BuildSettlement_Params;
import facade.IModelFacade;

/**
 * @author Chad
 * 
 * Makes all necessary calls for a player to build a settlement.
 */
@SuppressWarnings("unused")
public class BuildSettlement_CO implements ICommandObject {
	
	private IModelFacade modelFacade;
	private int gameId;
	private BuildSettlement_Params params;

	/**
	 * @param modelFacade The implementation of IModelFacde to be used.
	 * @param gameId The id of the game where the settlement is to be built.
	 * @param params Parameters needed to build a settlement.
	 */
	public BuildSettlement_CO(IModelFacade modelFacade, int gameId,
			BuildSettlement_Params params) {
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
