package command;

import facade.GameManager;
import model.sboard.ServerBoard;
import model.sboard.ServerBoardException;
import model.sgame.ServerGame;

public class CreateGame_CO implements ICommandObject {
	
	GameManager manager;
	ServerBoard board;
	String title;
	boolean randNumbers;
	boolean randTiles;
	boolean randPorts;

	public CreateGame_CO(GameManager manager, boolean randNumbers, boolean randTiles, boolean randPorts, String title) 
			throws ServerBoardException
	{
		this.randNumbers = randNumbers;
		this.randPorts = randPorts;
		this.randTiles = randTiles;		
		this.manager = manager;
		this.title = title;
		board = null;
	}
	
	@Override
	public void setGame(ServerGame game) {
		
	}

	@Override
	public boolean execute() {
		ServerGame newGame;
		if(board != null)
		{
			newGame = new ServerGame(board, title);
		}
		else
		{
			ServerBoard gameBoard;
			try {
				gameBoard = new ServerBoard(randTiles, randNumbers, randPorts);
			} catch (ServerBoardException e) {
				e.printStackTrace();
				return false;
			}
			board = new ServerBoard(gameBoard);
			newGame = new ServerGame(gameBoard, title);
		}
		manager.createGame(newGame);
		return true;
	}

}
