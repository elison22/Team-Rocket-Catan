package command;

import facade.GameManager;
import model.sgame.ServerGame;
import shared.dto.SendChat_Params;

/**
 * @author Chad
 *
 * Takes care of everything needed to send a chat.
 */
public class SendChat_CO implements ICommandObject {
	
	private String type;
	private SendChat_Params chatParams;
	transient private ServerGame game;
	
	/**
	 * @param gameId The id of the game where the chat is being sent in.
	 * @param gameManager 
	 * @param chatParams 
	 */
	public SendChat_CO(SendChat_Params chatParams, ServerGame game) {
		this.type = "SendChat";
		this.chatParams = chatParams;
		this.game = game;
	}

	@Override
	public boolean execute() {
		String message = chatParams.getContent();
		return game.doSendChat(chatParams.getPlayerIndex(), message);
	}

	@Override
	public void setGame(ServerGame game) {
		this.game = game;
		
	}

	@Override
	public void setGameManager(GameManager gameManager) {}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
