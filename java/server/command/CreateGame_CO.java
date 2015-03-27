package command;

import java.util.Random;

import facade.GameManager;
import model.sboard.ServerBoardException;
import model.sgame.ServerGame;

public class CreateGame_CO implements ICommandObject {
	
	
	private String title;
	private boolean randTiles;
	private boolean randNumbers;
	private boolean randPorts;
	transient private GameManager gameManager;
	private Integer seed;

	public CreateGame_CO(GameManager gameManager, boolean randNumbers, boolean randTiles, boolean randPorts, String title) 
			throws ServerBoardException
	{
		this.randNumbers = randNumbers;
		this.randPorts = randPorts;
		this.randTiles = randTiles;		
		this.gameManager = gameManager;
		this.title = title;
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
			
			gameManager.createGame(randNumbers, randTiles, randPorts, title, seed);
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

}
