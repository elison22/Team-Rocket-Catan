package command;

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
	
	private IModelFacade modelFacade;
	private ICommandObject[] commands;
	private int gameId;
	
	/**
	 * @param modelFacade The implementation of IModelFacde to be used.
	 * @param commands An array of ICommandObjects.
	 * @param gameId The id of the game to execute the given commands in.
	 */
	public DoCommands_CO(IModelFacade modelFacade, ICommandObject[] commands, int gameId) {
		this.modelFacade = modelFacade;
		this.commands = commands;
		this.gameId = gameId;
	}

	@Override
	public Object execute() {
		// TODO Auto-generated method stub
		return null;
	}

}
