package command;

import model.sgame.ServerGame;
import shared.dto.RoadBuilding_Params;
import facade.IModelFacade;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;

/**
 * @author Chad
 *
 * Makes all necessary calls on the model for a player to play the
 * road building dev card.
 */
@SuppressWarnings("unused")
public class RoadBuilding_CO implements ICommandObject {
	
	private RoadBuilding_Params params;
    private ServerGame game;

	/**
	 * @param params The Parameters needed to play the road building card.
	 */
	public RoadBuilding_CO(RoadBuilding_Params params, ServerGame game) {
		super();
		this.params = params;
        this.game = game;
	}

	@Override
	public boolean execute() {
        if(game.doRoadBuilding(params.getPlayerIndex(), params.getEdgeLocationRoad1(), params.getEdgeLocationRoad2())){
            return true;
        }
		return false;
	}

}
