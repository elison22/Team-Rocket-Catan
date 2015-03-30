package command;

import facade.GameManager;
import model.sgame.ServerGame;
import shared.dto.OfferTrade_Params;

/**
 * @author Chad
 *
 * Makes all necessary calls for a player to offer a trade.
 */
public class OfferTrade_CO implements ICommandObject {
	
	private String type;
	private OfferTrade_Params offerTradeParams;
    transient private ServerGame game;

	/**
	 * @param params Parameters needed to offer a trade.
	 */
	public OfferTrade_CO(OfferTrade_Params params, ServerGame game) {
		super();
		this.type = "OfferTrade";
		this.offerTradeParams = params;
        this.game = game;
	}
	
	public void setGame(ServerGame game)
	{
		this.game = game;
	}

	@Override
	public boolean execute() {
        return game.offerDomesticTrade(offerTradeParams.getPlayerIndex(), offerTradeParams.getReceiver(), offerTradeParams.getOfferedResources());
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
