package command;

import facade.GameManager;
import model.sgame.ServerGame;
import shared.dto.BuildRoad_Params;

/**
 * @author Chad
 * 
 * Makes all necessary calls for a player to build a road.
 */
public class BuildRoad_CO implements ICommandObject {
	
	private String type;
	private BuildRoad_Params buildRoadParams;
	transient private ServerGame game;

	/**
	 * @param params Parameters needed for a player to build a road.
	 * @param game The game to build a road in.
	 */
	public BuildRoad_CO(BuildRoad_Params params, ServerGame game) {
		super();
		type = "BuildRoad";
		this.buildRoadParams = params;
		this.game = game;
	}
	
	public void setGame(ServerGame game)
	{
		this.game = game;
	}

	@Override
	public boolean execute() {
		return game.doBuildRoad(buildRoadParams.getPlayerIndex(), buildRoadParams.getLocation(), buildRoadParams.isFree());
	}

	@Override
	public void setGameManager(GameManager gameManager) {}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
