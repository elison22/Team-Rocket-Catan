package command;

import java.util.Random;

import facade.GameManager;
import model.sboard.ServerBoard;
import model.sboard.ServerBoardException;
import model.sgame.ServerGame;

public class CreateGame_CO implements ICommandObject {
	
	GameManager manager;
	ServerBoard board;
	private String title;
	private boolean randTiles;
	private boolean randNumbers;
	private boolean randPorts;
	private Integer seed;

	public CreateGame_CO(GameManager manager, boolean randNumbers, boolean randTiles, boolean randPorts, String title) 
			throws ServerBoardException
	{
		this.randNumbers = randNumbers;
		this.randPorts = randPorts;
		this.randTiles = randTiles;		
		this.manager = manager;
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
			
			manager.createGame(randNumbers, randTiles, randPorts, title, seed);
			return true;
		} catch (ServerBoardException e) {
			e.printStackTrace();
			return false;
		}
	}

}
