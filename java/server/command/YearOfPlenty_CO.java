package command;

import facade.GameManager;
import model.sgame.ServerGame;
import shared.definitions.ResourceType;
import shared.dto.YearOfPlenty_Params;

/**
 * @author Chad
 *
 * Makes all necessary calls to play a Year of Plenty card.
 */
public class YearOfPlenty_CO implements ICommandObject {

	private String type;
	private YearOfPlenty_Params yearOfPlentyParams;
    transient private ServerGame game;
	
	/**
	 * @param params Parameters needed to play a Year of Plenty card.
	 */
	public YearOfPlenty_CO(YearOfPlenty_Params params, ServerGame game) {
		this.type = "YearOfPlenty";
		this.yearOfPlentyParams = params;
        this.game = game;
	}
	
	public void setGame(ServerGame game)
	{
		this.game = game;
	}

	@Override
	public boolean execute() {
		return game.doYearOfPlenty(
                yearOfPlentyParams.getPlayerIndex(),
                ResourceType.convert(yearOfPlentyParams.getResource1()),
                ResourceType.convert(yearOfPlentyParams.getResource2())
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
