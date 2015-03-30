package command;

import facade.GameManager;
import model.sgame.ServerGame;
import shared.dto.BuildCity_Params;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;

/**
 * @author Chad
 *
 * Makes all necessary calls for a player to build a City.
 */
public class BuildCity_CO implements ICommandObject {
	
	private String type;
	private BuildCity_Params buildCityParams;
    transient private ServerGame game;

	/**
	 * @param game The game.
	 * @param params Parameters needed to build a city.
	 */
	public BuildCity_CO(BuildCity_Params params, ServerGame game) {
		super();
		this.type = "BuildCity";
		this.buildCityParams = params;
        this.game = game;
	}
	
	public void setGame(ServerGame game)
	{
		this.game = game;
	}

	@Override
	public boolean execute() {
        return game.doBuildCity( buildCityParams.getPlayerIndex(), 
        						 new VertexLocation( new HexLocation( buildCityParams.getVertexX(), 
        								 							  buildCityParams.getVertexY()), 
        								 			 VertexDirection.convert(buildCityParams.getVertexDir())));
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
