package command;

import java.util.ArrayList;

import model.sgame.ServerGame;
import facade.IModelFacade;

/**
 * @author Chad
 *
 * This class will perform all of the given commands. Each command is stored as
 * a commandobject in an array. The execute method will iterate through each 
 * index in the array and call execute().
 */
@SuppressWarnings("unused")
public class DoCommands_CO implements ICommandObject {
	
	private ArrayList<ICommandObject> commands;
	private int gameId;
	
	/**
	 * @param commands An array of ICommandObjects.
	 * @param gameId The id of the game to execute the given commands in.
	 */
	public DoCommands_CO(ArrayList<ICommandObject> commands, int gameId) {
		this.commands = commands;
		this.gameId = gameId;
	}

	@Override
	public boolean execute() {
		return false;
	}

	@Override
	public void setGame(ServerGame game) {
		// TODO Auto-generated method stub
		
	}

}
