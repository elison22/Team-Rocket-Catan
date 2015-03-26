package command;

import model.sgame.ServerGame;
import shared.dto.RoadBuilding_Params;

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
	public RoadBuilding_CO(ServerGame game, RoadBuilding_Params params) {
		super();
		this.params = params;
        this.game = game;
	}
	
	public void setGame(ServerGame game)
	{
		this.game = game;
	}

	@Override
	public boolean execute() {
		return game.doRoadBuilding(
                params.getPlayerIndex(),
                params.getRoad1(),
                params.getRoad2()
        );
	}

}
