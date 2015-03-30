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
	
	private String type;
	private Monopoly_Params monopolyParams;
    transient private ServerGame game;

	/**
	 * @param params Parameters needed for a player to play the monopoly card.
	 */
	public Monopoly_CO(Monopoly_Params params, ServerGame game) {
		super();
		this.type = "Monopoly";
		this.monopolyParams = params;
        this.game = game;
	}
	
	public void setGame(ServerGame game)
	{
		this.game = game;
	}

	@Override
	public boolean execute() {
		return game.doMonopoly(
                monopolyParams.getPlayerIndex(),
                ResourceType.convert(monopolyParams.getResource())
        );
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
