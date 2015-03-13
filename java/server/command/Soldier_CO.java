package command;

import shared.dto.Soldier_Params;
import facade.IModelFacade;

/**
 * @author Chad
 *
 * Makes all necessary calls for a player to play the soldier card.
 */
@SuppressWarnings("unused")
public class Soldier_CO implements ICommandObject {
	
	private int gameId;
	private Soldier_Params params;

	/**
	 * @param gameId The id of the game where the card is to be played.
	 * @param params Parameters needed to play the soldier card.
	 */
	public Soldier_CO(int gameId, Soldier_Params params) {
		super();
		this.gameId = gameId;
		this.params = params;
	}

	@Override
	public void execute() {
	}

}
