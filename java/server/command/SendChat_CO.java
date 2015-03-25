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
	
	private ServerGame game;
	private SendChat_Params chatParams;
	private GameManager gameManager;
	
	/**
	 * @param gameId The id of the game where the chat is being sent in.
	 * @param gameManager 
	 * @param chatParams 
	 */
	public SendChat_CO(ServerGame game, SendChat_Params chatParams, GameManager gameManager) {
		this.game = game;
		this.chatParams = chatParams;
		this.gameManager = gameManager;
	}
	
	public void setGame(ServerGame game)
	{
		this.game = game;
	}

	@Override
	public boolean execute() {
		String owner = game.getPlayerColorByIndex(chatParams.getPlayerIndex()).name();
		String message = chatParams.getContent();
		return game.doSendChat(owner, message);
	}

}
