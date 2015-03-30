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
	
	private String type;
	private BuildSettlement_Params buildSettlementParams;
	transient private ServerGame game;

	/**
	 * @param params Parameters needed to build a settlement.
	 */
	public BuildSettlement_CO(BuildSettlement_Params params, ServerGame game) {
		super();
		this.type = "BuildSettlement";
		this.game = game;
		this.buildSettlementParams = params;
	}
	
	public void setGame(ServerGame game)
	{
		this.game = game;
	}

	@Override
	public boolean execute() {
		if( game.doBuildSettlement(buildSettlementParams.getPlayerIndex(), 
								   new VertexLocation( new HexLocation( buildSettlementParams.getVertexX(), 
																	    buildSettlementParams.getVertexY()), 
																	    VertexDirection.convert( buildSettlementParams.getVertexDir())),
								   buildSettlementParams.isFree())) {
			
			
			// If the settlement is free, end the turn
			if (buildSettlementParams.isFree())
				game.finishTurn(buildSettlementParams.getPlayerIndex());
			
			return true;
		}
		return false;
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
