package command;

import facade.IModelFacade;

/**
 * @author Chad
 *
 * Takes care of everything needed to send a chat.
 */
@SuppressWarnings("unused")
public class SendChat_CO implements ICommandObject {
	
	private IModelFacade modelFacade;
	private int gameId;
	
	/**
	 * @param modelFacade The implementation of IModelFacde to be used.
	 * @param gameId The id of the game where the chat is being sent in.
	 */
	public SendChat_CO(IModelFacade modelFacade, int gameId) {
		this.modelFacade = modelFacade;
		this.gameId = gameId;
	}

	@Override
	public Object execute() {
		// TODO Auto-generated method stub
		return null;
	}

}
