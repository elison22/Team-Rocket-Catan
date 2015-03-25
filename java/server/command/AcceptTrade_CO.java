package command;

import facade.GameManager;
import model.sgame.ServerGame;
import shared.dto.AcceptTrade_Params;
import facade.IModelFacade;

/**
 * @author Chad
 *
 * Makes all necessary calls for a player to accept a trade.
 */
@SuppressWarnings("unused")
public class AcceptTrade_CO implements ICommandObject {

	private AcceptTrade_Params params;
    private ServerGame game;

	/**
	 * @param params Parameters needed for a player to accept a trade.
	 */
	public AcceptTrade_CO(AcceptTrade_Params params, ServerGame game) {
		super();
		this.params = params;
        this.game = game;
	}

	@Override
	public boolean execute() {
        return game.doDomesticTrade(params.getPlayerIndex(), params.isWillAccept());
	}

}
