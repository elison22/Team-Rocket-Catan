package command;

import model.sgame.ServerGame;
import shared.dto.BuildSettlement_Params;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;

/**
 * @author Chad
 * 
 * Makes all necessary calls for a player to build a settlement.
 */
public class BuildSettlement_CO implements ICommandObject {
	
	private ServerGame game;
	private BuildSettlement_Params params;

	/**
	 * @param params Parameters needed to build a settlement.
	 */
	public BuildSettlement_CO(ServerGame game,
			BuildSettlement_Params params) {
		super();
		this.game = game;
		this.params = params;
	}

	@Override
	public boolean execute() {
		if(game.doBuildSettlement(params.getPlayerIndex(), new VertexLocation(new HexLocation(params.getVertexX(), params.getVertexY()), VertexDirection.convert(params.getVertexDir())), params.isFree())) {
				return true;
		}
		return false;
	}

}
