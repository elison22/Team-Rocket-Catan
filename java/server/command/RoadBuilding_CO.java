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
	
	private ServerGame game;
	private RoadBuilding_Params params;

	/**
	 * @param game The id of the game where the card is to be played.
	 * @param params The Parameters needed to play the road building card.
	 */
	public RoadBuilding_CO(ServerGame game,
			RoadBuilding_Params params) {
		super();
		this.game = game;
		this.params = params;
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
