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
	
	private int gameId;
	private BuildSettlement_Params params;

	/**
	 * @param gameId The id of the game where the settlement is to be built.
	 * @param params Parameters needed to build a settlement.
	 */
	public BuildSettlement_CO(int gameId,
			BuildSettlement_Params params) {
		super();
		this.gameId = gameId;
		this.params = params;
	}

	@Override
	public boolean execute() {
		return false;
	}

}
