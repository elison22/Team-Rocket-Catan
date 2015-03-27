package command;

import facade.GameManager;
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
	
	private BuildSettlement_Params params;
	transient private ServerGame game;

	/**
	 * @param params Parameters needed to build a settlement.
	 */
	public BuildSettlement_CO(ServerGame game,
			BuildSettlement_Params params) {
		super();
		this.game = game;
		this.params = params;
	}
	
	public void setGame(ServerGame game)
	{
		this.game = game;
	}

	@Override
	public boolean execute() {
		if( game.doBuildSettlement(params.getPlayerIndex(), 
								   new VertexLocation( new HexLocation( params.getVertexX(), 
																	    params.getVertexY()), 
																	    VertexDirection.convert( params.getVertexDir())),
								   params.isFree())) {
			
			
			// If the settlement is free, end the turn
			if (params.isFree())
				game.finishTurn(params.getPlayerIndex());
			
			return true;
		}
		return false;
	}

	@Override
	public void setGameManager(GameManager gameManager) {}

}
