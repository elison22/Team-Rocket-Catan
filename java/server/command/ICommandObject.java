package command;

import facade.GameManager;
import model.sgame.ServerGame;

/**@author Chad
 * 
 * Interface for our command objects.
 */
public interface ICommandObject {
	
	
	public void setGame(ServerGame game);
	
	public void setGameManager(GameManager gameManager);
	
	
	/**Performs the main function of the command object. 
	 * 
	 */
	public boolean execute();
}
