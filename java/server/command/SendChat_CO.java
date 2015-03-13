package command;

import facade.IModelFacade;

/**
 * @author Chad
 *
 * Takes care of everything needed to send a chat.
 */
@SuppressWarnings("unused")
public class SendChat_CO implements ICommandObject {
	
	private int gameId;
	
	/**
	 * @param gameId The id of the game where the chat is being sent in.
	 */
	public SendChat_CO(int gameId) {
		this.gameId = gameId;
	}

	@Override
	public void execute() {
	}

}
