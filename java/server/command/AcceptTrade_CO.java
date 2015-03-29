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

	private String type;
	private AcceptTrade_Params acceptTradeParams;
    transient private ServerGame game;

	/**
	 * @param params Parameters needed for a player to accept a trade.
	 */
	public AcceptTrade_CO(AcceptTrade_Params params, ServerGame game) {
		super();
		this.type = "AcceptTrade";
		this.acceptTradeParams = params;
        this.game = game;
	}
	
	public void setGame(ServerGame game)
	{
		this.game = game;
	}

	@Override
	public boolean execute() {
        return game.doDomesticTrade(acceptTradeParams.getPlayerIndex(), acceptTradeParams.isWillAccept());
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
