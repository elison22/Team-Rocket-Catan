package command;

import facade.GameManager;
import model.sgame.ServerGame;
import shared.dto.Monument_Params;

/**
 * @author Chad
 *
 * Makes all necessary calls for a player to play the monument dev card.
 */
public class Monument_CO implements ICommandObject {
	
	private String type;
	private Monument_Params monumentParams;
    transient private ServerGame game;

	/**
	 * @param params Parameters needed to play the monument card.
	 */
	public Monument_CO(Monument_Params params, ServerGame game) {
		super();
		this.type = "Monument";
		this.monumentParams = params;
        this.game = game;
	}
	
	public void setGame(ServerGame game)
	{
		this.game = game;
	}

	@Override
	public boolean execute() {
		return game.doMonument(monumentParams.getPlayerIndex());
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
