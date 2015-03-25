package command;

import model.sgame.ServerGame;
import shared.dto.SendChat_Params;
import facade.GameManager;
import facade.IModelFacade;

/**
 * @author Chad
 *
 * Takes care of everything needed to send a chat.
 */
@SuppressWarnings("unused")
public class SendChat_CO implements ICommandObject {
	
	private int gameId;
	private SendChat_Params chatParams;
	private GameManager gameManager;
	
	/**
	 * @param gameId The id of the game where the chat is being sent in.
	 * @param gameManager 
	 * @param chatParams 
	 */
	public SendChat_CO(int gameId, SendChat_Params chatParams, GameManager gameManager) {
		this.gameId = gameId;
		this.chatParams = chatParams;
		this.gameManager = gameManager;
	}

	@Override
	public boolean execute() {
		ServerGame game = gameManager.getGame(gameId);
		String message = chatParams.getContent();
		return game.doSendChat(chatParams.getPlayerIndex(), message);
	}

}
