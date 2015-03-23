package command;

import model.sgame.ServerGame;
import shared.dto.BuildRoad_Params;
import facade.IModelFacade;

/**
 * @author Chad
 * 
 * Makes all necessary calls for a player to build a road.
 */
@SuppressWarnings("unused")
public class BuildRoad_CO implements ICommandObject {
	
	private BuildRoad_Params params;
	private ServerGame game;

	/**
	 * @param params Parameters needed for a player to build a road.
	 * @param game The game to build a road in.
	 */
	public BuildRoad_CO(BuildRoad_Params params, ServerGame game) {
		super();
		this.params = params;
		this.game = game;
	}

	@Override
	public boolean execute() {
		return game.doBuildRoad(params.getPlayerIndex(), params.getLocation(), params.isFree());
	}

}
