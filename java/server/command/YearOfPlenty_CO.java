package command;

import model.sgame.ServerGame;
import shared.definitions.ResourceType;
import shared.dto.YearOfPlenty_Params;
import facade.IModelFacade;

/**
 * @author Chad
 *
 * Makes all necessary calls to play a Year of Plenty card.
 */
@SuppressWarnings("unused")
public class YearOfPlenty_CO implements ICommandObject {

	private YearOfPlenty_Params params;
    private ServerGame game;
	
	/**
	 * @param params Parameters needed to play a Year of Plenty card.
	 */
	public YearOfPlenty_CO(YearOfPlenty_Params params, ServerGame game) {
		this.params = params;
        this.game = game;
	}

	@Override
	public boolean execute() {
        // convert strings to ResourceType enums
        ResourceType resourceType1 = convertString(params.getResource1());
        ResourceType resourceType2 = convertString(params.getResource2());

        if(game.doYearOfPlenty(params.getPlayerIndex(), resourceType1, resourceType2)){
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
