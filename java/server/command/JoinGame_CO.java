package command;

import facade.GameManager;
import shared.definitions.CatanColor;
import shared.dto.JoinGame_Params;
import model.sgame.ServerGame;

public class JoinGame_CO implements ICommandObject {
	
	private String type;
	private JoinGame_Params joinGameParams;
	transient private GameManager gameManager;
	
	public JoinGame_CO(JoinGame_Params params, GameManager gameManager) {
		this.type = "JoinGame";
		this.joinGameParams = params;
		this.gameManager = gameManager;
	}

	@Override
	public void setGame(ServerGame game) {}

	@Override
	public boolean execute() {
		// Verify the color is valid
		if (CatanColor.convert(joinGameParams.getColor()) == null)
			return false;
		else
			return gameManager.addPlayerToGame(joinGameParams.getId(), 
											   joinGameParams.getPlayer(), 
											   joinGameParams.getPlayerId(), 
											   joinGameParams.getColor());
	}
	
	@Override
	public void setGameManager(GameManager gameManager) {
		this.gameManager = gameManager;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
