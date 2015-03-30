package command;

import facade.GameManager;
import model.sgame.ServerGame;
import shared.dto.RoadBuilding_Params;

/**
 * @author Chad
 *
 * Makes all necessary calls on the model for a player to play the
 * road building dev card.
 */
public class RoadBuilding_CO implements ICommandObject {
	
	private String type;
	private RoadBuilding_Params roadBuildingParams;
    transient private ServerGame game;

	/**
	 * @param params The Parameters needed to play the road building card.
	 */
	public RoadBuilding_CO(RoadBuilding_Params params, ServerGame game) {
		super();
		type = "RoadBuilding";
		this.roadBuildingParams = params;
        this.game = game;
	}
	
	public void setGame(ServerGame game)
	{
		this.game = game;
	}

	@Override
	public boolean execute() {
		return game.doRoadBuilding(
                roadBuildingParams.getPlayerIndex(),
                roadBuildingParams.getRoad1(),
                roadBuildingParams.getRoad2()
        );
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
