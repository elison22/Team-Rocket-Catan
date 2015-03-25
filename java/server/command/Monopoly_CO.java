package command;

import model.sgame.ServerGame;
import shared.definitions.ResourceType;
import shared.dto.Monopoly_Params;
import facade.IModelFacade;

/**
 * @author Chad
 *
 * Makes all necessary calls for a player to play the monopoly card.
 */
@SuppressWarnings("unused")
public class Monopoly_CO implements ICommandObject {
	
	private Monopoly_Params params;
    private ServerGame game;

	/**
	 * @param params Parameters needed for a player to play the monopoly card.
	 */
	public Monopoly_CO(Monopoly_Params params, ServerGame game) {
		super();
		this.params = params;
        this.game = game;
	}

	@Override
	public boolean execute() {
        String resource = params.getResource();

        // Convert string to resource type
        ResourceType resourceType = convertString(resource);

        // return true if doMonopoly runs correctly
        if(game.doMonopoly(params.getPlayerIndex(), resourceType)){
            return true;
        }
		return false;
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
