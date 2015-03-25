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

	private ServerGame game;
	private YearOfPlenty_Params params;
	
	/**
	 * @param game blah
	 * @param params Parameters needed to play a Year of Plenty card.
	 */
	public YearOfPlenty_CO(ServerGame game, YearOfPlenty_Params params) {
		this.game = game;
		this.params = params;
	}

	@Override
	public boolean execute() {
		return game.doYearOfPlenty(
                params.getPlayerIndex(),
                ResourceType.convert(params.getResource1()),
                ResourceType.convert(params.getResource2())
        );
	}

}
