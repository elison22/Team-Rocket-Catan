package command;

import facade.GameManager;
import shared.definitions.CatanColor;
import shared.dto.JoinGame_Params;
import model.sgame.ServerGame;

public class JoinGame_CO implements ICommandObject {
	
	private JoinGame_Params params;
	private String player;
	private int playerId;
	transient private GameManager gameManager;
	
	public JoinGame_CO(JoinGame_Params params, String player, int playerId, GameManager gameManager) {
		this.params = params;
		this.player = player;
		this.playerId = playerId;
		this.gameManager = gameManager;
	}

	@Override
	public void setGame(ServerGame game) {}

	@Override
	public boolean execute() {
		// Verify the color is valid
		if (CatanColor.convert(params.getColor()) == null)
			return false;
		else
			return gameManager.addPlayerToGame(params.getId(), player, playerId, params.getColor());
	}
	
	public void setGameManager(GameManager gameManager) {
		this.gameManager = gameManager;
	}

}
