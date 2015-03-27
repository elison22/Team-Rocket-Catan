package command;

import facade.GameManager;
import model.sgame.ServerGame;
import shared.definitions.ResourceType;
import shared.dto.Monopoly_Params;

/**
 * @author Chad
 *
 * Makes all necessary calls for a player to play the monopoly card.
 */
public class Monopoly_CO implements ICommandObject {
	
	private Monopoly_Params params;
    transient private ServerGame game;

	/**
	 * @param params Parameters needed for a player to play the monopoly card.
	 */
	public Monopoly_CO(ServerGame game, Monopoly_Params params) {
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
		return game.doMonopoly(
                params.getPlayerIndex(),
                ResourceType.convert(params.getResource())
        );
	}

	@Override
	public void setGameManager(GameManager gameManager) {}

}
