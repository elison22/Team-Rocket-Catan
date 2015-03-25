package command;

import model.sgame.ServerGame;
import shared.definitions.ResourceType;
import shared.dto.YearOfPlenty_Params;

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
		return game.doYearOfPlenty(
                params.getPlayerIndex(),
                ResourceType.convert(params.getResource1()),
                ResourceType.convert(params.getResource2())
        );
	}

}
