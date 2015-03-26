package command;

import model.sgame.ServerGame;

/**@author Chad
 * 
 * Interface for our command objects.
 */
public interface ICommandObject {
	
	
	public void setGame(ServerGame game);
	
	
	/**Performs the main function of the command object. 
	 * @return TODO
	 * 
	 */
	public boolean execute();
}
