package command;

import java.util.Random;

import shared.dto.CreateGame_Params;
import facade.GameManager;
import model.sboard.ServerBoardException;
import model.sgame.ServerGame;

public class CreateGame_CO implements ICommandObject {
	
	private String type;
	private CreateGame_Params createGameParams;
	transient private GameManager gameManager;
	private Integer seed;

	public CreateGame_CO(CreateGame_Params params, GameManager gameManager, Integer seed) 
	{
		this.type = "CreateGame";
		this.createGameParams = params;
		this.gameManager = gameManager;
		this.seed = seed;
	}
	
	@Override
	public void setGame(ServerGame game) {}

	@Override
	public boolean execute() {
		
		try {
			// If a seed hasn't been generated already
        	if (seed == null) {
        		// Generate and remember a random seed
        		Random random = new Random();
        		seed = random.nextInt();
        	}
			
			gameManager.createGame(createGameParams.getRandomNumbers(), 
								   createGameParams.getRandomTiles(), 
								   createGameParams.getRandomPorts(), 
								   createGameParams.getName(), seed);
			
			return true;
		} catch (ServerBoardException e) {
			e.printStackTrace();
			return false;
		}
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
