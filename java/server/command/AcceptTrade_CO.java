package command;

import facade.GameManager;
import model.sgame.ServerGame;
import shared.dto.AcceptTrade_Params;

/**
 * @author Chad
 *
 * Makes all necessary calls for a player to accept a trade.
 */
public class AcceptTrade_CO implements ICommandObject {

	private AcceptTrade_Params params;
    transient private ServerGame game;

	/**
	 * @param params Parameters needed for a player to accept a trade.
	 */
	public AcceptTrade_CO(AcceptTrade_Params params, ServerGame game) {
		super();
		this.params = params;
        this.game = game;
	}
	
	public void setGame(ServerGame game)
	{
		this.game = game;
	}

	@Override
	public boolean execute() {
        return game.doDomesticTrade(params.getPlayerIndex(), params.isWillAccept());
	}

	@Override
	public void setGameManager(GameManager gameManager) {}

}
