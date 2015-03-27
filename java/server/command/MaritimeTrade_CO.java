package command;

import model.sgame.ServerGame;
import model.strade.ServerMaritimeTrade;
import shared.definitions.ResourceType;
import shared.dto.MaritimeTrade_Params;

/**
 * @author Chad
 *
 * Makes all necessary calls for a player to make a maritime trade.
 */
public class MaritimeTrade_CO implements ICommandObject {
	
	private MaritimeTrade_Params params;
    transient private ServerGame game;

	/**
	 * @param params Parameters needed for a player to make a maritime trade.
	 */
	public MaritimeTrade_CO(MaritimeTrade_Params params, ServerGame game) {
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
        ResourceType input = convertString(params.getInputResource());
        ResourceType output = convertString(params.getOutputResource());
        if(input == null || output == null)
            return false;

        ServerMaritimeTrade maritimeTrade = new ServerMaritimeTrade(input, output, params.getRatio());
        if(!game.canMaritimeTrade(params.getPlayerIndex(), maritimeTrade))
            return false;
        return game.doMaritimeTrade(params.getPlayerIndex(), maritimeTrade);
	}

    public ResourceType convertString(String res) {
        String resource = res.toLowerCase();
        switch(resource){
            case("brick"):
                return ResourceType.BRICK;
            case("wood"):
                return ResourceType.WOOD;
            case("wheat"):
                return ResourceType.WHEAT;
            case("sheep"):
                return ResourceType.SHEEP;
            case("ore"):
                return ResourceType.ORE;
        }
        return null;
    }

}
